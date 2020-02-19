package com.Android.spot.model;

import java.util.List;

import com.Android.tripDetail.model.TripDetailVO;

public class SpotListService {
	SpotListDAO_interface dao = null;
	
	public SpotListService() {
		dao = new SpotListJNDIDAO();
	}
	
	public List<SpotListVO> getSpots(List<TripDetailVO> tripDetailLists){
		List<SpotListVO> list = null;		
		list=dao.getSpotFromTrip(tripDetailLists);
		return list;
	}
	
	public List<SpotListVO> getSpotByCity(String cityNo){
		List<SpotListVO> list = null;		
		list=dao.getSpotByCityNo(cityNo);
		return list;
	}
	
	public List<SpotListVO> getAllSpot(){
		List<SpotListVO> list = null;		
		list=dao.getAllSpot();
		return list;
	}
	
	public byte[] getImage(String spotNo) {
		byte[] image = null;
		image = dao.getImage(spotNo);
		return image;
	}
	
	public SpotListVO getOneSpot(String spotNo) {
		SpotListVO spotListVO = null;
		
		spotListVO = dao.findByPrimaryKey(spotNo);
		
		return spotListVO;
	}
	
}
