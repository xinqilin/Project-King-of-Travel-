package com.wishord.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.wishitem.model.WishItemService;
import com.wishitem.model.WishItemVO;
import com.wishord.model.*;

public class WishOrdServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("sellcheckout".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<WishItemVO> sellList = new ArrayList();
			HttpSession session = req.getSession();
			sellList = (ArrayList)session.getAttribute("buyList");
			try {
				/***********************1.*************************/
				String buyMemNo1 = req.getParameter("buyMemNo1");
				String wishMemNo1 = req.getParameter("wishMemNo1");
				Integer wishOrdStatus1 = new Integer(req.getParameter("wishOrdStatus1").trim());
				Integer price1 = new Integer(req.getParameter("price1").trim());
				java.sql.Date dayofest = new Date(System.currentTimeMillis());
				try {
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
					dayofest = java.sql.Date.valueOf(sdf.format(dayofest));
				} catch (IllegalArgumentException e) {
					dayofest = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				java.sql.Date wishSendDate1 = null;
				try {
					wishSendDate1 = java.sql.Date.valueOf(req.getParameter("wishSendDate1").trim());
				} catch (IllegalArgumentException e) {
					wishSendDate1 = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				String buyNote1 = req.getParameter("buyNote1");
				java.sql.Date lastDate1 = null;
				try {
					lastDate1 = java.sql.Date.valueOf(req.getParameter("lastDate1").trim());
				} catch (IllegalArgumentException e) {
					lastDate1 = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				String address1 = req.getParameter("address1");
				if (address1 == null) {
					errorMsgs.add("請輸入地址");
				}
				String zone1 = req.getParameter("zone1");
				String zone2 = req.getParameter("zone2");
				String addr = zone1 + zone2 + address1;
				// Send the use back to the form, if there were errors
				
				WishOrdVO wishOrdVO = new WishOrdVO();
				wishOrdVO.setBuyMemNo(buyMemNo1);
				wishOrdVO.setWishMemNo(wishMemNo1);
				wishOrdVO.setDayOfEst(dayofest);
				wishOrdVO.setWishOrdStatus(wishOrdStatus1);
				wishOrdVO.setWishOrdTotalPrice(price1);
				wishOrdVO.setWishSendDate(wishSendDate1);
				wishOrdVO.setWishOrdBuyNote(buyNote1);
				wishOrdVO.setLastDate(lastDate1);
				wishOrdVO.setWishOrdSendAddr(addr);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishOrdVO", wishOrdVO); //
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/WishOrd/sell_CheckOut.jsp");
					failureView.forward(req, res);
					return; 
				}				
				/***************************2.***************************************/
				
				if(sellList.size() != 0) {
					WishOrdDAO dao = new WishOrdDAO();
					dao.insertWithWishDetail(wishOrdVO, sellList);
				}

				/***************************3.(Send the Success view)***********/
				req.setAttribute("wishOrdVO", wishOrdVO); // 
				String url = "/front-end/WishOrd/listMyWishOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
				
				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishOrd/sell_CheckOut.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		if ("finish".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
			String wishord=(String)req.getParameter("wishord");
			WishOrdService wishSvc=new WishOrdService();
			WishOrdVO wishO=wishSvc.getOneWishOrd(wishord);
			wishSvc.updateWishOrd(wishO.getWishOrdNo(), wishO.getBuyMemNo(), wishO.getWishMemNo(), wishO.getDayOfEst(), 2, wishO.getWishOrdTotalPrice(), wishO.getWishSendDate(), wishO.getWishOrdBuyNote(), wishO.getLastDate(), wishO.getWishOrdSendAddr());
			
			String url = "/front-end/WishOrd/listMyWishOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 
			successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishOrd/listMyWishOrd.jsp");
				failureView.forward(req, res);
			}
		
		}
		
		if ("sendwishitem".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
			String wishord=(String)req.getParameter("wishord");
			WishOrdService wishSvc=new WishOrdService();
			WishOrdVO wishO=wishSvc.getOneWishOrd(wishord);
			wishSvc.updateWishOrd(wishO.getWishOrdNo(), wishO.getBuyMemNo(), wishO.getWishMemNo(), wishO.getDayOfEst(), 1, wishO.getWishOrdTotalPrice(), wishO.getWishSendDate(), wishO.getWishOrdBuyNote(), wishO.getLastDate(), wishO.getWishOrdSendAddr());
			
			String url = "/front-end/WishOrd/listMyWishOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 
			successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishOrd/listMyWishOrd.jsp");
				failureView.forward(req, res);
			}
		
		}
		
		
		
		if ("buycheckout".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<WishItemVO> buyList = new ArrayList();
			HttpSession session = req.getSession();
			buyList = (ArrayList)session.getAttribute("sellList");
			try {
				/***********************1.*************************/
				String buyMemNo1 = req.getParameter("buyMemNo1");
				String wishMemNo1 = req.getParameter("wishMemNo1");
				Integer wishOrdStatus1 = new Integer(req.getParameter("wishOrdStatus1").trim());
				Integer price1 = new Integer(req.getParameter("price1").trim());
				java.sql.Date dayofest = new Date(System.currentTimeMillis());
				try {
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
					dayofest = java.sql.Date.valueOf(sdf.format(dayofest));
				} catch (IllegalArgumentException e) {
					dayofest = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				java.sql.Date wishSendDate1 = null;
				try {
					wishSendDate1 = java.sql.Date.valueOf(req.getParameter("wishSendDate1").trim());
				} catch (IllegalArgumentException e) {
					wishSendDate1 = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				String buyNote1 = req.getParameter("buyNote1");
				java.sql.Date lastDate1 = null;
				try {
					lastDate1 = java.sql.Date.valueOf(req.getParameter("lastDate1").trim());
				} catch (IllegalArgumentException e) {
					lastDate1 = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				String address1 = req.getParameter("address1");
				if (address1 == null) {
					errorMsgs.add("請輸入地址");
				}
				String zone1 = req.getParameter("zone1");
				String zone2 = req.getParameter("zone2");
				String addr = zone1 + zone2 + address1;
				// Send the use back to the form, if there were errors
				
				WishOrdVO wishOrdVO = new WishOrdVO();
				wishOrdVO.setBuyMemNo(buyMemNo1);
				wishOrdVO.setWishMemNo(wishMemNo1);
				wishOrdVO.setDayOfEst(dayofest);
				wishOrdVO.setWishOrdStatus(wishOrdStatus1);
				wishOrdVO.setWishOrdTotalPrice(price1);
				wishOrdVO.setWishSendDate(wishSendDate1);
				wishOrdVO.setWishOrdBuyNote(buyNote1);
				wishOrdVO.setLastDate(lastDate1);
				wishOrdVO.setWishOrdSendAddr(addr);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishOrdVO", wishOrdVO); //
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/WishOrd/buy_CheckOut.jsp");
					failureView.forward(req, res);
					return; 
				}				
				/***************************2.***************************************/
				
				if(buyList.size() != 0) {
					WishOrdDAO dao = new WishOrdDAO();
					dao.insertWithWishDetail(wishOrdVO, buyList);
				}

				/***************************3.(Send the Success view)***********/
				req.setAttribute("wishOrdVO", wishOrdVO); // 
				String url = "/front-end/WishOrd/listMyWishOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
				
				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/WishOrd/buy_CheckOut.jsp");
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
				String wishOrdNo = req.getParameter("wishOrdNo");
				
				/***************************2.***************************************/
				WishOrdService wishOrdSvc = new WishOrdService();
				wishOrdSvc.deleteWishOrd(wishOrdNo);
				
				/***************************3.*********/								
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 
				successView.forward(req, res);
				
				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
		if ("addCart".equals(action)) { //

			
			try {
				
				List<WishItemVO> buyList = new ArrayList();
				List<WishItemVO> sellList = new ArrayList();
				
				HttpSession session = req.getSession();
				if(session.getAttribute("buyList")!=null) {
					buyList = (ArrayList)session.getAttribute("buyList");
				}
				if(session.getAttribute("sellList")!=null) {					
					sellList = (ArrayList)session.getAttribute("sellList");
				}
				/***************************1.***************************************/
				String wishItemNo = req.getParameter("wishItemNo");
				WishItemService wishItemService = new WishItemService();
				
				WishItemVO wishItemVO = new WishItemVO();
				for(int i=0;i<wishItemService.getAll().size();i++) {
					if(wishItemService.getAll().get(i).getWishItemNo().equals(wishItemNo)) {
						wishItemVO = wishItemService.getAll().get(i);
					}
				}
			
				/***************************2.***************************************/
				if (wishItemVO.getBuyOrSell()==0) {
					int i;
					i=buyList.size();
					if (i<=0) {
						buyList.add(0, wishItemVO);
					}else {
						buyList.add(i, wishItemVO);
					}
				}
				if (wishItemVO.getBuyOrSell()==1) {
					int i;
					i=sellList.size();
					if (i<=0) {
						sellList.add(0, wishItemVO);
					}else {
						sellList.add(i, wishItemVO);
					}
				}

				/***************************3.*********/	
				session.setAttribute("buyList", buyList);
				session.setAttribute("sellList", sellList);
				if ("加入購物車".equals(req.getParameter("type"))) {
					String url ="/front-end/WishItem/wishItemHomepage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 
					successView.forward(req, res);
				return;
				}
				if ("結帳".equals(req.getParameter("type"))) {
					String url ="/front-end/WishOrd/Cart.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 
					successView.forward(req, res);
				}
				/*************************************************************/
			} catch (Exception e) {
			System.out.println("錯了印一下");			}
		}
if ("remove".equals(action)) { //

	
	try {
		List<WishItemVO> buyList = new ArrayList();
		List<WishItemVO> sellList = new ArrayList();
		HttpSession session = req.getSession();
		if(session.getAttribute("buyList")!=null) {
			buyList = (ArrayList)session.getAttribute("buyList");
		}
		if(session.getAttribute("sellList")!=null) {					
			sellList = (ArrayList)session.getAttribute("sellList");
		}
		/***************************1.***************************************/
		String wishItemNo = req.getParameter("wishItemNo");
		String buyOrSell = req.getParameter("buyOrSell");
		if(buyOrSell.equals("0")) {
			for(int i=0;i<buyList.size();i++) {
				if(buyList.get(i).getWishItemNo().equals(wishItemNo)) {
					buyList.remove(i);
				}
			}
		}else {
			for(int i=0;i<sellList.size();i++) {
				if(sellList.get(i).getWishItemNo().equals(wishItemNo)) {
					sellList.remove(i);
				}
			}
		}	
		session.setAttribute("buyList", buyList);
		session.setAttribute("sellList", sellList);
		String url ="/front-end/WishOrd/Cart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 
			successView.forward(req, res);
		
		/*************************************************************/
	} catch (Exception e) {
	System.out.println("錯了印一下");			}
}
	}
}
