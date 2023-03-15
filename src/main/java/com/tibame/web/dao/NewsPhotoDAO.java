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

import com.tibame.web.vo.NewsPhotoVO;

public class NewsPhotoDAO implements NewsPhotoDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example"); // jndi
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO NEWS_PHOTO (NEWS_ID,NEWS_PHOTO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT PHOTO_ID,NEWS_ID,NEWS_PHOTO FROM NEWS_PHOTO order by PHOTO_ID";
	private static final String GET_ONE_STMT = "SELECT PHOTO_ID,NEWS_ID,NEWS_PHOTO FROM NEWS_PHOTO where PHOTO_ID = ?";

//	private static final String DELETE_NEWS_MESSAGE = "DELETE FROM NEWS_MESSAGE WHERE NEW_MESSAGE_ID=?";
//	private static final String DELETE_NEWS_PHOTO ="DELETE FROM NEWS_PHOTO WHERE PHOTO_ID=?";

	private static final String DELETE = "DELETE FROM NEWS_PHOTO where NEWS_ID = ?";
	private static final String UPDATE = "UPDATE NEWS_PHOTO set NEWS_PHOTO=?where NEWS_ID = ?";

	@Override
	public void insert(NewsPhotoVO newsPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			// pstmt對應sql語法,把資料匯進mysql裡面對應
			pstmt.setInt(1, newsPhotoVO.getNewsId());
			pstmt.setBytes(2, newsPhotoVO.getNewsPhoto());

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
	public void update(NewsPhotoVO newsPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, newsPhotoVO.getPhotoId());
			pstmt.setInt(2, newsPhotoVO.getNewsId());
			pstmt.setBytes(3, newsPhotoVO.getNewsPhoto());
			
			pstmt.executeUpdate();
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
	public void delete(Integer photoId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, photoId);
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
	public NewsPhotoVO findByPrimaryKey(Integer photoId) {
		
		NewsPhotoVO newsPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, photoId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// newsPhotoVO 也稱為 Domain objects
				newsPhotoVO = new NewsPhotoVO();
				newsPhotoVO.setPhotoId(rs.getInt("PHOTO_ID"));
				newsPhotoVO.setNewsId(rs.getInt("NEWS_ID"));
				newsPhotoVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
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
		return newsPhotoVO;
	}

	@Override
	public List<NewsPhotoVO> getAll() {
		List<NewsPhotoVO> list = new ArrayList<NewsPhotoVO>();
		NewsPhotoVO newsPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				newsPhotoVO = new NewsPhotoVO();
				newsPhotoVO.setPhotoId(rs.getInt("PHOTO_ID"));
				newsPhotoVO.setNewsId(rs.getInt("NEWS_ID"));
				newsPhotoVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
				list.add(newsPhotoVO);
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

	public NewsPhotoVO getphotoById(int photoId) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		NewsPhotoVO newsPhotoVO = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("SELECT PHOTO_ID,NEWS_ID,NEWS_PHOTO FROM NEWS_PHOTO where PHOTO_ID = ?");
			ps.setInt(1, photoId);
			rs = ps.executeQuery();

			if (rs.next()) {
				newsPhotoVO = new NewsPhotoVO();
				newsPhotoVO.setPhotoId(rs.getInt("PHOTO_ID"));
				newsPhotoVO.setNewsId(rs.getInt("NEWS_ID"));
				newsPhotoVO.setNewsPhoto(rs.getBytes("NEWS_PHOTO"));
			}
		} finally {
			conn.close();
			ps.close();
			rs.close();
		}

		return newsPhotoVO;
	}
}