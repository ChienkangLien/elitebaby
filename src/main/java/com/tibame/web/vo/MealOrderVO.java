package com.tibame.web.vo;

import java.sql.Timestamp;

public class MealOrderVO {

	private Integer mealOrderId;
	private Integer userId;
	private Integer orderPayment;
	private Integer orderStatus;
	private Timestamp orderDate;
	private String orderNotes;

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

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}
}
