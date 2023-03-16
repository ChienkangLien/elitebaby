package com.tibame.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.vo.NewsMessageVO;

public class NewsMessageDAO implements NewsMessageDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");  //jndi
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO NEWS_MESSAGE (USER_ID,MESSAGE_CONTENT,CONTENT_TIME,NEWS_ID) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT NEWS_MESSAGE_ID,USER_ID,MESSAGE_CONTENT,CONTENT_TIME,NEWS_ID FROM NEWS_MESSAGE order by NEWS_MESSAGE_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM NEWS_MESSAGE WHERE NEWS_MESSAGE_ID = ?";
	private static final String DELETE = "DELETE FROM NEWS_MESSAGE where NEWS_MESSAGE_ID = ?";
	private static final String UPDATE = "UPDATE NEWS_MESSAGE set USER_ID=?,MESSAGE_CONTENT=?,CONTENT_TIME=?,NEWS_ID=?where NEWS_MESSAGE_ID = ?";
	private static final String SEARCH = "SELECT NEWS_MESSAGE_ID,USER_ID,MESSAGE_CONTENT,CONTENT_TIME,NEWS_ID from  NEWS_MESSAGE  where NEWS_ID = ? ";
	
	public void insert(NewsMessageVO newsMessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			// pstmt對應sql語法,把資料匯進mysql裡面對應
			LocalDateTime now = LocalDateTime.now();
	        Timestamp timestamp = Timestamp.valueOf(now);
	        
			pstmt.setInt(1, newsMessageVO.getUserId());
			pstmt.setString(2, newsMessageVO.getMessageContent());
			pstmt.setTimestamp(3,newsMessageVO.getContentTime());
			pstmt.setInt(4, newsMessageVO.getNewsId());
			
		
			pstmt.executeUpdate();

			// Handle any driver errors
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

	public void update(NewsMessageVO newsMessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, newsMessageVO.getUserId());
			pstmt.setString(2, newsMessageVO.getMessageContent());
			pstmt.setTimestamp(3, newsMessageVO.getContentTime());
			pstmt.setInt(4, newsMessageVO.getNewsId());
			pstmt.setInt(5, newsMessageVO.getNewsMessageId());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public void delete(Integer newsMessageId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, newsMessageId);

			pstmt.executeUpdate();

			// Handle any driver errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
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

	public NewsMessageVO findByPrimaryKey(Integer newsMessageId) {

		NewsMessageVO newsMessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, newsMessageId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				newsMessageVO = new NewsMessageVO();
				newsMessageVO.setNewsMessageId(rs.getInt("NEWS_MESSAGE_ID"));
				newsMessageVO.setUserId(rs.getInt("USER_ID"));
				newsMessageVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				newsMessageVO.setContentTime(rs.getTimestamp("CONTENT_TIME"));
				newsMessageVO.setNewsId(rs.getInt("NEWS_ID"));	
			}

			// Handle any driver errors
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
		return newsMessageVO;
	}

	public List<NewsMessageVO> getAll() {
		List<NewsMessageVO> list = new ArrayList<NewsMessageVO>();
		NewsMessageVO newsMessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsMessageVO = new NewsMessageVO();
				newsMessageVO.setNewsMessageId(rs.getInt("NEWS_MESSAGE_ID"));
				newsMessageVO.setUserId(rs.getInt("USER_ID"));
				newsMessageVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				newsMessageVO.setContentTime(rs.getTimestamp("CONTENT_TIME"));
				newsMessageVO.setNewsId(rs.getInt("NEWS_ID")); // Store the row in the list
				list.add(newsMessageVO);
			}

			// Handle any driver errors
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
//	public static void main(String[] args) {
//		NewsMessageDAO NewsId = new NewsMessageDAO();
//		List<NewsMessageVO> list=NewsId.getMessage(2);
//		for(NewsMessageVO news:list) {
//			System.out.println(news.getMessageContent());
//			System.out.println(news.getContentTime());
//			System.out.println(news.getNewsMessageId());
//			System.out.println(news.getUserId());
//			
//		}
//	System.out.println();
//	}
	
	public List<NewsMessageVO> getMessage(Integer newsId) {
		List<NewsMessageVO> list = new ArrayList<NewsMessageVO>();
		NewsMessageVO newsMessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setInt(1,newsId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsMessageVO = new NewsMessageVO();
				newsMessageVO.setNewsMessageId(rs.getInt("NEWS_MESSAGE_ID"));
				newsMessageVO.setUserId(rs.getInt("USER_ID"));
				newsMessageVO.setMessageContent(rs.getString("MESSAGE_CONTENT"));
				newsMessageVO.setContentTime(rs.getTimestamp("CONTENT_TIME"));
				newsMessageVO.setNewsId(rs.getInt("NEWS_ID")); // Store the row in the list
				list.add(newsMessageVO);
			}

			// Handle any driver errors
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
}