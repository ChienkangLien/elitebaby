package com.tibame.web.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class LatestNewsVO implements java.io.Serializable{
	//                                        interface 介面
	private Integer newsId;
	private Integer sortId;
	private Integer adminId;
	private String newsIntro;
    private Date scheduledTime;
	private String postTitle;
	private byte[] newsPhoto;
	

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

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public byte[] getNewsPhoto() {
		return newsPhoto;
	}
	public void setNewsPhoto(byte[] newsPhoto) {
		this.newsPhoto = newsPhoto;
	}

	@Override
	public String toString() {
		return "LatestNewsVO [newsId=" + newsId + ", sortId=" + sortId + ", adminId=" + adminId + ", newsIntro="
				+ newsIntro + ", scheduledTime=" + scheduledTime + ", postTitle=" + postTitle + ", newsPhoto="
				+ Arrays.toString(newsPhoto) + "]";
	}



}
