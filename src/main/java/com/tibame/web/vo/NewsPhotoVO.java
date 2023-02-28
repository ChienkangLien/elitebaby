package com.tibame.web.vo;

import java.util.Arrays;

public class NewsPhotoVO {
	private Integer photoId;
	private Integer newsId;
	private byte[] newsPhoto;
	@Override
	public String toString() {
		return "NewsPhotoVo [photoId=" + photoId + ", newsId=" + newsId + ", newsPhoto=" + Arrays.toString(newsPhoto)
				+ "]";
	}
	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public byte[] getNewsPhoto() {
		return newsPhoto;
	}
	public void setNewsPhoto(byte[] newsPhoto) {
		this.newsPhoto = newsPhoto;
	}
}