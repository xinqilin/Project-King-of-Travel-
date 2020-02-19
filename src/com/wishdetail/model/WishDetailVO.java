package com.wishdetail.model;

public class WishDetailVO  implements java.io.Serializable{
	private String wishOrdNo;
	private String wishItemNo;
	private Integer wishItemNumbers;
	private Integer wishItemPrice;
	public String getWishOrdNo() {
		return wishOrdNo;
	}
	public void setWishOrdNo(String wishOrdNo) {
		this.wishOrdNo = wishOrdNo;
	}
	public String getWishItemNo() {
		return wishItemNo;
	}
	public void setWishItemNo(String wishItemNo) {
		this.wishItemNo = wishItemNo;
	}
	public Integer getWishItemNumbers() {
		return wishItemNumbers;
	}
	public void setWishItemNumbers(Integer wishItemNumbers) {
		this.wishItemNumbers = wishItemNumbers;
	}
	public Integer getWishItemPrice() {
		return wishItemPrice;
	}
	public void setWishItemPrice(Integer wishItemPrice) {
		this.wishItemPrice = wishItemPrice;
	}


}
