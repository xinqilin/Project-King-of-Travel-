package com.pointgoods.model;

import java.util.List;

public interface PointGoodsDAO_interface {
	
	public void insert (PointGoodsVO pointgoodsVO);
	public void update(PointGoodsVO pointgoodsVO);
	public void delete(String pointgoodsno);
	public PointGoodsVO findByPrimaryKey(String pointgoodsno);
	public List<PointGoodsVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<PointGoodsVO> getAllByAny(Map<String, String[]> map)
}
