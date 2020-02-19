package com.f_item.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.storeOrdDetail.model.StoreOrdDetailVO;

public interface F_ItemDAO_interface {
	public void add(String memNo,String itemNo);
//    public void update(F_ItemVO itemVO);
    public void del(String memNo,String itemNo);
    public HashSet<String> findByKey(String memNo);
//    public List<F_ItemVO> getAll_By_MemNO();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
