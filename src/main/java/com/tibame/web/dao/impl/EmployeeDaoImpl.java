package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.EmployeeDao;
import com.tibame.web.vo.EmployeeVO;

public class EmployeeDaoImpl implements EmployeeDao {

	private DataSource ds;

	public EmployeeDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<EmployeeVO> selectAll() {
		
		List<EmployeeVO> employees = new ArrayList<>();
		String sql = "SELECT * FROM ADMINISTRATOR";
		try (	Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet resultSet = pstmt.executeQuery();
				) {
			
			while (resultSet.next()) {
				EmployeeVO employee = new EmployeeVO();
				employee.setEmpaccount(resultSet.getString("ADMIN_ACCOUNT"));
				employee.setEmpid(resultSet.getInt("ADMIN_ID"));
				employee.setEmpname(resultSet.getString("ADMIN_NAME"));
				employee.setEmppassword(resultSet.getString("ADMIN_PASSWORD"));
				employee.setEmppermission(resultSet.getString("ADMIN_PERMISSION"));
				employees.add(employee);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public int insert(EmployeeVO employee) {
		String sql = "INSERT INTO ADMINISTRATOR (ADMIN_NAME, ADMIN_ACCOUNT, ADMIN_PASSWORD, ADMIN_PERMISSION) "
				+ "VALUES (?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, employee.getEmpname());
			pstmt.setString(2, employee.getEmpaccount());
			pstmt.setString(3, employee.getEmppassword());
			pstmt.setString(4, employee.getEmppermission());
			return pstmt.executeUpdate();
		} catch (Exception e) {
		}
		return -1;
	}

	@Override
	public int updateById(EmployeeVO employee) {
		String sql = "UPDATE ADMINISTRATOR SET ADMIN_NAME=?, ADMIN_PASSWORD=?, ADMIN_PERMISSION=? WHERE ADMIN_ID=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, employee.getEmpname());
			pstmt.setString(2, employee.getEmppassword());
			pstmt.setString(3, employee.getEmppermission());
			pstmt.setInt(4, employee.getEmpid());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public EmployeeVO find(EmployeeVO employeeCheck) {
		String sql = "SELECT * FROM ADMINISTRATOR WHERE ADMIN_ACCOUNT = ? and ADMIN_PASSWORD = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, employeeCheck.getEmpaccount());
			pstmt.setString(2, employeeCheck.getEmppassword());
			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					employeeCheck.setEmpid(resultSet.getInt("ADMIN_ID"));
					employeeCheck.setEmpname(resultSet.getString("ADMIN_NAME"));
					employeeCheck.setEmpaccount(resultSet.getString("ADMIN_ACCOUNT"));
					employeeCheck.setEmppassword(resultSet.getString("ADMIN_PASSWORD"));
					employeeCheck.setEmppermission(resultSet.getString("ADMIN_PERMISSION"));
					return employeeCheck;

				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public EmployeeVO selectById(int empid) {
		String sql = "SELECT * FROM ADMINISTRATOR WHERE ADMIN_ID=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, empid);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					EmployeeVO employee = new EmployeeVO();
					employee.setEmpid(resultSet.getInt("ADMIN_ID"));
					employee.setEmpname(resultSet.getString("ADMIN_NAME"));
					employee.setEmpaccount(resultSet.getString("ADMIN_ACCOUNT"));
					employee.setEmppassword(resultSet.getString("ADMIN_PASSWORD"));
					employee.setEmppermission(resultSet.getString("ADMIN_PERMISSION"));
					return employee;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
