package com.storeOrd.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.item.model.ItemVO;

public class StoreOrdService {

	private StoreOrdDAO_interface dao;

	public StoreOrdService() {
		dao = new StoreOrdDAO();
	}
	//從前台購物車新增訂單
	public StoreOrdVO addStoreOrd_from_cart(String memNo,Integer price,String address,Integer paymentMethod,LinkedHashMap<ItemVO, Integer> cart) {
		StoreOrdVO storeOrdVO = new StoreOrdVO();
		storeOrdVO.setMemNo(memNo);
		storeOrdVO.setPrice(price);
		storeOrdVO.setAddress(address);
		storeOrdVO.setPaymentMethod(paymentMethod);
		dao.insert_with_storeDetail(storeOrdVO,cart);
		return storeOrdVO;
	};
	//新增
	public StoreOrdVO addStoreOrd(String memNo,Integer price,String address) {
		StoreOrdVO storeOrdVO = new StoreOrdVO();
		storeOrdVO.setMemNo(memNo);
		storeOrdVO.setPrice(price);
		storeOrdVO.setAddress(address);
		dao.insert(storeOrdVO);
		return storeOrdVO;
	};
	//更新訂單
	public StoreOrdVO update(String ordNo, String memNo,Integer price,String address,Integer status,Integer paymentMethod) {
		StoreOrdVO storeOrdVO = new StoreOrdVO();
		storeOrdVO.setOrdNo(ordNo);
		storeOrdVO.setMemNo(memNo);
		storeOrdVO.setPrice(price);
		storeOrdVO.setAddress(address);
		storeOrdVO.setStatus(status);
		storeOrdVO.setPaymentMethod(paymentMethod);
		dao.update(storeOrdVO);
		return storeOrdVO;
	};
	//搜尋一筆訂單
	public StoreOrdVO findByPrimaryKey(String ordNo) {
		System.out.println("Service ordNo="+ordNo);
		return dao.findByPrimaryKey(ordNo);
	};
	//取得所有訂單
	public List<StoreOrdVO> getAll() {
		return dao.getAll();
	};
	//取得某個會員的所有訂單
	public List<StoreOrdVO> getAllByMemno(String memNo) {
		List<StoreOrdVO> OrdList=dao.getAllByMemno(memNo);
		return OrdList;
	};
	//客戶修改訂單狀態
		public StoreOrdVO change_ord_status(String ordNo) {
			StoreOrdVO storeOrdVO = new StoreOrdVO();
			storeOrdVO.setOrdNo(ordNo);
			dao.change_ord_status(storeOrdVO);
			return storeOrdVO;
		};
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
