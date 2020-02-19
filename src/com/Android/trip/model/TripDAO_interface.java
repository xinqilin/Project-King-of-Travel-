package com.Android.trip.model;

import java.util.List;

public interface TripDAO_interface {
	
	public TripVO insertTrip(TripVO tripVO);
	public Boolean updateTrip(TripVO tripVO);
	public boolean deleteTrip(String tripNo);
	public TripVO getOneTrip(String tripNo);
	public List<TripVO> getMemAllTrips(String email);
	public List<TripVO> getHotTrips();
}
