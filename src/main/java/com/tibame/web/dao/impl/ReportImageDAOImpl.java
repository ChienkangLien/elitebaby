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

import com.tibame.web.dao.ReportImageDAO;
import com.tibame.web.vo.AnswerImageVO;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.ReportImageVO;

public class ReportImageDAOImpl implements ReportImageDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_SQL = "insert into `REPORT_MAIL_IMG`(`AUTH_CODE`,`REPORT_IMG`)values(?,?)";
	private static final String SELECT_ONE_PHOTO = "SELECT RIMG_ID, AUTH_CODE, REPORT_IMG FROM REPORT_MAIL_IMG WHERE AUTH_CODE = ?;";
	private static final String INSERT_ANSWER_PHOTO = "insert into `ANSWER_MAIL_IMG`(`AUTH_CODE`,`ANSWER_IMG`)values(?,?)";
	private static final String SELECT_ONE_ANSWER_PHOTO = "SELECT AIMG_ID, AUTH_CODE, ANSWER_IMG FROM ANSWER_MAIL_IMG WHERE AUTH_CODE = ?;";

	@Override
	public int insertPhoto(ReportImageVO reportImg) {
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(INSERT_SQL);

			stmt.setString(1, reportImg.getAuthCode());
			stmt.setBytes(2, reportImg.getReportImage());

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
	public List<ReportImageVO> getOneAllPhoto(String authCode) {
		List<ReportImageVO> imgList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_PHOTO);
			pstmt.setString(1, authCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ReportImageVO reportImageVO = new ReportImageVO();
				reportImageVO.setRimgId(rs.getInt("RIMG_ID"));
				reportImageVO.setAuthCode(rs.getString("AUTH_CODE"));
				reportImageVO.setReportImage(rs.getBytes("REPORT_IMG"));
				imgList.add(reportImageVO);
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

		return imgList;
	}

	@Override
	public int inserAnswerPhoto(AnswerImageVO answerImageVO) {
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(INSERT_ANSWER_PHOTO);

			stmt.setString(1, answerImageVO.getAuthCode());
			stmt.setBytes(2, answerImageVO.getAnswerImage());

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
	public List<AnswerImageVO> getOneAllAnswerPhoto(String authCode) {
		List<AnswerImageVO> imgList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_ANSWER_PHOTO);
			pstmt.setString(1, authCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				AnswerImageVO answerImageVO = new AnswerImageVO();
				answerImageVO.setRimgId(rs.getInt("AIMG_ID"));
				answerImageVO.setAuthCode(rs.getString("AUTH_CODE"));
				answerImageVO.setAnswerImage(rs.getBytes("ANSWER_IMG"));
				imgList.add(answerImageVO);
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

		return imgList;
	}

	@Override
	public int deleteReportImg(String authCode) {
		final String delete_report_img = "delete from REPORT_MAIL_IMG where AUTH_CODE = ? ;";
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(delete_report_img);

			stmt.setString(1, authCode);

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
	public int deleteAnswerImg(String authCode) {
		final String delete_answer_img = "delete from ANSWER_MAIL_IMG where AUTH_CODE = ? ;";
		int rowsAffected = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(delete_answer_img);

			stmt.setString(1, authCode);

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

}
