package com.tibame.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.mysql.cj.protocol.Resultset;
import com.tibame.web.vo.LatestNewsVO;

public class LatestNewsDAO implements LatestNewsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");  //jndi
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO LATEST_NEWS (SORT_ID,ADMIN_ID,NEWS_INTRO,SCHEDULED_TIME,POST_TITLE,NEWS_PHOTO) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,SCHEDULED_TIME,POST_TITLE,NEWS_PHOTO FROM LATEST_NEWS order by NEWS_ID";
	private static final String GET_ONE_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,SCHEDULED_TIME,POST_TITLE,NEWS_PHOTO FROM LATEST_NEWS where NEWS_ID = ?";
	private static final String DELETE_NEWS_MESSAGE = "DELETE FROM NEWS_MESSAGE WHERE NEWS_ID=?";				
	private static final String DELETE_LATEST_NEWS = "DELETE FROM LATEST_NEWS where NEWS_ID = ?";
	private static final String UPDATE = "UPDATE LATEST_NEWS  set SORT_ID=?,ADMIN_ID=?,NEWS_INTRO=?,SCHEDULED_TIME=?,POST_TITLE=?,NEWS_PHOTO=?where NEWS_ID = ?";
	private static final String SEARCH = "Select  NEWS_ID,POST_TITLE, NEWS_PHOTO,NEWS_INTRO from  Latest_news  where post_title LIKE ? ";
	private static final String FindBySortId="SELECT * FROM LATEST_NEWS where SORT_ID=?";
	private static final String FindBynewsId="SELECT * FROM LATEST_NEWS where NEWS_ID=?";
	private static final String SEARCH_ONE="SELECT * FROM LATEST_NEWS where NEWS_ID=?";
	@Override
	public void insert(LatestNewsVO latestNewsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			// pstmt對應sql語法,把資料匯進mysql裡面對應
			pstmt.setInt(1, latestNewsVO.getSortId());
			pstmt.setInt(2, latestNewsVO.getAdminId());
			pstmt.setString(3, latestNewsVO.getNewsIntro());
			pstmt.setDate(4, latestNewsVO.getScheduledTime());
//			pstmt.setDate(5, latestNewsVO.getOffShelfTime());
			pstmt.setString(5, latestNewsVO.getPostTitle());
			pstmt.setBytes(6, latestNewsVO.getNewsPhoto());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public void insert1(Integer SortId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBySortId);
			// pstmt對應sql語法,把資料匯進mysql裡面對應
			pstmt.setInt(0, SortId);
			ResultSet rs=pstmt.executeQuery();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	//
	public LatestNewsVO findByPrimaryKey1(Integer newsId) {

		LatestNewsVO latestNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBynewsId);
			pstmt.setInt(1, newsId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				latestNewsVO.setNewsIntro(rs.getString("NEWS_INTRO"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE"));
				return latestNewsVO;
				}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
//			System.out.println("aaa");
		}
		return latestNewsVO;
	}
	@Override
	public void update(LatestNewsVO latestNewsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, latestNewsVO.getSortId());
			pstmt.setInt(2, latestNewsVO.getAdminId());
			pstmt.setString(3, latestNewsVO.getNewsIntro());
			pstmt.setDate(4, latestNewsVO.getScheduledTime());
			pstmt.setString(5, latestNewsVO.getPostTitle());
			pstmt.setBytes(6, latestNewsVO.getNewsPhoto());
			pstmt.setInt(7, latestNewsVO.getNewsId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer newsId) {
		int updateCount_NEWS_MESSAGE = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_NEWS_MESSAGE);
			pstmt.setInt(1, newsId);
			updateCount_NEWS_MESSAGE = pstmt.executeUpdate();
			

			pstmt = con.prepareStatement(DELETE_LATEST_NEWS);

			pstmt.setInt(1, newsId);

			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public LatestNewsVO findByPrimaryKey(Integer newsId) {

		LatestNewsVO latestNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, newsId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				latestNewsVO.setSortId(rs.getInt("SORT_ID"));
				latestNewsVO.setAdminId(rs.getInt("ADMIN_ID"));
				latestNewsVO.setNewsIntro(rs.getString("NEWS_INTRO"));
				latestNewsVO.setScheduledTime(rs.getDate("SCHEDULED_TIME"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE"));
				latestNewsVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return latestNewsVO;
	}

	@Override
	public List<LatestNewsVO> getAll() {
		List<LatestNewsVO> list = new ArrayList<LatestNewsVO>();
		LatestNewsVO latestNewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				latestNewsVO.setSortId(rs.getInt("SORT_ID"));
				latestNewsVO.setAdminId(rs.getInt("ADMIN_ID"));
				latestNewsVO.setNewsIntro(rs.getString("NEWS_INTRO"));
				latestNewsVO.setScheduledTime(rs.getDate("SCHEDULED_TIME"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE")); // Store the row in the list
				latestNewsVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
				list.add(latestNewsVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
		
//	搜尋欄位
	public List<LatestNewsVO> findByWords(String Words) {
		List<LatestNewsVO> list = new ArrayList<LatestNewsVO>();
		LatestNewsVO latestNewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, "%"+Words+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				latestNewsVO.setNewsIntro(rs.getString("NEWS_INTRO"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE")); 
				latestNewsVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
				list.add(latestNewsVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	//搜尋單筆
	public LatestNewsVO findByPrimaryKey2(Integer newsId) {

		LatestNewsVO latestNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_ONE);

			pstmt.setInt(1, newsId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
//				latestNewsVO.setSortId(rs.getInt("SORT_ID"));
//				latestNewsVO.setAdminId(rs.getInt("ADMIN_ID"));
				latestNewsVO.setNewsIntro(rs.getString("NEWS_INTRO"));
//				latestNewsVO.setScheduledTime(rs.getDate("SCHEDULED_TIME"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE"));
				latestNewsVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return latestNewsVO;
	}
//	照片
	public LatestNewsVO getnewsById(int newsId) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LatestNewsVO latestNewsVO = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("SELECT NEWS_ID,NEWS_PHOTO FROM LATEST_NEWS where NEWS_ID = ?");
			ps.setInt(1, newsId);
			rs = ps.executeQuery();

			if (rs.next()) {
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
//				latestNewsVO.setSortId(rs.getInt("SORT_ID"));
//				latestNewsVO.setAdminId(rs.getInt("ADMIN_ID"));
//				latestNewsVO.setNewsIntro(rs.getString("NEWS_INTRO"));
//				latestNewsVO.setScheduledTime(rs.getDate("SCHEDULED_TIME"));
//				latestNewsVO.setOffShelfTime(rs.getDate("OFF_SHELF_TIME"));
//				latestNewsVO.setPostTitle(rs.getString("POST_TITLE")); // Store the row in the list
				latestNewsVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
			}
		} finally {
			conn.close();
			ps.close();
			rs.close();
		}
		return latestNewsVO;
	}
}