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

import com.tibame.web.dao.MemberDao;
import com.tibame.web.vo.MemberVO;

public class MemberDaoImpl implements MemberDao {
	private DataSource ds;

	public MemberDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	public List<MemberVO> selectAll() {
		List<MemberVO> members = new ArrayList<>();
		String GET_ALL = "SELECT * FROM MEMBER";
		try (Connection conn = ds.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(GET_ALL);
			ResultSet resultSet = pstmt.executeQuery();
		) {
			while (resultSet.next()) {
				MemberVO member = new MemberVO();
				member.setId(resultSet.getInt("USER_ID")); 
				member.setUserName(resultSet.getString("USER_NAME"));
				member.setEmail(resultSet.getString("USER_EMAIL"));
				member.setPassword(resultSet.getString("USER_PASSWORD"));
				member.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
				member.setAddress(resultSet.getString("ADDRESS"));
				
				members.add(member);
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return members;
	}

	@Override
	public int insert(MemberVO member) {
		String sql = "INSERT INTO MEMBER (USER_EMAIL, USER_NAME, USER_PASSWORD, ADDRESS, PHONE_NUMBER) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getUserName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getPhoneNumber());
			return pstmt.executeUpdate();
		} catch (Exception e) {
		}
		return -1;
	}

	@Override
	public int updateById(MemberVO member) {
		String sql = "UPDATE MEMBER SET USER_NAME=?, USER_PASSWORD=?, ADDRESS=?, PHONE_NUMBER=? "
				+ "WHERE USER_ID=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getPhoneNumber());
			pstmt.setInt(5, member.getId());
//			pstmt.setDate(6, (java.sql.Date) member.getBirthday());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public MemberVO find(MemberVO memberCheck) {
		        String sql = "SELECT * FROM MEMBER WHERE USER_EMAIL = ? and USER_PASSWORD = ?";
		        try (Connection conn = ds.getConnection(); 
		        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, memberCheck.getEmail());
		            pstmt.setString(2, memberCheck.getPassword());
		            try (ResultSet resultSet = pstmt.executeQuery()) {
		                while (resultSet.next()) {
		                	memberCheck.setId(resultSet.getInt("USER_ID"));
		                	memberCheck.setEmail(resultSet.getString("USER_EMAIL"));
		                	memberCheck.setUserName(resultSet.getString("USER_NAME"));
		                	memberCheck.setPassword(resultSet.getString("USER_PASSWORD"));
		                	memberCheck.setAddress(resultSet.getString("ADDRESS"));
		                	memberCheck.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
		                	return memberCheck;
		                    
		                }
		            }
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
		        return null;
		    }
		

	@Override
	public MemberVO selectById(int id) {
		String sql = "SELECT * FROM MEMBER WHERE USER_ID=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					MemberVO member = new MemberVO();
					member.setId(resultSet.getInt("USER_ID"));
					member.setEmail(resultSet.getString("USER_EMAIL"));
					member.setUserName(resultSet.getString("USER_NAME"));
					member.setPassword(resultSet.getString("USER_PASSWORD"));
					member.setAddress(resultSet.getString("ADDRESS"));
					member.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public MemberVO selectByEmail(String email) {
		String sql = "SELECT * FROM MEMBER WHERE USER_EMAIL=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					MemberVO member = new MemberVO();
					member.setId(resultSet.getInt("USER_ID"));
					member.setEmail(resultSet.getString("USER_EMAIL"));
					member.setUserName(resultSet.getString("USER_NAME"));
					member.setPassword(resultSet.getString("USER_PASSWORD"));
					member.setAddress(resultSet.getString("ADDRESS"));
					member.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
