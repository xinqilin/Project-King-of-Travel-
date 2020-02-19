package com.item.model;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class ItemService {

	private ItemDAO_interface dao;

	public ItemService() {
		dao = new ItemDAO();
	}
	
	public ItemVO addItem(String itemName,Integer price,Integer amount,String itemDetail,Integer itemClass,byte[] picture) {
		ItemVO itemVO = new ItemVO();
		itemVO.setItemName(itemName);
		itemVO.setPrice(price);
		itemVO.setAmount(amount);
		itemVO.setItemDetail(itemDetail);
		itemVO.setItemClass(itemClass);
		itemVO.setPicture(picture);
		dao.insert(itemVO);
		return itemVO;
	}
	
	public ItemVO updateItem(String itemNo,String itemName,Integer price,Integer amount,String itemDetail,Integer itemClass,Integer status,byte[] picture) {

		ItemVO itemVO = new ItemVO();
		itemVO.setItemNo(itemNo);
		itemVO.setItemName(itemName);
		itemVO.setPrice(price);
		itemVO.setAmount(amount);
		itemVO.setItemDetail(itemDetail);
		itemVO.setItemClass(itemClass);
		itemVO.setStatus(status);
		itemVO.setPicture(picture);
		dao.update(itemVO);
 
		return itemVO;
	}
	
	public ItemVO getOneItem(String itemNo) {
		return dao.findByPrimaryKey(itemNo);
	}

	public List<ItemVO> getAll() {
		return dao.getAll();
	}
	public List<ItemVO> showStore() {
		List<ItemVO> itemlist =dao.getAll();
		List<ItemVO> result = itemlist.stream()
			.filter(e -> e.getStatus()==1)
		.collect(Collectors.toList());
		return result;

	}
	public List<ItemVO> showStore_byclass(int itemClass) {
		List<ItemVO> itemlist =dao.getAll();
		List<ItemVO> result = itemlist.stream()
			.filter(e -> e.getStatus()==1)
			.filter(e -> e.getItemClass()==itemClass)
		.collect(Collectors.toList());
		return result;

	}
	
}
