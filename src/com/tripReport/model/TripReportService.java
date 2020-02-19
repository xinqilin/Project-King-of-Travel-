package com.tripReport.model;

import java.util.List;

public class TripReportService {

	private TripReportDAO_interface dao;

	public TripReportService() {
		dao = new TripReportDAO();
	}

	public TripReportVO addTripReport(String tripno, String memno, String reason, Integer tripstatus) {

		TripReportVO tripReportVO = new TripReportVO();
		tripReportVO.setTripno(tripno);
		tripReportVO.setMemno(memno);
		tripReportVO.setReason(reason);
		tripReportVO.setTripstatus(tripstatus);
		dao.insert(tripReportVO);

		return tripReportVO;
	}

	public TripReportVO updateTripReport(String tripno, String memno, String reason, Integer tripstatus) {

		TripReportVO tripReportVO = new TripReportVO();
		tripReportVO.setTripno(tripno);
		tripReportVO.setMemno(memno);
		tripReportVO.setReason(reason);
		tripReportVO.setTripstatus(tripstatus);
		dao.update(tripReportVO);

		return tripReportVO;
	}

	public void deleteTripReport(TripReportVO tripReport) {
		dao.delete(tripReport);
	}

	public TripReportVO getOneTripDetails(String tripno, String spotno) {
		return dao.findByPrimaryKey(tripno, spotno);
	}

	public List<TripReportVO> getAll() {
		return dao.getAll();
	}
}
