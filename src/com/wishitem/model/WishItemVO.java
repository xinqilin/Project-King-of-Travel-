package com.wishitem.model;

public class WishItemVO  implements java.io.Serializable{
	private String wishItemNo;
	private String memNo;
	private Integer amount;
	private String itemName;
	private Integer itemPrice;
	private String itemStoreName;
	private String itemStoreAddr;
	private String itemStoreLati;
	private String itemStoreLong;
	private Integer buyOrSell;
	private String wishItemDetail;	
	private byte[] wishItemPicture;
	private String wishNote;
	private Integer status;
	private Integer itemType;
	
	public WishItemVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getWishItemNo() {
		return wishItemNo;
	}
	public void setWishItemNo(String wishItemNo) {
		this.wishItemNo = wishItemNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemStoreName() {
		return itemStoreName;
	}
	public void setItemStoreName(String itemStoreName) {
		this.itemStoreName = itemStoreName;
	}
	public String getItemStoreAddr() {
		return itemStoreAddr;
	}
	public void setItemStoreAddr(String itemStoreAddr) {
		this.itemStoreAddr = itemStoreAddr;
	}
	public String getItemStoreLati() {
		return itemStoreLati;
	}
	public void setItemStoreLati(String itemStoreLati) {
		this.itemStoreLati = itemStoreLati;
	}
	public String getItemStoreLong() {
		return itemStoreLong;
	}
	public void setItemStoreLong(String itemStoreLong) {
		this.itemStoreLong = itemStoreLong;
	}
	public Integer getBuyOrSell() {
		return buyOrSell;
	}
	public void setBuyOrSell(Integer buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	public String getWishItemDetail() {
		return wishItemDetail;
	}
	public void setWishItemDetail(String wishItemDetail) {
		this.wishItemDetail = wishItemDetail;
	}
	public byte[] getWishItemPicture() {
		return wishItemPicture;
	}
	public void setWishItemPicture(byte[] wishItemPicture) {
		this.wishItemPicture = wishItemPicture;
	}
	public String getWishNote() {
		return wishNote;
	}
	public void setWishNote(String wishNote) {
		this.wishNote = wishNote;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getItemType() {
		return itemType;
	}
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}
	
}
