package com.partlist.model;

import java.util.List;

public interface PartListDAO_interface {
	
	public void insert(PartListVO partlistVO);
	public void update(PartListVO partlistVO);
	public void delete(String memno, String activityno);
	public PartListVO findByPrimaryKey(String memno, String activityno);
	public List<PartListVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<PointGoodsVO> getAllByAny(Map<String, String[]> map)
}
