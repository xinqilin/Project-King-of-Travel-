package com.item.model;

import java.sql.Connection;
import java.util.List;



public interface ItemDAO_interface {
	public void insert(ItemVO itemVO);
    public void update(ItemVO itemVO);
    public ItemVO findByPrimaryKey(String itemNo);
    public List<ItemVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    public void Check_out(String itemNo, int cart_amount, Connection con);
}
