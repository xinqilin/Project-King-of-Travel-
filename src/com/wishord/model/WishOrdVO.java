package com.wishord.model;

import java.sql.Date;

public class WishOrdVO {
	private String wishOrdNo;
	private String buyMemNo;
	private String wishMemNo;//賣家
	private Date dayOfEst;//代購單創建日期
	private Integer wishOrdStatus;//(0:未付款; 1:已付款; 2:已寄貨;	3: 已收貨 4: 買家取消; 5: 賣家取消; 6: 檢舉凍結;)
	private Integer wishOrdTotalPrice;
	private Date wishSendDate;
	private String wishOrdBuyNote;
	private Date lastDate;
	private String wishOrdSendAddr;
	
	public String getWishOrdNo() {
		return wishOrdNo;
	}
	public void setWishOrdNo(String wishOrdNo) {
		this.wishOrdNo = wishOrdNo;
	}
	public String getBuyMemNo() {
		return buyMemNo;
	}
	public void setBuyMemNo(String buyMemNo) {
		this.buyMemNo = buyMemNo;
	}
	public String getWishMemNo() {
		return wishMemNo;
	}
	public void setWishMemNo(String wishMemNo) {
		this.wishMemNo = wishMemNo;
	}
	public Date getDayOfEst() {
		return dayOfEst;
	}
	public void setDayOfEst(Date dayOfEst) {
		this.dayOfEst = dayOfEst;
	}
	public Integer getWishOrdStatus() {
		return wishOrdStatus;
	}
	public void setWishOrdStatus(Integer wishOrdStatus) {
		this.wishOrdStatus = wishOrdStatus;
	}
	public Integer getWishOrdTotalPrice() {
		return wishOrdTotalPrice;
	}
	public void setWishOrdTotalPrice(Integer wishOrdTotalPrice) {
		this.wishOrdTotalPrice = wishOrdTotalPrice;
	}
	public Date getWishSendDate() {
		return wishSendDate;
	}
	public void setWishSendDate(Date wishSendDate) {
		this.wishSendDate = wishSendDate;
	}
	public String getWishOrdBuyNote() {
		return wishOrdBuyNote;
	}
	public void setWishOrdBuyNote(String wishOrdBuyNote) {
		this.wishOrdBuyNote = wishOrdBuyNote;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public String getWishOrdSendAddr() {
		return wishOrdSendAddr;
	}
	public void setWishOrdSendAddr(String wishOrdSendAddr) {
		this.wishOrdSendAddr = wishOrdSendAddr;
	}
}
