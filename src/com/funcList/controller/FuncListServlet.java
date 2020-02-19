package com.funcList.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.funcList.model.FuncListService;
import com.funcList.model.FuncListVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class FuncListServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {	//來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("funcNo");
				if(str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入功能編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/select_page.jsp");
					failureView.forward(req, res);
					return;		//程式中斷
				}
				
				String funcNo = null;
				try {
					funcNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("功能編號格式錯誤");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/select_page.jsp");
					failureView.forward(req, res);
					return;		//程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				FuncListService funcSvc = new FuncListService();
				FuncListVO funcListVO = funcSvc.getOneFunc(funcNo);
				if(funcNo == null) {
					errorMsgs.add("查無資料");
				}
				if(!funcNo.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/select_page.jsp");
					failureView.forward(req, res);
					return;		//程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("funcListVO", funcListVO);
				String url = "/back-end/funclist/select_page.jsp/";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {	//	來自listAllFunc.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String funcNo = new String(req.getParameter("funcNo"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				FuncListService funcSvc = new FuncListService();
				FuncListVO funcListVO = funcSvc.getOneFunc(funcNo);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("funcListVO", funcListVO);		// 資料庫取出的funListVO物件,存入req
				String url = "/back-end/funclist/update_func_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);		//// 成功轉交 update_func_input.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/listAllFunc.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_func_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String funcNo = new String(req.getParameter("funcNo").trim());

				// 功能名稱funcName
				String funcName = req.getParameter("funcName");
				String funcNameReg = "[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (funcName == null || funcName.trim().length() == 0) {
					errorMsgs.add("功能名稱：請勿空白");
				} else if (!funcName.trim().matches(funcNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("功能名稱：只能是中、英文字母、數字和_，且長度必須在2到10之間");
				}

				FuncListVO funcListVO = new FuncListVO();
				funcListVO.setFuncNo(funcNo);
				funcListVO.setFuncName(funcName);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("funListVo", funcListVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/update_func_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				FuncListService funcSvc = new FuncListService();
				funcListVO = funcSvc.updateFunc(funcNo, funcName);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/back-end/funclist/listOneFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFunc.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/update_func_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addFunc.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String funcName = req.getParameter("funcName");
				String funcNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (funcName == null || funcName.trim().length() == 0) {
					errorMsgs.add("功能名稱：請勿空白");
				} else if (!funcName.trim().matches(funcNameReg)) {
					errorMsgs.add("功能名稱：只能是中、英文字母、數字和_，且長度必須在2到10之間");
				}

				FuncListVO funcListVO = new FuncListVO();
				funcListVO.setFuncName(funcName);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("funcListVO", funcListVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/addFunc.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				FuncListService funcSvc = new FuncListService();
				funcListVO = funcSvc.addFunc(funcName);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/funclist/listAllFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFunc.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/addFunc.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllFunc.jsp

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String funcNo = new String(req.getParameter("funcNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				FuncListService funcSvc = new FuncListService();
				funcSvc.deleteFunc(funcNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/funclist/listAllFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/funclist/listAllFunc.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
