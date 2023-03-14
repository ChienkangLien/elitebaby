package com.tibame.web.vo;

public class EmailDTO {
	private String category;
	private String likesome;
	private Integer userId;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "EmailDTO [category=" + category + ", likesome=" + likesome + ", userId=" + userId + "]";
	}

	public String getLikesome() {
		return likesome;
	}

	public void setLikesome(String likesome) {
		this.likesome = likesome;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
