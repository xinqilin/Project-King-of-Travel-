package com.Android.city.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Android.city.model.CityService;
import com.Android.city.model.CityVO;
import com.Android.spot.model.SpotListVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public CityServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
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
		CityService citySvc = new CityService();
		List<CityVO> cityVOlist = null;

		if (action.equals("getCityList")) {
			String cityNo = jsonObject.get("cityNo").getAsString();
			cityVOlist = citySvc.get_ClosedCities(cityNo);
			writeText(res, gson.toJson(cityVOlist));
		} else if (action.equals("getCityListByCountryNo")) {
			String countryNo = jsonObject.get("countryNo").getAsString();
			cityVOlist = citySvc.getAll();
			Stream cityListStream = cityVOlist.stream();

			Predicate<CityVO> inCountry = p -> p.getCountryNo().equals(countryNo);
			cityVOlist = (ArrayList<CityVO>) cityListStream.filter(inCountry).collect(Collectors.toList());
			
			writeText(res, gson.toJson(cityVOlist));
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
