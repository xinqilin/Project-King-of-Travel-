package com.trip.model;

import java.util.List;

public class TripService {

	private TripDAO_interface dao;

	public TripService() {
		dao = new TripDAO();
	}

	public TripVO addTrip(String memno,String cityno,String tripname,
			java.sql.Date tripstartday,java.sql.Date tripendday,Integer tripdays,java.sql.Date tripestdate,Integer bethebuyer,
			Integer tripstatus,Integer timeofviews,Integer kindoftrip,byte[] trippic) {

		TripVO tripVO = new TripVO();
		tripVO.setTripname(tripname);
		tripVO.setTripstartday(tripstartday);
		tripVO.setTripendday(tripendday);
		tripVO.setTripestdate(tripestdate);
		tripVO.setTripdays(tripdays);
		tripVO.setBethebuyer(bethebuyer);
		tripVO.setMemno(memno);
		tripVO.setCityno(cityno);
		tripVO.setTripstatus(tripstatus);
		tripVO.setTimeofviews(timeofviews);
		tripVO.setKindoftrip(kindoftrip);
		
		tripVO.setTrippic(trippic);
		dao.insert(tripVO);

		return tripVO;
	}

	public TripVO updateTrip(String tripname,
			java.sql.Date tripstartday,java.sql.Date tripendday,java.sql.Date tripestdate,Integer tripdays,Integer bethebuyer,
			String memno,String cityno,Integer tripstatus,Integer timeofviews,Integer kindoftrip,byte[] trippic,String tripno) {

		TripVO tripVO = new TripVO();
		tripVO.setTripname(tripname);
		tripVO.setTripstartday(tripstartday);
		tripVO.setTripendday(tripendday);
		tripVO.setTripestdate(tripestdate);
		tripVO.setTripdays(tripdays);
		tripVO.setBethebuyer(bethebuyer);
		tripVO.setMemno(memno);
		tripVO.setCityno(cityno);
		tripVO.setTripstatus(tripstatus);
		tripVO.setTimeofviews(timeofviews);
		tripVO.setKindoftrip(kindoftrip);
		
		tripVO.setTrippic(trippic);
		tripVO.setTripno(tripno);
		
		dao.update(tripVO);

		return tripVO;
	}

	public void deleteTrip(String tripno) {
		dao.delete(tripno);
	}
	public void acceptTrip(String tripno) {
		dao.accept(tripno);
	}

	public TripVO getlastOne() {
		return dao.lastTrip();
	}
	public TripVO getOneTrip(String tripno) {
		return dao.findByPrimaryKey(tripno);
	}

	public List<TripVO> getAll() {
		return dao.getAll();
	}
	public List<TripVO> getorderByViews(){
		return dao.orderByViews();
	}
    public List<TripVO> getorderByDate(){
    	return dao.orderByDate();
    }
    public List<TripVO> getorderByCity(){
    	return dao.orderByCity();
    }
    public List<TripVO> getorderByDays(){
    	return dao.orderByDays();
    }
}
