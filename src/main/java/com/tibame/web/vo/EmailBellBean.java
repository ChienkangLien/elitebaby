package com.tibame.web.vo;

public class EmailBellBean {
	
	private Integer userId;
	
	private String status;
	
	private Integer unreadCount;
	
	private String from;
	
	

	@Override
	public String toString() {
		return "EmailBellBean [userId=" + userId + ", status=" + status + ", unreadCount=" + unreadCount + ", from="
				+ from + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(Integer unreadCount) {
		this.unreadCount = unreadCount;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
