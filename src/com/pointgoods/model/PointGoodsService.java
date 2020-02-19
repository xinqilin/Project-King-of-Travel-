package com.pointgoods.model;

import java.util.List;

public class PointGoodsService {
	
	private PointGoodsDAO_interface dao;
	
	public PointGoodsService() {
		dao = new PointGoodsDAO();
	}
	
	public PointGoodsVO addPointGoods(String pointgoodsname, Integer pointgoodsquantity, Integer needpoints, String pointgoodsdesc, byte[] pointgoodspic,
			Integer pointgoodsstatus) {
		PointGoodsVO pointgoodsVO = new PointGoodsVO();
		pointgoodsVO.setPointgoodsname(pointgoodsname);
		pointgoodsVO.setPointgoodsquantity(pointgoodsquantity);
		pointgoodsVO.setNeedpoints(needpoints);
		pointgoodsVO.setPointgoodsdesc(pointgoodsdesc);
		pointgoodsVO.setPointgoodspic(pointgoodspic);
		pointgoodsVO.setPointgoodsstatus(pointgoodsstatus);
		dao.insert(pointgoodsVO);
		
		return pointgoodsVO;
	}
	
	public PointGoodsVO updatePointGoods(String pointgoodsno, String pointgoodsname, Integer pointgoodsquantity, Integer needpoints, String pointgoodsdesc, byte[] pointgoodspic,
			Integer pointgoodsstatus) {
		
		PointGoodsVO pointgoodsVO = new PointGoodsVO();
		pointgoodsVO.setPointgoodsno(pointgoodsno);
		pointgoodsVO.setPointgoodsname(pointgoodsname);
		pointgoodsVO.setPointgoodsquantity(pointgoodsquantity);
		pointgoodsVO.setNeedpoints(needpoints);
		pointgoodsVO.setPointgoodsdesc(pointgoodsdesc);
		pointgoodsVO.setPointgoodspic(pointgoodspic);
		pointgoodsVO.setPointgoodsstatus(pointgoodsstatus);
		dao.update(pointgoodsVO);
		
		return pointgoodsVO;
	}
	
	//商品不應該能被刪除
	public void deletePointGoods(String pointgoodsno) {
		dao.delete(pointgoodsno);
	}
	
	public PointGoodsVO getOnePointGoods(String pointgoodsno) {
		return dao.findByPrimaryKey(pointgoodsno);
	}
	
	public List<PointGoodsVO> getAll() {
		return dao.getAll();
	}
}
