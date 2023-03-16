package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.RoomTypeDAO;
import com.tibame.web.vo.RoomTypeVO;

public class RoomTypeDAOImpl implements RoomTypeDAO {
	private DataSource ds;

	public RoomTypeDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(RoomTypeVO type) {
		String sql = "insert into ROOM_TYPE (ROOM_TYPE_NAME, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_STATUS) values (?, ?, ?, ?);";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, type.getRoomTypeName().trim());
			ps.setInt(2, type.getRoomPrice());
			ps.setString(3, type.getRoomDescription());
			ps.setString(4, type.getRoomStatus());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String update(RoomTypeVO type) {
		String sql = "update ROOM_TYPE set ROOM_TYPE_NAME = ?,  ROOM_PRICE = ?,  ROOM_DESCRIPTION= ?,  ROOM_STATUS= ? where ROOM_TYPE_ID = ?;";
		int result = -1;
		String diplicatedValue = "";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, type.getRoomTypeName());
			ps.setInt(2, type.getRoomPrice());
			ps.setString(3, type.getRoomDescription());
			ps.setString(4, type.getRoomStatus());
			ps.setInt(5, type.getRoomTypeId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
			// 擷取錯誤訊息(重複的UK->房型名稱)
			String errorMessage = e.getMessage();
			Pattern pattern = Pattern.compile("'([^']*)'");
			Matcher matcher = pattern.matcher(errorMessage);
			if (matcher.find()) {
				diplicatedValue = matcher.group(1);
			}
		}
		return result == 1 ? "修改成功" : diplicatedValue + "名稱重複";
	}

	@Override
	public RoomTypeVO findByPrimaryKey(RoomTypeVO type) {
		String sql = "select ROOM_TYPE_ID, ROOM_TYPE_NAME, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_CREATE_TIME, ROOM_CHANGE_TIME, ROOM_STATUS from ROOM_TYPE where ROOM_TYPE_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, type.getRoomTypeId());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer roomTypeId = rs.getInt(1);
					String roomTypeName = rs.getString(2);
					Integer roomPrice = rs.getInt(3);
					String roomDescription = rs.getString(4);
					Timestamp roomCreateTime = rs.getTimestamp(5);
					Timestamp roomChangeTime = rs.getTimestamp(6);
					String roomStatus = rs.getString(7);

					type.setRoomTypeId(roomTypeId);
					type.setRoomTypeName(roomTypeName);
					type.setRoomPrice(roomPrice);
					type.setRoomDescription(roomDescription);
					type.setRoomCreateTime(roomCreateTime);
					type.setRoomChangeTime(roomChangeTime);
					type.setRoomStatus(roomStatus);
					return type;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RoomTypeVO> getAll() {
		String sql1 = "select ROOM_TYPE_ID, ROOM_TYPE_NAME, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_CREATE_TIME, ROOM_CHANGE_TIME, ROOM_STATUS from ROOM_TYPE;";
		String sql2 = "select count(*) from ROOM where ROOM_TYPE_ID = ?;";
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();

		try (Connection con = ds.getConnection();
				PreparedStatement ps1 = con.prepareStatement(sql1);
				PreparedStatement ps2 = con.prepareStatement(sql2)) {
			try (ResultSet rs1 = ps1.executeQuery()) {
				while (rs1.next()) {
					RoomTypeVO type = new RoomTypeVO();
					Integer roomTypeId = rs1.getInt(1);
					String roomTypeName = rs1.getString(2);
					Integer roomPrice = rs1.getInt(3);
					String roomDescription = rs1.getString(4);
					Timestamp roomCreateTime = rs1.getTimestamp(5);
					Timestamp roomChangeTime = rs1.getTimestamp(6);
					String roomStatus = rs1.getString(7);

					ps2.setInt(1, roomTypeId);
					try (ResultSet rs2 = ps2.executeQuery()) {
						if (rs2.next()) {
							Integer roomQuantity = rs2.getInt(1);
							type.setRoomQuantity(roomQuantity);
						}
					}

					type.setRoomTypeId(roomTypeId);
					type.setRoomTypeName(roomTypeName);
					type.setRoomPrice(roomPrice);
					type.setRoomDescription(roomDescription);
					type.setFormattedCreateTimestamp(roomCreateTime);
					if (roomChangeTime != null) {
						type.setFormattedChangeTimestamp(roomChangeTime);
					}
					type.setRoomStatus(roomStatus);
					list.add(type);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public RoomTypeVO findByRoomTypeName(RoomTypeVO type) {
		String sql = "select ROOM_TYPE_ID, ROOM_TYPE_NAME, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_CREATE_TIME, ROOM_CHANGE_TIME, ROOM_STATUS from ROOM_TYPE where ROOM_TYPE_NAME = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, type.getRoomTypeName());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer roomTypeId = rs.getInt(1);
					String roomTypeName = rs.getString(2);
					Integer roomPrice = rs.getInt(3);
					String roomDescription = rs.getString(4);
					Timestamp roomCreateTime = rs.getTimestamp(5);
					Timestamp roomChangeTime = rs.getTimestamp(6);
					String roomStatus = rs.getString(7);

					type.setRoomTypeId(roomTypeId);
					type.setRoomTypeName(roomTypeName);
					type.setRoomPrice(roomPrice);
					type.setRoomDescription(roomDescription);
					type.setRoomCreateTime(roomCreateTime);
					type.setRoomChangeTime(roomChangeTime);
					type.setRoomStatus(roomStatus);
					return type;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
