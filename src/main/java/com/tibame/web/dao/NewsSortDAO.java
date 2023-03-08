package com.tibame.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.vo.LatestNewsVO;
import com.tibame.web.vo.NewsSortVO;


public class NewsSortDAO implements NewsSortDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO NEWS_SORT (SORT_NAME) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT SORT_ID,SORT_NAME FROM NEWS_SORT order by SORT_ID";
	private static final String GET_ONE_STMT = "SELECT SORT_ID,SORT_NAME FROM NEWS_SORT where SORT_ID=?";
	private static final String GET_LATEST_NEWS_BySortID_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS,OFF_NEWS,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS where SORT_ID = ? order by SORT_ID";
	private static final String DELETE_LATEST_NEWS = "DELETE FROM LATEST_NEWS where SORT_ID = ?";
	private static final String DELETE_SORT = "DELETE FROM NEWS_SORT where SORT_ID = ?";
	private static final String UPDATE = "UPDATE NEWS_SORT set SORT_NAME=? where SORT_ID =?";

	public void insert(NewsSortVO newsSortVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, newsSortVO.getSortName());

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

	public void update(NewsSortVO newsSortVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, newsSortVO.getSortName());
			pstmt.setInt(2, newsSortVO.getSortId());
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
	public void delete(Integer sortId) {
		int updateCount_LATEST_NEWS= 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {		
			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			// 先刪消息
			pstmt = con.prepareStatement(DELETE_LATEST_NEWS);
			pstmt.setInt(1, sortId);
			updateCount_LATEST_NEWS = pstmt.executeUpdate();
			// 再刪種類
			pstmt = con.prepareStatement(DELETE_SORT);
			pstmt.setInt(1, sortId);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
						con.commit();
						con.setAutoCommit(true);
//						System.out.println("刪除消息" +sortId + "時,共有消息" + updateCount_LATEST_NEWS
//								+ "消息同時被刪除");
			
			// Handle any SQL errors
		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
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
	
	public NewsSortVO findByPrimaryKey(Integer sortId) {

		NewsSortVO newsSortVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sortId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				newsSortVO = new NewsSortVO();
				newsSortVO.setSortId(rs.getInt("SORT_ID"));
				newsSortVO.setSortName(rs.getString("SORT_NAME"));
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
		return newsSortVO;
	}
	@Override
	public List<NewsSortVO> getAll() {
		List<NewsSortVO> list = new ArrayList<NewsSortVO>();
		NewsSortVO newsSortVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsSortVO = new NewsSortVO();
				newsSortVO.setSortId(rs.getInt("SORT_ID"));
				newsSortVO.setSortName(rs.getString("SORT_NAME"));
				list.add(newsSortVO);
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

	@Override
	public Set<LatestNewsVO> getLatestNewsBySortId(Integer sortId) {
		Set<LatestNewsVO> set = new LinkedHashSet<LatestNewsVO>();
		LatestNewsVO latestNewsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LATEST_NEWS_BySortID_STMT);
			pstmt.setInt(1,sortId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				latestNewsVO.setSortId(rs.getInt("SORT_ID"));
				latestNewsVO.setAdminId(rs.getInt("ADMIN_ID"));
				latestNewsVO.setNewsIntro(rs.getString("NEWS_INTRO"));
				latestNewsVO.setScheduledTime(rs.getDate("SCHEDULED_TIME"));
//				latestNewsVO.setOffShelfTime(rs.getDate("OFF_SHELF_TIME"));
				latestNewsVO.setPostTitle(rs.getString("POST_TITLE")); // Store the row in the list
				set.add(latestNewsVO);
			}
	
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
}