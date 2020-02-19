package com.storeOrdDetail.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.item.model.ItemVO;
import com.storeOrd.model.*;

public class StoreOrdDetailServlet extends HttpServlet {
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
		if ("get_details".equals(action)) {
			System.out.println("detail servlet有執行");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ordNo = req.getParameter("ordNo");
				req.setAttribute("ordNo", ordNo);
				String url = "/front-end/store/storeOrd_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_list_all.jsp");
				failureView.forward(req, res);
			}
		}

//		// ██████████████████████████████████████████
//
//		if ("getOne_For_Update".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				String ordNo = new String(req.getParameter("ordNo"));
//				/*************************** 2.開始查詢資料 ****************************************/
//				StoreOrdService storeOrdSvc = new StoreOrdService();
//				StoreOrdVO storeOrdVO = storeOrdSvc.findByPrimaryKey(ordNo);
//				System.out.println("StoreOrdServlet:storeordvo.address=" + storeOrdVO.getAddress());
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("storeOrdVO", storeOrdVO);
//				String url = "/front-end/store/storeOrd_update_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_list_all.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		// ██████████████████████████████████████████
//		if ("update".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String ordNo = req.getParameter("ordNo").trim();
//				String memNo = req.getParameter("memNo").trim();
//				Integer price = new Integer(req.getParameter("price").trim());
//				String address = req.getParameter("address");
//				Integer status = new Integer(req.getParameter("status").trim());
//				Integer paymentMethod = new Integer(req.getParameter("paymentMethod").trim());
//				StoreOrdVO storeOrdVO = new StoreOrdVO();
//				
//				
//				storeOrdVO.setOrdNo(ordNo);
//				storeOrdVO.setMemNo(memNo);
//				storeOrdVO.setPrice(price);
//				storeOrdVO.setAddress(address);
//				storeOrdVO.setStatus(status);
//				storeOrdVO.setPaymentMethod(paymentMethod);
//				System.out.println(address);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("storeOrdVO", storeOrdVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/store/storeOrd_update_input.jsp");
//					failureView.forward(req, res);
//					return;
//					
//
//				}
//				StoreOrdService storeOrdSvc = new StoreOrdService();
//				storeOrdVO = storeOrdSvc.update(ordNo, memNo, price, address, status, paymentMethod);
//				req.setAttribute("storeOrdVO", storeOrdVO);
//				
//				String url = "/front-end/store/storeOrd_list_one.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//				
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_update_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
//
//		if ("insert".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				String memNo = req.getParameter("memno");
//				Integer price = new Integer(req.getParameter("price").trim());
//				String address = req.getParameter("address");
//				StoreOrdVO storeOrdVO = new StoreOrdVO();
//				storeOrdVO.setMemNo(memNo);
//				storeOrdVO.setPrice(price);
//				storeOrdVO.setAddress(address);
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("storeOrdVO", storeOrdVO); //
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_add.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				StoreOrdService storeOrdSvc = new StoreOrdService();
//				storeOrdVO = storeOrdSvc.addStoreOrd(memNo, price, address);
//				String url = "/front-end/store/storeOrd_list_all.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_add.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
////★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
//		if ("check_out".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//
//				try {
//					HttpSession session = req.getSession();
//					LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");
//				    
//				    
//				    
//				    //計算訂單總金額
//					Integer total;
//				    total = cart.entrySet().stream()
//				    		.mapToInt(e -> e.getKey().getPrice() * e.getValue())
//			    			.sum();
//					System.out.println("新增訂單時，總金額為="+total);
//					session.setAttribute("total", total);
//					//計算訂單總金額
//					
//					//設定訂單參數//
//					String memNo = (String) session.getAttribute("memno");
//					Integer price = total;
//					String address = req.getParameter("address");
//					StoreOrdVO storeOrdVO = new StoreOrdVO();
//					storeOrdVO.setMemNo(memNo);//會員編號
//					storeOrdVO.setPrice(price);//總金額
//					storeOrdVO.setAddress(address);//地址
//					//設定訂單參數//
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("storeOrdVO", storeOrdVO); //
//						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/cart.jsp");
//						failureView.forward(req, res);
//						return;
//					}
//					StoreOrdService storeOrdSvc = new StoreOrdService();
//					storeOrdVO = storeOrdSvc.addStoreOrd_from_cart(memNo, price, address,cart);System.out.println("servlet memNo="+memNo);
//					String url = "/front-end/store/storeOrd_list_all.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);
//					successView.forward(req, res);
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_add.jsp");
//					failureView.forward(req, res);
//				}
//		    }
	}
}
