package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.RoomPhotoDAO;
import com.tibame.web.vo.RoomPhotoVO;

public class RoomPhotoDAOImpl implements RoomPhotoDAO {
	private DataSource ds;

	public RoomPhotoDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(RoomPhotoVO photo) {
		String sql = "insert into ROOM_PHOTO (ROOM_TYPE_ID,  ROOM_PHOTO) values (?, ?);";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, photo.getRoomTypeId());
			ps.setBytes(2, Base64.getDecoder().decode(photo.getRoomPhoto()));
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(RoomPhotoVO photo) {
		String sql = "delete from ROOM_PHOTO where ROOM_PHOTO_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, photo.getRoomPhotoId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<RoomPhotoVO> getAllByRoomTypeId(Integer id) {
		String sql = "select ROOM_PHOTO_ID, ROOM_TYPE_ID, ROOM_PHOTO from ROOM_PHOTO where ROOM_TYPE_ID = ?;";
		List<RoomPhotoVO> list = new ArrayList<RoomPhotoVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					RoomPhotoVO photo = new RoomPhotoVO();
					Integer roomPhotoId = rs.getInt(1);
					Integer roomTypeId = rs.getInt(2);
					byte[] roomPhoto = rs.getBytes(3);

					photo.setRoomPhotoId(roomPhotoId);
					photo.setRoomTypeId(roomTypeId);
					photo.setRoomPhoto(Base64.getEncoder().encodeToString(roomPhoto));
					list.add(photo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
