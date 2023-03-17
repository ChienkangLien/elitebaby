package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.VistDAO_interface;
import com.tibame.web.vo.MemberVO;
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
	private static final String UPDATE_STMT = "UPDATE ROOM_VISIT SET USER_NAME=?, PHONE_NUMBER=?,EMAIL=?, CONTECT_TIME=?, DUE_DATE=?, KIDS=?, VISIT_TIME=?, REMARK=? ,VISIT_STATUS=?, CONTACT_STATUS=?  WHERE VISIT_ID=? AND USER_ID = ? ";
	private static final String DELETE_STMT = "DELETE FROM ROOM_VISIT WHERE VISIT_ID = ?";
	private static final String GET_ALL_STMT = "SELECT VISIT_ID, USER_ID, USER_NAME, PHONE_NUMBER, CONTECT_TIME, DUE_DATE,EMAIL, KIDS, VISIT_TIME, REMARK ,VISIT_STATUS,CONTACT_STATUS,CREATE_TIME FROM ROOM_VISIT ORDER BY USER_ID";
	private static final String GET_ONE_STMT = "SELECT `USER_ID`, `USER_NAME`, `PHONE_NUMBER`, `EMAIL`, `CONTECT_TIME`, `DUE_DATE`, `KIDS`, `VISIT_TIME`, `REMARK`,`VISIT_STATUS`,`CONTACT_STATUS` FROM ROOM_VISIT WHERE VISIT_ID = ?";
	private static final String GET_ONE_ALL = "SELECT `VISIT_ID`, `USER_NAME`, `PHONE_NUMBER`, `EMAIL`, `CONTECT_TIME`, `DUE_DATE`, `KIDS`, `VISIT_TIME`, `REMARK`,`VISIT_STATUS`,`CONTACT_STATUS`,CREATE_TIME FROM ROOM_VISIT WHERE USER_ID = ?";
	private static final String CHECK_VISIT_DATE = "SELECT VISIT_TIME FROM ROOM_VISIT where VISIT_TIME = ?;";
	private static final String GET_ALL_PAGE = "SELECT * FROM elitebaby.ROOM_VISIT where VISIT_STATUS = 0 limit 5 offset ?;";
	private static final String GET_ALL_PAGE_HISTORY = "SELECT * FROM elitebaby.ROOM_VISIT where VISIT_STATUS = 1 limit 5 offset ?;";
	private static final String GET_ALL_MEMBER_ID = "SELECT USER_ID,USER_EMAIL,USER_NAME,PHONE_NUMBER FROM elitebaby.MEMBER WHERE USER_ID = ?;";
	@Override
	public int insert(VisitVO visitVO) {
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
	        pstmt.setDate(6,visitVO.getDueDate());
	        pstmt.setInt(7, visitVO.getKids());
	        pstmt.setDate(8, (Date) visitVO.getVisitTime());
	        pstmt.setString(9, visitVO.getRemark());
	        
	        return  pstmt.executeUpdate();
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
	public int update(VisitVO visitVO) {
		Connection con = null;
		  PreparedStatement pstmt = null;
		  
		  try {
		    con = ds.getConnection();
		    pstmt = con.prepareStatement(UPDATE_STMT);
		    
		    pstmt.setString(1, visitVO.getUserName());
		    pstmt.setString(2, visitVO.getPhoneNumber());
		    pstmt.setString(3, visitVO.getEmail());
		    pstmt.setString(4, visitVO.getContectTime());
		    pstmt.setDate(5, (Date) visitVO.getDueDate());
		    pstmt.setInt(6, visitVO.getKids());
		    pstmt.setDate(7, (Date) visitVO.getVisitTime());
		    pstmt.setString(8, visitVO.getRemark());
		    pstmt.setInt(9, visitVO.getVisitStatus());
		    pstmt.setInt(10, visitVO.getContactSatus());
		    pstmt.setInt(11, visitVO.getVisitId());
		    pstmt.setInt(12, visitVO.getUserId());
		   
		    
		    return pstmt.executeUpdate();
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
	public int delete(Integer visitId) {
		    Connection con = null;
	        PreparedStatement pstmt = null;

	        try {
	        	con = ds.getConnection();
		        pstmt = con.prepareStatement(DELETE_STMT);

	            pstmt.setInt(1, visitId);

	           return pstmt.executeUpdate();
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
				visitVO.setContactSatus(rs.getInt("CONTACT_STATUS"));
				visitVO.setVisitStatus(rs.getInt("VISIT_STATUS"));
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
	            visitVO.setStrCreateTime(String.valueOf(visitVO.getCreateTime()));
	            visitVO.setStrVisitTime(String.valueOf(visitVO.getVisitTime()));
	            visitVO.setVisitStatus(rs.getInt("VISIT_STATUS"));
	            visitVO.setContactSatus(rs.getInt("CONTACT_STATUS"));
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
	
	
	@Override
	public List<VisitVO> getOneAll(Integer userId) {
		List<VisitVO> list = new ArrayList<VisitVO>();
	    VisitVO visitVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ONE_ALL);


			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				visitVO = new VisitVO();
				visitVO.setUserId(userId);
				visitVO.setVisitId(rs.getInt("VISIT_ID"));
				visitVO.setUserName(rs.getString("USER_NAME"));
				visitVO.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				visitVO.setEmail(rs.getString("EMAIL"));
				visitVO.setContectTime(rs.getString("CONTECT_TIME"));
				visitVO.setStrDueDate(String.valueOf(rs.getDate("DUE_DATE")));
				visitVO.setKids(rs.getInt("KIDS"));
				visitVO.setStrVisitTime(String.valueOf(rs.getDate("VISIT_TIME")));
				visitVO.setRemark(rs.getString("REMARK"));
				visitVO.setContactSatus(rs.getInt("CONTACT_STATUS"));
				visitVO.setVisitStatus(rs.getInt("VISIT_STATUS")); 
				visitVO.setStrCreateTime(String.valueOf(rs.getDate("CREATE_TIME")));
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
	@Override
	public List<VisitVO> checkVisitDate(Date visitTime) {
		List<VisitVO> list = new ArrayList<VisitVO>();
	    VisitVO visitVO = new VisitVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
	        pstmt = con.prepareStatement(CHECK_VISIT_DATE);


			pstmt.setDate(1, visitTime);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {			
				visitVO.setVisitTime(rs.getDate("VISIT_TIME"));
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
	
	@Override
	public List<VisitVO> getAllPage(Integer offset) {
		List<VisitVO> list = new ArrayList<VisitVO>();
	    VisitVO visitVO = null;

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {

	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ALL_PAGE);
	        pstmt.setInt(1, offset);
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
	            visitVO.setStrCreateTime(String.valueOf(visitVO.getCreateTime()));
	            visitVO.setStrVisitTime(String.valueOf(visitVO.getVisitTime()));
	            visitVO.setVisitStatus(rs.getInt("VISIT_STATUS"));
	            visitVO.setContactSatus(rs.getInt("CONTACT_STATUS"));
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
	@Override
	public List<VisitVO> getAllPageHistory(Integer offset) {
		List<VisitVO> list = new ArrayList<VisitVO>();
	    VisitVO visitVO = null;

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {

	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ALL_PAGE_HISTORY);
	        pstmt.setInt(1, offset);
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
	            visitVO.setStrCreateTime(String.valueOf(visitVO.getCreateTime()));
	            visitVO.setStrVisitTime(String.valueOf(visitVO.getVisitTime()));
	            visitVO.setVisitStatus(rs.getInt("VISIT_STATUS"));
	            visitVO.setContactSatus(rs.getInt("CONTACT_STATUS"));
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
	@Override
	public MemberVO getMemeberInfo(Integer userId) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ALL_MEMBER_ID);


			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getInt("USER_ID"));
				memberVO.setUserName(rs.getString("USER_NAME"));
				memberVO.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				memberVO.setEmail(rs.getString("USER_EMAIL"));

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
		return memberVO;
	}
	
	
	

}
