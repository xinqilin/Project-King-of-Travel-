package com.Android.articleDetail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Android.articleDetail.model.ArticleDetailsService;
import com.Android.articleDetail.model.ArticleDetailsVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class ArticleDetailServlet extends HttpServlet {
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
		ArticleDetailsService articleDetailSvc = new ArticleDetailsService(); 
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		Map<Integer, List<ArticleDetailsVO>> list = null;
		
		if(action.equals("getAllArticleDetails")) {
			String articleNo   = jsonObject.get("articleNo").getAsString();
			int tripDays = Integer.parseInt(jsonObject.get("tripDays").getAsString());
			list = articleDetailSvc.getArticleDetails(articleNo, tripDays);
			writeText(res, gson.toJson(list));
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
