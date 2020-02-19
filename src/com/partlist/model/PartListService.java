package com.partlist.model;

import java.util.List;

import com.pointgoods.model.PointGoodsVO;

public class PartListService {
	
	private PartListDAO_interface dao;
	
	public PartListService() {
		dao = new PartListDAO();
	}
	
	public PartListVO addPartList(String memno, String activityno, String oddteam, Integer putpoints, Integer getpoints, Double oddrate) {
		PartListVO partlistVO = new PartListVO();
		partlistVO.setMemno(memno);
		partlistVO.setActivityno(activityno);
		partlistVO.setOddteam(oddteam);
		partlistVO.setPutpoints(putpoints);
		partlistVO.setGetpoints(getpoints);
		partlistVO.setOddrate(oddrate);
		dao.insert(partlistVO);
		
		return partlistVO;
	}
	
	public PartListVO updatePartList(String memno, String activityno, Integer putpoints, Integer getpoints, Double oddrate) {
		PartListVO partlistVO = new PartListVO();
		partlistVO.setMemno(memno);
		partlistVO.setActivityno(activityno);
		partlistVO.setPutpoints(putpoints);
		partlistVO.setGetpoints(getpoints);
		partlistVO.setOddrate(oddrate);
		dao.update(partlistVO);
		
		return partlistVO;
	}
	public void deletePartList(String memno, String activityno) {
		dao.delete(memno, activityno);
	}
	
	public PartListVO getOnePartList(String memno, String activityno) {
		return dao.findByPrimaryKey(memno, activityno);
	}
	
	public List<PartListVO> getAll() {
		return dao.getAll();
	}
}
