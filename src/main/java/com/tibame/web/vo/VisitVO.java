package com.tibame.web.vo;

import java.sql.*;

public class VisitVO implements java.io.Serializable {
	private Integer visitId;
	private Integer userId;
	private String userName;
	private String phoneNumber;
	private String contectTime;
	private Date dueDate;
	private String email;
	private Integer kids;
	private Date visitTime;
	private String remark;
	
	
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getContectTime() {
		return contectTime;
	}
	public void setContectTime(String contectTime) {
		this.contectTime = contectTime;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Integer getKids() {
		return kids;
	}
	public void setKids(Integer kids) {
		this.kids = kids;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
