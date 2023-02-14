package com.tibame.web.service.impl;

import java.util.List;

import com.tibame.web.dao.RoomPhotoDAO;
import com.tibame.web.dao.RoomTypeDAO;
import com.tibame.web.dao.impl.RoomPhotoDAOImpl;
import com.tibame.web.dao.impl.RoomTypeDAOImpl;
import com.tibame.web.service.RoomTypeService;
import com.tibame.web.vo.RoomPhotoVO;
import com.tibame.web.vo.RoomTypeVO;

public class RoomTypeServiceImpl implements RoomTypeService {
	private RoomTypeDAO typeDao;
	private RoomPhotoDAO photoDao;
	
	public RoomTypeServiceImpl() {
		typeDao = new RoomTypeDAOImpl();
		photoDao = new RoomPhotoDAOImpl();
	}
	
	@Override
	public String createRoomType(RoomTypeVO roomType) {
		String roomTypeName = roomType.getRoomTypeName();
		Integer roomQuantity = roomType.getRoomQuantity();
		Integer roomPrice= roomType.getRoomPrice();
		String roomDescription = roomType.getRoomDescription();
		String roomStatus = roomType.getRoomStatus();
		
		if (roomTypeName == null || roomTypeName.isEmpty()) {
			return "房型名稱不合規則";
		}
		
		if(roomQuantity <= 0 || roomQuantity % 1 != 0 || roomQuantity == null) {
			return "房數不合規則";
		}
		
		if(roomPrice <= 0 || roomPrice % 1 != 0 || roomPrice == null) {
			return "房間單價不合規則";
		}
		
		if (roomDescription == null || roomDescription.isEmpty() || roomDescription.equals("<p><br></p>")) {
			return "房型描述不合規則";
		}
		
		if (roomStatus == null || roomStatus.isEmpty()) {
			return "房型狀態不合規則";
		}
		
		final int resultCount = typeDao.insert(roomType);
		return resultCount > 0 ? "新增成功" : "此房型名稱已被使用";
	}
	
	@Override
	public String createRoomType(RoomTypeVO roomType, List<RoomPhotoVO> roomPhotoVO) {
		String roomTypeName = roomType.getRoomTypeName();
		Integer roomQuantity = roomType.getRoomQuantity();
		Integer roomPrice= roomType.getRoomPrice();
		String roomDescription = roomType.getRoomDescription();
		String roomStatus = roomType.getRoomStatus();
		
		if (roomTypeName == null || roomTypeName.isEmpty()) {
			return "房型名稱不合規則";
		}
		
		if(roomQuantity <= 0 || roomQuantity % 1 != 0 || roomQuantity == null) {
			return "房數不合規則";
		}
		
		if(roomPrice <= 0 || roomPrice % 1 != 0 || roomPrice == null) {
			return "房間單價不合規則";
		}
		
		if (roomDescription == null || roomDescription.isEmpty() || roomDescription.equals("<p><br></p>")) {
			return "房型描述不合規則";
		}
		
		if (roomStatus == null || roomStatus.isEmpty()) {
			return "房型狀態不合規則";
		}
		
		int insertResultCount = typeDao.insert(roomType);
		
		if(insertResultCount<1) {
			return "此房型名稱已被使用";
		}
		
		RoomTypeVO roomTypeVO = typeDao.findByRoomTypeName(roomType);
		Integer roomTypeId = roomTypeVO.getRoomTypeId();
		for(int i = 0; i<roomPhotoVO.size();i++) {
			roomPhotoVO.get(i).setRoomTypeId(roomTypeId);
			insertResultCount = photoDao.insert(roomPhotoVO.get(i));
			if(insertResultCount<1) {
				return "照片加入失敗，請直接進入該房型做編輯";
			}
		}
		
		return "新增成功";
	}

	@Override
	public List<RoomTypeVO> getAllTypes() {
		return typeDao.getAll();
	}

	@Override
	public String deleteRoomType(RoomTypeVO roomType) {
		String roomTypeName = roomType.getRoomTypeName();
		if (roomTypeName == null || roomTypeName.isEmpty()) {
			return "查無此房";
		}
		
		final int resultCount = typeDao.delete(roomType);
		return resultCount > 0 ? "刪除成功" : "刪除失敗";
	}



}
