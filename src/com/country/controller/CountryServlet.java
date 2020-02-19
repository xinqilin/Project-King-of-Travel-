package com.country.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.country.model.CountryService;
import com.country.model.CountryVO;



public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String countryName = req.getParameter("countryName");
			
			
			/*************1. 檢查 request 資料 ***************/
			try {
				if(countryName == null || countryName.trim().isEmpty()) {
					errorMsgs.put("countryName","國家名字欄位不得為空");
				}
				
				if(!errorMsgs.isEmpty()) {
					//因為資料錯誤，將訊息彈回select_page.jsp呈現在輸入資料上方
					RequestDispatcher failureView= req.getRequestDispatcher("/back-end/country/addCountry.jsp");
					failureView.forward(req, res);
					return;
				}
				
								
			CountryVO country=new CountryVO();
			country.setCountryName(countryName);
				
			/*************2. 建造  Service 輸入資料 ***************/
			CountryService countrySvc = new CountryService();
			country = countrySvc.insertCountry(countryName);
				
			/*************3. 資料更新完成 (準備轉交)***************/
			String url = "/back-end/country/listAllCountry.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交給/back-end/listAllCountry.jsp
				successView.forward(req, res);
			}catch(Exception e) { //出現不明 error
				errorMsgs.put("finally","資料格式有誤"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/country/countrySelect.jsp");//出現錯誤，將資訊回傳給 /select_page.jsp
				failureView.forward(req, res);
			}

		} else if ("delete".equals(action)) {
			// delete
//			String CountryNo="CRY0005";
//			dao.delete(CountryNo);
			
		} else if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String countryNo = req.getParameter("countryNo");
			/*************2. 製作 service 並且查詢   ***************/
				CountryService countrySvc= new CountryService();
				CountryVO country= countrySvc.getOne(countryNo);
			/*************3. 傳回 PAGE ***************/
				req.setAttribute("country", country); // 資料庫取出的 countryVO 物件,存入req
				String url = "/back-end/country/updateCountry.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交給/back-end/listAllCountry.jsp
				successView.forward(req, res);	

			}catch(Exception e) {
				errorMsgs.add("資料格式有誤"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/country/countrySelect.jsp");//出現錯誤，將資訊回傳給 /select_page.jsp
				failureView.forward(req, res);
			}				

		}else if ("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("errorMsgs", errorMsgs);
			String countryNo   = req.getParameter("countryNo");
			String countryName = req.getParameter("countryName");
			
	
			/*************1. 檢查 request 資料 ***************/
			try {
				if(countryName == null || countryName.trim().isEmpty()) {
					errorMsgs.put("countryName","國家名字欄位不得為空");
				}
				
				if(!errorMsgs.isEmpty()) {
					//因為資料錯誤，將訊息彈回select_page.jsp呈現在輸入資料上方
					RequestDispatcher failureView= req.getRequestDispatcher("/back-end/country/addCountry.jsp");
					failureView.forward(req, res);
					return;
				}
				
								
			CountryVO country=new CountryVO();
			country.setCountryNo(countryNo);
			country.setCountryName(countryName);
			
			/*************2. 建造  Service 輸入資料 ***************/
			CountryService countrySvc = new CountryService();
			country = countrySvc.updateCountry(countryNo, countryName);
			req.setAttribute("country", country);	
			/*************3. 資料更新完成 (準備轉交)***************/
			String url = "/back-end/country/listAllCountry.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交給/back-end/listAllCountry.jsp
				successView.forward(req, res);
			}catch(Exception e) { //出現不明 error
				errorMsgs.put("finally","資料格式有誤"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/country/countrySelect.jsp");//出現錯誤，將資訊回傳給 /select_page.jsp
				failureView.forward(req, res);
			}

		} else if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String countryNo = req.getParameter("countryNo");
				
				/*************1. 確認 request 的輸入 ***************/
				if(countryNo == null || countryNo.trim().isEmpty()) {
					errorMsgs.add("請輸入國家編號");
				}else if(!countryNo.matches("CRY[0-9]{4}")) {
					errorMsgs.add("國家編號格式錯誤");
				}
				
				if(!errorMsgs.isEmpty()) {
					//因為資料錯誤，將訊息彈回select_page.jsp呈現在輸入資料上方
					RequestDispatcher failureView= req.getRequestDispatcher("/back-end/country/countrySelect.jsp");
					failureView.forward(req, res);
					return;
				}
			/*************2. 製作 service 並且查詢   ***************/
				CountryService countrySvc= new CountryService();
				CountryVO country= countrySvc.getOne(countryNo);
				if (country == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/country/countrySelect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			/*************3. 傳回 PAGE ***************/
				req.setAttribute("countryVO", country); // 資料庫取出的 countryVO 物件,存入req
				String url = "/back-end/country/listOneCountry.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交給/back-end/listAllCountry.jsp
					successView.forward(req, res);	
			}catch(Exception e) {
				errorMsgs.add("資料格式有誤"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/country/countrySelect.jsp");//出現錯誤，將資訊回傳給 /select_page.jsp
				failureView.forward(req, res);
			}	
		} 
//			else if("getAll".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			/*************1. 建造  Service 輸入資料 ***************/
//			CountryService countrySvc = new CountryService();
//			List<CountryVO> countries= countrySvc.getAll();
//			if(countries == null) {
//				errorMsgs.add("資料庫沒撈到");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/country/countrySelect_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//			/*************2. 傳回 PAGE ***************/
//				req.getSession().setAttribute("list", countries); // 資料庫取出的 countryVO 物件,存入req
//				String url = "/back-end/country/listAllCountry.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交給/back-end/listAllCountry.jsp
//					successView.forward(req, res);	
//		}
	}

}
