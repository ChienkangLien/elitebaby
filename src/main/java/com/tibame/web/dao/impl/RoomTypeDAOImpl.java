package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
		String sql = "insert into ROOM_TYPE (ROOM_TYPE_NAME, ROOM_QUANTITY, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_STATUS) values (?, ?, ?, ?, ?);";

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1,  type.getRoomTypeName());
			ps.setInt(2,  type.getRoomQuantity());
			ps.setInt(3,  type.getRoomPrice());
			ps.setString(4,  type.getRoomDescription());
			ps.setString(5,  type.getRoomStatus());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public int update(RoomTypeVO type) {
		String sql = "update ROOM_TYPE set ROOM_TYPE_NAME = ?,  ROOM_QUANTITY = ?,  ROOM_PRICE = ?,  ROOM_DESCRIPTION= ?,  ROOM_STATUS= ? where ROOM_TYPE_ID = ?;";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1,  type.getRoomTypeName());
			ps.setInt(2,  type.getRoomQuantity());
			ps.setInt(3,  type.getRoomPrice());
			ps.setString(4,  type.getRoomDescription());
			ps.setString(5,  type.getRoomStatus());
			ps.setInt(6,  type.getRoomTypeId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(RoomTypeVO type) {
		String sql = "delete from ROOM_TYPE where ROOM_TYPE_NAME = ?;";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1,  type.getRoomTypeName());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public RoomTypeVO findByPrimaryKey(RoomTypeVO type) {
		String sql = "select ROOM_TYPE_ID, ROOM_TYPE_NAME, ROOM_QUANTITY, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_CREATE_TIME, ROOM_CHANGE_TIME, ROOM_STATUS from ROOM_TYPE where ROOM_TYPE_ID = ?;";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1,  type.getRoomTypeId());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer roomTypeId = rs.getInt(1);
					String roomTypeName = rs.getString(2);
					Integer roomQuantity = rs.getInt(3);
					Integer roomPrice = rs.getInt(4);
					String roomDescription = rs.getString(5);
					Timestamp roomCreateTime = rs.getTimestamp(6);
					Timestamp roomChangeTime = rs.getTimestamp(7);
					String roomStatus = rs.getString(8);

					type.setRoomTypeId(roomTypeId);
					type.setRoomTypeName(roomTypeName);
					type.setRoomQuantity(roomQuantity);
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
		String sql = "select ROOM_TYPE_ID, ROOM_TYPE_NAME, ROOM_QUANTITY, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_CREATE_TIME, ROOM_CHANGE_TIME, ROOM_STATUS from ROOM_TYPE;";
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					RoomTypeVO type = new RoomTypeVO();
					Integer roomTypeId = rs.getInt(1);
					String roomTypeName = rs.getString(2);
					Integer roomQuantity = rs.getInt(3);
					Integer roomPrice = rs.getInt(4);
					String roomDescription = rs.getString(5);
					Timestamp roomCreateTime = rs.getTimestamp(6);
					Timestamp roomChangeTime = rs.getTimestamp(7);
					String roomStatus = rs.getString(8);

					type.setRoomTypeId(roomTypeId);
					type.setRoomTypeName(roomTypeName);
					type.setRoomQuantity(roomQuantity);
					type.setRoomPrice(roomPrice);
					type.setRoomDescription(roomDescription);
//					type.setRoomCreateTime(roomCreateTime);
//					type.setRoomChangeTime(roomChangeTime);
					type.setFormattedCreateTimestamp(roomCreateTime);
					if(roomChangeTime!=null) {
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
		String sql = "select ROOM_TYPE_ID, ROOM_TYPE_NAME, ROOM_QUANTITY, ROOM_PRICE, ROOM_DESCRIPTION, ROOM_CREATE_TIME, ROOM_CHANGE_TIME, ROOM_STATUS from ROOM_TYPE where ROOM_TYPE_NAME = ?;";


		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1,  type.getRoomTypeName());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer roomTypeId = rs.getInt(1);
					String roomTypeName = rs.getString(2);
					Integer roomQuantity = rs.getInt(3);
					Integer roomPrice = rs.getInt(4);
					String roomDescription = rs.getString(5);
					Timestamp roomCreateTime = rs.getTimestamp(6);
					Timestamp roomChangeTime = rs.getTimestamp(7);
					String roomStatus = rs.getString(8);

					type.setRoomTypeId(roomTypeId);
					type.setRoomTypeName(roomTypeName);
					type.setRoomQuantity(roomQuantity);
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
