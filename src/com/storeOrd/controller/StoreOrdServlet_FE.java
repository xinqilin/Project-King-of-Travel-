package com.storeOrd.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.item.model.ItemVO;
import com.storeOrd.model.*;

public class StoreOrdServlet_FE extends HttpServlet {
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
							.getRequestDispatcher("/front-end/store/storeOrd_select_page.jsp");
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
							.getRequestDispatcher("/front-end/store/storeOrd_select_page.jsp");
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
							.getRequestDispatcher("/front-end/store/storeOrd_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("storeOrdVO", storeOrdVO); // 資料庫取出的StoreOrdVO物件,存入req
				String url = "/front-end/store/storeOrd_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_select_page.jsp");
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
				System.out.println("StoreOrdServlet:storeordvo.address=" + storeOrdVO.getAddress());
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("storeOrdVO", storeOrdVO);
				String url = "/front-end/store/storeOrd_update_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_list_all.jsp");
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
				Integer price = new Integer(req.getParameter("price").trim());
				String address = req.getParameter("address");
				Integer status = new Integer(req.getParameter("status").trim());
				Integer paymentMethod = new Integer(req.getParameter("paymentMethod").trim());
				StoreOrdVO storeOrdVO = new StoreOrdVO();
				
				
				storeOrdVO.setOrdNo(ordNo);
				storeOrdVO.setMemNo(memNo);
				storeOrdVO.setPrice(price);
				storeOrdVO.setAddress(address);
				storeOrdVO.setStatus(status);
				storeOrdVO.setPaymentMethod(paymentMethod);
				System.out.println(address);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeOrdVO", storeOrdVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/store/storeOrd_update_input.jsp");
					failureView.forward(req, res);
					return;
					

				}
				StoreOrdService storeOrdSvc = new StoreOrdService();
				storeOrdVO = storeOrdSvc.update(ordNo, memNo, price, address, status, paymentMethod);
				req.setAttribute("storeOrdVO", storeOrdVO);
				
				String url = "/front-end/store/storeOrd_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_update_input.jsp");
				failureView.forward(req, res);
			}
		}

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
//		似乎用不到
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

//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		//顧客結帳(成立訂單)
		if ("check_out".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				try {
					HttpSession session = req.getSession();
					LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");
				    //計算訂單總金額
					Integer total;
				    total = cart.entrySet().stream()
				    		.mapToInt(e -> e.getKey().getPrice() * e.getValue())
			    			.sum();
					System.out.println("新增訂單時，總金額為="+total);
					session.setAttribute("total", total);
					//設定訂單參數//
					String memNo = (String) session.getAttribute("memno");
					Integer price = total;
					Integer paymentMethod = new Integer(req.getParameter("paymentMethod").trim());
					String address = req.getParameter("address");
					StoreOrdVO storeOrdVO = new StoreOrdVO();
					storeOrdVO.setMemNo(memNo);//會員編號
					storeOrdVO.setPrice(price);//總金額   
					storeOrdVO.setAddress(address);System.out.println("servlet 地址"+address);
					storeOrdVO.setPaymentMethod(paymentMethod);
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("storeOrdVO", storeOrdVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/cart.jsp");
						failureView.forward(req, res);
						return;
					}
					StoreOrdService storeOrdSvc = new StoreOrdService();
					storeOrdVO = storeOrdSvc.addStoreOrd_from_cart(memNo, price, address,paymentMethod,cart);
					
					//發送E-mail
					String e_mail =(String) req.getParameter("e_mail");
					MailService mailService = new MailService();
					System.out.println("email="+e_mail);
					
					
					String message="會員編號："+memNo+"、購買總金額："+price+"元，我們將盡速為您出貨。";
					
					
					
					mailService.sendMail(e_mail,"[遊記王] 謝謝您的訂單！",message);
					session.setAttribute("cart", null);
					String url = "/front-end/store/storeOrd_list_all.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_list_all.jsp");
					failureView.forward(req, res);
				}
		    }
		
		// ██████████████████████████████████████████
		//顧客修改訂單狀態
		if ("change_ord_status".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ordNo = req.getParameter("ordNo").trim();
				StoreOrdVO storeOrdVO = new StoreOrdVO();
				storeOrdVO.setOrdNo(ordNo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeOrdVO", storeOrdVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/store/storeOrd_list_all.jsp");
					failureView.forward(req, res);
					return;
				}
				StoreOrdService storeOrdSvc = new StoreOrdService();
				storeOrdVO = storeOrdSvc.change_ord_status(ordNo);

				
				String url = "/front-end/store/storeOrd_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/storeOrd_update_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		
		
	
	}
}
