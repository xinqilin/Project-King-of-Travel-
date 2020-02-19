package com.storeOrdDetail.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface StoreOrdDetailDAO_interface {
	public void insert(StoreOrdDetailVO storeOrdDetailsVO); //後台新增訂單明細
	public void check_out(StoreOrdDetailVO storeOrdDetailsVO,Connection con); //新增訂單明細
    public List<StoreOrdDetailVO> findByPrimaryKey(String ordNo);//查詢單筆訂單明細
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
