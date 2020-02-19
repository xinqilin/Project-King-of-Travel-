package com.mem.model;

import java.sql.Date;

public class MemVO implements java.io.Serializable{
	private	String memNo;
	private String memName;
	private String e_mail;
	private String memPassWd;
	private byte[] memPhoto;
	private String nickName;
	private String idNo;
	private Date birDay;
	private String address;
	private String phone;
	private Date dateOfAccountEshablished;
	private Integer status;
	private String introduction;
	private Integer points;
	private Integer maxPoints;
	
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getMemPassWd() {
		return memPassWd;
	}
	public void setMemPassWd(String memPassWd) {
		this.memPassWd = memPassWd;
	}
	public byte[] getMemPhoto() {
		return memPhoto;
	}
	public void setMemPhoto(byte[] memPhoto) {
		this.memPhoto = memPhoto;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Date getBirDay() {
		return birDay;
	}
	public void setBirDay(Date birDay) {
		this.birDay = birDay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}
	public Date getDateOfAccountEshablished() {
		return dateOfAccountEshablished;
	}
	public void setDateOfAccountEshablished(Date dateOfAccountEshablished) {
		this.dateOfAccountEshablished = dateOfAccountEshablished;
	}
			
}
