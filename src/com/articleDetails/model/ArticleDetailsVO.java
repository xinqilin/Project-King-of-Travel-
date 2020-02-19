package com.articleDetails.model;

public class ArticleDetailsVO implements java.io.Serializable{
	private String articleno;
	private String spotno;
	private String articlenotes;
	private byte[] articledetailspic;
	private Integer articletriporderby;
	private String picnote;
	private Integer tripdayorder;
	
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
	public byte[] getArticledetailspic() {
		return articledetailspic;
	}
	public void setArticledetailspic(byte[] articledetailspic) {
		this.articledetailspic = articledetailspic;
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
