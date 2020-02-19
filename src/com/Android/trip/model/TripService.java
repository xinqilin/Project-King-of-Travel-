package com.Android.trip.model;

import java.util.List;
import java.util.Map;

import com.Android.tripDetail.model.TripDetailService;
import com.Android.tripDetail.model.TripDetailVO;

public class TripService {
	TripDAO_interface dao = null;
	
	public TripService() {
		dao = new TripJNDIDAO();
	}
	
	public List<TripVO> getMemAllTrips(String email){
		List<TripVO> list = null;
		list = dao.getMemAllTrips(email);
		return list;
	} 
	
	public List<TripVO> getHotTrips(){
		List<TripVO> list = null;
		list = dao.getHotTrips();
		return list;
	}
	
	public TripVO getOneTrip(String tripNo) {
		TripVO tripVO = null;
		tripVO = dao.getOneTrip(tripNo);
		return tripVO;
	}
	
	public Boolean copyTrip(String tripNo, String memNo) {
		TripVO tripVO = dao.getOneTrip(tripNo);
		tripVO.setMemNo(memNo);
		tripVO = insertNewTrip(tripVO);
		TripDetailService tripDetailSvc = new TripDetailService();
		String newTripNo = tripVO.getTripNo();
		int tripDays = tripVO.getTripDays();

		Map<Integer,List<TripDetailVO>> tripDetailList = tripDetailSvc.getTripDetails(tripNo, tripDays);
		
		for(int day = 1; day<=tripDays;day++) {
			List<TripDetailVO> detailList =tripDetailList.get(day);
			tripDetailSvc.deleteTripDetails(newTripNo, day);
			runUpdateDetail(tripDetailSvc, detailList, day, newTripNo);
		}
		return true;
	}
	
	public TripVO insertNewTrip(TripVO tripVO) {
		tripVO = dao.insertTrip(tripVO);
		return tripVO;
	}
	
	public boolean deleteTrip(String tripNo) {
		boolean result = false;
		TripVO tripVO = dao.getOneTrip(tripNo);
		int tripDays = tripVO.getTripDays();
		TripDetailService tripDetailSvc = new TripDetailService();
		
		for(int day = 1; day<=tripDays; day++) {
			tripDetailSvc.deleteTripDetails(tripNo, day);
		}
		
		tripVO.setTripStatus(0);
		result =dao.updateTrip(tripVO);

		return result;
	}
	
	public boolean updateTrip(TripVO tripVO) {
		boolean result = false;
		result = dao.updateTrip(tripVO);
		return result;
	}
	
	
	private void runUpdateDetail(TripDetailService tripDetailSvc, List<TripDetailVO> tripDetailList, int day, String newTripNo) {
		int triporderby = 1; //設計行程順序起始為1
		for(TripDetailVO tripDetailVO: tripDetailList) {
			tripDetailVO.setTripno(newTripNo);
			tripDetailVO.setTripdayorder(day);
			tripDetailVO.setTriporderby(triporderby++);
			tripDetailSvc.updateTripDetails(tripDetailVO);
		}
	}
	
}
