package com.f_item.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f_item.model.F_ItemService;

public class F_ItemServlet extends HttpServlet {
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
		if ("add".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("erroeMsgs", errorMsgs);
			String memNo = null;
			String itemNo = null;
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memNo");
				String str2 = req.getParameter("itemNo");
				System.out.println("servlet memno=" + str);
				System.out.println("servlet itemno=" + str2);
				memNo = str;
				itemNo = str2;
			} catch (Exception e) {
				errorMsgs.add("會員編號不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/item_list_all.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			F_ItemService f_itemSvc = new F_ItemService();
			f_itemSvc.add(memNo, itemNo);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/item_list_all.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			String url = "/front-end/store/item_list_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
			
		
		if ("del".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("erroeMsgs", errorMsgs);
			String memNo =null;
			String itemNo = null;
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memNo");
				String str2 = req.getParameter("itemNo");
				memNo =str;
				itemNo =str2;
				} catch (Exception e) {
					errorMsgs.add("會員編號不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/item_list_all.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				F_ItemService f_itemSvc = new F_ItemService();
				f_itemSvc.del(memNo, itemNo);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/item_list_all.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/front-end/store/item_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} 
		}
//我是分隔線
	}

