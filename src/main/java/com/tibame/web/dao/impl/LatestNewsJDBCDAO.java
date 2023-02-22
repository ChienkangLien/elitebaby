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

import com.tibame.web.dao.LatestNewsDAO_interface;
import com.tibame.web.vo.LatestNewsVO;

public class LatestNewsJDBCDAO implements LatestNewsDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/elitebaby?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO LATEST_NEWS (SORT_ID,ADMIN_ID,NEWS_INTRO,PUBLISHED_TIME,ON_NEWS,OFF_NEWS,POST_TITLE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS,OFF_NEWS,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS order by NEWS_ID";
	private static final String GET_ONE_STMT = "SELECT NEWS_ID,SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS,OFF_NEWS,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS where NEWS_ID = ?";
//	private static final String GET_Emps_ByDeptno_STMT = 
//			"SELECT SORT_ID,ADMIN_ID,NEWS_INTRO,ON_NEWS_ID,OFF_NEWS_ID,POST_TITLE,PUBLISHED_TIME FROM LATEST_NEWS where deptno = ? order by empno";

	private static final String DELETE = "DELETE FROM LATEST_NEWS where NEWS_ID = ?";
	private static final String UPDATE = "UPDATE LATEST_NEWS  set SORT_ID=?, ADMIN_ID=?,NEWS_INTRO=?,ON_NEWS=?,OFF_NEWS=?,PUBLISHED_TIME=?,POST_TITLE=?where NEWS_ID = ?";

	public void insert(LatestNewsVO latestNewsVo) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		LatestNewsVo latestNewsVo1 = new LatestNewsVo();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
//pstmt對應sql語法,把資料匯進mysql裡面對應
			pstmt.setInt(1, latestNewsVo.getSortId());
			pstmt.setInt(2, latestNewsVo.getAdminId());
			pstmt.setString(3, latestNewsVo.getNewsIntro());
			pstmt.setDate(4, latestNewsVo.getPublishedTime());
			pstmt.setDate(5, latestNewsVo.getOnNews());
			pstmt.setDate(6, latestNewsVo.getOffNews());
			pstmt.setString(7, latestNewsVo.getPostTitle());

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

	public void update(LatestNewsVO latestNewsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		LatestNewsVo sum = new LatestNewsVo();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public void delete(Integer newsid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, newsid);

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

	public LatestNewsVO findByPrimaryKey(Integer newsId) {

		LatestNewsVO latestNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return latestNewsVO;
	}

	public List<LatestNewsVO> getAll() {
		List<LatestNewsVO> list = new ArrayList<LatestNewsVO>();
		LatestNewsVO latestNewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	public static void main(String[] args) {

		LatestNewsJDBCDAO dao = new LatestNewsJDBCDAO();

		// 新增
		LatestNewsVO latestNewsVO1 = new LatestNewsVO();
		latestNewsVO1.setSortId(1);
		latestNewsVO1.setAdminId(1);
		latestNewsVO1.setNewsIntro("一般");
		latestNewsVO1.setPublishedTime(java.sql.Date.valueOf("2022-11-25"));
		latestNewsVO1.setOnNews(java.sql.Date.valueOf("2023-05-18"));
		latestNewsVO1.setOffNews(java.sql.Date.valueOf("2023-6-31"));
		latestNewsVO1.setPostTitle("歡慶清明節");
		dao.insert(latestNewsVO1);

		// 修改
//		LatestNewsVO latestNewsVO2 = new LatestNewsVO();
//		latestNewsVO2.setNewsId(1);
//		latestNewsVO2.setSortId(1);
//		latestNewsVO2.setAdminId(3);
//		latestNewsVO2.setNewsIntro("其他");
//		latestNewsVO2.setPublishedTime(java.sql.Date.valueOf("2022-04-30"));
//		latestNewsVO2.setOnNews(java.sql.Date.valueOf("2022-07-31"));
//		latestNewsVO2.setOffNews(java.sql.Date.valueOf("2022-08-31"));
//		latestNewsVO2.setPostTitle("歡慶周年");
//		dao.update(latestNewsVO2);

		// 刪除
//		dao.delete(4);
		
		
		// 查詢一筆資料 ()裡指的是一筆資料
//		LatestNewsVO latestNewsVO3 = dao.findByPrimaryKey(1);
//		System.out.print(latestNewsVO3.getNewsId() + ",");
//		System.out.print(latestNewsVO3.getSortId() + ",");
//		System.out.print(latestNewsVO3.getAdminId() + ",");
//		System.out.print(latestNewsVO3.getNewsIntro() + ",");
//		System.out.print(latestNewsVO3.getPublishedTime() + ",");
//		System.out.print(latestNewsVO3.getOnNews() + ",");
//		System.out.print(latestNewsVO3.getOffNews() + ",");
//		System.out.println(latestNewsVO3.getPostTitle());
//		System.out.println("---------------------");
//
//		// 查詢一批資料
//		List<LatestNewsVO> list = dao.getAll();
//		for (LatestNewsVO aLatest : list) {
//			System.out.print(latestNewsVO3.getNewsId() + ",");
//			System.out.print(aLatest.getSortId() + ",");
//			System.out.print(aLatest.getAdminId() + ",");
//			System.out.print(aLatest.getNewsIntro() + ",");
//			System.out.print(aLatest.getPublishedTime() + ",");
//			System.out.print(aLatest.getOnNews() + ",");
//			System.out.print(aLatest.getOffNews());
//			System.out.print(aLatest.getPostTitle());
//			System.out.println();

//		}
	}
}
