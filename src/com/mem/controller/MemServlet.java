package com.mem.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mem.model.*;
import com.mem.model.MemService;
import com.mem.model.MemVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
//		res.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		
		
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {	// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				String memNo = null;
				try {
					memNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memNo);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/front-end/mem/new_ListOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memNo = req.getParameter("memNo");

				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO);		// 資料庫取出的memVO物件,存入req
				String url = "/front-end/mem/new_ListOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);		//// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_mem_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memNo = new String(req.getParameter("memNo").trim());
			
				// 會員密碼memPasswd
				String memPassWd = req.getParameter("memPassWd");
				String memPassWdReg = "^[a-zA-Z0-9]{6,12}$";
				if (memPassWd == null || memPassWd.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!memPassWd.trim().matches(memPassWdReg)) {
					errorMsgs.add("請輸入6-12位包含大小寫英文字母及數字");
				}
				
				String memPassWdConfirm = req.getParameter("memPassWdConfirm");
				if (memPassWdConfirm == null || memPassWdConfirm.trim().length() == 0) {
					errorMsgs.add("請輸入驗證密碼");
				} else if (!memPassWd.trim().matches(memPassWdReg)) {
					errorMsgs.add("請輸入6-12位包含大小寫英文字母及數字");
				} else if(!memPassWd.equals(memPassWdConfirm)) {
					errorMsgs.add("兩組密碼不相同，請重新輸入");
				}
				// 會員暱稱nickName
				String nickName = req.getParameter("nickName");
				String nickNameReg = "[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (!nickName.trim().matches(nickNameReg)) {
					errorMsgs.add("暱稱只能是中、英文字母和_，且長度必須在2到10之間");
				}

				// 地址address
				String address = req.getParameter("address");
				
				// 電話phone
				String phone = req.getParameter("phone");
				
				//會員簡介introduction
				String introduction = req.getParameter("introduction");
				if(introduction == null || introduction.length()==0) {
					introduction = "這個人很懶，什麼都不想填！！！";
				}
				MemVO memVO = new MemVO();
				memVO.setMemNo(memNo);
				memVO.setMemPassWd(memPassWd);
				memVO.setNickName(nickName);
				memVO.setAddress(address);
				memVO.setPhone(phone);
				memVO.setIntroduction(introduction);
		

		
System.out.println("SERVLET 的   memNO:"+memNo+"    memPassWd:"+memPassWd+
"    nickName:"+nickName+"    addr:"+address+"    phone"+phone+"    introduction:"+introduction);
				if (!errorMsgs.isEmpty()) {
					System.out.println("any error?"+errorMsgs);
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/new_ListOneMem.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
//				MemService memSvc = new MemService();
//				memVO = memSvc.updateMem(memNo, memName, e_mail, memPassWd, memPhoto, nickName, idNo, birDay, addr, phone, introduction);
				MemService memSvc = new MemService();
				MemVO memVO1 = new MemVO();
				memVO1=memSvc.updateMem(memNo, memPassWd, nickName,address, phone, introduction);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				memVO=memSvc.getOneMem(memNo);
				req.setAttribute("memVO_update", memVO1);
				String url = "/front-end/mem/new_ListOneMem.jsp";
				System.out.println("1");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名：請勿空白");
				} else if (!memName.trim().matches(memNameReg)) {
					errorMsgs.add("會員姓名：只能是中、英文字母、數字和_，且長度必須在2到10之間");
				}

				// 會員信箱e_mail
				String e_mail = req.getParameter("e_mail");
				String e_mailReg = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
				if (e_mail == null || e_mail.trim().length() == 0) {
					errorMsgs.add("Email:請勿空白");
				} else if (!e_mail.trim().matches(e_mailReg)) {
					errorMsgs.add("Email:XXXXX@OOOmail.com");
				}

				// 會員密碼memPasswd
				String memPassWd = req.getParameter("memPassWd");
				String memPassWdReg = "^[a-zA-Z0-9]{6,12}$";
				if (memPassWd == null || memPassWd.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!memPassWd.trim().matches(memPassWdReg)) {
					errorMsgs.add("請輸入6-12位包含大小寫英文字母及數字");
				}
				
				String memPassWdConfirm = req.getParameter("memPassWdConfirm");
				if (memPassWdConfirm == null || memPassWdConfirm.trim().length() == 0) {
					errorMsgs.add("請輸入驗證密碼");
				} else if (!memPassWd.trim().matches(memPassWdReg)) {
					errorMsgs.add("請輸入6-12位包含大小寫英文字母及數字");
				} else if(!memPassWd.equals(memPassWdConfirm)) {
					errorMsgs.add("兩組密碼不相同，請重新輸入");
				}
				
				
				// 會員頭像memPhoto
				byte[] memPhoto=null;
				Part part = req.getPart("memPhoto");
				InputStream is = part.getInputStream();
				memPhoto = new byte[is.available()];
				is.read(memPhoto);
				is.close();

				
				
				// 會員暱稱nickName
				String nickName = req.getParameter("nickName");
				String nickNameReg = "[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (!nickName.trim().matches(nickNameReg)) {
					errorMsgs.add("暱稱只能是中、英文字母和_，且長度必須在2到10之間");
				}

				// 會員身分證字號idNo
//				String idNo = null;
				String idNo = req.getParameter("idNo");
//				String idNoReg = "/^[A-Z]{1}[0-9]{9}$/";
				String idNoReg = "^[A-Z]\\d{9}$";
				MemService memSvc = new MemService();
				List<MemVO> list = memSvc.getAll();
				for(int i=0; i<list.size(); i++ ) {
					if(list.get(i).getIdNo().equals(idNo)) {
						errorMsgs.add("身分證字號已重複，請重新輸入");
					};
				}
				
				if (idNo == null || idNo.trim().length() == 0) {
					errorMsgs.add("請輸入身分證字號");
				}else if (!idNo.trim().matches(idNoReg)) {
					errorMsgs.add("請輸入正確身分證字號格式");
				}

				// 會員生日birday
				java.sql.Date birDay = null;
				try {
					birDay = java.sql.Date.valueOf(req.getParameter("birDay").trim());
				} catch (IllegalArgumentException e) {
					birDay = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}

				// 地址address
				String address = req.getParameter("address");
				if (address == null) {
					errorMsgs.add("請輸入地址");
				}
				String zone1 = req.getParameter("zone1");
				String zone2 = req.getParameter("zone2");
				String addr = zone1 + zone2 + address;
				
				// 電話phone
				String phone = req.getParameter("phone");
				if (phone == null) {
					errorMsgs.add("請輸入電話");
				}
				
				// 會員簡介introduction
				String introduction = req.getParameter("introduction");
				if(introduction == null || introduction.length()==0) {
					introduction = "這個人很懶，什麼都不想填！！！";
				}

				MemVO memVO = new MemVO();
				memVO.setMemName(memName);
				memVO.setE_mail(e_mail);
				memVO.setMemPassWd(memPassWd);
				memVO.setMemPhoto(memPhoto);
				memVO.setNickName(nickName);
				memVO.setIdNo(idNo);
				memVO.setBirDay(birDay);
				memVO.setAddress(addr);
				memVO.setPhone(phone);
				memVO.setIntroduction(introduction);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				MemService memSvc1 = new MemService();
				memVO = memSvc1.addMem(memName, e_mail, memPassWd, memPhoto, nickName, idNo, birDay, addr, phone, introduction);
				
				String code = memSvc1.getCode(memVO);
				Boolean result =false;
				result = memSvc1.sendMailToMem(memVO, code, req);
						
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
					
				if(result) {
					String url ="/front-end/mem/ConfirmMem.jsp"; 		//點選註冊後，跳出已寄送驗證信，請會員收驗證信
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交index.jsp
					successView.forward(req, res);
				}
												 
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/addMem.jsp");
				failureView.forward(req, res);	
			}
		}
		
		if("confirmStatus".equals(action)) {
			String code = req.getParameter("code");
			MemService memSvc1 = new MemService();
			Boolean result = memSvc1.confirmCode(code);
			System.out.println("有驗證碼進入");
			if(result) {
				String url = "/front-end/mem/ConfirmSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				System.out.println("驗證成功");
			}else {
				
			}
			
			
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

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
		
		
		if ("update_mem_photo".equals(action)) { // 來自update_mem_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memNo = new String(req.getParameter("memNo").trim());
			
				// 會員頭像memPhoto
				byte[] memPhoto = null; // 可以跑的寫法
//				MemService memSvc = new MemService();// 取出這項商品資料庫內的圖片
//				MemVO memVO1 = memSvc.getOneMem(memNo);// 新增一個vo物件
//				memPhoto = memVO1.getMemPhoto();// 將圖片丟到memVO1裡面
//				Collection<Part> part = req.getParts();// 查詢這次網頁操作，是否有上傳新的圖片
				//String header = part.getHeader("content-disposition");
				//String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();								
				Collection<Part> parts=req.getParts();
				for(Part pic:parts)
				{
					if(pic.getContentType()!=null)
					{
						InputStream in =pic.getInputStream();
						memPhoto=new byte[in.available()];
						in.read(memPhoto);
						in.close();
					}
				}				
//				ServletOutputStream out = res.getOutputStream();
//				Part part = req.getPart("memPhoto");
//				InputStream is = part.getInputStream();
//				byte[] memPhoto = new byte[is.available()];
//				is.read(memPhoto);
//				is.close();
//
////				if(is.available()==0) {
////					MemService memSvc = new MemService();
////					MemVO memVO = memSvc.getOneMem(memNo);
////					byte[] buf = memVO.getMemPhoto();
////					out.write(buf);
////				}
//				try {
//					res.setContentType("image/gif");
//
//					MemService memSvc = new MemService();
//					MemVO memVO = new MemVO();
//					
//					if(memNo != null) {
//						memVO = memSvc.getOneMem(memNo);
//						byte[] buf = memVO.getMemPhoto();
//						out.write(buf);
//					}
//					//buf = lBPVO.getLBP_PHOTO(); // 4K buffer
//					//out.write(buf);
//					}catch (Exception e) {
//					InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
//					byte[] b = new byte[in.available()];
//					in.read(b);
//					out.write(b);
//					in.close();
//				}

				MemVO memVO = new MemVO();
				memVO.setMemNo(memNo);
				memVO.setMemPhoto(memPhoto);

				if (!errorMsgs.isEmpty()) {
					System.out.println("any error?"+errorMsgs);
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/new_ListOneMem.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
//				MemService memSvc = new MemService();
//				memVO = memSvc.updateMem(memNo, memName, e_mail, memPassWd, memPhoto, nickName, idNo, birDay, addr, phone, introduction);
				MemService memSvc = new MemService();
				memSvc.updateMemPhoto(memNo, memPhoto);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				memVO=memSvc.getOneMem(memNo);
				req.setAttribute("memVO", memVO);
				String url = "/front-end/mem/new_ListOneMem.jsp";
				System.out.println("1");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("getEmailConfirm".equals(action)) { // 來自confirmEmail.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				String memNo = new String(req.getParameter("memNo"));
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				MemService memSvc = new MemService();
//				memSvc.deleteMem(memNo);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/mem/listAllMem.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
	
//	
//	//隨機產生驗證碼
//	private static String returnAuthCode() {
//		StringBuilder sb = new StringBuilder();
//		for (int i = 1; i <= 8; i++) {
//			int condition = (int) (Math.random() * 3) + 1;
//			switch (condition) {
//			case 1:
//				char c1 = (char)((int)(Math.random() * 26) + 65);
//				sb.append(c1);
//				break;
//			case 2:
//				char c2 = (char)((int)(Math.random() * 26) + 97);
//				sb.append(c2);
//				break;
//			case 3:
//				sb.append((int)(Math.random() * 10));
//			}
//		}
//		return sb.toString();
//	}
}
