package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.RoomTypeVO;


public interface RoomTypeDAO {
	public int insert(RoomTypeVO type);
	public String update(RoomTypeVO type);
	public RoomTypeVO findByPrimaryKey(RoomTypeVO type);
	public RoomTypeVO findByRoomTypeName(RoomTypeVO type);
	public List<RoomTypeVO> getAll();
}
