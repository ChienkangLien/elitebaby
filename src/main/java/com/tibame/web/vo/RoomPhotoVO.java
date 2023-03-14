package com.tibame.web.vo;

import java.io.Serializable;

public class RoomPhotoVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer roomPhotoId;
	private Integer roomTypeId;
	private String roomPhoto;
	
	public RoomPhotoVO() {
	}

	public RoomPhotoVO(Integer roomPhotoId, Integer roomTypeId, String roomPhoto) {
		super();
		this.roomPhotoId = roomPhotoId;
		this.roomTypeId = roomTypeId;
		this.roomPhoto = roomPhoto;
	}

	public Integer getRoomPhotoId() {
		return roomPhotoId;
	}

	public void setRoomPhotoId(Integer roomPhotoId) {
		this.roomPhotoId = roomPhotoId;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomPhoto() {
		return roomPhoto;
	}

	public void setRoomPhoto(String roomPhoto) {
		this.roomPhoto = roomPhoto;
	}

	@Override
	public String toString() {
		return "RoomPhotoVO [roomPhotoId=" + roomPhotoId + ", roomTypeId=" + roomTypeId + ", roomPhoto=" + roomPhoto
				+ "]";
	}

	
}
