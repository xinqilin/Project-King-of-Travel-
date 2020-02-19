package com.Android.tripDetail.model;

import java.util.*;


public interface TripDetailDAO_interface {
          public void insert(TripDetailVO tripDetailsVO);
          
          public void delete(String tripNo, Integer day);
//          public TripDetailsVO findByPrimaryKey(String tripno,String spotno);
//          public List<TripDetailsVO> getAll();
//          public List<TripDetailsVO> findByTripno(String tripno);

	public Map<Integer,List<TripDetailVO>> getTripDetails(String tripNo, int tripDays);
	public boolean isSpotExist   (String tripNo, String spotNo);
	public List<TripDetailVO> getTripDayDetail(int day, String tripNo);
	public List<String> getSpotByTripNo(String tripNo); 
}
