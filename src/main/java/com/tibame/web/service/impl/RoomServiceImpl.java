package com.tibame.web.service.impl;

import java.util.List;
import java.util.Map;

import com.tibame.web.dao.RoomDAO;
import com.tibame.web.dao.impl.RoomDAOImpl;
import com.tibame.web.service.RoomService;
import com.tibame.web.vo.RoomVO;

public class RoomServiceImpl implements RoomService {
	private RoomDAO roomDao;

	public RoomServiceImpl() {
		roomDao = new RoomDAOImpl();
	}

	@Override
	public List<RoomVO> getRoomsByType(RoomVO room) {
		if (room != null && room.getRoomTypeId() != null) {
			return roomDao.getAllByType(room);
		}
		return null;
	}

	@Override
	public String editRoom(List<RoomVO> insertRoomVOList, List<RoomVO> updateRoomVOList) {
		String result = "";
//		if (insertRoomVOList.size() != 0 || insertRoomVOList != null) {
//			result = roomDao.insertRooms(insertRoomVOList);
//
//		}
//		if (updateRoomVOList.size() != 0) {
//			for (int i = 0; i < updateRoomVOList.size(); i++) {
//				int deleteResultCount = roomDao.update(updateRoomVOList.get(i));
//				if (deleteResultCount < 1) {
//
//					if (insertRoomVOList.size() != 0) {
//						for (int j = 0; j < insertRoomVOList.size(); j++) {
//							roomDao.delete(insertRoomVOList.get(j));
//						}
//					}
//
//					for (int k = 0; k < i; k++) {
//						roomDao.delete(updateRoomVOList.get(k));
//					}
//
//					return "房間修改失敗，此房型既有的第" + (i + 1) + "個房間名稱重複";
//				}
//			}
//		}
//		return result == 1 ? "修改成功" : "修改失敗";
		if (insertRoomVOList != null && !insertRoomVOList.isEmpty()
				|| updateRoomVOList != null && !updateRoomVOList.isEmpty()) {
			result = roomDao.updateRooms(insertRoomVOList, updateRoomVOList);
			return result;
		}
		return result == "" ? "沒有資料異動" : "操作失敗";
	}

	@Override
	public List<RoomVO> getRoomsByDate(Map<String, String> map) {
		if (map.get("startDate") != null && map.get("endDate") != null && map.get("typeId") != null) {
			return roomDao.getAvaByDate(map);
		}
		return null;
	}

	@Override
	public String checkAva(Map<String, String> map) {
		if (map.get("startDate") != null && map.get("endDate") != null && map.get("orderId") != null
				&& map.get("roomId") != null) {
			return roomDao.getAvaByRoomId(map);
		}
		return null;
	}

}
