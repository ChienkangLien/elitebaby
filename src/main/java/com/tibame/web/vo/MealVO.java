package com.tibame.web.vo;

public class MealVO {
	private Integer mealId;
	private String mealName;
	private byte[] mealPic;
	private Integer mealQuantity;
	private Integer mealPrice;
	private Integer reserverPrivce;
	private Integer mealStatus;
	
	public MealVO() {	
	};
	
	public MealVO(Integer mealId, String mealName, byte[] mealPic, Integer mealQuantity, Integer mealPrice,
			Integer reservePrivce, Integer mealStatus) {
		super();
		this.mealId = mealId;
		this.mealName = mealName;
		this.mealPic = mealPic;
		this.mealQuantity = mealQuantity;
		this.mealPrice = mealPrice;
		this.reserverPrivce = reservePrivce;
		this.mealStatus = mealStatus;
	}

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

	public Integer getMealQuantity() {
		return mealQuantity;
	}

	public void setMealQuantity(Integer mealQuantity) {
		this.mealQuantity = mealQuantity;
	}

	public Integer getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(Integer mealPrice) {
		this.mealPrice = mealPrice;
	}

	public Integer getReserverPrice() {
		return reserverPrivce;
	}

	public void setReservePrivce(Integer reservePrivce) {
		this.reserverPrivce = reservePrivce;
	}

	public Integer getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(Integer mealStatus) {
		this.mealStatus = mealStatus;
	}
	
	@Override
	public String toString() {
		return "MealVO [mealId=" + mealId + ", mealname=" + mealName + ", mealQuantity=" + mealQuantity
				+", mealPrice="+ mealPrice +", reservePrivce="+reserverPrivce+", mealStatus"+mealStatus+ "]";
	}

}
