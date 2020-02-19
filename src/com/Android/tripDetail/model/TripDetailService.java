package com.Android.tripDetail.model;

import java.util.List;
import java.util.Map;

public class TripDetailService {

	private TripDetailDAO_interface dao = null;
	
	public TripDetailService() {
		dao = new TripDetailJNDIDAO();
	}
	
	public Map<Integer, List<TripDetailVO>> getTripDetails(String tripNo, int tripDays){
		Map<Integer, List<TripDetailVO>> list = dao.getTripDetails(tripNo, tripDays);
		return list;
	}
	
	public void updateTripDetails(TripDetailVO tripDetailVO) {
		dao.insert(tripDetailVO);
	}
	
	public void deleteTripDetails(String tripNo, int day) {
		dao.delete(tripNo, day);
	}
	
	public List<TripDetailVO> getDayTripDetails(String tripNo, int day){
		List<TripDetailVO> dayTripList = null;
		dayTripList = dao.getTripDayDetail(day, tripNo);
		
		return dayTripList;
	}
	
	public List<String> getSpotByTrip(String tripNo){
		List<String> spotInTrip = null;
		spotInTrip = dao.getSpotByTripNo(tripNo);
		
		return spotInTrip;
	}
	
}
