package com.trip.model;
import java.sql.Date;

public class TripVO implements java.io.Serializable{
	private String tripno;
	private String memno;
	private String cityno;
	private String tripname;
	private Date tripstartday;
	private Date tripendday;
	private Integer tripdays;
	private Date tripestdate;
	private Integer bethebuyer;
	private Integer tripstatus;
	private Integer timeofviews;
	private Integer kindoftrip;
	private byte[] trippic;
	public byte[] getTrippic() {
		return trippic;
	}
	public void setTrippic(byte[] trippic) {
		this.trippic = trippic;
	}
	public String getTripno() {
		return tripno;
	}
	public void setTripno(String tripno) {
		this.tripno = tripno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getCityno() {
		return cityno;
	}
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	public String getTripname() {
		return tripname;
	}
	public void setTripname(String tripname) {
		this.tripname = tripname;
	}
	public Date getTripstartday() {
		return tripstartday;
	}
	public void setTripstartday(Date tripstartday) {
		this.tripstartday = tripstartday;
	}
	public Date getTripendday() {
		return tripendday;
	}
	public void setTripendday(Date tripendday) {
		this.tripendday = tripendday;
	}
	public Integer getTripdays() {
		return tripdays;
	}
	public void setTripdays(Integer tripdays) {
		this.tripdays = tripdays;
	}
	public Date getTripestdate() {
		return tripestdate;
	}
	public void setTripestdate(Date tripestdate) {
		this.tripestdate = tripestdate;
	}
	public Integer getBethebuyer() {
		return bethebuyer;
	}
	public void setBethebuyer(Integer bethebuyer) {
		this.bethebuyer = bethebuyer;
	}
	public Integer getTripstatus() {
		return tripstatus;
	}
	public void setTripstatus(Integer tripstatus) {
		this.tripstatus = tripstatus;
	}
	public Integer getTimeofviews() {
		return timeofviews;
	}
	public void setTimeofviews(Integer timeofviews) {
		this.timeofviews = timeofviews;
	}
	public Integer getKindoftrip() {
		return kindoftrip;
	}
	public void setKindoftrip(Integer kindoftrip) {
		this.kindoftrip = kindoftrip;
	}
	
	
}
