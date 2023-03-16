package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.RoomOrderVO;

public interface RoomOrderService {
	List<RoomOrderVO> getAllOrders(String status, Integer limit);
	String changeStatus(RoomOrderVO roomOrder);
	String remove(RoomOrderVO roomOrder);
	List<RoomOrderVO> getOrdersForCalendar(Integer roomId);
	String addOrder(RoomOrderVO roomOrder);
	List<RoomOrderVO> getOrdersByUser(Integer userId);
	RoomOrderVO getByOrderId(Integer orderId);
	String editOrder(RoomOrderVO roomOrder);
}
