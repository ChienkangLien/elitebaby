package com.tibame.web.service.impl;

import java.util.List;

import com.tibame.web.dao.RoomOrderDAO;
import com.tibame.web.dao.impl.RoomOrderDAOImpl;
import com.tibame.web.service.RoomOrderService;
import com.tibame.web.vo.RoomOrderVO;

public class RoomOrderServiceImpl implements RoomOrderService {
	private RoomOrderDAO orderDao;

	public RoomOrderServiceImpl() {
		orderDao = new RoomOrderDAOImpl();
	}

	@Override
	public List<RoomOrderVO> getAllOrders() {
		return orderDao.getAll();
	}

	@Override
	public String changeStatus(RoomOrderVO roomOrder) {
		if (roomOrder != null && roomOrder.getRoomOrderId() != null) {
			return orderDao.updateStatus(roomOrder) > 0 ? "狀態更新成功" : "狀態更新失敗";
		}
		return "狀態更新失敗";
	}

	@Override
	public String remove(RoomOrderVO roomOrder) {
		if (roomOrder != null && roomOrder.getRoomOrderId() != null) {
			return orderDao.delete(roomOrder) > 0 ? "刪除成功" : "刪除失敗";
		}
		return "刪除失敗";
	}

}
