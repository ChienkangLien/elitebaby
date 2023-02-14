package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.RoomPhotoVO;
import com.tibame.web.vo.RoomTypeVO;

public interface RoomPhotoService {
	List<RoomPhotoVO> getAllPhotos(Integer id);
//	String editRoomTypePhoto(List<RoomPhotoVO>  inserRoomPhotoVOList);
	String editRoomTypePhoto(List<RoomPhotoVO>  inserRoomPhotoVOList,List<RoomPhotoVO>  deleteRoomPhotoVOList);
}
