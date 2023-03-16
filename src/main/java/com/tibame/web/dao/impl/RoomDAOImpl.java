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
	public String updateRooms(List<RoomVO> insertRooms, List<RoomVO> updateRooms) {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		String diplicatedValue = "";
		// 新增以及更新一同處理
		String sql1 = "insert into ROOM (ROOM_TYPE_ID,  ROOM_NAME) values (?, ?);";
		String sql2 = "update ROOM set ROOM_NAME = ? where ROOM_ID = ?;";
		int result = -1;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // 交易開始
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
					con.rollback(); // 若有利外即rollback
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			result = -1; // 操作失敗
			// 擷取錯誤訊息(重複的UK->房間名稱)
			String errorMessage = e.getMessage();
			Pattern pattern = Pattern.compile("'([^']*)'");
			Matcher matcher = pattern.matcher(errorMessage);
			if (matcher.find()) {
				diplicatedValue = matcher.group(1);
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
					con.setAutoCommit(true); // 交易結束
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result == 1 ? "修改成功" : diplicatedValue + "名稱重複";
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
		// 找出日期間有衝突的房間(基於訂單)
		String sql1 = "select o.ROOM_ID from ROOM_ORDER o join ROOM r on o.ROOM_ID = r.ROOM_ID join ROOM_TYPE t on r.ROOM_TYPE_ID = t.ROOM_TYPE_ID where (? between ORDER_START_DATE and ORDER_END_DATE or ? between ORDER_START_DATE and ORDER_END_DATE or (? < ORDER_START_DATE and ?> ORDER_END_DATE)) and ORDER_STATUS != '完成單' and t.ROOM_TYPE_ID = ?;";
		// 去除掉有衝突的房間、即為可取得空房
		StringBuffer sql2 = new StringBuffer(
				"select ROOM_ID, ROOM_TYPE_ID, ROOM_NAME from ROOM where ROOM_TYPE_ID = ? and ROOM_ID not in(0");
		// 儲存衝突房間id
		Set<Integer> unavaSet = new HashSet<Integer>();

		// 第一個? 入住日
		// 第二個? 退房日
		// 第三個? 入住日
		// 第四個? 退房日
		// 第五個? 房型編號
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql1);) {
			ps.setString(1, map.get("startDate"));
			ps.setString(2, map.get("endDate"));
			ps.setString(3, map.get("startDate"));
			ps.setString(4, map.get("endDate"));
			ps.setInt(5, Integer.parseInt(map.get("typeId")));

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					unavaSet.add(rs.getInt(1));
					sql2.append("," + rs.getInt(1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql2.append(");");

		// 儲存該房型所有房間id
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

		// 利用stream處理串流，篩選掉衝突房間id
		allRooms = allRooms.stream().filter(room -> !unavaSet.contains(room)).collect(Collectors.toList());
		return allRooms;
	}

}
