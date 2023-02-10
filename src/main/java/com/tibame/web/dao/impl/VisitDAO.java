package com.tibame.web.dao.impl;

import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.tibame.web.dao.VistDAO_interface;
import com.tibame.web.vo.VisitVO;

public class VisitDAO implements VistDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ROOM_VISIT (`USER_ID`, `USER_NAME`, `PHONE_NUMBER`, `EMAIL`, `CONTECT_TIME`, `DUE_DATE`, `KIDS`, `VISIT_TIME`, `REMARK`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE ROOM_VISIT SET USER_NAME=?, PHONE_NUMBER=?,EMAIL=?, CONTECT_TIME=?, DUE_DATE=?, KIDS=?, VISIT_TIME=?, REMARK=? WHERE VISIT_ID=? AND USER_ID = ? ";
	private static final String DELETE_STMT = "DELETE FROM ROOM_VISIT WHERE VISIT_ID = ?";
	private static final String GET_ALL_STMT = "SELECT VISIT_ID, USER_ID, USER_NAME, PHONE_NUMBER, CONTECT_TIME, DUE_DATE,EMAIL, KIDS, VISIT_TIME, REMARK ,CREATE_TIME FROM ROOM_VISIT ORDER BY USER_ID";
	private static final String GET_ONE_STMT = "SELECT `USER_ID`, `USER_NAME`, `PHONE_NUMBER`, `EMAIL`, `CONTECT_TIME`, `DUE_DATE`, `KIDS`, `VISIT_TIME`, `REMARK` FROM ROOM_VISIT WHERE VISIT_ID = ?";
	
	@Override
	public void insert(VisitVO visitVO) {
		Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(INSERT_STMT);

	        pstmt.setInt(1, visitVO.getUserId());
	        pstmt.setString(2, visitVO.getUserName());
	        pstmt.setString(3, visitVO.getPhoneNumber());
	        pstmt.setString(4, visitVO.getEmail());
	        pstmt.setString(5, visitVO.getContectTime());
	        pstmt.setDate(6, visitVO.getDueDate());
	        pstmt.setInt(7, visitVO.getKids());
	        pstmt.setDate(8, visitVO.getVisitTime());
	        pstmt.setString(9, visitVO.getRemark());

	        pstmt.executeUpdate();
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(VisitVO visitVO) {
		Connection con = null;
		  PreparedStatement pstmt = null;
		  
		  try {
		    con = ds.getConnection();
		    pstmt = con.prepareStatement(UPDATE_STMT);
		    
		    pstmt.setString(1, visitVO.getUserName());
		    pstmt.setString(2, visitVO.getPhoneNumber());
		    pstmt.setString(3, visitVO.getEmail());
		    pstmt.setString(4, visitVO.getContectTime());
		    pstmt.setDate(5, visitVO.getDueDate());
		    pstmt.setInt(6, visitVO.getKids());
		    pstmt.setDate(7, visitVO.getVisitTime());
		    pstmt.setString(8, visitVO.getRemark());
		    pstmt.setInt(9, visitVO.getVisitId());
		    pstmt.setInt(10, visitVO.getUserId());
		    
		    pstmt.executeUpdate();
		  } catch (SQLException se) {
		    throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer visitId) {
		    Connection con = null;
	        PreparedStatement pstmt = null;

	        try {
	        	con = ds.getConnection();
		        pstmt = con.prepareStatement(DELETE_STMT);

	            pstmt.setInt(1, visitId);

	            pstmt.executeUpdate();
	        } catch (SQLException se) {
	            throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public VisitVO findByPrimaryKey(Integer visitId) {
		VisitVO visitVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ONE_STMT);


			pstmt.setInt(1, visitId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				visitVO = new VisitVO();
				visitVO.setVisitId(visitId);
				visitVO.setUserId(rs.getInt("USER_ID"));
				visitVO.setUserName(rs.getString("USER_NAME"));
				visitVO.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				visitVO.setEmail(rs.getString("EMAIL"));
				visitVO.setContectTime(rs.getString("CONTECT_TIME"));
				visitVO.setDueDate(rs.getDate("DUE_DATE"));
				visitVO.setKids(rs.getInt("KIDS"));
				visitVO.setVisitTime(rs.getDate("VISIT_TIME"));
				visitVO.setRemark(rs.getString("REMARK"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return visitVO;
	}
	@Override
	public List<VisitVO> getAll() {
		List<VisitVO> list = new ArrayList<VisitVO>();
	    VisitVO visitVO = null;

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {

	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ALL_STMT);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            visitVO = new VisitVO();
	            visitVO.setVisitId(rs.getInt("visit_Id"));
	            visitVO.setUserId(rs.getInt("user_Id"));
	            visitVO.setUserName(rs.getString("user_Name"));
	            visitVO.setPhoneNumber(rs.getString("phone_Number"));
	            visitVO.setContectTime(rs.getString("contect_Time"));
	            visitVO.setDueDate(rs.getDate("due_Date"));
	            visitVO.setEmail(rs.getString("email"));
	            visitVO.setKids(rs.getInt("kids"));
	            visitVO.setVisitTime(rs.getDate("visit_Time"));
	            visitVO.setRemark(rs.getString("remark"));
	            visitVO.setCreateTime(rs.getTimestamp("create_Time"));
	            list.add(visitVO);
	            
	            
	        }

	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	

}
