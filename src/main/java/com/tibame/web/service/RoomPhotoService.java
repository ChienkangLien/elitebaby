package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.RoomPhotoVO;

public interface RoomPhotoService {
	List<RoomPhotoVO> getAllPhotos(Integer id);
	String editRoomTypePhoto(List<RoomPhotoVO>  insertRoomPhotoVOList,List<RoomPhotoVO>  deleteRoomPhotoVOList);
}
