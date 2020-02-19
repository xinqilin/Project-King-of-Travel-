package com.storeOrd.model;

import java.util.LinkedHashMap;
import java.util.List;

import com.item.model.ItemVO;
import com.storeOrdDetail.model.StoreOrdDetailVO;

public interface StoreOrdDAO_interface {
	public void insert(StoreOrdVO storeOrdVO);
	public void insert_with_storeDetail(StoreOrdVO storeOrdVO,LinkedHashMap<ItemVO, Integer> cart);
	
	
	
    public void update(StoreOrdVO storeOrdVO);
    public void change_ord_status(StoreOrdVO storeOrdVO);
    
    
//    public void delete(String storeOrdNo);
    
    
    
    public StoreOrdVO findByPrimaryKey(String ordNo);

    
    
    public List<StoreOrdVO> getAll();
    public List<StoreOrdVO> getAllByMemno(String memNo);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
