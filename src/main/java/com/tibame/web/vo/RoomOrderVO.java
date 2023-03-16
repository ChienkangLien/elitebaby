package com.tibame.web.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RoomOrderVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer roomOrderId;
	private Integer roomId;
	private String roomTypeName;
	private String roomName;
	private Date orderStartDate;
	private Date orderEndDate;
	private Timestamp orderCreateTime;
	private Timestamp orderChangeTime;
	private String orderResident;
	private Integer orderPrice;
	private String orderRemark;
	private Integer userId;
	private String userName;
	private Integer adminId;
	private String adminName;
	private String orderStatus;
	private String formattedCreateTimestamp;
	private String formattedChangeTimestamp;

	public RoomOrderVO() {
	}

	public RoomOrderVO(Integer roomOrderId, Integer roomId, String roomTypeName, String roomName, Date orderStartDate,
			Date orderEndDate, Timestamp orderCreateTime, Timestamp orderChangeTime, String orderResident,
			Integer orderPrice, String orderRemark, Integer userId, String userName, Integer adminId, String adminName,
			String orderStatus, String formattedCreateTimestamp, String formattedChangeTimestamp) {
		super();
		this.roomOrderId = roomOrderId;
		this.roomId = roomId;
		this.roomTypeName = roomTypeName;
		this.roomName = roomName;
		this.orderStartDate = orderStartDate;
		this.orderEndDate = orderEndDate;
		this.orderCreateTime = orderCreateTime;
		this.orderChangeTime = orderChangeTime;
		this.orderResident = orderResident;
		this.orderPrice = orderPrice;
		this.orderRemark = orderRemark;
		this.userId = userId;
		this.userName = userName;
		this.adminId = adminId;
		this.adminName = adminName;
		this.orderStatus = orderStatus;
		this.formattedCreateTimestamp = formattedCreateTimestamp;
		this.formattedChangeTimestamp = formattedChangeTimestamp;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getRoomOrderId() {
		return roomOrderId;
	}

	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getOrderStartDate() {
		return orderStartDate;
	}

	public void setOrderStartDate(Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	public Date getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public Timestamp getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Timestamp orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public Timestamp getOrderChangeTime() {
		return orderChangeTime;
	}

	public void setOrderChangeTime(Timestamp orderChangeTime) {
		this.orderChangeTime = orderChangeTime;
	}

	public String getOrderResident() {
		return orderResident;
	}

	public void setOrderResident(String orderResident) {
		this.orderResident = orderResident;
	}

	public Integer getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "RoomOrderVO [roomOrderId=" + roomOrderId + ", roomTypeName=" + roomTypeName + ", roomName=" + roomName
				+ ", orderStartDate=" + orderStartDate + ", orderEndDate=" + orderEndDate + ", orderCreateTime="
				+ orderCreateTime + ", orderChangeTime=" + orderChangeTime + ", orderResident=" + orderResident
				+ ", orderPrice=" + orderPrice + ", orderRemark=" + orderRemark + ", userId=" + userId + ", adminId="
				+ adminId + ", orderStatus=" + orderStatus + "]";
	}

}