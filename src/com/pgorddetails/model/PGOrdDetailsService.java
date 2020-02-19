package com.pgorddetails.model;

import java.util.List;

public class PGOrdDetailsService {
	
	private PGOrdDetailsDAO_interface dao;
	
	public PGOrdDetailsService() {
		dao = new PGOrdDetailsDAO();
	}
	
	public PGOrdDetailsVO addPGOrdDetails(String pointgoodordno, String pointgoodsno, Integer pointgoodsquantity, Integer goodspoint) {
		PGOrdDetailsVO pgorddetailsVO = new PGOrdDetailsVO();
		pgorddetailsVO.setPointgoodsordno(pointgoodordno);
		pgorddetailsVO.setPointgoodsno(pointgoodsno);
		pgorddetailsVO.setPointgoodsquantity(pointgoodsquantity);
		pgorddetailsVO.setGoodspoint(goodspoint);
		dao.insert(pgorddetailsVO);
		
		return pgorddetailsVO;
	}
	
	public PGOrdDetailsVO updatePGOrdDetails(String pointgoodordno, String pointgoodsno, Integer pointgoodsquantity, Integer goodspoint) {
		PGOrdDetailsVO pgorddetailsVO = new PGOrdDetailsVO();
		pgorddetailsVO.setPointgoodsordno(pointgoodordno);
		pgorddetailsVO.setPointgoodsno(pointgoodsno);
		pgorddetailsVO.setPointgoodsquantity(pointgoodsquantity);
		pgorddetailsVO.setGoodspoint(goodspoint);
		dao.update(pgorddetailsVO);
		
		return pgorddetailsVO; 
	}
	public void deletePGOrdDetails(String pointgoodordno, String pointgoodsno) {
		dao.delete(pointgoodordno, pointgoodsno);
	}
	
	public PGOrdDetailsVO getOnePGOrdDetails(String pointgoodordno, String pointgoodsno) {
		return dao.findByPrimaryKey(pointgoodordno, pointgoodsno);
	}
	
	public List<PGOrdDetailsVO> getAll() {
		return dao.getAll();
	}
}
