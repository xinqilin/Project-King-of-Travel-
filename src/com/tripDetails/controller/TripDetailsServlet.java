package com.tripDetails.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.city.model.CityService;
import com.city.model.CityVO;
import com.spot.model.SpotListService;
import com.spot.model.SpotListVO;
import com.trip.model.TripService;
import com.trip.model.TripVO;
import com.tripDetails.model.TripDetailsService;
import com.tripDetails.model.TripDetailsVO;

@WebServlet("/front-end/tripDetails/TripDetails.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TripDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TripDetailsServlet() {
		super();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		String action = req.getParameter("action");

		
		
		
		
		if ("getOne".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String str = req.getParameter("tripno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入trip編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
				tripSvc.updateTrip(tripVO.getTripname(),tripVO.getTripstartday(), tripVO.getTripendday(),tripVO.getTripestdate(), tripVO.getTripdays(), tripVO.getBethebuyer(), tripVO.getMemno(),tripVO.getCityno(), tripVO.getTripstatus(), tripVO.getTimeofviews()+1, tripVO.getKindoftrip(), tripVO.getTrippic(), tripno);
				req.setAttribute("tripVO", tripVO);
				String url = "/front-end/tripDetails/oneTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("insertDetails".equals(action)) {
			System.out.println("進來");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String cityno=(String)req.getParameter("cityno");
			
//	System.out.println(req.getParameter("timeofarrive")+"     110");
			try {
				String hour1=req.getParameter("time11");
				String min1=req.getParameter("time22");
				String hour2=req.getParameter("time33");
				String min2=req.getParameter("time44");
				Integer h1=Integer.valueOf(hour1);
				Integer m1=Integer.valueOf(min1);
				Integer h2=Integer.valueOf(hour2);
				Integer m2=Integer.valueOf(min2);
				Integer h3=h2-h1;
				int ccc=00;
				Time date1=new Time(h1,m1, ccc);
				Time date2=new Time(h2,m2, ccc);
				DateFormat sim=new SimpleDateFormat("HH:mm:ss");
				String t1=sim.format(date1);
				String t2=sim.format(date2);
				java.sql.Time timeofarrive=java.sql.Time.valueOf(t1);
				java.sql.Time timeofleave=java.sql.Time.valueOf(t2);

				Integer tripdayorder=new Integer(req.getParameter("tripdayorder"));
//System.out.println("tripdayorder: "+tripdayorder);
				String tripno=new String(req.getParameter("tripno"));
//System.out.println("tripno: "+tripno);
				String spotno=new String(req.getParameter("spotno"));
//System.out.println("spotno: "+spotno);				
				Integer stayhours = new Integer(req.getParameter("stayhours").trim());
//System.out.println("stayhours: "+stayhours);
				Integer triporderby = new Integer(req.getParameter("triporderby").trim());
//System.out.println("triporderby: "+triporderby);
				String triptips=new String(req.getParameter("triptips"));
//System.out.println("triptips: "+triptips);
				Double a = (((Double) Math.random() * 99) + 1.0);
				String c = a.toString().substring(0, 5);
				Double b = Double.parseDouble(c);
				Double milestonextspots=new Double(b);
//System.out.println("milestonextspots: "+milestonextspots);
				
System.out.println("tripno:"+tripno+"  spotno:"+ spotno+"  timeofarrive:"+  timeofarrive+"  timeofleave:"+  timeofleave+"  "+  stayhours+"  "+  milestonextspots+"  tripdayorder:"+  tripdayorder+"  triporderby:"+  triporderby+"  "+  triptips);
				TripDetailsVO detailsVO = new TripDetailsVO();
				detailsVO.setTripno(tripno);
				detailsVO.setSpotno(spotno);
				detailsVO.setMilestonextspots(milestonextspots);
				detailsVO.setStayhours(stayhours);
				detailsVO.setTimeofarrive(timeofarrive);
				detailsVO.setTimeofleave(timeofleave);
				detailsVO.setTriporderby(triporderby);
				detailsVO.setTripdayorder(tripdayorder);
				detailsVO.setTriptips(triptips);
				
				
System.out.println(errorMsgs);				

				if (!errorMsgs.isEmpty()) {
					TripService tripSvc = new TripService();
					TripVO tripVO = tripSvc.getOneTrip(tripno);
					SpotListService spotSvc = new SpotListService();
					SpotListVO spotVO = spotSvc.getOneSpot(spotno);
					req.setAttribute("cityno", cityno);
					req.setAttribute("spotVO", spotVO); 
					req.setAttribute("tripVO", tripVO);
					
					req.setAttribute("tripDetailsVO", detailsVO); // 含有輸入格式錯誤的tripVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
	int temp=triporderby;
	int tem=0;
	try {
		TripDetailsService details=new TripDetailsService();
		for(TripDetailsVO det:details.getfindByTripno(tripno)) {
			if(det.getTripdayorder() == Integer.valueOf(tripdayorder) && det.getTriporderby()==temp) {
				details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), det.getMilestonextspots(),det.getTripdayorder(), det.getTriporderby()+1, det.getTriptips());
			temp++;
			}
			if(det.getTripdayorder() == Integer.valueOf(tripdayorder)) {
					tem++;
			}
		}
	}catch(Exception e) {
		errorMsgs.add("景點往後推失敗");
		RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
		failureView.forward(req, res);
	}
				
				/*************************** 2.開始新增資料 ***************************************/
System.out.println("開始新增資料");
			try {
				TripDetailsService detailsSvc=new TripDetailsService();
				detailsSvc.addTripDetails(tripno, spotno, timeofarrive, timeofleave, stayhours, milestonextspots, tripdayorder, triporderby, triptips);
				}catch(Exception e){
	errorMsgs.add("同一個景點不能重複哦");
//	errorMsgs.add(e.getMessage());
	RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
	failureView.forward(req, res);
}
			System.out.println("那一天幾個?"+tem);
				System.out.println("新增完成");
				if((tem+1)<Integer.valueOf(triporderby)) {
				TripDetailsService d=new TripDetailsService();
				d.updateTripDetails(tripno, spotno, timeofarrive, timeofleave, stayhours, milestonextspots, tripdayorder,tem+1, triptips);	
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
				SpotListService spotSvc = new SpotListService();
				SpotListVO spotVO = spotSvc.getOneSpot(spotno);
				req.setAttribute("whichday", tripdayorder);
				req.setAttribute("cityno", cityno);
				req.setAttribute("spotVO", spotVO); 
				req.setAttribute("tripVO", tripVO);
				req.setAttribute("welcome", "3345678");
				String url = "/front-end/tripDetails/editTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("同一個景點不能重複哦");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
		if("swapSpot".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String cityno=(String)req.getParameter("cityno");
				String tripno=new String(req.getParameter("tripno"));
				String spotno1=new String(req.getParameter("spotno1"));
				String spotno2=new String(req.getParameter("spotno2"));
				TripDetailsService swap=new TripDetailsService();
				TripDetailsVO a=swap.getOneTripDetails(tripno, spotno1);
				System.out.println(a+"   天:"+a.getTripdayorder()+"   順序:"+a.getTriporderby());
				TripDetailsVO b=swap.getOneTripDetails(tripno, spotno2);
				System.out.println(b+"   天:"+b.getTripdayorder()+"   順序:"+b.getTriporderby());

				
				Double aa = (((Double) Math.random() * 99) + 1.0);
				String cc = aa.toString().substring(0, 5);
				Double bb = Double.parseDouble(cc);
				Double amilestonextspots=new Double(bb);
				
				Double aaa = (((Double) Math.random() * 99) + 1.0);
				String ccc = aaa.toString().substring(0, 5);
				Double bbb = Double.parseDouble(ccc);
				Double bmilestonextspots=new Double(bbb);
				
				
				TripDetailsService swap2=new TripDetailsService();
				swap2.updateTripDetails(tripno, a.getSpotno(), b.getTimeofarrive(), b.getTimeofleave(), b.getStayhours(), amilestonextspots, b.getTripdayorder(), b.getTriporderby(), a.getTriptips());
				swap2.updateTripDetails(tripno, b.getSpotno(), a.getTimeofarrive(), a.getTimeofleave(), a.getStayhours(), bmilestonextspots, a.getTripdayorder(), a.getTriporderby(), b.getTriptips());
				
				
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
				req.setAttribute("welcome", "3345678");
				req.setAttribute("cityno", cityno);
				req.setAttribute("tripVO", tripVO);
				String url = "/front-end/tripDetails/editTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("交換失敗:"+e.getMessage());
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if("deleteDetails".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			System.out.println("有進來刪除嗎?");
			String cityno=(String)req.getParameter("cityno");
			String tripno=new String(req.getParameter("tripno"));
			String spotno=new String(req.getParameter("spotno"));
			System.out.println("tripno:"+tripno+"  cityno:"+cityno+"  spotno"+spotno);
			
			TripDetailsService delete=new TripDetailsService();
			TripDetailsVO a=delete.getOneTripDetails(tripno, spotno);
			
			System.out.println("刪除的第"+a.getTripdayorder()+"天，的第"+a.getTriporderby()+"個");
			
			
			int cDay=0;//這景點的當天有幾個
			TripDetailsService details=new TripDetailsService();
			for(TripDetailsVO det:details.getfindByTripno(tripno)) {
			if(det.getTripdayorder()==a.getTripdayorder()) {
				cDay++;
			}
			}
			
			
			//a進來的
	for(TripDetailsVO det:details.getfindByTripno(tripno)) {
	if(cDay!=1) {
		//det.getTriporderby()!=cDay && 
		if(det.getTripdayorder()==a.getTripdayorder()) {
		//不是最後一天，往前推
		if(det.getTripdayorder() == a.getTripdayorder() && det.getTriporderby()>a.getTriporderby()) {
		details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), det.getMilestonextspots(),det.getTripdayorder(), det.getTriporderby()-1, det.getTriptips());
		}}
	}else {
		if(det.getTripdayorder()>a.getTripdayorder()) {
		details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(),det.getTimeofleave(), det.getStayhours(), det.getMilestonextspots(), det.getTripdayorder()-1, det.getTriporderby(), det.getTriptips());
		}
		if(det.getTripdayorder()>a.getTripdayorder() && det.getTriporderby()>1) {
			details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(),det.getTimeofleave(), det.getStayhours(), det.getMilestonextspots(), det.getTripdayorder()-1, det.getTriporderby(), det.getTriptips());	
		}
	}
	
}
	
	
	

	delete.deleteTripDetails(tripno,spotno);
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.getOneTrip(tripno);
			req.setAttribute("welcome", "3345678");
			req.setAttribute("cityno", cityno);
			req.setAttribute("tripVO", tripVO);
			String url = "/front-end/tripDetails/editTripDetails.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		if("selectOneCity".equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String TripNO=  (String) req.getParameter("tripVO999");
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.getOneTrip(TripNO);
			try {
			String str = req.getParameter("cityNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入城市編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
				failureView.forward(req, res);
				return;
			}
			String cityno = null;
			try {
				cityno = new String(str);
			} catch (Exception e) {
				errorMsgs.add("城市編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CityService citySvc = new CityService();
			CityVO cityVO = citySvc.getOne(cityno);
			if (cityno == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("cityno", cityno); 
			req.setAttribute("tripVO", tripVO);
			req.setAttribute("welcome", "3345678");
			
			String url="/front-end/tripDetails/editTripDetails.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
			failureView.forward(req, res);
		}
	}
		
		
		
		if("getOne_spot".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String whichday=(String)req.getParameter("whichday");
			Integer newwhichday=1;
			System.out.println(whichday);
			if(whichday!=null) {
			newwhichday=new Integer(whichday);
			}
			//夾帶修改天數
			
			String TripNO=  (String) req.getParameter("tripVO999");
			String cityno=(String)req.getParameter("cityno");
//			System.out.println(cityno);
//			System.out.println("tripDetails controller  getOne_spot 收到");
			
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.getOneTrip(TripNO);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("spotNo");
//				System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入景點編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String spotno = null;
				try {
					spotno = new String(str);
					System.out.println(spotno);
				} catch (Exception e) {
					errorMsgs.add("景點編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SpotListService spotSvc = new SpotListService();
				SpotListVO spotVO = spotSvc.getOneSpot(spotno);
				System.out.println(spotVO);
				if (spotno == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cityno", cityno);
				req.setAttribute("spotVO", spotVO); 
				req.setAttribute("tripVO", tripVO);
				req.setAttribute("whichday", newwhichday);
				req.setAttribute("welcome", "3345678");
//				System.out.println(cityno);
//				System.out.println(spotVO);
//				System.out.println(tripVO);
				String url="/front-end/tripDetails/editTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
		
		
		
		
		if("getOne_spot2".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String TripNO=  (String) req.getParameter("tripVO999");
			System.out.println("tripDetails controller  getOne_spot2 收到");
			
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.getOneTrip(TripNO);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("spotNo");
//				System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入景點編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/oneTripDetails.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String spotno = null;
				try {
					spotno = new String(str);
//					System.out.println(spotno);
				} catch (Exception e) {
					errorMsgs.add("景點編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/oneTripDetails.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SpotListService spotSvc = new SpotListService();
				SpotListVO spotVO = spotSvc.getOneSpot(spotno);
//				System.out.println(spotVO);
				if (spotno == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/oneTripDetails.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spotVO", spotVO); 
				req.setAttribute("tripVO", tripVO);
				req.setAttribute("welcome", "3345678");
//				System.out.println(spotVO);
//				System.out.println(tripVO);
				String url="/front-end/tripDetails/oneTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tripDetails/oneTripDetails.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
		
		
		
		if ("insert".equals(action)) { 
//			新增一個新的行程
			List<String> errorMsgs = new LinkedList<String>();
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
				Integer tripstatus=new Integer(1);
				Integer timeofviews = new Integer(0);
				Integer kindoftrip = new Integer(req.getParameter("kindoftrip").trim());
				
				byte[] trippic=null;
				Collection<Part> parts=req.getParts();
				for(Part pic:parts)
				{
					if(pic.getContentType()!=null)
					{
						InputStream in =pic.getInputStream();
						trippic=new byte[in.available()];
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/addTrip.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TripService tripSvc = new TripService();
				tripVO = tripSvc.addTrip(memno,cityno,tripname,
						tripstartday, tripendday, tripdays, tripestdate, bethebuyer,
						tripstatus,timeofviews,kindoftrip,trippic);
//				TripService tripSvc1 = new TripService();
//				TripVO tripVO1=new TripVO();
//				tripVO1=tripSvc1.getlastOne();
//				req.setAttribute("tripVO666", tripVO1);
				req.setAttribute("cityno", cityno);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/tripDetails/connectPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trip/addTrip.jsp");
				failureView.forward(req, res);
			}
		}
		if ("lastone".equals(action)) {

				TripService tripSvc1 = new TripService();
				TripVO tripVO1=new TripVO();
				tripVO1=tripSvc1.getlastOne();
				String  cityno=new String(req.getParameter("cityno"));
				req.setAttribute("cityno", cityno);
				req.setAttribute("tripVO", tripVO1);
				String url = "/front-end/tripDetails/newTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		if("up".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String cityno=req.getParameter("cityno");
				String tripno=new String(req.getParameter("tripno"));
				String spotno1=new String(req.getParameter("spotNo"));
				TripDetailsService swap=new TripDetailsService();
				TripDetailsVO a=swap.getOneTripDetails(tripno, spotno1);
				System.out.println("收到"+a+"   天:"+a.getTripdayorder()+"   順序:"+a.getTriporderby());
				
				Double aa = (((Double) Math.random() * 99) + 1.0);
				String cc = aa.toString().substring(0, 5);
				Double bb = Double.parseDouble(cc);
				Double amilestonextspots=new Double(bb);
				Double aaa = (((Double) Math.random() * 99) + 1.0);
				String ccc = aaa.toString().substring(0, 5);
				Double bbb = Double.parseDouble(ccc);
				Double bmilestonextspots=new Double(bbb);
				
				int cDay=0,cDay2=0;
				TripDetailsService details=new TripDetailsService();
				for(TripDetailsVO det:details.getfindByTripno(tripno)) {
				if(det.getTripdayorder()==a.getTripdayorder()-1) {
					cDay++;//前一天有幾個
				}
				}
				for(TripDetailsVO det:details.getfindByTripno(tripno)) {
					if(det.getTripdayorder()==a.getTripdayorder()) {
						cDay2++;//當天有幾個
					}
					}
				
				
				if(details.getOneTripDetails(tripno, spotno1).getTriporderby()==1) {
					if(details.getOneTripDetails(tripno, spotno1).getTriporderby()==cDay2) {
		for(TripDetailsVO det:details.getfindByTripno(tripno)) {
		if(det.getTripdayorder() > a.getTripdayorder()-1) {
			details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), bmilestonextspots,det.getTripdayorder()-1, det.getTriporderby(), det.getTriptips());
		}
		}
		swap.updateTripDetails(tripno, a.getSpotno(), a.getTimeofarrive(), a.getTimeofleave(), a.getStayhours(), amilestonextspots, a.getTripdayorder()-1, cDay+1, a.getTriptips());
		
					}else {
					for(TripDetailsVO det:details.getfindByTripno(tripno)) {
						if(det.getTripdayorder() == a.getTripdayorder()) {
							details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), bmilestonextspots,det.getTripdayorder(), det.getTriporderby()-1, det.getTriptips());
							}
					}
					details.updateTripDetails(tripno, spotno1, a.getTimeofarrive(), a.getTimeofleave(), a.getStayhours(), amilestonextspots, a.getTripdayorder()-1, cDay+1, a.getTriptips());
					}
				}
				else {
				for(TripDetailsVO det:details.getfindByTripno(tripno)) {
					if(det.getTripdayorder() == a.getTripdayorder() && det.getTriporderby()==a.getTriporderby()-1) {
					details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), bmilestonextspots,det.getTripdayorder(), det.getTriporderby()+1, det.getTriptips());
					}
				}
				swap.updateTripDetails(tripno, a.getSpotno(), a.getTimeofarrive(), a.getTimeofleave(), a.getStayhours(), amilestonextspots, a.getTripdayorder(), a.getTriporderby()-1, a.getTriptips());
				}
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
				
				req.setAttribute("cityno", cityno);
				req.setAttribute("welcome", "3345678");
				req.setAttribute("tripVO", tripVO);
				String url = "/front-end/tripDetails/editTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("上移失敗:"+e.getMessage());
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("down".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String cityno=req.getParameter("cityno");
				String tripno=new String(req.getParameter("tripno"));
				String spotno1=new String(req.getParameter("spotNo"));
				TripDetailsService swap=new TripDetailsService();
				TripDetailsVO a=swap.getOneTripDetails(tripno, spotno1);
				System.out.println("收到"+a+"   天:"+a.getTripdayorder()+"   順序:"+a.getTriporderby());
				
				Double aa = (((Double) Math.random() * 99) + 1.0);
				String cc = aa.toString().substring(0, 5);
				Double bb = Double.parseDouble(cc);
				Double amilestonextspots=new Double(bb);
				Double aaa = (((Double) Math.random() * 99) + 1.0);
				String ccc = aaa.toString().substring(0, 5);
				Double bbb = Double.parseDouble(ccc);
				Double bmilestonextspots=new Double(bbb);
				
				int cDay=0;//這景點的當天有幾個
				TripDetailsService details=new TripDetailsService();
				for(TripDetailsVO det:details.getfindByTripno(tripno)) {
				if(det.getTripdayorder()==a.getTripdayorder()) {
					cDay++;
				}
				}
				if(details.getOneTripDetails(tripno, spotno1).getTriporderby()==cDay) {//當天最後一個
					if(cDay==1) {
						//第一天只剩一個
						for(TripDetailsVO det:details.getfindByTripno(tripno)) {
						if(det.getTripdayorder() == a.getTripdayorder()+1) {
							//第二天往前推一天，往後擠一個
							details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), bmilestonextspots,det.getTripdayorder()-1, det.getTriporderby()+1, det.getTriptips());
						}else if(det.getTripdayorder() > a.getTripdayorder()+1) {
							//第三天後往前一天
							details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), bmilestonextspots,det.getTripdayorder()-1, det.getTriporderby(), det.getTriptips());
						}
						}
					}
					
					else {
						//當天最後一個
					for(TripDetailsVO det:details.getfindByTripno(tripno)) {
						if(det.getTripdayorder() == a.getTripdayorder()+1) {
							//第二天後的天數內只剩一個
							details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), bmilestonextspots,det.getTripdayorder(), det.getTriporderby()+1, det.getTriptips());
							}
					}
					details.updateTripDetails(tripno, spotno1, a.getTimeofarrive(), a.getTimeofleave(), a.getStayhours(), amilestonextspots, a.getTripdayorder()+1, 1, a.getTriptips());
				}
				}
				else {
				for(TripDetailsVO det:details.getfindByTripno(tripno)) {
					if(det.getTripdayorder() == a.getTripdayorder() && det.getTriporderby()==a.getTriporderby()+1) {
					details.updateTripDetails(det.getTripno(), det.getSpotno(), det.getTimeofarrive(), det.getTimeofleave(), det.getStayhours(), bmilestonextspots,det.getTripdayorder(), det.getTriporderby()-1, det.getTriptips());
					}
				}
				swap.updateTripDetails(tripno, a.getSpotno(), a.getTimeofarrive(), a.getTimeofleave(), a.getStayhours(), amilestonextspots, a.getTripdayorder(), a.getTriporderby()+1, a.getTriptips());
				}
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripno);
				
				req.setAttribute("cityno", cityno);
				req.setAttribute("welcome", "3345678");
				req.setAttribute("tripVO", tripVO);
				String url = "/front-end/tripDetails/editTripDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("下移失敗:"+e.getMessage());
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("copyTrip".equals(action)) {
			
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);	
				System.out.println("copy servlet");
				String memno=req.getParameter("memno");
				String tripno=req.getParameter("tripVO999");
				Date date=new Date();
				DateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
				String date2=sim.format(date);
				java.sql.Date date3=java.sql.Date.valueOf(date2);
				TripService tripSvc = new TripService();
				TripVO t=tripSvc.getOneTrip(tripno);
				tripSvc.addTrip(memno, t.getCityno(), t.getTripname(), t.getTripstartday(), t.getTripendday(), t.getTripdays(), date3, t.getBethebuyer(), 1, 0, t.getKindoftrip(), t.getTrippic());
				TripDetailsService details =new TripDetailsService();
				List<TripDetailsVO> Details=details.getfindByTripno(tripno);
				TripVO newtripno=tripSvc.getlastOne();
				for(TripDetailsVO d:Details) {
					details.addTripDetails(newtripno.getTripno(), d.getSpotno(), d.getTimeofarrive(), d.getTimeofleave(), d.getStayhours(), d.getMilestonextspots(), d.getTripdayorder(), d.getTriporderby(), d.getTriptips());
				}
//			System.out.println("複製行程完成");
				req.setAttribute("welcome", t.getTripname());
				System.out.println(t.getTripname());
					String url = "/front-end/trip/listAllTrip.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
		}
		
		
		if("blockTrip".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);	
		System.out.println("block servlet");
//			String memno=req.getParameter("memno");
			String tripno=req.getParameter("tripVO999");
			TripService tripSvc = new TripService();
			TripVO t=tripSvc.getOneTrip(tripno);
//			t.setTripstatus(0);
			tripSvc.updateTrip(t.getTripname(), t.getTripstartday(), t.getTripendday(),t.getTripestdate(), t.getTripdays(), t.getBethebuyer(), t.getMemno(),t.getCityno(), 0, t.getTimeofviews(),t.getKindoftrip(), t.getTrippic(), t.getTripno());
//		System.out.println("檢舉行程完成");
			req.setAttribute("welcome", t.getTripname());
			System.out.println(t.getTripname());
				String url = "/front-end/trip/trip_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		
		}
		
		if("timetime".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);	
			try {
			String cityno=req.getParameter("cityno");
			String tripno=req.getParameter("tripVO999");
			String spotno=req.getParameter("spotNo");
			String hour1=req.getParameter("time11");
			String min1=req.getParameter("time22");
			String hour2=req.getParameter("time33");
			String min2=req.getParameter("time44");
			System.out.println("tripno:  "+tripno+"spotno:  "+spotno+"hour1:  "+hour1+"min1:  "+min1+"hour2:  "+hour2+"min2:  "+min2);
			Integer h1=Integer.valueOf(hour1);
			Integer m1=Integer.valueOf(min1);
			Integer h2=Integer.valueOf(hour2);
			Integer m2=Integer.valueOf(min2);
			Integer h3=h2-h1;
			int c=00;
			Time date1=new Time(h1,m1, c);
			Time date2=new Time(h2,m2, c);
			DateFormat sim=new SimpleDateFormat("HH:mm:ss");
			String t1=sim.format(date1);
			String t2=sim.format(date2);
			java.sql.Time tt1=java.sql.Time.valueOf(t1);
			java.sql.Time tt2=java.sql.Time.valueOf(t2);
			TripDetailsService de=new TripDetailsService();
			TripDetailsVO a=de.getOneTripDetails(tripno, spotno);
			de.updateTripDetails(tripno, spotno, tt1, tt2, h3, a.getMilestonextspots(), a.getTripdayorder(), a.getTriporderby(), a.getTriptips());
			TripService tripSvc = new TripService();
			TripVO tripVO=tripSvc.getOneTrip(tripno);
			
			req.setAttribute("cityno", cityno);
			req.setAttribute("welcome", "3345678");
			req.setAttribute("tripVO", tripVO);
			String url = "/front-end/tripDetails/editTripDetails.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			
			}catch (Exception e) {
				errorMsgs.add("時間修改失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/trip_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		if("gogogoJapan".equals(action)) {
			String tripno=req.getParameter("tripno");
			String cityno=req.getParameter("cityno");
			TripService tripSvc=new TripService();
			TripVO tripVO=tripSvc.getOneTrip(tripno);
			SpotListService spot =new SpotListService();
			List<SpotListVO> spotVO= spot.getAll();
			String keyword=req.getParameter("gogogo");
			
			String[] keywords=keyword.split("\\s+");
			System.out.println(Arrays.toString(keywords));
			Stream list=spotVO.stream();
			for(String key:keywords) {
				Predicate<SpotListVO> predicate=p->p.getSpotName().contains(key);
				list=spotVO.stream().filter(predicate);
			}
			spotVO=(List<SpotListVO>)list.collect(Collectors.toList());
			SpotListVO newSpot=null;
			if(spotVO.size()>0) {
			newSpot=spotVO.get(0);
			req.setAttribute("spotVO", newSpot);
			}
			req.setAttribute("tripVO", tripVO);
			req.setAttribute("cityno", cityno);
			RequestDispatcher View = req.getRequestDispatcher("/front-end/tripDetails/editTripDetails.jsp");
			View.forward(req, res);
			
		}
		
		
		
		
		
		
		
		// doGet 括號
	}

}