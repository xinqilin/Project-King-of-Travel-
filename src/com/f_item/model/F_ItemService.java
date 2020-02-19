package com.f_item.model;

import java.util.HashSet;

public class F_ItemService {

	private F_ItemDAO_interface dao;

	public F_ItemService() {
		dao = new F_ItemDAO();
	}
	public HashSet<String> findByPrimaryKey(String memNo){
		return dao.findByKey(memNo);
	}
	
	public void add(String memNo,String itemNo) {
	dao.add(memNo, itemNo);
	}
	
	public void del(String memNo,String itemNo) {
	dao.del(memNo, itemNo);
	}
}
