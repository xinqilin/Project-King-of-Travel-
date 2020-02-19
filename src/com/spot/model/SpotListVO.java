package com.spot.model;

public class SpotListVO implements java.io.Serializable{
	private String spotNo;
	private String spotName;
	private String cityNo;
	private String location;
	private Integer spotType;
	private byte[] spotPhoto;
	private Integer spotStatus;
	private String tel;
	private Double spotLati;
	private Double spotLong;
	private String spotDetail;
	
	
	public String getSpotNo() {
		return spotNo;
	}
	public void setSpotNo(String spotNo) {
		this.spotNo = spotNo;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getSpotType() {
		return spotType;
	}
	public void setSpotType(Integer spotType) {
		this.spotType = spotType;
	}
	public byte[] getSpotPhoto() {
		return spotPhoto;
	}
	public void setSpotPhoto(byte[] spotPhoto) {
		this.spotPhoto = spotPhoto;
	}
	public Integer getSpotStatus() {
		return spotStatus;
	}
	public void setSpotStatus(Integer spotStatus) {
		this.spotStatus = spotStatus;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Double getSpotLati() {
		return spotLati;
	}
	public void setSpotLati(Double spotLati) {
		this.spotLati = spotLati;
	}
	public Double getSpotLong() {
		return spotLong;
	}
	public void setSpotLong(Double spotLong) {
		this.spotLong = spotLong;
	}
	public String getSpotDetail() {
		return spotDetail;
	}
	public void setSpotDetail(String spotDetail) {
		this.spotDetail = spotDetail;
	}
	
}
