package com.tibame.web.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class NewsMessageVO {
	private Integer newsMessageId;
	private Integer userId;
	private String messageContent;
	private Timestamp contentTime;
	private Integer newsId;
	
	public Integer getNewsMessageId() {
		return newsMessageId;
	}
	public void setNewsMessageId(Integer newsMessageId) {
		this.newsMessageId = newsMessageId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Timestamp getContentTime() {
		return contentTime;
	}
	public void setContentTime(Timestamp contentTime) {
		this.contentTime = contentTime;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	@Override
	public String toString() {
		return "NewsMessageVO [newsMessageId=" + newsMessageId + ", userId=" + userId + ", messageContent="
				+ messageContent + ", contentTime=" + contentTime + ", newsId=" + newsId + "]";
	}
}
