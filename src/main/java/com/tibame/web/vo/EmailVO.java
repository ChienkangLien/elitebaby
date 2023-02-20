package com.tibame.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "REPORT_MAIL")
public class EmailVO {

	@Id
	@Column(name = "MAIL_ID")
	private Integer mailId;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	@Column(name = "ADMIN_ID")
	private Integer adminId;

	@Column(name = "REPORT_TITLE")
	private String reportTile;

	@Column(name = "REPORT_CONTENT")
	@Type(type = "text")
	private String reportContent;

	@Column(name = "REPORT_CREATE_TIME")
	private java.sql.Timestamp preportCreateTime;

	@Column(name = "ANSWER_CONTENT")
	@Type(type = "text")
	private String answerContent;

	@Column(name = "ANSWER_CREATE_TIME")
	private java.sql.Timestamp ansertCreateTime;

	@Column(name = "ANSWER_TITLE")
	private String answerTitle;
	
	private String authCode;
	
	private String strBase64;

	@Override
	public String toString() {
		return "EmailVO [mailId=" + mailId + ", userId=" + userId + ", categoryId=" + categoryId + ", adminId="
				+ adminId + ", reportTile=" + reportTile + ", reportContent=" + reportContent + ", preportCreateTime="
				+ preportCreateTime + ", answerContent=" + answerContent + ", ansertCreateTime=" + ansertCreateTime
				+ ", answerTitle=" + answerTitle + "]";
	}

	public Integer getMailId() {
		return mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getReportTile() {
		return reportTile;
	}

	public void setReportTile(String reportTile) {
		this.reportTile = reportTile;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public java.sql.Timestamp getPreportCreateTime() {
		return preportCreateTime;
	}

	public void setPreportCreateTime(java.sql.Timestamp preportCreateTime) {
		this.preportCreateTime = preportCreateTime;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public java.sql.Timestamp getAnsertCreateTime() {
		return ansertCreateTime;
	}

	public void setAnsertCreateTime(java.sql.Timestamp ansertCreateTime) {
		this.ansertCreateTime = ansertCreateTime;
	}

	public String getAnswerTitle() {
		return answerTitle;
	}

	public void setAnswerTitle(String answerTitle) {
		this.answerTitle = answerTitle;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getStrBase64() {
		return strBase64;
	}

	public void setStrBase64(String strBase64) {
		this.strBase64 = strBase64;
	}

}
