package com.Android.articleDetail.model;

import com.Android.spot.model.SpotListVO;

public class ArticleDetailsVO implements java.io.Serializable{
	private String articleno;
	private String spotno;
	private String articlenotes;
	private Integer articletriporderby;
	private String picnote;
	private Integer tripdayorder;
	private SpotListVO spotListVO;
	
	
	public SpotListVO getSpotListVO() {
		return spotListVO;
	}
	public void setSpotListVO(SpotListVO spotListVO) {
		this.spotListVO = spotListVO;
	}
	public String getArticleno() {
		return articleno;
	}
	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	public String getSpotno() {
		return spotno;
	}
	public void setSpotno(String spotno) {
		this.spotno = spotno;
	}
	public String getArticlenotes() {
		return articlenotes;
	}
	public void setArticlenotes(String articlenotes) {
		this.articlenotes = articlenotes;
	}
	public Integer getArticletriporderby() {
		return articletriporderby;
	}
	public void setArticletriporderby(Integer articletriporderby) {
		this.articletriporderby = articletriporderby;
	}
	public String getPicnote() {
		return picnote;
	}
	public void setPicnote(String picnote) {
		this.picnote = picnote;
	}
	public Integer getTripdayorder() {
		return tripdayorder;
	}
	public void setTripdayorder(Integer tripdayorder) {
		this.tripdayorder = tripdayorder;
	}
	
}
