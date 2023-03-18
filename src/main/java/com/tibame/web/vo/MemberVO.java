package com.tibame.web.vo;

import java.io.Serializable;
import java.util.Date;

public class MemberVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phoneNumber;
	private Date birthday;
	private String login;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public MemberVO(Integer id, String username, String password, String email, String address, String phoneNumber, Date birthday) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
		
	public MemberVO() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "member [id=" + id + ", userName=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", birthday=" + birthday + "]";
	}
}
