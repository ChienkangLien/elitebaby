package com.tibame.web.vo;

public class MealVO {
	
	private Integer mealId;
	private String mealName;
	private byte[] mealPic;
	private Integer mealPrice;
	private Integer reserverPrice;
	private Integer mealStatus;
	private String base64;
	
	public MealVO() {	
	};
	
	public MealVO(String mealName, Integer mealPrice, Integer reservePrivce) {
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.reserverPrice = reservePrivce;
	}
	
	public MealVO(Integer mealId, String mealName, byte[] mealPic, Integer mealPrice, 
			Integer reservePrivce, Integer mealStatus) {
		super();
		this.mealId = mealId;
		this.mealName = mealName;
		this.mealPic = mealPic;
		this.mealPrice = mealPrice;
		this.reserverPrice = reservePrivce;
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

	public Integer getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(Integer mealPrice) {
		this.mealPrice = mealPrice;
	}

	public Integer getReserverPrice() {
		return reserverPrice;
	}

	public void setReservePrivce(Integer reservePrivce) {
		this.reserverPrice = reservePrivce;
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

	@Override
	public String toString() {
		return "MealVO [mealId=" + mealId + ", mealname=" + mealName +", mealpic="+ mealPic
				+", mealPrice="+ mealPrice +", reservePrivce="+reserverPrice+", mealStatus"+mealStatus+ "]";
	}

}
