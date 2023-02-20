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
				
//				if (rs.getBytes("REPORT_IMG") == null ||rs.getString("AUTH_CODE")==null) {
//					return null;
//				}
				
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

}
