package com.trip.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.trip.model.*;


@WebServlet("/back-end/trip/Trip.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BackTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		
		
		if ("seeDetails".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String str = req.getParameter("tripno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入trip編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
				String tripno = null;
				try {
					tripno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("trip編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
				
		System.out.println(tripno);
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
				if (tripVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("tripVO", tripVO);
				String url = "/back-end/trip/b_oneTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自index.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("tripno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入trip編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String tripno = null;
				try {
					tripno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("trip編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
				if (tripVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tripVO", tripVO);
//				String url = "/front-end/trip/listOneTrip.jsp";
				String url = "/back-end/trip/b_trip_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String tripno = new String(req.getParameter("tripno"));
//				String memno=new String(req.getParameter("memno"));
				/*************************** 2.開始查詢資料 ****************************************/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
//				tripVO = tripSvc.getOneTrip(memno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("tripVO", tripVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/trip/b_update_trip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String tripno = new String(req.getParameter("tripno").trim());
				String memno = new String(req.getParameter("memno").trim());
				String cityno = new String(req.getParameter("cityno").trim());

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tripname = req.getParameter("tripname");
				String tripnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (tripname == null || tripname.trim().length() == 0) {
					errorMsgs.add("tripname: 請勿空白");
				} else if (!tripname.trim().matches(tripnameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("tripname: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}

				java.sql.Date tripstartday = null;
				try {
					tripstartday = java.sql.Date.valueOf(req.getParameter("tripstartday").trim());
				} catch (IllegalArgumentException e) {
					tripstartday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date tripendday = null;
				try {
					tripendday = java.sql.Date.valueOf(req.getParameter("tripendday").trim());
				} catch (IllegalArgumentException e) {
					tripendday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer tripdays = null;
//				try {
				tripdays = new Integer(req.getParameter("tripdays").trim());
//				} catch (NumberFormatException e) {
//					tripdays = 0;
//					errorMsgs.add("天數請填數字.");
//				}

				java.sql.Date tripestdate = null;
				try {
					tripestdate = java.sql.Date.valueOf(req.getParameter("tripestdate").trim());
				} catch (IllegalArgumentException e) {
					tripestdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer bethebuyer = new Integer(req.getParameter("bethebuyer").trim());
				Integer tripstatus = new Integer(req.getParameter("tripstatus").trim());
				Integer timeofviews = new Integer(req.getParameter("timeofviews").trim());
				Integer kindoftrip = new Integer(req.getParameter("kindoftrip").trim());

				byte[] trippic = null;
				Collection<Part> parts = req.getParts();
				for (Part pic : parts) {
					if (pic.getContentType() != null) {
						InputStream in = pic.getInputStream();
						trippic = new byte[in.available()];
						in.read(trippic);
						in.close();
					}
				}

				TripVO tripVO = new TripVO();
				tripVO.setMemno(memno);
				tripVO.setCityno(cityno);
				tripVO.setTripname(tripname);
				tripVO.setTripstartday(tripstartday);
				tripVO.setTripendday(tripendday);
				tripVO.setTripdays(tripdays);
				tripVO.setTripestdate(tripestdate);
				tripVO.setBethebuyer(bethebuyer);
				tripVO.setTripstatus(tripstatus);
				tripVO.setTimeofviews(timeofviews);
				tripVO.setKindoftrip(kindoftrip);
				tripVO.setTrippic(trippic);
				tripVO.setTripno(tripno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripVO", tripVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				TripService tripSvc = new TripService();
				tripVO = tripSvc.updateTrip(tripname, tripstartday, tripendday, tripestdate, tripdays, bethebuyer,
						memno, cityno, tripstatus, timeofviews, kindoftrip, trippic, tripno);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tripVO", tripVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front-end/trip/listOneTrip.jsp";
				String url = "/back-end/trip/b_trip_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_update_trip.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String memno = new String(req.getParameter("memno").trim());
			String cityno = new String(req.getParameter("cityno").trim());

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tripname = req.getParameter("tripname");
				String tripnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (tripname == null || tripname.trim().length() == 0) {
					errorMsgs.add("tripname: 請勿空白");
				} else if (!tripname.trim().matches(tripnameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("tripname: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}

				java.sql.Date tripstartday = null;
				try {
					tripstartday = java.sql.Date.valueOf(req.getParameter("tripstartday").trim());
				} catch (IllegalArgumentException e) {
					tripstartday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date tripendday = null;
				try {
					tripendday = java.sql.Date.valueOf(req.getParameter("tripendday").trim());
				} catch (IllegalArgumentException e) {
					tripendday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer tripdays = null;
				tripdays = new Integer(req.getParameter("tripdays").trim());

				java.sql.Date tripestdate = null;
				try {
					tripestdate = java.sql.Date.valueOf(req.getParameter("tripestdate").trim());
				} catch (IllegalArgumentException e) {
					tripestdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer bethebuyer = new Integer(req.getParameter("bethebuyer").trim());
				Integer tripstatus = new Integer(req.getParameter("tripstatus").trim());
				Integer timeofviews = new Integer(0);
				Integer kindoftrip = new Integer(req.getParameter("kindoftrip").trim());

				byte[] trippic = null;
				Collection<Part> parts = req.getParts();
				for (Part pic : parts) {
					if (pic.getContentType() != null) {
						InputStream in = pic.getInputStream();
						trippic = new byte[in.available()];
						in.read(trippic);
						in.close();
					}
				}

				TripVO tripVO = new TripVO();
				tripVO.setMemno(memno);
				tripVO.setCityno(cityno);
				tripVO.setTripname(tripname);
				tripVO.setTripstartday(tripstartday);
				tripVO.setTripendday(tripendday);
				tripVO.setTripdays(tripdays);
				tripVO.setTripestdate(tripestdate);
				tripVO.setBethebuyer(bethebuyer);
				tripVO.setTripstatus(tripstatus);
				tripVO.setTimeofviews(timeofviews);
				tripVO.setKindoftrip(kindoftrip);
				tripVO.setTrippic(trippic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripVO", tripVO); // 含有輸入格式錯誤的tripVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TripService tripSvc = new TripService();
				tripVO = tripSvc.addTrip(memno, cityno, tripname, tripstartday, tripendday, tripdays, tripestdate,
						bethebuyer, tripstatus, timeofviews, kindoftrip, trippic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/trip/b_trip_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 凍結
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				String tripno = new String(req.getParameter("tripno"));
				TripService tripSvc = new TripService();
				tripSvc.deleteTrip(tripno);
//				String url = "/back-end/trip/b_trip_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("凍結資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("accept".equals(action)) { // 通過
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				String tripno = new String(req.getParameter("tripno"));
				TripService tripSvc = new TripService();
				tripSvc.acceptTrip(tripno);
//				String url = "/back-end/trip/b_trip_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("通過資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/trip/b_trip_index.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
