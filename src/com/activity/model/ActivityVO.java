package com.activity.model;

import java.sql.Timestamp;

public class ActivityVO {
	
	private String activityno;
	private String activityname;
	private String actrule;
	private Timestamp datestart; 
	private Timestamp dateend;
	private Double betrate;
	private String chatcontent;
	private String streamcontent;
	private String activitycontent;
	public String getActivityno() {
		return activityno;
	}
	public void setActivityno(String activityno) {
		this.activityno = activityno;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public String getActrule() {
		return actrule;
	}
	public void setActrule(String actrule) {
		this.actrule = actrule;
	}
	public Timestamp getDatestart() {
		return datestart;
	}
	public void setDatestart(Timestamp datestart) {
		this.datestart = datestart;
	}
	public Timestamp getDateend() {
		return dateend;
	}
	public void setDateend(Timestamp dateend) {
		this.dateend = dateend;
	}
	public Double getBetrate() {
		return betrate;
	}
	public void setBetrate(Double betrate) {
		this.betrate = betrate;
	}
	public String getChatcontent() {
		return chatcontent;
	}
	public void setChatcontent(String chatcontent) {
		this.chatcontent = chatcontent;
	}
	public String getStreamcontent() {
		return streamcontent;
	}
	public void setStreamcontent(String streamcontent) {
		this.streamcontent = streamcontent;
	}
	public String getActivitycontent() {
		return activitycontent;
	}
	public void setActivitycontent(String activitycontent) {
		this.activitycontent = activitycontent;
	}
}
