package com.spot.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.spot.model.SpotListService;
import com.spot.model.SpotListVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SpotListServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {	//來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("spotNo");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入景點編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/select_page.jsp");
					failureView.forward(req, res);
					return;		// 程式中斷
				}
				
				String spotNo = null;
				try {
					spotNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("景點編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/select_page.jsp");
					failureView.forward(req, res);
					return;		//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				SpotListService spotSvc = new SpotListService();
				SpotListVO spotListVO = spotSvc.getOneSpot(spotNo);
				if(spotListVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/select_page.jsp");
					failureView.forward(req, res);
					return;		//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("spotListVO", spotListVO);	// 資料庫取出的spotListVO物件，存入req
				String url = "/back-end/spot/listOneSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);	//// 成功轉交 listOneSpot.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {	//來自listAllSpot.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String spotNo = new String(req.getParameter("spotNo"));
				
				/***************************2.開始查詢資料****************************************/
				SpotListService spotSvc = new SpotListService();
				SpotListVO spotListVO = spotSvc.getOneSpot(spotNo);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("spotListVO", spotListVO);		// 資料庫取出的empVO物件,存入req
				String url = "/back-end/spot/update_spot_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);	//成功轉交update_spot_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/listAllSpot.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {	//來自update_spot_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			try {
				String spotNo = new String (req.getParameter("spotNo").trim());
				
				//景點名稱
				String spotName = req.getParameter("spotName");
				String spotNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
				if (spotName == null || spotName.trim().length() == 0) {
					errorMsgs.add("景點名稱請勿空白");
				} else if (!spotName.trim().matches(spotNameReg)) {
					errorMsgs.add("景點名稱只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
				}
				
				//城市編號
				String cityNo = req.getParameter("cityNo");
				if (cityNo == null || cityNo.trim().length() == 0) {
					errorMsgs.add("城市編號請勿空白");
				}
				
				System.out.println(cityNo);
				//景點地址
				String location = req.getParameter("location");
				if (location == null || location.trim().length() == 0) {
					errorMsgs.add("景點地址請勿空白");
				}

				//景點類型
				Integer spotType = new Integer(req.getParameter("spotType"));
				if (spotType == null || cityNo.trim().length() == 0) {
					errorMsgs.add("請填入景點類型");
				} else if ( 0 >= spotType && spotType <= 6) {
					errorMsgs.add("請填入0~6之中的類型");
				}
				
				//景點照片
				byte[] spotPhoto; // 可以跑的寫法
				SpotListService spotSvc1 = new SpotListService();// 取出這項商品資料庫內的圖片
				SpotListVO spotListVO1 = spotSvc1.getOneSpot(spotNo);// 新增一個vo物件
				spotPhoto = spotListVO1.getSpotPhoto();// 將圖片丟到spotListVO裡面

				Part part = req.getPart("spotPhoto");// 查詢這次網頁操作，是否有上傳新的圖片
				String header = part.getHeader("content-disposition");
				String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
				if (filename.length() != 0 && part.getContentType() != null) {
					InputStream in = part.getInputStream();
					spotPhoto = new byte[in.available()];
					in.read(spotPhoto);
				}
				
//				Part part = req.getPart("spotPhoto");
//				InputStream is = part.getInputStream();
//				byte[] spotPhoto = new byte[is.available()];
//				is.read(spotPhoto);
//				is.close();
//				
//				try {
//					SpotListService spotSvc = new SpotListService();
//					Part part1 = req.getPart("spotPhoto"); 
//					String filename = getFileNameFromPart(part1); 
//					byte[] buf=null; 
//					if (filename!= null && part1.getContentType()!=null) { 
//					InputStream in=part1.getInputStream();	
//					buf = new byte[in.available()]; 
//					in.read(buf); 
//					in.close(); 
//					} 
//					else { 
//					SpotListVO spotListVO = spotSvc.getOneSpot(spotNo); 
//					buf = spotListVO.getSpotPhoto(); 
//					}
//				}catch (Exception e) {
//								
//					InputStream in = getServletContext().getResourceAsStream("/NoData/NoSpotPhoto.jpg");
//					byte[] buf = new byte[in.available()];
//					in.read(buf);
//					in.close();
//				}
				
				//景點狀態
				Integer spotStatus = new Integer(req.getParameter("spotStatus"));
//				if (spotStatus == null || location.trim().length() == 0) {
//					errorMsgs.add("請填入景點狀態");
//				} 
//				else if ( 0 >= spotStatus && spotStatus <= 2) {
//					errorMsgs.add("請填入0~2之中的狀態");
//				}
//				
				//電話
				String tel = req.getParameter("tel");
				
				//景點緯度
				Double spotLati = new Double(req.getParameter("spotLati").trim());
//				if (spotLati == null) 
//					errorMsgs.add("緯度請勿空白");
				
//				Double spotLatiReg = ;	//正規表示式
//				try {
//					if (spotLati) {
//						
//					}
//				} catch (NumberFormatException e) {
//					
//				}
				
				
				//景點經度
				Double spotLong = new Double(req.getParameter("spotLati").trim());
//				if (spotLong == null) 
//					errorMsgs.add("經度請勿空白");
//				try {
//					spotLong = new Double(req.getParameter("spotLong").trim());
//				} catch (NumberFormatException e) {
//					
//				}
				
				//景點描述
				String spotDetail = req.getParameter("spotDetail");
				String spotDetailReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,100}$";
				if (spotDetail == null || spotDetail.trim().length() == 0) {
					errorMsgs.add("景點描述請勿空白");
				} else if (spotDetail.trim().matches(spotDetailReg)) {
					errorMsgs.add("景點描述只能是中、英文字母、數字和_ , 且長度必需在10到100之間");
				}
				
				SpotListVO spotListVO = new SpotListVO();
				spotListVO.setSpotNo(spotNo);
				spotListVO.setSpotName(spotName);
				spotListVO.setCityNo(cityNo);
				spotListVO.setLocation(location);
				spotListVO.setSpotType(spotType);
				spotListVO.setSpotPhoto(spotPhoto);
				spotListVO.setSpotStatus(spotStatus);
				spotListVO.setTel(tel);
				spotListVO.setSpotLati(spotLati);
				spotListVO.setSpotLong(spotLong);
				spotListVO.setSpotDetail(spotDetail);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotListVO", spotListVO);	//// 含有輸入格式錯誤的spotListVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/update_spot_input.jsp");
					failureView.forward(req, res);
					return;	//程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				SpotListService spotSvc = new SpotListService();
				spotListVO = spotSvc.updateSpot(spotNo, spotName, cityNo, location, spotType, spotPhoto, spotStatus, tel, spotLati, spotLong, spotDetail);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("spotListVO", spotListVO);
				String url = "/back-end/spot/listOneSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/update_spot_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {	//來自addSpot.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				//景點名稱
				String spotName = req.getParameter("spotName");
				String spotNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
				if (spotName == null || spotName.trim().length() == 0) {
					errorMsgs.add("景點名稱請勿空白");
				} else if (!spotName.trim().matches(spotNameReg)) {
					errorMsgs.add("景點名稱只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
				}
				
				//城市編號
				String cityNo = new String(req.getParameter("cityNo").trim());
				if (cityNo == null || cityNo.trim().length() == 0) {
					errorMsgs.add("城市編號請勿空白");
				}
				
				//景點地址
				String location = req.getParameter("location");
				if (location == null || location.trim().length() == 0) {
					errorMsgs.add("景點地址請勿空白");
				}
				
				//景點類型
				Integer spotType = new Integer(req.getParameter("spotType"));
//				if (spotType == null) {
//					errorMsgs.add("請填入景點類型");
//				} else if ( 0 >= spotType && spotType <= 6) {
//					errorMsgs.add("請填入0~6之中的類型");
//				}
				
				//景點照片
				Part part = req.getPart("spotPhoto");
				InputStream is = part.getInputStream();
				byte[] spotPhoto = new byte[is.available()];
				is.read(spotPhoto);
				is.close();
				
				//景點狀態
				Integer spotStatus = new Integer(req.getParameter("spotStatus"));
//				if (spotStatus == null) {
//					errorMsgs.add("請填入景點狀態");
//				} else if ( 0 >= spotStatus && spotStatus <= 2) {
//					errorMsgs.add("請填入0~2之中的狀態");
//				}
				
				//電話
				String tel = req.getParameter("tel");
				
				//景點緯度
				Double spotLati = new Double(req.getParameter("spotLati").trim());
//				if (spotLati == null) 
//					errorMsgs.add("緯度請勿空白");
//				Double spotLatiReg = ;	//正規表示式
//				try {
//					if (spotLati) {
//						
//					}
//				} catch (NumberFormatException e) {
//					
//				}
				
				
				//景點經度
				Double spotLong = new Double(req.getParameter("spotLati").trim());
//				if (spotLong == null) 
//					errorMsgs.add("經度請勿空白");
//				try {
//					spotLong = new Double(req.getParameter("spotLong").trim());
//				} catch (NumberFormatException e) {
//					
//				}
				
				//景點描述
				String spotDetail = req.getParameter("spotDetail");
				String spotDetailReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,100}$";
				if (spotDetail == null || spotDetail.trim().length() == 0) {
					errorMsgs.add("景點描述請勿空白");
				} else if (spotDetail.trim().matches(spotDetailReg)) {
					errorMsgs.add("景點描述只能是中、英文字母、數字和_ , 且長度必需在10到100之間");
				}
				
				SpotListVO spotListVO = new SpotListVO();
				spotListVO.setSpotName(spotName);
				spotListVO.setCityNo(cityNo);
				spotListVO.setLocation(location);
				spotListVO.setSpotType(spotType);
				spotListVO.setSpotPhoto(spotPhoto);
				spotListVO.setSpotStatus(spotStatus);
				spotListVO.setTel(tel);
				spotListVO.setSpotLati(spotLati);
				spotListVO.setSpotLong(spotLong);
				spotListVO.setSpotDetail(spotDetail);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotListVO", spotListVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/addSpot.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SpotListService spotSvc = new SpotListService();
				spotListVO = spotSvc.addSpot(spotName, cityNo, location, spotType, spotPhoto, spotStatus, tel, spotLati, spotLong, spotDetail);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/spot/listAllSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);	//新增成功後轉交listAllSpot.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spot/addSpot.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) {	// 來自listAllSpot.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String spotNo = new String(req.getParameter("spotNo"));
				
				/***************************2.開始刪除資料***************************************/
				SpotListService spotSvc = new SpotListService();
				spotSvc.deleteSpot(spotNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/spot/listAllSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/spot/listAllSpot.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	public String getFileNameFromPart(Part part) { 
		String header = part.getHeader("content-disposition"); 
		System.out.println("header=" + header); // 測試用 
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName(); 
		System.out.println("filename=" + filename); // 測試用 
		if (filename.length() == 0) { 
		return null; 
		} 
		return filename; 
		}


}
