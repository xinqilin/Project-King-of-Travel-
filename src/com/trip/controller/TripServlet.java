package com.trip.controller;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.trip.model.*;

@WebServlet("/front-end/trip/Trip.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tripVO", tripVO); 
//				String url = "/front-end/trip/listOneTrip.jsp";
				String url="/front-end/trip/trip_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String tripno = new String(req.getParameter("tripno"));
//				String memno=new String(req.getParameter("memno"));
				/***************************2.開始查詢資料****************************************/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
//				tripVO = tripSvc.getOneTrip(memno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("tripVO", tripVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/trip/update_trip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			
			try {
				String tripno =new String (req.getParameter("tripno").trim());
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
					tripdays = new Integer(req.getParameter("tripdays").trim());

				java.sql.Date tripestdate = null;
				try {
					tripestdate = java.sql.Date.valueOf(req.getParameter("tripestdate").trim());
				} catch (IllegalArgumentException e) {
					tripestdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}


				Integer bethebuyer = new Integer(req.getParameter("bethebuyer").trim());
				Integer tripstatus=new Integer(req.getParameter("tripstatus").trim());
				Integer timeofviews = new Integer(req.getParameter("timeofviews").trim());
				Integer kindoftrip = new Integer(req.getParameter("kindoftrip").trim());
				
				byte[] trippic;
				

				TripService Svc = new TripService();// 取出這項商品資料庫內的圖片
				TripVO VO = Svc.getOneTrip(tripno);// 新增一個vo物件
				trippic = VO.getTrippic();// 將圖片丟到itemVO2裡面

				Part part = req.getPart("trippic");// 查詢這次網頁操作，是否有上傳新的圖片
				String header = part.getHeader("content-disposition");
				String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();


//				System.out.println("part.getContentType()=" + part.getContentType());
				if (filename.length() != 0 && part.getContentType() != null) {
					InputStream in = part.getInputStream();
					trippic = new byte[in.available()];
					in.read(trippic);
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				TripService tripSvc = new TripService();
				tripVO = tripSvc.updateTrip(tripname,
						tripstartday,tripendday,tripestdate,tripdays, bethebuyer,
						memno,cityno,tripstatus,timeofviews,kindoftrip,trippic,tripno);
				
				
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cityno", tripVO.getCityno());
				req.setAttribute("tripVO", tripVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front-end/trip/listAllTrip.jsp";
				String url="/front-end/tripDetails/editTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/update_trip.jsp");
				failureView.forward(req, res);
			}
		}

		if("gogoJapan".equals(action)) {
			TripService trip=new TripService();
			List<TripVO> tripVO=trip.getAll();
			String keyword=req.getParameter("gogo");
			
			String[] keywords=keyword.split("\\s+");
			System.out.println(Arrays.toString(keywords));
			Stream list=tripVO.stream();
			for(String key:keywords) {
				Predicate<TripVO> predicate=p->p.getTripname().contains(key);
				list=tripVO.stream().filter(predicate);
			}
			tripVO=(List<TripVO>)list.collect(Collectors.toList());
			req.setAttribute("keyword", tripVO);
			RequestDispatcher View = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
			View.forward(req, res);
			
		}
		

	}
}
