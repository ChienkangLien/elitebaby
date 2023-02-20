package com.tibame.web.vo;

import java.sql.Timestamp;

public class ReserveSampleVO {

	private Integer reserverId;
	private Integer userId;
	private String reserverDate;
	private Timestamp createTime;
	private Integer reserverStatus;

	public Integer getReserveId() {
		return reserverId;
	}

	public void setReserveId(Integer reserveId) {
		this.reserverId = reserveId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReserverDate() {
		return reserverDate;
	}

	public void setReserverDate(String reserverDate) {
		this.reserverDate = reserverDate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getReserverStatus() {
		return reserverStatus;
	}

	public void setReserverStatus(Integer reserverStatus) {
		this.reserverStatus = reserverStatus;
	}

}
