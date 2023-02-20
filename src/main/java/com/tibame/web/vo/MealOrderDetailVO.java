package com.tibame.web.vo;

public class MealOrderDetailVO {

	private Integer mealOrderDetailId;
	private Integer mealOrderId;
	private Integer mealId;
	private Integer orderCount;

	public Integer getMealOrderDetailId() {
		return mealOrderDetailId;
	}

	public void setMealOrderDetailId(Integer mealOrderDetailId) {
		this.mealOrderDetailId = mealOrderDetailId;
	}

	public Integer getMealOrderId() {
		return mealOrderId;
	}

	public void setMealOrderId(Integer mealOrderId) {
		this.mealOrderId = mealOrderId;
	}

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

}
