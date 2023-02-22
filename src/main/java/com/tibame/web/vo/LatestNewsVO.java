package com.tibame.web.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class LatestNewsVO implements java.io.Serializable{
	//                                        interface 介面
	private Integer newsId;
	private Integer sortId;
	private Integer adminId;
	private String newsIntro;
	private Date publishedTime;
	private Date onNews;
	private Date offNews;
	private String postTitle;

	

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getNewsIntro() {
		return newsIntro;
	}

	public void setNewsIntro(String newsIntro) {
		this.newsIntro = newsIntro;
	}

	public Date getPublishedTime() {
		return publishedTime;
	}

	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}

	public Date getOnNews() {
		return onNews;
	}

	public void setOnNews(Date onNews) {
		this.onNews = onNews;
	}

	public Date getOffNews() {
		return offNews;
	}

	public void setOffNews(Date offNews) {
		this.offNews = offNews;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

//	@Override
//	public String toString() {
//		return "LatestNewsVo [newsId=" + newsId + ", sortId=" + sortId + ", adminId=" + adminId + ", newsIntro="
//				+ newsIntro + ", publishedTime=" + publishedTime + ", onNewsId=" + onNewsId + ", offNewsId=" + offNewsId
//				+ ", postTitle=" + postTitle + "]";
//	}

}
