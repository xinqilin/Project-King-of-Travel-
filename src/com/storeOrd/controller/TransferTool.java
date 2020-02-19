package com.storeOrd.controller;

import java.util.HashMap;
import java.util.Map;

public class TransferTool{
	
	public static String getPaymentMethod(Integer paymentMethod){
		Map<Integer, String> map = new HashMap<>();
		 map.put(1,"信用卡");
	     map.put(2,"貨到付款");
	     return map.get(paymentMethod);
	}

	public static String getStatus(Integer statusNo){
		Map<Integer, String> map = new HashMap<>();
		 map.put(0,"取消訂單");
	     map.put(1,"收到訂單");
	     map.put(2,"出貨");
	     map.put(3,"已送達");
	     return map.get(statusNo);
	}

}
