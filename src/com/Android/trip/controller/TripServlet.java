package com.Android.trip.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.Android.trip.model.*;
import com.google.gson.*;

public class TripServlet extends HttpServlet {
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
		TripService tripSvc = new TripService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		List<TripVO> list = null;
		
		if(action.equals("getMemAllTrips")) {
			String email = jsonObject.get("email").getAsString();
			list = tripSvc.getMemAllTrips(email);
			writeText(res, gson.toJson(list));
		}else if(action.equals("getHotTrips")){
			list = tripSvc.getHotTrips();
			writeText(res, gson.toJson(list));
		}else if(action.equals("copyToMyTrip")) {
			String tripNo  = jsonObject.get("tripNo").getAsString();
			String memNo   = jsonObject.get("memNo").getAsString();
			boolean result = tripSvc.copyTrip(tripNo, memNo);
					
			writeText(res, gson.toJson(result));
		}else if(action.equals("addTrip")) {
			TripVO tripVO  = gson.fromJson(jsonObject.get("tripVO").getAsString(), TripVO.class);
			tripVO = tripSvc.insertNewTrip(tripVO);
			
			writeText(res, gson.toJson(true));
		}else if(action.equals("modifyTrip")) {
			TripVO tripVO  = gson.fromJson(jsonObject.get("tripVO").getAsString(), TripVO.class);
			tripVO = tripSvc.insertNewTrip(tripVO);
			
			writeText(res, gson.toJson(true));
		}else if(action.equals("deleteTrip")) {
			String tripNo  = jsonObject.get("tripNo").getAsString();
			Boolean result = tripSvc.deleteTrip(tripNo);
			writeText(res, gson.toJson(result));
		}else if(action.equals("getOneTrip")) {
			String tripNo  = jsonObject.get("tripNo").getAsString();
			TripVO tripVO  = tripSvc.getOneTrip(tripNo);
			writeText(res, gson.toJson(tripVO));
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
