package com.pointgoods.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Status {
	
	public static String getStatus(int status) {
		if(status == 1) {
			return "上架";
		} else {
			return "下架";
		}
	}
	
	public static String getOrderStatus(int orderstatus) {
		String orderStatus = null;
		switch(orderstatus) {
		case 0:
			orderStatus = "已結賬未出貨";
			break;
		case 1:
			orderStatus = "已結賬已出貨";
			break;
		case 2:
			orderStatus = "已取消";
			break;
		}
		return orderStatus;
	}
	
	public static String getNoZeroDate(Timestamp ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tss = sdf.format(ts);
		return tss;
	}

}
