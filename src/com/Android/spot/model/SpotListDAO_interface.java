package com.Android.spot.model;

import java.util.List;

import com.Android.tripDetail.model.TripDetailVO;





public interface SpotListDAO_interface {
	public List<SpotListVO> getSpotFromTrip(List<TripDetailVO> TripDetailList);
	public SpotListVO findByPrimaryKey(String spotNo);
	public List<SpotListVO> getSpotByCityNo(String cityNo);
	public List<SpotListVO> getAllSpot();
	public byte[] getImage(String spotNo);
}
