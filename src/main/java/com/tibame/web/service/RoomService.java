package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.RoomVO;

public interface RoomService {
	List<RoomVO> getRoomsByType(RoomVO room);
	String editRoom(List<RoomVO>  insertRoomVOList,List<RoomVO>  updateRoomVOList);
}
