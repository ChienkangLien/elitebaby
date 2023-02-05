package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tibame.web.dao.RoomDAO;
import com.tibame.web.vo.RoomVO;


public class RoomDAOImpl implements RoomDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/elitebaby?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String username = "root";
	String password = "password";

	@Override
	public int insert(RoomVO room) {
		String sql = "insert into ROOM (ROOM_TYPE_ID,  ROOM_NAME) values (?, ?);";

		
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try (Connection con = DriverManager.getConnection(url,  username,  password);
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1,  room.getRoomTypeId());
			ps.setString(2,  room.getRoomName());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public int update(RoomVO room) {
		String sql = "update ROOM set ROOM_NAME = ? where ROOM_ID = ?;";
		
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try (Connection con = DriverManager.getConnection(url,  username,  password);
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1,  room.getRoomName());
			ps.setInt(2,  room.getRoomId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(RoomVO room) {
		String sql = "delete from ROOM where ROOM_ID = ?;";
		
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try (Connection con = DriverManager.getConnection(url,  username,  password);
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1,  room.getRoomId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public RoomVO findByPrimaryKey(RoomVO room) {
		String sql = "select ROOM_ID, ROOM_TYPE_ID, ROOM_NAME from ROOM where ROOM_ID = ?;";
		
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try (Connection con = DriverManager.getConnection(url,  username,  password);
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1,  room.getRoomId());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer roomId = rs.getInt(1);
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
		
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try (Connection con = DriverManager.getConnection(url,  username,  password);
				PreparedStatement ps = con.prepareStatement(sql);) {
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

}
