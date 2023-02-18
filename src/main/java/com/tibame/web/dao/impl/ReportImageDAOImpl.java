package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.ReportImageDAO;
import com.tibame.web.vo.ReportImageVO;

public class ReportImageDAOImpl implements ReportImageDAO{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_SQL ="insert into `REPORT_MAIL_IMG`(`AUTH_CODE`,`REPORT_IMG`)values(?,?)";
	

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
		}finally{
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
