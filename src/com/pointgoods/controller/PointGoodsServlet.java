package com.pointgoods.controller;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.mem.model.MemVO;
import com.pointgoods.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常

public class PointGoodsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Set<String> actionset = new HashSet<String>();
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String pointgoodsno = req.getParameter("pointgoodsno");
				if (pointgoodsno == null || (pointgoodsno.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgoods/selectPage.jsp");
					failureView.forward(req, res);
					return;
				}
			//=====開始查詢資料======
				PointGoodsService pgSvc = new PointGoodsService();
				PointGoodsVO pointgoodsVO = pgSvc.getOnePointGoods(pointgoodsno);
				if (pointgoodsVO == null) {
					errorMsgs.add("查無此積分商品");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgoods/selectPage.jsp");
					failureView.forward(req, res);
					return;
				}
			//=====查詢完成,準備轉交======
				req.setAttribute("pointgoodsVO", pointgoodsVO);
				String url = "/back-end/pointgoods/listOnePointGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			//=====其他可能的錯誤處裡=====
			} catch ( Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgoods/selectPage.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//=====接收請求參數=====
				String pointgoodsno = new String(req.getParameter("pointgoodsno"));
				//=====開始查詢資料=====
				PointGoodsService pointgoodsSvc = new PointGoodsService();
				PointGoodsVO pointgoodsVO = pointgoodsSvc.getOnePointGoods(pointgoodsno);
				//=====查詢完成準備轉交=====
				req.setAttribute("pointgoodsVO", pointgoodsVO);
				String url = "/back-end/pointgoods/updatePointGoodsInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				//=====其他可能的錯誤處裡=====
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pointgoods/listAllPointGoods.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//=====接收請求參數,錯誤處理=====
				String pointgoodsno = req.getParameter("pointgoodsno").trim();
				
				PointGoodsService pointgoodsSvc = new PointGoodsService();
				PointGoodsVO pointgoodsVO = pointgoodsSvc.getOnePointGoods(pointgoodsno);
				
				String pointgoodsname = req.getParameter("pointgoodsname");
				String pointgoodsnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(!~*)]{2,30}$";
				if (pointgoodsname == null || pointgoodsname.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				} else if (!pointgoodsname.trim().matches(pointgoodsnameReg)) {
					errorMsgs.add("產品名稱只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer pointgoodsquantity = null;
				try {
					pointgoodsquantity = new Integer(req.getParameter("pointgoodsquantity").trim());
				} catch (Exception e) {
					errorMsgs.add("產品數量請輸入數字");
				}
				
				Integer needpoints = null;
				try {
					needpoints = new Integer(req.getParameter("needpoints").trim());
				} catch (Exception e) {
					errorMsgs.add("需求點數請輸入數字");
				}
				
				String pointgoodsdesc = req.getParameter("pointgoodsdesc").trim();
				if (pointgoodsdesc == null || pointgoodsdesc.length() == 0) {
					errorMsgs.add("產品描述請勿空白");
				}
				
				Part part = req.getPart("pointgoodspic");
				InputStream in = part.getInputStream();
				byte[] pointgoodspic = null;
				if (in.available() == 0) {
					pointgoodspic = pointgoodsVO.getPointgoodspic();
				} else {
					byte[] picarr = new byte[in.available()];
					in.read(picarr);
					pointgoodspic = picarr;
					in.close();
				}
				
				Integer pointgoodsstatus = new Integer(req.getParameter("pointgoodsstatus").trim());
				
				pointgoodsVO.setPointgoodsno(pointgoodsno);
				pointgoodsVO.setPointgoodsname(pointgoodsname);
				pointgoodsVO.setPointgoodsquantity(pointgoodsquantity);
				pointgoodsVO.setNeedpoints(needpoints);
				pointgoodsVO.setPointgoodsdesc(pointgoodsdesc);
				pointgoodsVO.setPointgoodspic(pointgoodspic);
				pointgoodsVO.setPointgoodsstatus(pointgoodsstatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointgoodsVO", pointgoodsVO); // 含有輸入格式錯誤的pointgoodsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/pointgoods/updatePointGoodsInput.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//=====開始修改資料=====
//				PointGoodsService pointgoodsSvc = new PointGoodsService();
				pointgoodsVO = pointgoodsSvc.updatePointGoods(pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus);
				
				//=====修改完成準備轉交=====
				String url = "/back-end/pointgoods/listAllPointGoods.jsp";
				req.setAttribute("in", 2);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pointgoods/updatePointGoodsInput.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//=====接收請求參數,錯誤處理=====
				String pointgoodsname = req.getParameter("pointgoodsname");
				String pointgoodsnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(!~*)]{2,30}$";
				if (pointgoodsname == null || pointgoodsname.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				} else if (!pointgoodsname.trim().matches(pointgoodsnameReg)) {
					errorMsgs.add("產品名稱只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer pointgoodsquantity = null;
				try {
					pointgoodsquantity = new Integer(req.getParameter("pointgoodsquantity").trim());
				} catch (Exception e) {
					errorMsgs.add("產品數量請輸入數字");
				}
				
				Integer needpoints = null;
				try {
					needpoints = new Integer(req.getParameter("needpoints").trim());
				} catch (Exception e) {
					errorMsgs.add("需求點數請輸入數字");
				}
				
				String pointgoodsdesc = req.getParameter("pointgoodsdesc").trim();
				if (pointgoodsdesc == null || pointgoodsdesc.length() == 0) {
					errorMsgs.add("產品描述請勿空白");
				}
				
				Part part = req.getPart("pointgoodspic");
				InputStream in = part.getInputStream();
				byte[] pointgoodspic = null;
				if (in.available() == 0) {
					int i;
					int j = 0;
					File pic = new File("D:\\nopic.png");
					FileInputStream fis = new FileInputStream(pic);
					byte[] picarr = new byte[fis.available()];
					while ((i = fis.read()) != -1) {
						picarr[j++] = (byte) i;
						pointgoodspic = picarr;
					}
					fis.close();
					in.close();
				} else {
					byte[] picarr = new byte[in.available()];
					in.read(picarr);
					pointgoodspic = picarr;
					in.close();
				}
				
				Integer pointgoodsstatus = new Integer(req.getParameter("pointgoodsstatus").trim());
				
				PointGoodsVO pointgoodsVO = new PointGoodsVO();
				pointgoodsVO.setPointgoodsname(pointgoodsname);
				pointgoodsVO.setPointgoodsquantity(pointgoodsquantity);
				pointgoodsVO.setNeedpoints(needpoints);
				pointgoodsVO.setPointgoodsdesc(pointgoodsdesc);
				pointgoodsVO.setPointgoodspic(pointgoodspic);
				pointgoodsVO.setPointgoodsstatus(pointgoodsstatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointgoodsVO", pointgoodsVO); // 含有輸入格式錯誤的pointgoodsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/pointgoods/addPointGoods.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//=====開始新增資料=====
				PointGoodsService pointgoodsSvc = new PointGoodsService();
				pointgoodsVO = pointgoodsSvc.addPointGoods(pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus);
				
				//=====新增完成準備轉交=====
				String url = "/back-end/pointgoods/listAllPointGoods.jsp";
				req.setAttribute("in", 1);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				//=====其他可能的錯誤處理=====
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pointgoods/selectPage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String pointgoodsno = req.getParameter("pointgoodsno");
				
				/***************************2.開始刪除資料***************************************/
				PointGoodsService pointgoodsSvc = new PointGoodsService();
				pointgoodsSvc.deletePointGoods(pointgoodsno);;
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/pointgoods/listAllPointGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pointgoods/listAllPointGoods.jsp");
				failureView.forward(req, res);
			}
		}
		
		HttpSession session = req.getSession();
		Vector<PointGoodsVO> buylist = (Vector<PointGoodsVO>)session.getAttribute("shoppingcartp");
		MemVO memVO = (MemVO)session.getAttribute("accountVO");
		Boolean checkout = false;
		
		if (!"checkout".equals(action)) {
			if (("remove").equals(action)) {
				checkout = true;
				String del = req.getParameter("remove");
				Predicate<PointGoodsVO> condition = pointgoodsVO -> pointgoodsVO.getPointgoodsno().equals(del);
				buylist.removeIf(condition);
			} 
			else if (("add").equals(action)) {
				boolean match = false;
				checkout = true;	
				String pointgoodsno = req.getParameter("pointgoodsno");
				String pointgoodsname = req.getParameter("pointgoodsname");
				Integer pointgoodsquantity = new Integer(req.getParameter("pointgoodsquantity"));
				Integer needpoints = new Integer(req.getParameter("needpoints"));

				PointGoodsVO pointgoodsVO = new PointGoodsVO();

				pointgoodsVO.setPointgoodsno(pointgoodsno);
				pointgoodsVO.setPointgoodsname(pointgoodsname);
				pointgoodsVO.setPointgoodsquantity(pointgoodsquantity);
				pointgoodsVO.setNeedpoints(needpoints);

				if (buylist == null) {
					buylist = new Vector<PointGoodsVO>();
					buylist.add(pointgoodsVO);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						PointGoodsVO pointgoodsVObuy = buylist.get(i);
						if (pointgoodsVO.getPointgoodsno().equals(pointgoodsVObuy.getPointgoodsno())) {
							pointgoodsVObuy.setPointgoodsquantity(
									pointgoodsVO.getPointgoodsquantity() + pointgoodsVObuy.getPointgoodsquantity());
							match = true;
						}
					}
					if (!match) {
						buylist.add(pointgoodsVO);
					}
				}
			}
			else if(("modifyq").equals(action)) {
				checkout = true;
				String pointgoodsno = req.getParameter("pointgoodsno");
				Integer pointgoodsquantity = new Integer(req.getParameter("pointgoodsquantity"));
				for (int i = 0; i < buylist.size(); i++) {
					PointGoodsVO pointgoodsVObuy = buylist.get(i);
					if (pointgoodsno.equals(pointgoodsVObuy.getPointgoodsno())) {
						pointgoodsVObuy.setPointgoodsquantity(pointgoodsquantity); }
				}
			}
			if(checkout == true) {
			session.setAttribute("shoppingcartp", buylist);
			session.setAttribute("shoppingcartq", buylist.size());
			int cartq = buylist.size();
			
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				PointGoodsVO order = buylist.get(i);
				int points = order.getNeedpoints();
				int quantity = order.getPointgoodsquantity();
				total += (points * quantity);
			}
			session.setAttribute("total", total);
			
			HashMap map = new HashMap();
			map.put("cartquantity", cartq);
			map.put("cartamount", total);
	        JSONObject responseJSONObject = new JSONObject(map);
	        PrintWriter out = res.getWriter();
	        out.println(responseJSONObject);
			}
		}
		else if (("checkout").equals(action)) {
//			int total = 0;
//			for (int i = 0; i < buylist.size(); i++) {
//				PointGoodsVO order = buylist.get(i);
//				int points = order.getNeedpoints();
//				int quantity = order.getPointgoodsquantity();
//				total += (points * quantity);
//			}

//				String amount = String.valueOf(total);
			String amount = (String) session.getAttribute("total");

//			req.setAttribute("amount", amount);
			String url = "/front-end/pointgoods/checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		if("checkmoney".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer currentPoints = memVO.getPoints();
				int totalPoints = (int)session.getAttribute("total");
				if (currentPoints < totalPoints) {
					errorMsgs.add("抱歉,您的點數餘額不足,請調整數量...");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pointgoods/checkout.jsp");
					failureView.forward(req, res);
					return;
				}
				String url = "/front-end/pointgoodsord/ordconfirm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/pointgoods/listAllPointGoodsF.jsp");
				failureView.forward(req, res);
			}
		}
	}	
}
