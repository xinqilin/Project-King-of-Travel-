package com.tripReport.model;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TripReportVO implements java.io.Serializable{
	private String tripno;
	private String memno;
	private String reason;
	private Integer	tripstatus;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getTripstatus() {
		return tripstatus;
	}
	public void setTripstatus(Integer tripstatus) {
		this.tripstatus = tripstatus;
	}
	
}
