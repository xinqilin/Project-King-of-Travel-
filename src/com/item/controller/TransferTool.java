package com.item.controller;

import java.util.HashMap;
import java.util.Map;

public class TransferTool{
	
	public static String getClass(Integer classNo){
		Map<Integer, String> map = new HashMap<>();
		 map.put(1, "旅用收納");
	     map.put(2, "旅用舒眠紓壓");
	     map.put(3, "貼身用品");
	     map.put(4, "SIM卡、WIFI機");
	     map.put(5, "3C商品");
	     map.put(6, "其他");
	     return map.get(classNo);
	}
	//0: 旅用行李周邊1:旅用收納2:旅用舒眠紓壓3:貼身用品4:旅用3C周邊5:其他
	public static String getStatus(Integer statusNo){
		Map<Integer, String> map = new HashMap<>();
		 map.put(0,"下架中");
	     map.put(1,"上架中");
	     return map.get(statusNo);
	}

}
