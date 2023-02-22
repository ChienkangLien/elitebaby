package com.tibame.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.impl.LatestNewsJDBCDAO;
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

	private static final String INSERT_STMT = "INSERT INTO LATEST_NEWS (SORT_ID,ADMIN_ID,NEWS_INTRO,PUBLISHED_TIME,ON_NEWS,OFF_NEWS,POST_TITLE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS,OFF_NEWS,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS order by NEWS_ID";
	private static final String GET_ONE_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS,OFF_NEWS,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS where NEWS_ID = ?";
//		private static final String GET_Emps_ByDeptno_STMT = 
//				"SELECT SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS_ID,OFF_NEWS_ID,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS where deptno = ? order by empno";

	private static final String DELETE_NEWS_MESSAGE = "DELETE FROM NEWS_MESSAGE WHERE NEW_MESSAGE_ID=?";
	private static final String DELETE_NEWS_PHOTO ="DELETE FROM NEWS_PHOTO WHERE PHOTO_ID=?";
			
	private static final String DELETE = "DELETE FROM LATEST_NEWS where NEWS_ID = ?";
	private static final String UPDATE = "UPDATE LATEST_NEWS  set SORT_ID=?,ADMIN_ID=?,NEWS_INTRO=?,ON_NEWS=?,OFF_NEWS=?,PUBLISHED_TIME=?,POST_TITLE=?where NEWS_ID = ?";

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
			pstmt.setDate(4, latestNewsVO.getPublishedTime());
			pstmt.setDate(5, latestNewsVO.getOnNews());
			pstmt.setDate(6, latestNewsVO.getOffNews());
			pstmt.setString(7, latestNewsVO.getPostTitle());

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

	public void update(LatestNewsVO latestNewsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
//			LatestNewsVo sum = new LatestNewsVo();
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, latestNewsVO.getSortId());
			pstmt.setInt(2, latestNewsVO.getAdminId());
			pstmt.setString(3, latestNewsVO.getNewsIntro());
			pstmt.setDate(4, latestNewsVO.getPublishedTime());
			pstmt.setDate(5, latestNewsVO.getOnNews());
			pstmt.setDate(6, latestNewsVO.getOffNews());
			pstmt.setString(7, latestNewsVO.getPostTitle());
			pstmt.setInt(8, latestNewsVO.getNewsId());

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

	public void delete(Integer newsId) {
		int updateCount_NEWS_MESSAGE = 0;
		int updateCount_NEWS_PHOTO = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_NEWS_MESSAGE);
			pstmt.setInt(1, newsId);
			updateCount_NEWS_MESSAGE = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE_NEWS_PHOTO);
			pstmt.setInt(1, newsId);
			updateCount_NEWS_PHOTO = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE);

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
				latestNewsVO.setPublishedTime(rs.getDate("PUBLISHED_TIME"));
				latestNewsVO.setOnNews(rs.getDate("ON_NEWS"));
				latestNewsVO.setOffNews(rs.getDate("OFF_NEWS"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE"));
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
				latestNewsVO.setPublishedTime(rs.getDate("PUBLISHED_TIME"));
				latestNewsVO.setOnNews(rs.getDate("ON_NEWS"));
				latestNewsVO.setOffNews(rs.getDate("OFF_NEWS"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE")); // Store the row in the list
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
//	public LatestNewsVO getDishById(int dishid) throws SQLException, ClassNotFoundException {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        LatestNewsVO latestNewsVO = null;
//
//        try {
//            conn = ds.getConnection();
//            ps = conn.prepareStatement("SELECT dish_id, dish_img FROM dish WHERE dish_id=?");
//            ps.setInt(1, dishid);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//            	latestNewsVO = new LatestNewsVO();
//            	latestNewsVO.setDishid(rs.getInt("dish_id"));
//            	latestNewsVO.setDishimg(rs.getBytes("dish_img"));
//            }
//        } finally {
//            conn.close();
//            ps.close();
//            rs.close();
//        }
//
//        return latestNewsVO;
//    }
}