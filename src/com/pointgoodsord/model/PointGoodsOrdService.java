package com.pointgoodsord.model;

import java.sql.Timestamp;
import java.util.List;

import com.pgorddetails.model.PGOrdDetailsVO;

public class PointGoodsOrdService {
	
	private PointGoodsOrdDAO_interface dao;
	
	public PointGoodsOrdService() {
		dao = new PointGoodsOrdDAO();
	}
	
	public PointGoodsOrdVO addPointGoodsOrd(String memno, String receiver, String phone, String address, Timestamp orderdate, Integer ordstatus, Integer orderpoint) {
		PointGoodsOrdVO pointgoodsordVO = new PointGoodsOrdVO();
		pointgoodsordVO.setMemno(memno);
		pointgoodsordVO.setReceiver(receiver);
		pointgoodsordVO.setPhone(phone);
		pointgoodsordVO.setAddress(address);
		pointgoodsordVO.setOrderdate(orderdate);
		pointgoodsordVO.setOrderstatus(ordstatus);
		pointgoodsordVO.setOrderpoint(orderpoint);
		dao.insert(pointgoodsordVO);
		
		return pointgoodsordVO;
	}
	
	public PointGoodsOrdVO updatePointGoodsOrd(String pointgoodsordno, String memno, String receiver, String phone, String address, Integer orderstatus, Integer orderpoint) {
		PointGoodsOrdVO pointgoodsordVO = new PointGoodsOrdVO();
		pointgoodsordVO.setPointgoodsordno(pointgoodsordno);
		pointgoodsordVO.setMemno(memno);
		pointgoodsordVO.setReceiver(receiver);
		pointgoodsordVO.setPhone(phone);
		pointgoodsordVO.setAddress(address);
		pointgoodsordVO.setOrderstatus(orderstatus);
		pointgoodsordVO.setOrderpoint(orderpoint);
		dao.update(pointgoodsordVO);
		
		return pointgoodsordVO;
	}
	//訂單不應該能被刪除
	public void deletePointGoodsOrd(String pointgoodsordno) {
		dao.delete(pointgoodsordno);
	}
	
	public PointGoodsOrdVO getOnePointGoodsOrd(String pointgoodsordno) {
		return dao.findByPrimaryKey(pointgoodsordno);
	}
	
	public List<PointGoodsOrdVO> getAll() {
		return dao.getAll();
	}
	
	public PointGoodsOrdVO addWithDetails(String memno, String receiver, String phone, String address, Timestamp orderdate, Integer ordstatus, Integer orderpoint, List<PGOrdDetailsVO> list) {
		PointGoodsOrdVO pointgoodsordVO = new PointGoodsOrdVO();
		pointgoodsordVO.setMemno(memno);
		pointgoodsordVO.setReceiver(receiver);
		pointgoodsordVO.setPhone(phone);
		pointgoodsordVO.setAddress(address);
		pointgoodsordVO.setOrderdate(orderdate);
		pointgoodsordVO.setOrderstatus(ordstatus);
		pointgoodsordVO.setOrderpoint(orderpoint);
		dao.insertWithDetails(pointgoodsordVO, list);
		
		return pointgoodsordVO;
	}
}
