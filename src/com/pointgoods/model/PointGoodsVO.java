package com.pointgoods.model;

public class PointGoodsVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String pointgoodsno;
	private String pointgoodsname;
	private Integer pointgoodsquantity;
	private Integer needpoints;
	private String pointgoodsdesc;
	private byte[] pointgoodspic;
	private Integer pointgoodsstatus; 
	
	public String getPointgoodsno() {
		return pointgoodsno;
	}
	public void setPointgoodsno(String pointgoodsno) {
		this.pointgoodsno = pointgoodsno;
	}
	public String getPointgoodsname() {
		return pointgoodsname;
	}
	public void setPointgoodsname(String pointgoodsname) {
		this.pointgoodsname = pointgoodsname;
	}
	public Integer getPointgoodsquantity() {
		return pointgoodsquantity;
	}
	public void setPointgoodsquantity(Integer pointgoodsquantity) {
		this.pointgoodsquantity = pointgoodsquantity;
	}
	public Integer getNeedpoints() {
		return needpoints;
	}
	public void setNeedpoints(Integer needpoints) {
		this.needpoints = needpoints;
	}
	public String getPointgoodsdesc() {
		return pointgoodsdesc;
	}
	public void setPointgoodsdesc(String pointgoodsdesc) {
		this.pointgoodsdesc = pointgoodsdesc;
	}
	public byte[] getPointgoodspic() {
		return pointgoodspic;
	}
	public void setPointgoodspic(byte[] pointgoodspic) {
		this.pointgoodspic = pointgoodspic;
	}
	public Integer getPointgoodsstatus() {
		return pointgoodsstatus;
	}
	public void setPointgoodsstatus(Integer pointgoodsstatus) {
		this.pointgoodsstatus = pointgoodsstatus;
	}
}
