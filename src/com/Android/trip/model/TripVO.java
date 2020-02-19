package com.Android.trip.model;
import java.sql.Date;

public class TripVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tripNo;
	private String memNo;
	private String cityNo;
	private String tripName;
	private Date tripStartDay;
	private Date tripEndDay;
	private Integer tripDays;
	private Date tripEstDate;
	private Integer beTheBuyer;
	private Integer tripStatus;
	private Integer timeOfViews;
	private Integer kindOfTrip;

	public String getTripNo() {
		return tripNo;
	}
	public void setTripNo(String tripNo) {
		this.tripNo = tripNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public Date getTripStartDay() {
		return tripStartDay;
	}
	public void setTripStartDay(Date tripStartDay) {
		this.tripStartDay = tripStartDay;
	}
	public Date getTripEndDay() {
		return tripEndDay;
	}
	public void setTripEndDay(Date tripEndDay) {
		this.tripEndDay = tripEndDay;
	}
	public Integer getTripDays() {
		return tripDays;
	}
	public void setTripDays(Integer tripDays) {
		this.tripDays = tripDays;
	}
	public Date getTripEstDate() {
		return tripEstDate;
	}
	public void setTripEstDate(Date tripEstDate) {
		this.tripEstDate = tripEstDate;
	}
	public Integer getBeTheBuyer() {
		return beTheBuyer;
	}
	public void setBeTheBuyer(Integer beTheBuyer) {
		this.beTheBuyer = beTheBuyer;
	}
	public Integer getTripStatus() {
		return tripStatus;
	}
	public void setTripStatus(Integer tripStatus) {
		this.tripStatus = tripStatus;
	}
	public Integer getTimeOfViews() {
		return timeOfViews;
	}
	public void setTimeOfViews(Integer timeOfViews) {
		this.timeOfViews = timeOfViews;
	}
	public Integer getKindOfTrip() {
		return kindOfTrip;
	}
	public void setKindOfTrip(Integer kindOfTrip) {
		this.kindOfTrip = kindOfTrip;
	}
}
