package com.tibame.web.vo;

import java.sql.Timestamp;

public class MealOrderVO {

	private Integer mealOrderId;
	private Integer userId;
	private Integer orderPayment;
	private Integer orderStatus;
	private String orderDate;
	private String orderNotes;
	private String strPayment;
	private String strstatus;
	private Integer total;

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

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}

	public String getStrPayment() {
		return strPayment;
	}

	public void setStrPayment(String strPayment) {
		this.strPayment = strPayment;
	}

	public String getStrstatus() {
		return strstatus;
	}

	public void setStrstatus(String strstatus) {
		this.strstatus = strstatus;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
