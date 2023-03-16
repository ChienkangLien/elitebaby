package com.tibame.web.vo;

import java.io.Serializable;

public class RoomVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer roomId;
	private Integer roomTypeId;
	private String roomName;
	
	public RoomVO() {
	}

	public RoomVO(Integer roomId, Integer roomTypeId, String roomName) {
		super();
		this.roomId = roomId;
		this.roomTypeId = roomTypeId;
		this.roomName = roomName;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "RoomVO [roomId=" + roomId + ", roomTypeId=" + roomTypeId + ", roomName=" + roomName + "]";
	}
	
}
