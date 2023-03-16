package com.tibame.web.vo;

import java.util.ArrayList;

public class ReportImageVO {
	private Integer rimgId;
	private String authCode;
	private byte[] reportImage;
	private String strBase64;
	private ArrayList  arryBase64;
	private String[] testBase64;

	public Integer getRimgId() {
		return rimgId;
	}

	public void setRimgId(Integer rimgId) {
		this.rimgId = rimgId;
	}


	public byte[] getReportImage() {
		return reportImage;
	}

	public void setReportImage(byte[] reportImage) {
		this.reportImage = reportImage;
	}

	public String getStrBase64() {
		return strBase64;
	}

	public void setStrBase64(String strBase64) {
		this.strBase64 = strBase64;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public ArrayList getArryBase64() {
		return arryBase64;
	}

	public void setArryBase64(ArrayList arryBase64) {
		this.arryBase64 = arryBase64;
	}

	public String[] getTestBase64() {
		return testBase64;
	}

	public void setTestBase64(String[] testBase64) {
		this.testBase64 = testBase64;
	}

}
