package com.tibame.web.vo;

public class NewsSortVO implements java.io.Serializable{
	private Integer sortId;
	private String sortName;
	
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	@Override
	public String toString() {
		return "NewsSortVo [sortId=" + sortId + ", sortName=" + sortName + "]";
	}
}
