package com.wishitem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.wishitem.model.WishItemService;
import com.wishitem.model.WishItemVO;

@MultipartConfig
public class WishItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數-輸入格式的錯誤處理**********************/
				String str =(req.getParameter("wishItemNo"));
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/WishItem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
					
				}
				String wishItemNo = str;				
				
				
				/***************************2.開始查詢*****************************************/
				WishItemService wishItemSvc = new WishItemService();
				WishItemVO wishItemVO = wishItemSvc.getOneWishItem(wishItemNo);
				if (wishItemVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/WishItem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("wishItemVO", wishItemVO); // 資料庫取出的wishListVO,存入req
				String url = "/front-end/WishItem/wishItemDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishItem/select_page.jsp");
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
				String wishItemNo = req.getParameter("wishItemNo");

				/***************************2.接收請求參數****************************************/
				WishItemService wishItemSvc = new WishItemService();
				WishItemVO wishItemVO = wishItemSvc.getOneWishItem(wishItemNo);

									
				/***************************3.查詢完成準備轉交(Send the Success view)************/
				req.setAttribute("wishItemVO", wishItemVO);         // req
				String url = "/front-end/WishItem/update_wishItem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//  update_emp_input.jsp
				successView.forward(req, res);
			
				/***************************其他錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishItem/listAllWishItem.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("update".equals(action)) { // 
				
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.**********************/
				String wishItemNo = req.getParameter("wishItemNo");

				String memNo = req.getParameter("memNo");
				String enameReg = "^[(a-zA-Z0-9_)]{7}$";
				if (memNo == null || memNo.trim().length() == 0) {
					errorMsgs.add("會員編號: 不准空白");
				} else if(!memNo.trim().matches(enameReg)) { //
					errorMsgs.add("會員編號: 不准空白,只能照著格式打,例:MEM0001");
	            }
				
				Integer amount = new Integer(req.getParameter("amount").trim());
				if (amount == null || amount == 0) {
					errorMsgs.add("數量不得為0");
				}	
				String itemName =req.getParameter("itemName");
				String enameReg1 = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名: 請勿空白");
				} else if(!itemName.trim().matches(enameReg1)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				Integer itemPrice = new Integer(req.getParameter("itemPrice").trim());
				
				String itemStoreName =req.getParameter("itemStoreName");
				String itemStoreAddr =req.getParameter("itemStoreAddr");
				String itemStoreLati =req.getParameter("itemStoreLati");
				String itemStoreLong =req.getParameter("itemStoreLong");
				Integer buyOrSell = new Integer(req.getParameter("buyOrSell").trim());
				String wishItemDetail =req.getParameter("wishItemdetail");
				InputStream is = req.getPart("wishItemPicture").getInputStream();
				byte[] wishItemPicture = new byte[is.available()];
				is.read(wishItemPicture);
				String wishNote =req.getParameter("wishNote");
				Integer status = new Integer(req.getParameter("status").trim());
				Integer itemType = new Integer(req.getParameter("itemType").trim());
								

				WishItemVO wishItemVO = new WishItemVO();
				wishItemVO.setWishItemNo(wishItemNo);
				wishItemVO.setMemNo(memNo);
				wishItemVO.setAmount(amount);
				wishItemVO.setItemStoreName(itemStoreName);
				wishItemVO.setItemPrice(itemPrice);
				wishItemVO.setItemStoreName(itemStoreName);
				wishItemVO.setItemStoreAddr(itemStoreAddr);
				wishItemVO.setItemStoreLati(itemStoreLati);
				wishItemVO.setItemStoreLong(itemStoreLong);
				wishItemVO.setBuyOrSell(buyOrSell);
				wishItemVO.setWishItemDetail(wishItemDetail);
				wishItemVO.setWishItemPicture(wishItemPicture);
				wishItemVO.setWishNote(wishNote);
				wishItemVO.setStatus(status);
				wishItemVO.setItemType(itemType);
				is.close();
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishItemVO", wishItemVO); //
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/WishItem/update_wishItem_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.*****************************************/
				WishItemService wishItemSvc = new WishItemService();
				wishItemVO = wishItemSvc.updateWishItem(wishItemNo, memNo, amount, itemName, itemPrice, itemStoreName, itemStoreAddr, itemStoreLati, itemStoreLong, buyOrSell, wishItemDetail, wishItemPicture, wishNote, status, itemType);
				/***************************3.(Send the Success view)*************/
				req.setAttribute("wishItemVO", wishItemVO); // 
				String url = "/front-end/WishItem/listOneWishItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishItem/update_wishItem_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.*************************/
			
				String memNo = req.getParameter("memNo");
				
				String enameReg = "^[(a-zA-Z0-9_)]{7}$";
				if (memNo == null || memNo.trim().length() == 0) {
					errorMsgs.add("會員編號: 不准空白");
				} else if(!memNo.trim().matches(enameReg)) { //
					errorMsgs.add("會員編號: 不准空白,只能照著格式打,例:MEM0001");
	            }
				
				Integer amount = new Integer(req.getParameter("wishItemAmount"));
				if (amount == null || amount == 0||amount<0) {
					errorMsgs.add("數量不得為0或是負數");
				}
				
				String itemName =req.getParameter("wishItemName");
				
				String enameReg1 = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名: 請勿空白");
				} else if(!itemName.trim().matches(enameReg1)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				Integer itemPrice = new Integer(req.getParameter("wishItemPrice"));
				
				String itemStoreName =req.getParameter("itemStoreName");
				String itemStoreAddr =req.getParameter("itemStoreAddr");
				String itemStoreLati =req.getParameter("itemStoreLati");
				String itemStoreLong =req.getParameter("itemStoreLong");
				Integer buyOrSell = new Integer(req.getParameter("buyOrSell"));
				String wishItemDetail =req.getParameter("wishItemDetail");
				InputStream is = req.getPart("wishItemPicture").getInputStream();
				byte[] wishItemPicture = new byte[is.available()];
				is.read(wishItemPicture);
				
				String wishNote =req.getParameter("wishNote");
				Integer status = new Integer(req.getParameter("status"));
				Integer itemType = new Integer(req.getParameter("wishItemType"));
				

				WishItemVO wishItemVO = new WishItemVO();
				wishItemVO.setMemNo(memNo);
				wishItemVO.setAmount(amount);
				wishItemVO.setItemStoreName(itemStoreName);
				wishItemVO.setItemPrice(itemPrice);
				wishItemVO.setItemStoreName(itemStoreName);
				wishItemVO.setItemStoreAddr(itemStoreAddr);
				wishItemVO.setItemStoreLati(itemStoreLati);
				wishItemVO.setItemStoreLong(itemStoreLong);
				wishItemVO.setBuyOrSell(buyOrSell);
				wishItemVO.setWishItemDetail(wishItemDetail);
				wishItemVO.setWishItemPicture(wishItemPicture);
				wishItemVO.setWishNote(wishNote);
				wishItemVO.setStatus(status);
				wishItemVO.setItemType(itemType);
				is.close();
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishItemVO", wishItemVO); //
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/WishItem/add_WishItem.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.***************************************/
				WishItemService wishItemSvc = new WishItemService();
				wishItemVO = wishItemSvc.addWishItem(memNo, amount, itemName, itemPrice, itemStoreName, itemStoreAddr, itemStoreLati, itemStoreLong, buyOrSell, wishItemDetail, wishItemPicture, wishNote, status, itemType);	
				
				/***************************3.(Send the Success view)***********/
				req.setAttribute("wishItemVO", wishItemVO); // 
				String url = "/front-end/WishItem/wishItemHomepage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
				
				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishItem/add_WishItem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.***************************************/
				String wishItemNo = req.getParameter("wishItemNo");
				
				/***************************2.***************************************/
				WishItemService wishItemSvc = new WishItemService();
				wishItemSvc.deleteWishItem(wishItemNo);;
				
				/***************************3.*********/								
				String url = "/front-end/WishItem/listAllWishItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 
				successView.forward(req, res);
				
				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishItem/listAllWishItem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
