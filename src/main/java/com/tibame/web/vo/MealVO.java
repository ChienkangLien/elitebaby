package com.tibame.web.vo;

import java.util.Arrays;

public class MealVO {

	private Integer mealId;
	private String mealName;
	private byte[] mealPic;
	private Integer mealPrice;
	private String mealInfo;
	private Integer mealStatus;
	private String base64;
	private Integer count;
	private Integer total;

	public String getMealInfo() {
		return mealInfo;
	}

	public void setMealInfo(String mealInfo) {
		this.mealInfo = mealInfo;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public MealVO() {
	};

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public byte[] getMealPic() {
		return mealPic;
	}

	public void setMealPic(byte[] mealPic) {
		this.mealPic = mealPic;
	}

	public Integer getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(Integer mealPrice) {
		this.mealPrice = mealPrice;
	}

	public Integer getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(Integer mealStatus) {
		this.mealStatus = mealStatus;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "MealVO [mealId=" + mealId + ", mealName=" + mealName + ", mealPic=" + Arrays.toString(mealPic)
				+ ", mealPrice=" + mealPrice + ", mealInfo=" + mealInfo + ", mealStatus=" + mealStatus + ", count=" + count + ", total=" + total + "]";
	}

}
