package com.tibame.web.dao;

import java.util.List;
import java.util.Map;

import com.tibame.web.vo.RoomVO;


public interface RoomDAO {
	public String updateRooms(List<RoomVO> insertRooms,List<RoomVO> updateRooms);
	public List<RoomVO> getAllByType(RoomVO room);	
	public List<RoomVO> getAvaByDate(Map<String, String> map);
}
