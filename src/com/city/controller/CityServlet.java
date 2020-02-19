package com.city.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.city.model.CityService;
import com.city.model.CityVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {	// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("cityNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入城市編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/city/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				String cityNo = null;
				try {
					cityNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("城市編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/city/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CityService citySvc = new CityService();
				CityVO cityVO = citySvc.getOne(cityNo);
				if (cityVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/city/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cityVO", cityVO); // 資料庫取出的cityVO物件,存入req
				String url = "/back-end/city/listOneCity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCity.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/city/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCity.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String cityNo = new String(req.getParameter("cityNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				CityService citySvc = new CityService();
				CityVO cityVO = citySvc.getOne(cityNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("cityVO", cityVO);		// 資料庫取出的cityVO物件,存入req
				String url = "/back-end/city/update_city_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);		//// 成功轉交 update_city_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/city/listAllCity.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_city_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String cityNo = new String(req.getParameter("cityNo").trim());

				// 城市名稱cityName
				String cityName = req.getParameter("cityName");
				String cityNameReg = "[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cityName == null || cityName.trim().length() == 0) {
					errorMsgs.add("城市名稱：請勿空白");
				} else if (!cityName.trim().matches(cityNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("城市名稱：只能是中、英文字母、數字和_，且長度必須在2到10之間");
				}

				// 國家編號countryNo
				String countryNo = null;

				CityVO cityVO = new CityVO();
				cityVO.setCityNo(cityNo);
				cityVO.setCityName(cityName);
				cityVO.setCountryNo(countryNo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cityVo", cityVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/city/update_city_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CityService citySvc = new CityService();
				cityVO = citySvc.update(cityNo, cityName, countryNo);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/back-end/city/listOneCity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCity.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/city/update_city_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
