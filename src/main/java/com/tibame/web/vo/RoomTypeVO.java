package com.tibame.web.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RoomTypeVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer roomTypeId;
	private String roomTypeName;
	private Integer roomQuantity;
	private Integer roomPrice;
	private String roomDescription;
	private Timestamp roomCreateTime;
	private Timestamp roomChangeTime;
	private String roomStatus;
	private String formattedCreateTimestamp;
	private String formattedChangeTimestamp;

	public RoomTypeVO() {
	}
	
	public RoomTypeVO(Integer roomTypeId, String roomTypeName, Integer roomQuantity, Integer roomPrice,
			String roomDescription, Timestamp roomCreateTime, Timestamp roomChangeTime, String roomStatus,
			String formattedCreateTimestamp, String formattedChangeTimestamp) {
		super();
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
		this.roomQuantity = roomQuantity;
		this.roomPrice = roomPrice;
		this.roomDescription = roomDescription;
		this.roomCreateTime = roomCreateTime;
		this.roomChangeTime = roomChangeTime;
		this.roomStatus = roomStatus;
		this.formattedCreateTimestamp = formattedCreateTimestamp;
		this.formattedChangeTimestamp = formattedChangeTimestamp;
	}

	public String getFormattedCreateTimestamp() {
		return formattedCreateTimestamp;
	}

	public void setFormattedCreateTimestamp(Timestamp timestamp) {
		this.formattedCreateTimestamp = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(timestamp);
	}

	public String getFormattedChangeTimestamp() {
		return formattedChangeTimestamp;
	}

	public void setFormattedChangeTimestamp(Timestamp timestamp) {
		this.formattedChangeTimestamp = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(timestamp);
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Integer getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(Integer roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public Integer getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public Timestamp getRoomCreateTime() {
		return roomCreateTime;
	}

	public void setRoomCreateTime(Timestamp roomCreateTime) {
		this.roomCreateTime = roomCreateTime;
	}

	public Timestamp getRoomChangeTime() {
		return roomChangeTime;
	}

	public void setRoomChangeTime(Timestamp roomChangeTime) {
		this.roomChangeTime = roomChangeTime;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	@Override
	public String toString() {
		return "TypeVO [roomTypeId=" + roomTypeId + ", roomTypeName=" + roomTypeName + ", roomQuantity=" + roomQuantity
				+ ", roomPrice=" + roomPrice + ", roomDescription=" + roomDescription + ", roomCreateTime="
				+ roomCreateTime + ", roomChangeTime=" + roomChangeTime + ", roomStatus=" + roomStatus + "]";
	}
	
	

}
