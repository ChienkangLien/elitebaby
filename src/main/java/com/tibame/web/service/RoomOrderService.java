package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.RoomOrderVO;

public interface RoomOrderService {
	List<RoomOrderVO> getAllOrders();
	String changeStatus(RoomOrderVO roomOrder);
	String remove(RoomOrderVO roomOrder);
}
