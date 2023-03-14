package com.tibame.web.vo;

import java.io.Serializable;

public class EmployeeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer empid;
	private String empname;
	private String empaccount;
	private String emppassword;
	private String emppermission;

	public EmployeeVO() {
		super();
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	public String getEmpaccount() {
		return empaccount;
	}
	
	public void setEmpaccount(String empaccount) {
		this.empaccount = empaccount;
	}

	public String getEmppassword() {
		return emppassword;
	}

	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
	}


	public String getEmppermission() {
		return emppermission;
	}

	public void setEmppermission(String emppermission) {
		this.emppermission = emppermission;
	}

	public EmployeeVO(Integer empid, String empname, String emppassword, String empaccount, String emppermission) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empaccount = empaccount;
		this.emppassword = emppassword;
		this.emppermission = emppermission;
	}

	@Override
	public String toString() {
		return "employee [empid=" + empid + ", empName=" + empname + ", emppassword=" + emppassword + ", empaccount="
				+ empaccount + ", emppermission=" + emppermission + "]";
	}
}
