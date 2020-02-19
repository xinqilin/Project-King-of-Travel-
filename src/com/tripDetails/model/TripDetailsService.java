package com.tripDetails.model;

import java.sql.Time;
import java.util.List;

import com.tripDetails.model.TripDetailsDAO;
import com.tripDetails.model.TripDetailsDAO_interface;
import com.tripDetails.model.TripDetailsVO;

public class TripDetailsService {

	private TripDetailsDAO_interface dao;

	public TripDetailsService() {
		dao = new TripDetailsDAO();
	}

	public TripDetailsVO addTripDetails(String tripno,String spotno, java.sql.Time timeofarrive, java.sql.Time timeofleave,
			Integer stayhours, Double milestonextspots, Integer tripdayorder, Integer triporderby, String triptips) {

		TripDetailsVO tripDetailsVO1 = new TripDetailsVO();
		tripDetailsVO1.setTripno(tripno);
		tripDetailsVO1.setSpotno(spotno);
		tripDetailsVO1.setTimeofarrive(timeofarrive);
		tripDetailsVO1.setTimeofleave(timeofleave);
		tripDetailsVO1.setStayhours(stayhours);
		tripDetailsVO1.setMilestonextspots(milestonextspots);
		tripDetailsVO1.setTripdayorder(tripdayorder);
		tripDetailsVO1.setTriporderby(triporderby);
		tripDetailsVO1.setTriptips(triptips);
		dao.insert(tripDetailsVO1);

		return tripDetailsVO1;
	}

	public TripDetailsVO updateTripDetails(String tripno, String spotno, java.sql.Time timeofarrive, java.sql.Time timeofleave,
			Integer stayhours, Double milestonextspots, Integer tripdayorder, Integer triporderby, String triptips) {

		TripDetailsVO tripDetailsVO = new TripDetailsVO();
		tripDetailsVO.setTripno(tripno);
		tripDetailsVO.setSpotno(spotno);
		tripDetailsVO.setTimeofarrive(timeofarrive);
		tripDetailsVO.setTimeofleave(timeofleave);
		tripDetailsVO.setStayhours(stayhours);
		tripDetailsVO.setMilestonextspots(milestonextspots);
		tripDetailsVO.setTripdayorder(tripdayorder);
		tripDetailsVO.setTriporderby(triporderby);
		tripDetailsVO.setTriptips(triptips);
		dao.update(tripDetailsVO);

		return tripDetailsVO;
	}

	public void deleteTripDetails(String tripno,String spotno) {
		dao.delete(tripno,spotno);
	}

	public TripDetailsVO getOneTripDetails(String tripno,String spotno) {
		return dao.findByPrimaryKey(tripno,spotno);
	}

	public List<TripDetailsVO> getAll() {
		return dao.getAll();
	}
	public List<TripDetailsVO> getfindByTripno(String tripno){
		return dao.findByTripno(tripno);
	}
}
