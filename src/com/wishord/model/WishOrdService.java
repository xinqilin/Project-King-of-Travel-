package com.wishord.model;

import java.sql.Date;
import java.util.List;


public class WishOrdService {
	
	private WishOrdDAO_interface dao;
	
	public WishOrdService() {
		dao = new WishOrdDAO();
	}
	
	public WishOrdVO addWishOrd(String buyMemNo,String wishMemNo,Date dayOfEst,Integer wishOrdStatus,Integer wishOrdTotalPrice,Date wishSendDate,String wishOrdBuyNote,Date lastDate,String wishOrdSendAddr) {
		WishOrdVO wishOrdVO = new WishOrdVO();
		
		wishOrdVO.setBuyMemNo(buyMemNo);
		wishOrdVO.setWishMemNo(wishMemNo);
		wishOrdVO.setDayOfEst(dayOfEst);
		wishOrdVO.setWishOrdStatus(wishOrdStatus);
		wishOrdVO.setWishOrdTotalPrice(wishOrdTotalPrice);
		wishOrdVO.setWishSendDate(wishSendDate);
		wishOrdVO.setWishOrdBuyNote(wishOrdBuyNote);
		wishOrdVO.setLastDate(lastDate);
		wishOrdVO.setWishOrdSendAddr(wishOrdSendAddr);
		dao.insert(wishOrdVO);
		
		return wishOrdVO;
	}
	
	public WishOrdVO updateWishOrd(String wishOrdNo,String buyMemNo,String wishMemNo,Date dayOfEst,Integer wishOrdStatus,Integer wishOrdTotalPrice,Date wishSendDate,String wishOrdBuyNote,Date lastDate,String wishOrdSendAddr) {
		WishOrdVO wishOrdVO = new WishOrdVO();
		
		wishOrdVO.setWishOrdNo(wishOrdNo);
		wishOrdVO.setBuyMemNo(buyMemNo);
		wishOrdVO.setWishMemNo(wishMemNo);
		wishOrdVO.setDayOfEst(dayOfEst);
		wishOrdVO.setWishOrdStatus(wishOrdStatus);
		wishOrdVO.setWishOrdTotalPrice(wishOrdTotalPrice);
		wishOrdVO.setWishSendDate(wishSendDate);
		wishOrdVO.setWishOrdBuyNote(wishOrdBuyNote);
		wishOrdVO.setLastDate(lastDate);
		wishOrdVO.setWishOrdSendAddr(wishOrdSendAddr);
		dao.update(wishOrdVO);

		return wishOrdVO;
	}

	public void deleteWishOrd(String wishOrdNo) {
		dao.delete(wishOrdNo);
	}
	
	public WishOrdVO getOneWishOrd(String wishOrdNo) {
		return dao.findByPrimaryKey(wishOrdNo);
	}
	
	public List<WishOrdVO> getAll(){
		return dao.getAll();
	}
}
