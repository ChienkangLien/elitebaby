package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.RoomDAO;
import com.tibame.web.vo.RoomVO;

public class RoomDAOImpl implements RoomDAO {
	private DataSource ds;

	public RoomDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertRooms(List<RoomVO> rooms) {
//		String sql = "insert into ROOM (ROOM_TYPE_ID,  ROOM_NAME) values (?, ?);";
//
//		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//			ps.setInt(1,  room.getRoomTypeId());
//			ps.setString(2,  room.getRoomName());
//			return ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		String sql = "insert into ROOM (ROOM_TYPE_ID,  ROOM_NAME) values (?, ?);";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			con.setAutoCommit(false);

			for (RoomVO room : rooms) {
				ps.setInt(1, room.getRoomTypeId());
				ps.setString(2, room.getRoomName());
				ps.addBatch();
			}

			int[] affectedRows = ps.executeBatch();
			for (int rows : affectedRows) {
				if (rows <= 0) {
					con.rollback();
					throw new SQLException("Batch insert failed");
				}
			}

			con.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}

		return 1;

	}

	@Override
	public String updateRooms(List<RoomVO> insertRooms, List<RoomVO> updateRooms) {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		String diplicatedValue = "";
		String sql1 = "insert into ROOM (ROOM_TYPE_ID,  ROOM_NAME) values (?, ?);";
		String sql2 = "update ROOM set ROOM_NAME = ? where ROOM_ID = ?;";
		int result = -1;
//		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//			ps.setString(1,  room.getRoomName());
//			ps.setInt(2,  room.getRoomId());
//			return ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps1 = con.prepareStatement(sql1);
			ps2 = con.prepareStatement(sql2);

			for (RoomVO room : insertRooms) {
				ps1.setInt(1, room.getRoomTypeId());
				ps1.setString(2, room.getRoomName());
				ps1.executeUpdate();
			}

			for (RoomVO room : updateRooms) {
				ps2.setString(1, room.getRoomName());
				ps2.setInt(2, room.getRoomId());
				ps2.executeUpdate();
			}

			con.commit();
			result = 1; // 操作成功

		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback(); // 回滾事務
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
//			System.out.println(e);
			e.printStackTrace();
			result = -1; // 操作失敗
			String errorMessage = e.getMessage();
		    Pattern pattern = Pattern.compile("'([^']*)'");
		    Matcher matcher = pattern.matcher(errorMessage);
		    if (matcher.find()) {
		    	diplicatedValue = matcher.group(1);
		        System.out.println("引號內的字符串: " + diplicatedValue);
		    }
		} finally {
			try {
				if (ps1 != null) {
					ps1.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				if (con != null) {
					con.setAutoCommit(true); // 開啟自動提交事務
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result==1?"修改成功":diplicatedValue+"名稱重複";
	}

	@Override
	public int delete(RoomVO room) {
		String sql = "delete from ROOM where ROOM_NAME = ?;";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, room.getRoomName());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public RoomVO findByPrimaryKey(Integer roomId) {
		String sql = "select ROOM_ID, ROOM_TYPE_ID, ROOM_NAME from ROOM where ROOM_ID = ?;";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, roomId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					RoomVO room = new RoomVO();
					Integer roomTypeId = rs.getInt(2);
					String roomName = rs.getString(3);

					room.setRoomId(roomId);
					room.setRoomTypeId(roomTypeId);
					room.setRoomName(roomName);
					return room;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RoomVO> getAll() {
		String sql = "select ROOM_ID, ROOM_TYPE_ID, ROOM_NAME from ROOM;";
		List<RoomVO> list = new ArrayList<RoomVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					RoomVO room = new RoomVO();
					Integer roomId = rs.getInt(1);
					Integer roomTypeId = rs.getInt(2);
					String roomName = rs.getString(3);

					room.setRoomId(roomId);
					room.setRoomTypeId(roomTypeId);
					room.setRoomName(roomName);
					list.add(room);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomVO> getAllByType(RoomVO room) {
		String sql = "select ROOM_ID, ROOM_TYPE_ID, ROOM_NAME from ROOM where ROOM_TYPE_ID = ?;";
		List<RoomVO> list = new ArrayList<RoomVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, room.getRoomTypeId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					RoomVO roomTemp = new RoomVO();
					Integer roomId = rs.getInt(1);
					Integer roomTypeId = rs.getInt(2);
					String roomName = rs.getString(3);

					roomTemp.setRoomId(roomId);
					roomTemp.setRoomTypeId(roomTypeId);
					roomTemp.setRoomName(roomName);
					list.add(roomTemp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomVO> getAvaByDate(Map<String, String> map) {
		String sql1 = "select o.ROOM_ID from ROOM_ORDER o join ROOM r on o.ROOM_ID = r.ROOM_ID join ROOM_TYPE t on r.ROOM_TYPE_ID = t.ROOM_TYPE_ID where (? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE)) and ORDER_STATUS != '完成單' and t.ROOM_TYPE_ID = ?;";
		StringBuffer sql2 = new StringBuffer("select ROOM_ID, ROOM_TYPE_ID, ROOM_NAME from ROOM where ROOM_TYPE_ID = ? and ROOM_ID not in(0");
//		String sql2 = "select ROOM_ID, ROOM_TYPE_ID, ROOM_NAME from ROOM where ROOM_TYPE_ID = ?;";
		Set<Integer> unavaSet = new HashSet<Integer>();
		
		//第一個? 入住日
		//第二個? 退房日
		//第三個? 入住日
		//第四個? 退房日
		//第五個? 房型編號
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql1);) {
			ps.setString(1, map.get("startDate"));
			ps.setString(2, map.get("endDate"));
			ps.setString(3, map.get("startDate"));
			ps.setString(4, map.get("endDate"));
			ps.setInt(5, Integer.parseInt(map.get("typeId")));
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					unavaSet.add(rs.getInt(1));
					sql2.append(","+rs.getInt(1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql2.append(");");
		List<RoomVO> allRooms = new LinkedList<RoomVO>();
	    try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql2.toString());) {
	        ps.setInt(1, Integer.parseInt(map.get("typeId")));
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	            	RoomVO room = new RoomVO();
					room.setRoomId(rs.getInt(1));
					room.setRoomTypeId(rs.getInt(2));
					room.setRoomName(rs.getString(3));
	                allRooms.add(room);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

//	    allRooms.removeAll(unavaSet);
	    allRooms = allRooms.stream()
	    	    .filter(room -> !unavaSet.contains(room))
	    	    .collect(Collectors.toList());
	    return allRooms;
	}

	@Override
	public String getAvaByRoomId(Map<String, String> map) {
		String sql1 = "select count(*) from ROOM_ORDER  where ROOM_ID = ? and(? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE))";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql1);) {
			ps.setInt(1, Integer.parseInt(map.get("roomId")));
			ps.setString(2, map.get("startDate"));
			ps.setString(3, map.get("endDate"));
			ps.setString(4, map.get("startDate"));
			ps.setString(5, map.get("endDate"));
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if(rs.getInt(1)>1)
					return "此期間已有其他房客入住、不得變更";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql2 = "select ROOM_ORDER_ID=? from ROOM_ORDER  where ROOM_ID = ? and(? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE))";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql2);) {
			ps.setInt(1, Integer.parseInt(map.get("orderId")));
			ps.setInt(2, Integer.parseInt(map.get("roomId")));
			ps.setString(3, map.get("startDate"));
			ps.setString(4, map.get("endDate"));
			ps.setString(5, map.get("startDate"));
			ps.setString(6, map.get("endDate"));
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if(rs.getInt(1)!=1)
					return "此期間已有其他房客入住、不得變更";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "可以變更";
	}

}
