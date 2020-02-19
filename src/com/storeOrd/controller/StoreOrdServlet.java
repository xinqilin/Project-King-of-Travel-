package com.storeOrd.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.storeOrd.model.*;

public class StoreOrdServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("ordNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/storeOrd_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String ordNo = null;
				try {
					ordNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/storeOrd_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StoreOrdService storeOrdSvc = new StoreOrdService();
				StoreOrdVO storeOrdVO = storeOrdSvc.findByPrimaryKey(ordNo);
				if (storeOrdVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/storeOrd_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("storeOrdVO", storeOrdVO); // 資料庫取出的StoreOrdVO物件,存入req
				String url = "/back-end/store/storeOrd_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/storeOrd_select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// ██████████████████████████████████████████

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ordNo = new String(req.getParameter("ordNo"));
				/*************************** 2.開始查詢資料 ****************************************/
				StoreOrdService storeOrdSvc = new StoreOrdService();
				StoreOrdVO storeOrdVO = storeOrdSvc.findByPrimaryKey(ordNo);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("storeOrdVO", storeOrdVO);
				String url = "/back-end/store/storeOrd_update_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/storeOrd_list_all.jsp");
				failureView.forward(req, res);
			}
		}

		// ██████████████████████████████████████████
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ordNo = req.getParameter("ordNo").trim();
				String memNo = req.getParameter("memNo").trim();
				System.out.println(memNo);
				System.out.println("120");
				Integer price = new Integer(req.getParameter("price").trim());
				System.out.println("122蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲蟲");
				String address = req.getParameter("address");
//				Timestamp orderedTime = new Timestamp(req.getParameter("orderedTime"));
//				Timestamp paymentTime = req.getParameter("paymentTime");
				Integer status = new Integer(req.getParameter("status").trim());
				Integer paymentMethod = new Integer(req.getParameter("paymentMethod").trim());

				StoreOrdVO storeOrdVO = new StoreOrdVO();
				storeOrdVO.setOrdNo(ordNo);System.out.println(ordNo);
				storeOrdVO.setMemNo(memNo);System.out.println(memNo);
				storeOrdVO.setPrice(price);System.out.println(price);
				storeOrdVO.setAddress(address);System.out.println(address);
//				storeOrdVO.setOrderedTime(orderedTime);
//				storeOrdVO.setPaymentTime(paymentTime);
				storeOrdVO.setStatus(status);System.out.println(status);
				storeOrdVO.setPaymentMethod(paymentMethod);System.out.println(paymentMethod);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeOrdVO", storeOrdVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/storeOrd_update_input.jsp");
					failureView.forward(req, res);
					return;
				}
				StoreOrdService storeOrdSvc = new StoreOrdService();
				storeOrdVO = storeOrdSvc.update(ordNo, memNo, price, address, status, paymentMethod);
				req.setAttribute("storeOrdVO", storeOrdVO);
				
				String url = "/back-end/store/storeOrd_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/storeOrd_update_input.jsp");
				failureView.forward(req, res);
			}
		}

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String memNo = req.getParameter("memNo");
				Integer price = new Integer(req.getParameter("price").trim());
				String address = req.getParameter("address");
				StoreOrdVO storeOrdVO = new StoreOrdVO();
				storeOrdVO.setMemNo(memNo);
				storeOrdVO.setPrice(price);
				storeOrdVO.setAddress(address);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeOrdVO", storeOrdVO); //
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/storeOrd_add.jsp");
					failureView.forward(req, res);
					return;
				}
				StoreOrdService storeOrdSvc = new StoreOrdService();
				storeOrdVO = storeOrdSvc.addStoreOrd(memNo, price, address);
				String url = "/back-end/store/storeOrd_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/storeOrd_add.jsp");
				failureView.forward(req, res);
			}
		}

//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★	
	}
}
