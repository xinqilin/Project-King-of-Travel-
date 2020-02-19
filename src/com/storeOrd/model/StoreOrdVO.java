package com.storeOrd.model;

import java.sql.Timestamp;
public class StoreOrdVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ordNo;
	private String memNo;
	private Integer price;
	private String address;
	private Integer status;  //訂單狀態
	private Timestamp orderedTime;
	private Timestamp paymentTime;
	private Integer paymentMethod;//付款方式
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(Timestamp orderedTime) {
		this.orderedTime = orderedTime;
	}
	public Timestamp getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
