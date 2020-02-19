package com.pointgoodsord.model;

import java.util.List;

import com.pgorddetails.model.PGOrdDetailsVO;

public interface PointGoodsOrdDAO_interface {
	
	public void insert (PointGoodsOrdVO pointgoodsordVO);
	public void update(PointGoodsOrdVO pointgoodsordVO);
	public void delete(String pointgoodsordno);
	public PointGoodsOrdVO findByPrimaryKey(String pointgoodsordno);
	public List<PointGoodsOrdVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<PointGoodsOrdVO> getAllByAny(Map<String, String[]> map ) 
	
	public void insertWithDetails(PointGoodsOrdVO pointgoodsordVO , List<PGOrdDetailsVO> list);

}
