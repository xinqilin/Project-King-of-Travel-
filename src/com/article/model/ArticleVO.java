package com.article.model;

import java.sql.Date;

public class ArticleVO implements java.io.Serializable{
	private String articleno;
	private String memno;
	private String tripno;
	private String articletitle;
	private Integer daysofstaying;
	private Date dayofstart;
	private Date dayofend;
	private Integer articlestatus;
	private Date dayoflastedit;
	private Integer timeofviews;
	private Integer kindoftrip;
	private byte[] articlepic;
	private Date dayofcreate;
	

	public Date getDayofcreate() {
		return dayofcreate;
	}
	public void setDayofcreate(Date dayofcreate) {
		this.dayofcreate = dayofcreate;
	}
	public byte[] getArticlepic() {
		return articlepic;
	}
	public void setArticlepic(byte[] articlepic) {
		this.articlepic = articlepic;
	}
	public String getArticleno() {
		return articleno;
	}
	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getTripno() {
		return tripno;
	}
	public void setTripno(String tripno) {
		this.tripno = tripno;
	}
	public String getArticletitle() {
		return articletitle;
	}
	public void setArticletitle(String articletitle) {
		this.articletitle = articletitle;
	}
	public Integer getDaysofstaying() {
		return daysofstaying;
	}
	public void setDaysofstaying(Integer daysofstaying) {
		this.daysofstaying = daysofstaying;
	}
	public Date getDayofstart() {
		return dayofstart;
	}
	public void setDayofstart(Date dayofstart) {
		this.dayofstart = dayofstart;
	}
	public Date getDayofend() {
		return dayofend;
	}
	public void setDayofend(Date dayofend) {
		this.dayofend = dayofend;
	}
	public Integer getArticlestatus() {
		return articlestatus;
	}
	public void setArticlestatus(Integer articlestatus) {
		this.articlestatus = articlestatus;
	}
	public Date getDayoflastedit() {
		return dayoflastedit;
	}
	public void setDayoflastedit(Date dayoflastedit) {
		this.dayoflastedit = dayoflastedit;
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
