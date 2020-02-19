package com.Android.tripDetail.model;

import java.sql.*;

import com.Android.spot.model.SpotListVO;


public class TripDetailVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tripno;
	private String spotno;
	private Time timeofarrive;
	private Time timeofleave;
	private Integer stayhours;
	private Double milestonextspots;
	private Integer tripdayorder;
	private Integer triporderby;
	private String triptips;
	private SpotListVO spotListVO;
	
	public TripDetailVO() {
		super();
	}
	public TripDetailVO(String tripno, String spotno, Time timeofarrive, Time timeofleave, Integer stayhours,
			Double milestonextspots, Integer tripdayorder, Integer triporderby, String triptips,
			SpotListVO spotListVO) {
		super();
		this.tripno = tripno;
		this.spotno = spotno;
		this.timeofarrive = timeofarrive;
		this.timeofleave = timeofleave;
		this.stayhours = stayhours;
		this.milestonextspots = milestonextspots;
		this.tripdayorder = tripdayorder;
		this.triporderby = triporderby;
		this.triptips = triptips;
		this.spotListVO = spotListVO;
	}
	public SpotListVO getSpotListVO() {
		return spotListVO;
	}
	public void setSpotListVO(SpotListVO spotListVO) {
		this.spotListVO = spotListVO;
	}
	public String getTripno() {
		return tripno;
	}
	public void setTripno(String tripno) {
		this.tripno = tripno;
	}
	public String getSpotno() {
		return spotno;
	}
	public void setSpotno(String spotno) {
		this.spotno = spotno;
	}
	public Time getTimeofarrive() {
		return timeofarrive;
	}
	public void setTimeofarrive(Time timeofarrive) {
		this.timeofarrive = timeofarrive;
	}
	public Time getTimeofleave() {
		return timeofleave;
	}
	public void setTimeofleave(Time timeofleave) {
		this.timeofleave = timeofleave;
	}
	public Integer getStayhours() {
		return stayhours;
	}
	public void setStayhours(Integer stayhours) {
		this.stayhours = stayhours;
	}
	public Double getMilestonextspots() {
		return milestonextspots;
	}
	public void setMilestonextspots(Double milestonextspots) {
		this.milestonextspots = milestonextspots;
	}
	public Integer getTripdayorder() {
		return tripdayorder;
	}
	public void setTripdayorder(Integer tripdayorder) {
		this.tripdayorder = tripdayorder;
	}
	public Integer getTriporderby() {
		return triporderby;
	}
	public void setTriporderby(Integer triporderby) {
		this.triporderby = triporderby;
	}
	public String getTriptips() {
		return triptips;
	}
	public void setTriptips(String triptips) {
		this.triptips = triptips;
	}



}
