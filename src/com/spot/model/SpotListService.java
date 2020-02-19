package com.spot.model;

import java.util.List;

public class SpotListService {
	
	private SpotListDAO dao;
	
	public SpotListService() {
		dao = new SpotListDAO();
	}
	
	public SpotListVO addSpot(String spotName, String cityNo, String location,
							Integer spotType, byte[] spotPhoto, Integer spotStatus,
							String tel, Double spotLati, Double spotLong, String spotDetail) {
		
		SpotListVO spotListVO = new SpotListVO();
		
		spotListVO.setSpotName(spotName);
		spotListVO.setCityNo(cityNo);
		spotListVO.setLocation(location);
		spotListVO.setSpotType(spotType);
		spotListVO.setSpotPhoto(spotPhoto);
		spotListVO.setSpotStatus(spotStatus);
		spotListVO.setTel(tel);
		spotListVO.setSpotLati(spotLati);
		spotListVO.setSpotLong(spotLong);
		spotListVO.setSpotDetail(spotDetail);
		
		dao.insert(spotListVO);
		
		return spotListVO;
	}
	
	public SpotListVO updateSpot(String spotNo, String spotName, String cityNo, String location,
								Integer spotType, byte[] spotPhoto, Integer spotStatus,
								String tel, Double spotLati, Double spotLong, String spotDetail) {
		
		SpotListVO spotListVO = new SpotListVO();
		
		spotListVO.setSpotNo(spotNo);
		spotListVO.setSpotName(spotName);
		spotListVO.setCityNo(cityNo);
		spotListVO.setLocation(location);
		spotListVO.setSpotType(spotType);
		spotListVO.setSpotPhoto(spotPhoto);
		spotListVO.setSpotStatus(spotStatus);
		spotListVO.setTel(tel);
		spotListVO.setSpotLati(spotLati);
		spotListVO.setSpotLong(spotLong);
		spotListVO.setSpotDetail(spotDetail);
		
		dao.update(spotListVO);
		return spotListVO;
	}
	
	public void deleteSpot(String spotNo) {
		dao.delete(spotNo);
	}
	
	public SpotListVO getOneSpot(String spotNo) {
		return dao.findByPrimaryKey(spotNo);
	}
	
	public List<SpotListVO> getAll(){
		return dao.getAll();
	}
	public List<SpotListVO> getAllNoPic(){
		return dao.getAllNoPic();
	}
}
