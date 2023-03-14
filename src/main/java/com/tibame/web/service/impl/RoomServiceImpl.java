package com.tibame.web.service.impl;

import java.util.List;
import java.util.Map;

import com.tibame.web.dao.RoomDAO;
import com.tibame.web.dao.RoomOrderDAO;
import com.tibame.web.dao.impl.RoomDAOImpl;
import com.tibame.web.dao.impl.RoomOrderDAOImpl;
import com.tibame.web.service.RoomService;
import com.tibame.web.vo.RoomVO;

public class RoomServiceImpl implements RoomService {
	private RoomDAO roomDao;
	private RoomOrderDAO roomOrderDAO;

	public RoomServiceImpl() {
		roomDao = new RoomDAOImpl();
		roomOrderDAO = new RoomOrderDAOImpl();
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
			if (roomOrderDAO.checkExistingNumForUpdate(map) == 1) {
				return roomOrderDAO.checkbelong(map) == 1 ? "可以變更" : "此期間已有其他房客入住、不得變更";
			} else {
				return "此期間已有其他房客入住、不得變更";
			}
		}
		return "操作錯誤";
	}

}
