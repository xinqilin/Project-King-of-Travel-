package com.Android.mem.model;

import java.io.Serializable;
import java.sql.Date;

public class MemVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2893128637889584130L;
	private	String memNo;
	private String memName;
	private String e_mail;
	private String memPassWd;
	private String nickName;
	private String idNo;
	private Date birDay;
	private String addr;
	private String point;
	private String articleCount;
	private String tripCount;
	private String phone;
	private Integer status;
	private String introduction;
	private String points;

	
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public MemVO() {
		super();
	}
	
	public MemVO(String memNo, String memName, String e_mail, String memPassWd, String nickName, String idNo,
			Date birDay, String addr, String point, String articleCount, String tripCount, String phone, Integer status,
			String introduction, String points) {
		super();
		this.memNo = memNo;
		this.memName = memName;
		this.e_mail = e_mail;
		this.memPassWd = memPassWd;
		this.nickName = nickName;
		this.idNo = idNo;
		this.birDay = birDay;
		this.addr = addr;
		this.point = point;
		this.articleCount = articleCount;
		this.tripCount = tripCount;
		this.phone = phone;
		this.status = status;
		this.introduction = introduction;
		this.points = points;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public String getTripCount() {
		return tripCount;
	}
	public void setTripCount(String tripCount) {
		this.tripCount = tripCount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
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

}
