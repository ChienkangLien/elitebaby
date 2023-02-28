package com.tibame.web.dao.impl;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tibame.web.dao.EmailDAO;
import com.tibame.web.util.HibernateUtil;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.VisitVO;

public class EmailDAOImpl implements EmailDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_SQL = "INSERT INTO REPORT_MAIL ( USER_ID, CATEGORY_ID, REPORT_TITLE, REPORT_CONTENT,AUTH_CODE,DETERMINE) VALUES (?, ?, ?, ?,?,?)";
	private static final String UPDATE_SQL = "UPDATE REPORT_MAIL SET ADMIN_ID = ?, ANSWER_CONTENT = ?, ANSWER_CREATE_TIME = ?, ANSWER_TITLE = ? WHERE MAIL_ID = ?";
	private static final String UPDATE_FROM_USER = "UPDATE REPORT_MAIL SET  ANSWER_CONTENT = ?, ANSWER_CREATE_TIME = ?, ANSWER_TITLE = ? WHERE MAIL_ID = ?";
	private static final String DELETE_SQL = "DELETE FROM REPORT_MAIL WHERE MAIL_ID = ?";
	private static final String SELECT_SQL = "SELECT * FROM REPORT_MAIL WHERE MAIL_ID = ?";
	private static final String SELECT_ALL_SQL = "SELECT * FROM REPORT_MAIL";
	private static final String SELECT_BYONE_SQL = "SELECT * FROM REPORT_MAIL WHERE USER_ID = ?";
	private static final String SELECT_ALL_BY_MEMBER = "SELECT MAIL_ID,USER_ID,USER_NAME,REPORT_CATEGORY,ADMIN_ID,REPORT_TITLE,REPORT_CONTENT,REPORT_CREATE_TIME,ANSWER_CONTENT,ANSWER_CREATE_TIME,ANSWER_TITLE,AUTH_CODE,DETERMINE FROM (select USER_NAME,REPORT_MAIL.USER_ID,CATEGORY_ID,MAIL_ID,ADMIN_ID,REPORT_TITLE,REPORT_CONTENT,REPORT_CREATE_TIME,ANSWER_CONTENT,ANSWER_CREATE_TIME,ANSWER_TITLE,AUTH_CODE,DETERMINE from REPORT_MAIL join MEMBER on  REPORT_MAIL.USER_ID =  MEMBER.USER_ID) se join REPORT_CATEGORY  on  se.CATEGORY_ID = REPORT_CATEGORY.CATEGORY_ID  where  DETERMINE = '會員' order by MAIL_ID limit 5 offset ? ;";
	private static final String SELECT_ALL_BY_ADMIN = "SELECT MAIL_ID,USER_ID,USER_NAME,REPORT_CATEGORY,ADMIN_ID,REPORT_TITLE,REPORT_CONTENT,REPORT_CREATE_TIME,ANSWER_CONTENT,ANSWER_CREATE_TIME,ANSWER_TITLE,AUTH_CODE,DETERMINE FROM (select USER_NAME,REPORT_MAIL.USER_ID,CATEGORY_ID,MAIL_ID,ADMIN_ID,REPORT_TITLE,REPORT_CONTENT,REPORT_CREATE_TIME,ANSWER_CONTENT,ANSWER_CREATE_TIME,ANSWER_TITLE,AUTH_CODE,DETERMINE from REPORT_MAIL join MEMBER on  REPORT_MAIL.USER_ID =  MEMBER.USER_ID) se join REPORT_CATEGORY  on  se.CATEGORY_ID = REPORT_CATEGORY.CATEGORY_ID  where  DETERMINE = '後台' order by MAIL_ID limit 5 offset ? ;";
	private static final String INSERT_FROMBACK = "INSERT INTO REPORT_MAIL ( USER_ID, CATEGORY_ID, ADMIN_ID, REPORT_TITLE, REPORT_CONTENT,AUTH_CODE,DETERMINE) VALUES (?, ?, ?, ?,?,?,?)";

	@Override
	public int insert(EmailVO emailVO) {
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(INSERT_SQL);

			stmt.setInt(1, emailVO.getUserId());
			stmt.setInt(2, emailVO.getCategoryId());
			stmt.setString(3, emailVO.getReportTile());
			stmt.setString(4, emailVO.getReportContent());
			stmt.setString(5, emailVO.getAuthCode());
			stmt.setString(6, emailVO.getDetermine());

			rowsAffected = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowsAffected;
	}

	@Override
	public int update(EmailVO emailVO) {
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(UPDATE_SQL);

			stmt.setInt(1, emailVO.getAdminId());
			stmt.setString(2, emailVO.getAnswerContent());
			stmt.setTimestamp(3, emailVO.getAnsertCreateTime());
			stmt.setString(4, emailVO.getAnswerTitle());
			stmt.setInt(5, emailVO.getMailId());
			rowsAffected = stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int delete(Integer mailId) {
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(DELETE_SQL);
			stmt.setInt(1, mailId);
			rowsAffected = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public EmailVO findByPrimaryKey(Integer mailId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmailVO emailVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setInt(1, mailId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				emailVO = new EmailVO();
				emailVO.setMailId(rs.getInt("MAIL_ID"));
				emailVO.setUserId(rs.getInt("USER_ID"));
				emailVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				emailVO.setAdminId(rs.getInt("ADMIN_ID"));
				emailVO.setReportTile(rs.getString("REPORT_TITLE"));
				emailVO.setReportContent(rs.getString("REPORT_CONTENT"));
				emailVO.setPreportCreateTime(rs.getTimestamp("REPORT_CREATE_TIME"));
				emailVO.setAnswerContent(rs.getString("ANSWER_CONTENT"));
				emailVO.setAnsertCreateTime(rs.getTimestamp("ANSWER_CREATE_TIME"));
				emailVO.setAnswerTitle(rs.getString("ANSWER_TITLE"));
				emailVO.setAuthCode(rs.getString("AUTH_CODE"));
				emailVO.setDetermine(rs.getString("DETERMINE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emailVO;
	}

	@Override
	public List<EmailVO> getAll(Integer offset) {
		List<EmailVO> emailList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_BY_MEMBER);
			pstmt.setInt(1, offset);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmailVO email = new EmailVO();
				email.setMailId(rs.getInt("MAIL_ID"));
				email.setUserId(rs.getInt("USER_ID"));
				email.setUserName(rs.getString("USER_NAME"));
				email.setReportCategory(rs.getString("REPORT_CATEGORY"));
				email.setAdminId(rs.getInt("ADMIN_ID"));
				email.setReportTile(rs.getString("REPORT_TITLE"));
				email.setReportContent(rs.getString("REPORT_CONTENT"));
				email.setPreportCreateTime(rs.getTimestamp("REPORT_CREATE_TIME"));
				email.setAnswerContent(rs.getString("ANSWER_CONTENT"));
				email.setAnsertCreateTime(rs.getTimestamp("ANSWER_CREATE_TIME"));
				email.setAnswerTitle(rs.getString("ANSWER_TITLE"));
				email.setAuthCode(rs.getString("AUTH_CODE"));
				email.setDetermine(rs.getString("DETERMINE"));
				email.setStrPreportCreateTime(String.valueOf(rs.getTimestamp("REPORT_CREATE_TIME")));
				emailList.add(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emailList;
	}

	@Override
	public List<EmailVO> findByUserId(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmailVO emailVO = null;
		List<EmailVO> list = new ArrayList<>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_BYONE_SQL);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emailVO = new EmailVO();
				emailVO.setMailId(rs.getInt("MAIL_ID"));
				emailVO.setUserId(rs.getInt("USER_ID"));
				emailVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				emailVO.setAdminId(rs.getInt("ADMIN_ID"));
				emailVO.setReportTile(rs.getString("REPORT_TITLE"));
				emailVO.setReportContent(rs.getString("REPORT_CONTENT"));
				emailVO.setPreportCreateTime(rs.getTimestamp("REPORT_CREATE_TIME"));
				emailVO.setAnswerContent(rs.getString("ANSWER_CONTENT"));
				emailVO.setAnsertCreateTime(rs.getTimestamp("ANSWER_CREATE_TIME"));
				emailVO.setAnswerTitle(rs.getString("ANSWER_TITLE"));
				emailVO.setAuthCode(rs.getString("AUTH_CODE"));
				emailVO.setDetermine(rs.getString("DETERMINE"));
				list.add(emailVO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<EmailVO> getAllCount() {
		List<EmailVO> emailList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmailVO email = new EmailVO();
				email.setMailId(rs.getInt("MAIL_ID"));
				email.setUserId(rs.getInt("USER_ID"));
				email.setAdminId(rs.getInt("ADMIN_ID"));
				email.setReportTile(rs.getString("REPORT_TITLE"));
				email.setReportContent(rs.getString("REPORT_CONTENT"));
				email.setPreportCreateTime(rs.getTimestamp("REPORT_CREATE_TIME"));
				email.setAnswerContent(rs.getString("ANSWER_CONTENT"));
				email.setAnsertCreateTime(rs.getTimestamp("ANSWER_CREATE_TIME"));
				email.setAnswerTitle(rs.getString("ANSWER_TITLE"));
				email.setAuthCode(rs.getString("AUTH_CODE"));
				email.setDetermine(rs.getString("DETERMINE"));
				email.setStrPreportCreateTime(String.valueOf(rs.getTimestamp("REPORT_CREATE_TIME")));
				emailList.add(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emailList;
	}

	@Override
	public List<EmailVO> getAllFromback(Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertFromBack(EmailVO emailVO) {
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(INSERT_FROMBACK);

			stmt.setInt(1, emailVO.getUserId());
			stmt.setInt(2, emailVO.getCategoryId());
			stmt.setInt(3, emailVO.getAdminId());
			stmt.setString(4, emailVO.getReportTile());
			stmt.setString(5, emailVO.getReportContent());
			stmt.setString(6, emailVO.getAuthCode());
			stmt.setString(7, emailVO.getDetermine());

			rowsAffected = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowsAffected;
	}

	@Override
	public int updateFromUser(EmailVO emailVO) {
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(UPDATE_FROM_USER);

			stmt.setString(1, emailVO.getAnswerContent());
			stmt.setTimestamp(2, emailVO.getAnsertCreateTime());
			stmt.setString(3, emailVO.getAnswerTitle());
			stmt.setInt(4, emailVO.getMailId());
			rowsAffected = stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public List<EmailVO> getAllByAdmin(Integer offset) {
		List<EmailVO> emailList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_BY_ADMIN);
			pstmt.setInt(1, offset);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmailVO email = new EmailVO();
				email.setMailId(rs.getInt("MAIL_ID"));
				email.setUserId(rs.getInt("USER_ID"));
				email.setUserName(rs.getString("USER_NAME"));
				email.setReportCategory(rs.getString("REPORT_CATEGORY"));
				email.setAdminId(rs.getInt("ADMIN_ID"));
				email.setReportTile(rs.getString("REPORT_TITLE"));
				email.setReportContent(rs.getString("REPORT_CONTENT"));
				email.setPreportCreateTime(rs.getTimestamp("REPORT_CREATE_TIME"));
				email.setAnswerContent(rs.getString("ANSWER_CONTENT"));
				email.setAnsertCreateTime(rs.getTimestamp("ANSWER_CREATE_TIME"));
				email.setAnswerTitle(rs.getString("ANSWER_TITLE"));
				email.setAuthCode(rs.getString("AUTH_CODE"));
				email.setDetermine(rs.getString("DETERMINE"));
				email.setStrPreportCreateTime(String.valueOf(rs.getTimestamp("REPORT_CREATE_TIME")));
				emailList.add(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emailList;
	}

}
