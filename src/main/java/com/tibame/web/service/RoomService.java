package com.tibame.web.service;

import java.util.List;
import java.util.Map;

import com.tibame.web.vo.RoomVO;

public interface RoomService {
	List<RoomVO> getRoomsByType(RoomVO room);
	String editRoom(List<RoomVO>  insertRoomVOList,List<RoomVO>  updateRoomVOList);
	List<RoomVO> getRoomsByDate(Map<String, String> map);
	String checkAva(Map<String, String> map);
}
