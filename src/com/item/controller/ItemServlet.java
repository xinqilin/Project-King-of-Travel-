package com.item.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.item.model.*;

@WebServlet("/uploadServlet3.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ItemServlet extends HttpServlet {

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
				String str = req.getParameter("itemNo");
				String itemsNo = null;
				String itemNoReg = "^[(a-zA-Z0-9)]{5}$";
				itemsNo = new String(str.toUpperCase().trim());
				try {
					if (itemsNo == null || itemsNo.trim().length() == 0) {
						errorMsgs.add("請輸入商品編號");
					} else if (!itemsNo.trim().matches(itemNoReg)) {
						errorMsgs.add("商品編號: 只能是英文字母、數字 , 且長度必需是5之間");
					}
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(itemsNo);
				if (itemVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemVO", itemVO);
				String url = "/back-end/store/item_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

		if ("getOne_For_Update".equals(action)) { // 來自listAllItem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String itemNo = new String(req.getParameter("itemNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(itemNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("itemVO", itemVO); // 資料庫取出的ItemVO物件,存入req
				String url = "/back-end/store/item_update_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_item_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_list_all.jsp");
				failureView.forward(req, res);
			}
		}

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String itemNo = new String(req.getParameter("itemNo").trim());
				String str = req.getParameter("itemNo");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String itemName = req.getParameter("itemName");
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}
				
				
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
					if (price == null || price== 0) {
						errorMsgs.add("商品價格不得為0");
					}
				} catch (NumberFormatException e) {
					price = 9999;
					errorMsgs.add("價格請填數字.");
				}
				
				
				Integer amount = null;
				try {
					amount = new Integer(req.getParameter("amount").trim());
					if (amount == null || amount<0) {
						errorMsgs.add("請輸入庫存數量");
					}
				} catch (NumberFormatException e) {
					amount = 0;
					errorMsgs.add("庫存請填數字.");
				}
				String itemDetail = req.getParameter("itemDetail").trim();
				if (itemDetail == null || itemDetail.trim().length() == 0) {
					errorMsgs.add("商品內容請勿空白");
				}

				Integer itemClass = null;
				try {
					itemClass = new Integer(req.getParameter("itemClass").trim());
					if (itemClass == null) {
						errorMsgs.add("請輸入商品類別");
					}
				} catch (NumberFormatException e) {
					itemClass = 1;
					errorMsgs.add("商品類別請填數字.");
				}

				Integer status = null;
				try {
					status = new Integer(req.getParameter("status").trim());
				} catch (NumberFormatException e) {
					status = 0;
					errorMsgs.add("上架狀態請填數字.");
				}

				byte[] picture; // 可以跑的寫法

				ItemService itemSvc = new ItemService();// 取出這項商品資料庫內的圖片
				ItemVO itemVO2 = itemSvc.getOneItem(itemNo);// 新增一個vo物件
				picture = itemVO2.getPicture();// 將itemVO2裡面的圖片指定給pictur;

				Part part = req.getPart("picture");// 查詢這次網頁操作，是否有上傳新的圖片
				String header = part.getHeader("content-disposition");
				String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1))
						.getName();

//				System.out.println("header=" + header); // 顯示header(測試用)
//				System.out.println("filename=" + filename); // 顯示filaname(測試用)				
//================================================
//				if(filename.equals("")) {//如果沒有上傳檔案filename會等於空字串
//					System.out.println("filename確定為空字串");
//				}

				System.out.println("part.getContentType()=" + part.getContentType());
				if (filename.length() != 0 && part.getContentType() != null) {
					InputStream in = part.getInputStream();
					picture = new byte[in.available()];
					in.read(picture);
				}
				ItemVO itemVO = new ItemVO();
				itemVO.setItemNo(itemNo);
				itemVO.setItemName(itemName);
				itemVO.setPrice(price);
				itemVO.setAmount(amount);
				itemVO.setStatus(status);
				itemVO.setItemDetail(itemDetail);
				itemVO.setItemClass(itemClass);
				itemVO.setPicture(picture);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_update_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				itemSvc = new ItemService();
				itemVO = itemSvc.updateItem(itemNo, itemName, price, amount, itemDetail, itemClass, status, picture);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemVO", itemVO); // 資料庫update成功後,正確的的ItemVO物件,存入req
				String url = "/back-end/store/item_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneitem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_update_input.jsp");
				failureView.forward(req, res);
			}
		}

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		if ("insert".equals(action))
		{
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println("有進入 item servlet insert");
				String itemName = req.getParameter("itemName");System.out.println("itemName="+itemName);
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}
				
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}
				System.out.println("price="+price);
				Integer amount = null;
				try {
					amount = new Integer(req.getParameter("amount").trim());
				} catch (NumberFormatException e) {
					amount = 0;
					errorMsgs.add("數量請填數字.");
				}
				System.out.println("amount="+amount);
				String itemDetail = req.getParameter("itemDetail");
				if (itemDetail == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品內容: 請勿空白");
				}
				System.out.println(itemDetail);
				Integer itemClass = null;
				try {
					itemClass = new Integer(req.getParameter("itemClass").trim());
				} catch (NumberFormatException e) {
					itemClass = 0;
					errorMsgs.add("商品類別請填數字.");
				}
				System.out.println("itemClass="+itemClass);
		
				byte[] picture=null; // 可以跑的寫法
				Part part = req.getPart("picture");// 查詢這次網頁操作，是否有上傳新的圖片
				String header = part.getHeader("content-disposition");
				String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1))
						.getName();

//				System.out.println("header=" + header); // 顯示header(測試用)
//				System.out.println("filename=" + filename); // 顯示filaname(測試用)				
//================================================
//				if(filename.equals("")) {//如果沒有上傳檔案filename會等於空字串
//					System.out.println("filename確定為空字串");
//				}

				System.out.println("part.getContentType()=" + part.getContentType());
				if (filename.length() != 0 && part.getContentType() != null) {
					InputStream in = part.getInputStream();
					picture = new byte[in.available()];
					in.read(picture);
				}

				ItemVO itemVO = new ItemVO();
				itemVO.setItemName(itemName);
				itemVO.setPrice(price);
				itemVO.setAmount(amount);
				itemVO.setItemDetail(itemDetail);
				itemVO.setItemClass(itemClass);
				itemVO.setPicture(picture);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ItemVO", itemVO); // 含有輸入格式錯誤的ItemVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ItemService itemSvc = new ItemService();
				itemVO = itemSvc.addItem(itemName, price, amount, itemDetail, itemClass, picture);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/store/item_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllItem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/item_add.jsp");
				failureView.forward(req, res);
			}
		}

//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★	
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
