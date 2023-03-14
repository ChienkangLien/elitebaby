package com.tibame.web.vo;

public class CartVO {
	
	private Integer mealId;
	private Integer count;
	private Integer userId;
	
	public CartVO() {
		super();
	}
	public CartVO(Integer mealId, Integer count, Integer userId) {
		super();
		this.mealId = mealId;
		this.count = count;
		this.userId = userId;
	}
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "CartVO [mealId=" + mealId + ", count=" + count + ", userId=" + userId + "]";
	}
}
