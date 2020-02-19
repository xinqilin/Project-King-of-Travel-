package com.Android.mem.controller;

import java.io.*;
import java.text.ParseException;



import javax.servlet.*;

import javax.servlet.http.*;

import com.Android.mem.model.*;
import com.google.gson.*;


public class MemServlet extends HttpServlet {
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
		MemService memSvc = new MemService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		MemVO memVO = null;
		
		if(action.equals("isMember")) {
			String email = jsonObject.get("email").getAsString();
			String password = jsonObject.get("password").getAsString();
			int isMember = memSvc.isMember(email, password);
			writeText(res, String.valueOf(isMember));
		}else if(action.equals("getMemVO")){
			String email = jsonObject.get("email").getAsString();
			memVO = memSvc.getMemByEmail(email);
			writeText(res, gson.toJson(memVO));
		}else if(action.equals("updateMemVO")){
			String email        = jsonObject.get("email").getAsString();
			String introduction = jsonObject.get("introduction").getAsString();
			String nickName     = jsonObject.get("nickName").getAsString();
			memSvc.updateMem(email, nickName, introduction);
			writeText(res, "updated");
		} if(action .equals("insert")) {
			int result = 0;
			String email           = jsonObject.get("email").getAsString();
			String memPassword     = jsonObject.get("password").getAsString();
			String idNo            = jsonObject.get("id").getAsString();
			String memName         = jsonObject.get("name").getAsString();
			String birthDayString  = jsonObject.get("birthDay").getAsString().toString();
			String address         = jsonObject.get("addr").getAsString().toString();
			String phone           = jsonObject.get("phone").getAsString().toString();
			String nickName        = jsonObject.get("nickName").getAsString();
			String introduction    = jsonObject.get("introduction").getAsString();
			
			memVO = memSvc.getMemByEmail(email);			
			if(memVO!=null) {
				result+=-1;
			}
			if(memSvc.isIdRegistered(idNo)) {
				result+=-2;
			}

			if(result == 0) {
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date birDay = null;
				try {
					birDay = new java.sql.Date(df.parse(birthDayString).getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				memSvc.addMem(memName, email, memPassword, nickName, idNo, birDay, introduction, address, phone);
			}
		
			writeText(res, String.valueOf(result));
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
