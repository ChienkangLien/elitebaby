package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.RoomPhotoVO;

public interface RoomPhotoDAO {
	public int insert(RoomPhotoVO photo);
	public int update(RoomPhotoVO photo);
	public int delete(RoomPhotoVO photo);
	public RoomPhotoVO findByPrimaryKey(RoomPhotoVO photo);
	public List<RoomPhotoVO> getAllByRoomTypeId(Integer id);
}
