package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.tibame.web.dao.NewsSortDAO_interface;
import com.tibame.web.vo.LatestNewsVO;
import com.tibame.web.vo.NewsSortVO;

public class NewsSortJDBCDAO implements NewsSortDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/elitebaby?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO NEWS_SORT (SORT_NAME) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT SORT_ID,SORT_NAME FROM NEWS_SORT order by SORT_ID";
	private static final String GET_ONE_STMT = "SELECT SORT_ID,SORT_NAME FROM NEWS_SORT where SORT_ID=?";
	private static final String GET_LATEST_NEWS_BySortID_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS,OFF_NEWS,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS where SORT_ID = ? order by SORT_ID";
	private static final String DELETE_LATEST_NEWS = "DELETE FROM LATEST_NEWS where NEWS_ID = ?";
	private static final String DELETE_SORT = "DELETE FROM NEWS_SORT where SORT_ID = ?";
	private static final String UPDATE = "UPDATE NEWS_SORT set SORT_NAME=? where SORT_ID =?";
	
	@Override
	public void insert(NewsSortVO newsSortVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		LatestNewsVo latestNewsVo1 = new LatestNewsVo();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//pstmt對應sql語法,把資料匯進mysql裡面對應
			pstmt.setString(1, newsSortVO.getSortName());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
//		LatestNewsVo sum = new LatestNewsVo();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, newsSortVO.getSortId());
			pstmt.setString(2, newsSortVO.getSortName());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public void delete(Integer sortId) {
		int updateCount_LATEST_NEWS= 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 先刪消息
			pstmt = con.prepareStatement(DELETE_LATEST_NEWS);
			pstmt.setInt(1, sortId);
			pstmt.executeUpdate();
			// 再刪種類
			pstmt = con.prepareStatement(DELETE_SORT);
			pstmt.setInt(1, sortId);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
						con.commit();
						con.setAutoCommit(true);
						System.out.println("刪除消息" +sortId + "時,共有消息" + updateCount_LATEST_NEWS
								+ "消息同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sortId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				newsSortVO = new NewsSortVO();
				newsSortVO.setSortId(rs.getInt("SORT_ID"));
				newsSortVO.setSortName(rs.getString("Sort_Name"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsSortVO = new NewsSortVO();
				newsSortVO.setSortId(rs.getInt("SORT_ID"));
				newsSortVO.setSortName(rs.getString("SORT_NAME")); // Store the row in the list
				list.add(newsSortVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LATEST_NEWS_BySortID_STMT);
			pstmt.setInt(1,sortId);
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
				set.add(latestNewsVO);
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public static void main(String[] args) {

		LatestNewsJDBCDAO dao = new LatestNewsJDBCDAO();

		// 新增
//		LatestNewsVO latestNewsVO1 = new LatestNewsVO();
//		latestNewsVO1.setSortId(1);
//		latestNewsVO1.setAdminId(1);
//		latestNewsVO1.setNewsIntro("一般");
//		latestNewsVO1.setPublishedTime(java.sql.Date.valueOf("2022-11-25"));
//		latestNewsVO1.setOnNews(java.sql.Date.valueOf("2023-05-18"));
//		latestNewsVO1.setOffNews(java.sql.Date.valueOf("2023-6-31"));
//		latestNewsVO1.setPostTitle("歡慶清明節");
//		dao.insert(latestNewsVO1);

		// 修改
//		LatestNewsVO latestNewsVO2 = new LatestNewsVO();
//		latestNewsVO2.setNewsId(17);
//		latestNewsVO2.setSortId(1);
//		latestNewsVO2.setAdminId(3);
//		latestNewsVO2.setNewsIntro("其他");
//		latestNewsVO2.setPublishedTime(java.sql.Date.valueOf("2022-04-30"));
//		latestNewsVO2.setOnNews(java.sql.Date.valueOf("2022-07-31"));
//		latestNewsVO2.setOffNews(java.sql.Date.valueOf("2022-08-31"));
//		latestNewsVO2.setPostTitle("歡慶周年xxx");
//		dao.update(latestNewsVO2);

		// 刪除
//	dao.delete(4);
		
		
		// 查詢一筆資料 ()裡指的是一筆資料
	LatestNewsVO latestNewsVO3 = dao.findByPrimaryKey(1);
		System.out.print(latestNewsVO3.getNewsId() + ",");
		System.out.print(latestNewsVO3.getSortId() + ",");
		System.out.print(latestNewsVO3.getAdminId() + ",");
	System.out.print(latestNewsVO3.getNewsIntro() + ",");
	System.out.print(latestNewsVO3.getPublishedTime() + ",");
	System.out.print(latestNewsVO3.getOnNews() + ",");
		System.out.print(latestNewsVO3.getOffNews() + ",");
		System.out.println(latestNewsVO3.getPostTitle());
		System.out.println("---------------------");

		// 查詢一批資料
	List<LatestNewsVO> list = dao.getAll();
	for (LatestNewsVO aLatest : list) {
		System.out.print(latestNewsVO3.getNewsId() + ",");
			System.out.print(aLatest.getSortId() + ",");
			System.out.print(aLatest.getAdminId() + ",");
		System.out.print(aLatest.getNewsIntro() + ",");
			System.out.print(aLatest.getPublishedTime() + ",");
			System.out.print(aLatest.getOnNews() + ",");
			System.out.print(aLatest.getOffNews());
			System.out.print(aLatest.getPostTitle());
			System.out.println();
		}
	}
}
