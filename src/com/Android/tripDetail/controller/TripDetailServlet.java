package com.Android.tripDetail.controller;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.Android.spot.model.SpotListVO;
import com.Android.tripDetail.model.TripDetailService;
import com.Android.tripDetail.model.TripDetailVO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class TripDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		System.out.println("input: " + jsonIn);
		TripDetailService tripDetailSvc = new TripDetailService(); 
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		Map<Integer, List<TripDetailVO>> list = null;
		
		if(action.equals("getAllTripDetails")) {
			String tripNo   = jsonObject.get("tripNo").getAsString();
			int tripDays = Integer.parseInt(jsonObject.get("tripDays").getAsString());
			list = tripDetailSvc.getTripDetails(tripNo, tripDays);
			writeText(res, gson.toJson(list));
		}if(action.equals("updateDetails")) {
			int day = Integer.parseInt(jsonObject.get("day").getAsString());//行程第幾天
			String tripNo = jsonObject.get("tripNo").getAsString();
			tripDetailSvc.deleteTripDetails(tripNo, day);
			
			Type listType = new TypeToken<ArrayList<TripDetailVO>>(){}.getType();
			List<TripDetailVO> tripDetailList = gson.fromJson(jsonObject.get("tripDetailList").getAsString(), listType);		
			runUpdateDetail(tripDetailSvc, tripDetailList, day);
			Boolean isUpdate = true;
			writeText(res, gson.toJson(isUpdate));
		}if(action.equals("addTripDetail")) {
			int day = Integer.parseInt(jsonObject.get("day").getAsString());
			String tripNo = jsonObject.get("tripNo").getAsString();
			SpotListVO spotListVO= gson.fromJson(jsonObject.get("spotListVO").getAsString(), SpotListVO.class);
			TripDetailVO tripDetail = new TripDetailVO();
			tripDetail.setTripno(tripNo);
			tripDetail.setSpotno(spotListVO.getSpotNo());
			
			List<TripDetailVO> detailList = tripDetailSvc.getDayTripDetails(tripNo, day);
			detailList.add(tripDetail);
			
			tripDetailSvc.deleteTripDetails(tripNo, day);
			runUpdateDetail(tripDetailSvc, detailList, day);
			
			Boolean isUpdate = true;
			writeText(res, gson.toJson(isUpdate));
		}
	}
	
	private void runUpdateDetail(TripDetailService tripDetailSvc, List<TripDetailVO> tripDetailList,int day) {
		int triporderby = 1; //設計行程順序起始為1
		for(TripDetailVO tripDetailVO: tripDetailList) {
			tripDetailVO.setTripdayorder(day);
			tripDetailVO.setTriporderby(triporderby);
			tripDetailSvc.updateTripDetails(tripDetailVO);
			triporderby++;
		}
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}
	
}
