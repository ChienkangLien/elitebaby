package com.tibame.web.vo;

public class MealOrderDetailVO {


	private Integer mealOrderDetailId;
	private Integer mealOrderId;
	private Integer mealId;
	private Integer orderCount;
	private Integer mealPrice;
	private Integer userId;
	private String mealName;
	private String authCode;
	private String base64;

	public MealOrderDetailVO() {
		
	}
	
	public MealOrderDetailVO(Integer userId, Integer mealId, Integer orderCount) {
		super();
		this.userId = userId;
		this.mealId = mealId;
		this.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "MealOrderDetailVO [mealOrderDetailId=" + mealOrderDetailId + ", mealOrderId=" + mealOrderId
				+ ", mealId=" + mealId + ", orderCount=" + orderCount + ", mealPrice=" + mealPrice + ", userId="
				+ userId + "]";
	}

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

	public Integer getMealPrice() {
		return mealPrice;
	}
	
	public void setMealPrice(Integer mealPrice) {
		this.mealPrice = mealPrice;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
}
