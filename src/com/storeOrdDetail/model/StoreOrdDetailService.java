package com.storeOrdDetail.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.item.model.ItemVO;

public class StoreOrdDetailService {

	private StoreOrdDetailDAO_interface dao;

	public StoreOrdDetailService() {
		dao = new StoreOrdDetailDAO();
	}

	
	public List<StoreOrdDetailVO> findByPrimaryKey(String ordNo){
		return dao.findByPrimaryKey(ordNo);
	}
}
