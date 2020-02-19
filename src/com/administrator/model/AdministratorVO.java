package com.administrator.model;

public class AdministratorVO implements java.io.Serializable{
	private String adminNo;
	private String adminName;
	private String e_mail;
	private String adminPassWd;
	private Integer adminStatus;
	
	
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getAdminPassWd() {
		return adminPassWd;
	}
	public void setAdminPassWd(String adminPassWd) {
		this.adminPassWd = adminPassWd;
	}
	public Integer getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}
}
