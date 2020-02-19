package com.Android.spot.controller;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.Android.spot.model.*;
import com.Android.tripDetail.model.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import utility.ImageUtil;

import java.lang.reflect.Type;

public class SpotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
       
    public SpotServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		req.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		SpotListService spotSvc = new SpotListService();
		List<SpotListVO> list = null;
		
		if(action.equals("getTripSpots")) {
			//用來畫googleMap的資料
			Type listType = new TypeToken<ArrayList<TripDetailVO>>(){}.getType();
			List<TripDetailVO> tripDetailLists = gson.fromJson(jsonObject.get("tripDetailList").getAsString(), listType);
			
			list = spotSvc.getSpots(tripDetailLists);			
			writeText(res, gson.toJson(list));
		}else if(action.equals("getSpotList")) {
			//搜索景點用  分成關鍵字搜索、城市附近搜索兩種
			List<SpotListVO> spotList = null;
			String type = jsonObject.get("type").getAsString();
			String tripNo = jsonObject.get("tripNo").getAsString();
			if(type.equals("citySearch")) {
				String cityNo = jsonObject.get("cityNo").getAsString();
				spotList = spotSvc.getSpotByCity(cityNo);
			}else if(type.equals("keyWordSearch")) {
				Type listType = new TypeToken<String[]>(){}.getType();
				String[] keywords = gson.fromJson(jsonObject.get("keyWords").getAsString(),listType);
				spotList = spotSvc.getAllSpot();
				Stream spotListStream = spotList.stream();
				for(String keyword:keywords) {
					Predicate<SpotListVO> containSpot = 
							p -> p.toString().contains(keyword);
					spotListStream = spotListStream.filter(containSpot);
				}
				spotList = (ArrayList<SpotListVO>)spotListStream.collect(Collectors.toList());
			}	
			
			TripDetailService tripDetailSvc = new TripDetailService();
			List<String> spots = tripDetailSvc.getSpotByTrip(tripNo);
			Stream spotListStream = spotList.stream();
			
			for(String spotNo:spots) {
				Predicate<SpotListVO> containSpot = 
						p -> !p.getSpotNo().contains(spotNo);
				spotListStream = spotListStream.filter(containSpot);
			}
			spotList = (ArrayList<SpotListVO>)spotListStream.collect(Collectors.toList());
				
			writeText(res, gson.toJson(spotList));
		}else if(action.equals("getImage")) {
			OutputStream os = res.getOutputStream();
			String spotNo = jsonObject.get("spotNo").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = spotSvc.getImage(spotNo);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/png");
				res.setContentLength(image.length);
			}
			os.write(image);
		}else if(action.equals("getSpotVO")) {
			String spotNo = jsonObject.get("spotNo").getAsString();
			SpotListVO spotListVO = spotSvc.getOneSpot(spotNo);
			writeText(res, gson.toJson(spotListVO));
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
