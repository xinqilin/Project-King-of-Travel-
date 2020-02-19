package com.pgorddetails.model;

import java.util.List;

public interface PGOrdDetailsDAO_interface {
	
	public void insert (PGOrdDetailsVO pgorddetailsVO);
	public void update(PGOrdDetailsVO pgorddetailsVO);
	public void delete(String pointgoodsordno, String pointgoodsno);
	public PGOrdDetailsVO findByPrimaryKey(String pointgoodsordno, String pointgoodsno);
	public List<PGOrdDetailsVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<PointGoodsVO> getAllByAny(Map<String, String[]> map )
	
	public void insert2 (PGOrdDetailsVO pgorddetailsVO , java.sql.Connection con);

}
