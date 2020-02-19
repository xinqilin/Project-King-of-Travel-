package com.administrator.controller;

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

import com.administrator.model.AdministratorService;
import com.administrator.model.AdministratorVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class AdministratorServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("adminNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String adminNo = null;
				try {
					adminNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdministratorService adminSvc = new AdministratorService();
				AdministratorVO administratorVO = adminSvc.getOneAdmin(adminNo);
				if (administratorVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("administratorVO", administratorVO); // 資料庫取出的administratorVO物件,存入req
				String url = "/back-end/admin/listOneAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdmin.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdmin.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String adminNo = new String(req.getParameter("adminNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdministratorService adminSvc = new AdministratorService();
				AdministratorVO administratorVO = adminSvc.getOneAdmin(adminNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("administratorVO", administratorVO);		// 資料庫取出的administratorVO物件,存入req
				String url = "/back-end/admin/update_admin_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);		//// 成功轉交 update_admin_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllAdmin.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_admin_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String adminNo = new String(req.getParameter("adminNo").trim());

				// 管理員姓名adminName
				String adminName = req.getParameter("adminName");
				String adminNameReg = "[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.add("管理員姓名：請勿空白");
				} else if (!adminName.trim().matches(adminNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名：只能是中、英文字母、數字和_，且長度必須在2到10之間");
				}

				// 管理員信箱e_mail
				String e_mail = req.getParameter("e_mail");
				String e_mailReg = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
				if (e_mail == null || e_mail.trim().length() == 0) {
					errorMsgs.add("Email:請勿空白");
				} else if (!e_mail.trim().matches(e_mailReg)) {
					errorMsgs.add("Email:XXXXX@OOOmail.com");
				}

				// 管理員密碼adminPasswd
				String adminPassWd = req.getParameter("adminPassWd");
				String adminPassWdReg = "^[a-zA-Z0-9]{6,12}$";
				if (adminPassWd == null || adminPassWd.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!adminPassWd.trim().matches(adminPassWdReg)) {
					errorMsgs.add("請輸入6-12位包含大小寫英文字母及數字");
				}

				AdministratorVO administratorVO = new AdministratorVO();
				administratorVO.setAdminNo(adminNo);
				administratorVO.setAdminName(adminName);
				administratorVO.setE_mail(e_mail);
				administratorVO.setAdminPassWd(adminPassWd);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("administratorVO", administratorVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_admin_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdministratorService adminSvc = new AdministratorService();
				administratorVO = adminSvc.updateAdmin(adminNo, adminName, e_mail, adminPassWd);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/back-end/admin/listOneAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdmin.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_admin_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addAdmin.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String adminName = req.getParameter("adminName");
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.add("管理員姓名：請勿空白");
				} else if (!adminName.trim().matches(adminNameReg)) {
					errorMsgs.add("管理員姓名：只能是中、英文字母、數字和_，且長度必須在2到10之間");
				}

				// 管理員信箱e_mail
				String e_mail = req.getParameter("e_mail");
				String e_mailReg = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
				if (e_mail == null || e_mail.trim().length() == 0) {
					errorMsgs.add("Email:請勿空白");
				} else if (!e_mail.trim().matches(e_mailReg)) {
					errorMsgs.add("Email:XXXXX@OOOmail.com");
				}

				// 管理員密碼adminPasswd
				String adminPassWd = req.getParameter("adminPassWd");
				String adminPassWdReg = "^[a-zA-Z0-9]{6,12}$";
				if (adminPassWd == null || adminPassWd.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!adminPassWd.trim().matches(adminPassWdReg)) {
					errorMsgs.add("請輸入6-12位包含大小寫英文字母及數字");
				}

				AdministratorVO administratorVO = new AdministratorVO();
				administratorVO.setAdminName(adminName);
				administratorVO.setE_mail(e_mail);
				administratorVO.setAdminPassWd(adminPassWd);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("administratorVO", administratorVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/addAdmin.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdministratorService adminSvc = new AdministratorService();
				administratorVO = adminSvc.addAdmin(adminName, e_mail, adminPassWd);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAdmin.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/addAdmin.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllAdmin.jsp

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String memNo = new String(req.getParameter("memNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(memNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
