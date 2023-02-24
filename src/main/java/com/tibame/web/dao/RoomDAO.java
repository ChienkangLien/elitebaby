package com.tibame.web.dao;

import java.util.List;
import java.util.Map;

import com.tibame.web.vo.RoomVO;


public interface RoomDAO {
	public int insertRooms(List<RoomVO> rooms);
//	public int updateRooms(List<RoomVO> rooms);
	public String updateRooms(List<RoomVO> insertRooms,List<RoomVO> updateRooms);
	public int delete(RoomVO room);
	public RoomVO findByPrimaryKey(Integer roomId);
	public List<RoomVO> getAll();	
	public List<RoomVO> getAllByType(RoomVO room);	
	public List<RoomVO> getAvaByDate(Map<String, String> map);
	public String getAvaByRoomId(Map<String, String> map);
}
