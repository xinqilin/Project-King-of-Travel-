package com.activity.model;

import java.util.List;

public interface ActivityDAO_interface {
	
	public void insert (ActivityVO activityVO);
	public void update(ActivityVO activityVO);
	public void delete(String activityno);
	public ActivityVO findByPrimaryKey(String activityno);
	public List<ActivityVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<PointGoodsOrdVO> getAllByAny(Map<String, String[]> map ) 

}
