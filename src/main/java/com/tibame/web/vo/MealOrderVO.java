package com.tibame.web.vo;

import java.sql.Timestamp;

public class MealOrderVO {

	private Integer mealOrderId;
	private Integer userId;
	private Integer orderPayment;
	private Integer orderStatus;
	private String orderDate;
	private String address;
	private String strPayment;
	private String strStatus;
	private Integer total;
	private String authCode;

	public Integer getMealOrderId() {
		return mealOrderId;
	}

	public void setMealOrderId(Integer mealOrderId) {
		this.mealOrderId = mealOrderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(Integer orderPayment) {
		this.orderPayment = orderPayment;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getStrPayment() {
		return strPayment;
	}

	public void setStrPayment(String strPayment) {
		this.strPayment = strPayment;
	}

	public String getStrstatus() {
		return strStatus;
	}

	public void setStrstatus(String strstatus) {
		this.strStatus = strstatus;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "MealOrderVO [mealOrderId=" + mealOrderId + ", userId=" + userId + ", orderPayment=" + orderPayment
				+ ", orderStatus=" + orderStatus + ", orderDate=" + orderDate + ", address=" + address + ", strPayment="
				+ strPayment + ", strStatus=" + strStatus + ", total=" + total + ", authCode=" + authCode + "]";
	}

}
