package com.pointgoodsord.model;

import java.sql.Timestamp;

public class PointGoodsOrdVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String pointgoodsordno;
	private String memno;
	private String receiver;
	private String phone;
	private String address;
	private Timestamp orderdate;
	private Integer orderstatus;
	private Integer orderpoint;
	
	public String getPointgoodsordno() {
		return pointgoodsordno;
	}
	public void setPointgoodsordno(String pointgoodsordno) {
		this.pointgoodsordno = pointgoodsordno;
	}
	public Timestamp getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}
	public Integer getOrderpoint() {
		return orderpoint;
	}
	public void setOrderpoint(Integer orderpoint) {
		this.orderpoint = orderpoint;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
