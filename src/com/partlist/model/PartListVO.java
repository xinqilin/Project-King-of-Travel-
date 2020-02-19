package com.partlist.model;

public class PartListVO {
	
	private String partno;
	private String memno;
	private String activityno;
	private String oddteam;
	public String getPartno() {
		return partno;
	}
	public void setPartno(String partno) {
		this.partno = partno;
	}
	public String getOddteam() {
		return oddteam;
	}
	public void setOddteam(String oddteam) {
		this.oddteam = oddteam;
	}
	private Integer putpoints;
	private Integer getpoints;
	private Double oddrate;
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getActivityno() {
		return activityno;
	}
	public void setActivityno(String activityno) {
		this.activityno = activityno;
	}
	public Integer getPutpoints() {
		return putpoints;
	}
	public void setPutpoints(Integer putpoints) {
		this.putpoints = putpoints;
	}
	public Integer getGetpoints() {
		return getpoints;
	}
	public void setGetpoints(Integer getpoints) {
		this.getpoints = getpoints;
	}
	public Double getOddrate() {
		return oddrate;
	}
	public void setOddrate(Double oddrate) {
		this.oddrate = oddrate;
	}
}
