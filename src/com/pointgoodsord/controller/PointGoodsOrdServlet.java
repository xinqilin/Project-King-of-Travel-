package com.pointgoodsord.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.MemDAO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.pgorddetails.model.PGOrdDetailsDAO;
import com.pgorddetails.model.PGOrdDetailsVO;
import com.pointgoods.model.PointGoodsService;
import com.pointgoods.model.PointGoodsVO;
import com.pointgoodsord.model.PointGoodsOrdService;
import com.pointgoodsord.model.PointGoodsOrdVO;

public class PointGoodsOrdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
	
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String pointgoodsordno = req.getParameter("pointgoodsordno");
				if (pointgoodsordno == null || (pointgoodsordno.trim()).length() == 0) {
					errorMsgs.add("請輸入積分商品訂單編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgoodsord/selectPage.jsp");
					failureView.forward(req, res);
					return;
				}
			//=====開始查詢資料======
				PointGoodsOrdService pgoSvc = new PointGoodsOrdService();
				PointGoodsOrdVO pointgoodsordVO = pgoSvc.getOnePointGoodsOrd(pointgoodsordno);
				if (pointgoodsordVO == null) {
					errorMsgs.add("查無此積分商品訂單");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgoodsord/selectPage.jsp");
					failureView.forward(req, res);
					return;
				}
			//=====查詢完成,準備轉交======
				req.setAttribute("pointgoodsordVO", pointgoodsordVO);
				String url = "/back-end/pointgoodsord/listOnePointGoodsOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			//=====其他可能的錯誤處裡=====
			} catch ( Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgoodsord/selectPage.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//=====接收請求參數=====
				String pointgoodsordno = new String(req.getParameter("pointgoodsordno"));
				//=====開始查詢資料=====
				PointGoodsOrdService pointgoodsordSvc = new PointGoodsOrdService();
				PointGoodsOrdVO pointgoodsordVO = pointgoodsordSvc.getOnePointGoodsOrd(pointgoodsordno);
				//=====查詢完成準備轉交=====
				req.setAttribute("pointgoodsordVO", pointgoodsordVO);
				String url = "/back-end/pointgoodsord/updatePointGoodsOrdInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				//=====其他可能的錯誤處裡=====
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pointgoodsord/listAllPointGoodsOrders.jsp");
				failureView.forward(req, res);
			}		
		}
		
		//前台建立訂單
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				// =====接收請求參數,錯誤處理=====
				String memno = req.getParameter("memno");
				String receiverReg = "^[(\u4e00-\u9fa5)]{2,6}$";
				String receiver = req.getParameter("receiver");
				if (receiver == null || receiver.trim().length() == 0) {
					errorMsgs.add("收件人姓名請勿空白");
				} else if (!receiver.trim().matches(receiverReg)) {
					errorMsgs.add("請輸入收件人的中文姓名喔");
				}

				String phone = req.getParameter("phone").trim();
				String phoneReg = "^09{1}\\d{2}\\d{6}$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("請輸入收件人的手機號碼喔");
				} else if (!phone.trim().matches(phoneReg)) {
					errorMsgs.add("請按09xx-xxxxxx的格式輸入手機號碼喔");
				}

				String county = req.getParameter("county").trim();
				String district = req.getParameter("district").trim();
				String road = req.getParameter("road").trim();
				if (county == null || county.trim().length() == 0) {
					errorMsgs.add("請選擇縣市");
				}
				if (district == null || district.trim().length() == 0) {
					errorMsgs.add("請選擇鄉鎮市區");
				}
				if (road == null || road.trim().length() == 0) {
					errorMsgs.add("請填寫道路號碼");
				}
				String address = county + district + road;
				
				Integer orderpoint = new Integer((int)session.getAttribute("total"));
				
				PointGoodsOrdVO pointgoodsordVO = new PointGoodsOrdVO();
				pointgoodsordVO.setMemno(memno);
				pointgoodsordVO.setReceiver(receiver);
				pointgoodsordVO.setPhone(phone);
				pointgoodsordVO.setAddress(address);
				pointgoodsordVO.setOrderpoint(orderpoint);
				
				Vector<PointGoodsVO> buylist = (Vector<PointGoodsVO>)session.getAttribute("shoppingcartp");
				List<PGOrdDetailsVO> list = new ArrayList<PGOrdDetailsVO>();
				for(PointGoodsVO pointgoodsVO : buylist) {
					PGOrdDetailsVO pgorddetailsVO = new PGOrdDetailsVO();
					pgorddetailsVO.setPointgoodsno(pointgoodsVO.getPointgoodsno());
					pgorddetailsVO.setPointgoodsquantity(pointgoodsVO.getPointgoodsquantity());
					pgorddetailsVO.setGoodspoint((Integer)(pointgoodsVO.getPointgoodsquantity()*pointgoodsVO.getNeedpoints()));
					list.add(pgorddetailsVO);
				}
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointgoodsordVO", pointgoodsordVO);							
					req.setAttribute("county", county);
					req.setAttribute("district", district);
					req.setAttribute("road", road);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pointgoodsord/ordconfirm.jsp");
					failureView.forward(req, res);
					return;
				}
				java.sql.Timestamp orderdate = new java.sql.Timestamp(System.currentTimeMillis());
				SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String orddate = ss.format(orderdate);
				Integer ordstatus = new Integer(0);
				// =====開始新增資料=====
				PointGoodsOrdService pointgoodsordSvc = new PointGoodsOrdService();
				pointgoodsordVO = pointgoodsordSvc.addWithDetails(memno, receiver, phone, address, orderdate, ordstatus, orderpoint, list);
				session.removeAttribute("total");
				session.removeAttribute("shoppingcartp");
				session.removeAttribute("shoppingcartq");
				
				req.setAttribute("pointgoodsordVO", pointgoodsordVO);
				
				// =====扣點數=====
				MemService memSVC = new MemService();
				MemVO memvo1 = memSVC.getOneMem(memno);
				memvo1.setPoints(memvo1.getPoints() - orderpoint);
				MemDAO dao = new MemDAO();
				dao.updatepoints(memvo1);
				
				// =====寄送訂單郵件=====
			try {
				MemVO memVO = (MemVO) session.getAttribute("accountVO");
				String pno = pointgoodsordVO.getPointgoodsordno();
				String to = memVO.getE_mail();
				String subject = "遊記王積分商城訂單編號" + pno + "已成立";

				MimeMultipart multipart = new MimeMultipart("related");

				BodyPart messageBodyPart = new MimeBodyPart();
				StringBuilder orderDetails = new StringBuilder();
				for(PointGoodsVO pointgoodsvo : buylist) {
					orderDetails.append("<tr><td style=\"border:1px black solid;\">" + pointgoodsvo.getPointgoodsno() + "</td><td style=\"border:1px black solid;\">" + pointgoodsvo.getPointgoodsname() + 
					"</td><td style=\"border:1px black solid;\">" + pointgoodsvo.getNeedpoints() + "</td><td style=\"border:1px black solid;\">" + pointgoodsvo.getPointgoodsquantity() 
					+ "</td><td style=\"border:1px black solid;\">" + 
					((Integer)(pointgoodsvo.getPointgoodsquantity()*pointgoodsvo.getNeedpoints())).toString() + "</td></tr>");
					}
				String htmlText = "<H1>親愛的" + memVO.getMemName() + "您好!</H1>" + "<br>" + "您的訂單編號:" + pno
						+ "已於"+ orddate + "成立, 我們將盡快為您配送! 您可以至會員中心查看您的訂單詳情." + "<br>" + "若是您有任何疑問,可隨時寄信至我們的客服信箱或與我們的客服人員聯絡! 感謝您的兌換!"
						+ "<br>" + "您本次兌換的訂單明細如下:" 
						+ "<table style=\"border:1px black solid;\">" +
							"<tr><th style=\"border:1px black solid;\">商品編號</th><th <th style=\"border:1px black solid;\">商品名稱</th>"
							+ "<th style=\"border:1px black solid;\">商品點數</th><th style=\"border:1px black solid;\">兌換數量</th>"
							+ "<th style=\"border:1px black solid;\">小計</th></tr>" + 
							orderDetails.toString() + "<tr><td></td><td></td><td></td><td>總計兌換點數</td><td>"+ orderpoint +"</td></tr></table><br><img src='cid:image'>";
				messageBodyPart.setContent(htmlText, "text/html; charset=\"utf-8\"");
				multipart.addBodyPart(messageBodyPart);
				messageBodyPart = new MimeBodyPart();
				URL url = getServletContext().getResource("/images/pointgoods/DaWu.jpg");
				messageBodyPart.setFileName("DaWu.jpg");
				messageBodyPart.setDisposition(MimeBodyPart.INLINE);
				messageBodyPart.setDataHandler(new DataHandler(url));
				messageBodyPart.setHeader("Content-ID", "<image>");
				
				multipart.addBodyPart(messageBodyPart);

				MailServiceWithPic2.sendMail(to, subject, multipart);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
				// =====新增完成準備轉交=====
				String url = "/front-end/pointgoodsord/ordFinal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				// =====其他可能的錯誤處理=====
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pointgoods/listAllPointGoodsF.jsp");
//				failureView.forward(req, res);
//			}
		}
	
//		if("update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				//=====接收請求參數,錯誤處理=====
//				String pointgoodsordno = req.getParameter("pointgoodsordno").trim();
//				
//				PointGoodsOrdService pointgoodsordSvc = new PointGoodsOrdService();
//				PointGoodsOrdVO pointgoodsordVO = pointgoodsordSvc.getOnePointGoodsOrd(pointgoodsordno);
//				
//				String pointgoodsname = req.getParameter("pointgoodsname");
//				String pointgoodsnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(!~*)]{2,30}$";
//				if (pointgoodsname == null || pointgoodsname.trim().length() == 0) {
//					errorMsgs.add("商品名稱請勿空白");
//				} else if (!pointgoodsname.trim().matches(pointgoodsnameReg)) {
//					errorMsgs.add("產品名稱只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//				
//				Integer pointgoodsquantity = null;
//				try {
//					pointgoodsquantity = new Integer(req.getParameter("pointgoodsquantity").trim());
//				} catch (Exception e) {
//					errorMsgs.add("產品數量請輸入數字");
//				}
//				
//				Integer needpoints = null;
//				try {
//					needpoints = new Integer(req.getParameter("needpoints").trim());
//				} catch (Exception e) {
//					errorMsgs.add("需求點數請輸入數字");
//				}
//				
//				String pointgoodsdesc = req.getParameter("pointgoodsdesc").trim();
//				if (pointgoodsdesc == null || pointgoodsdesc.length() == 0) {
//					errorMsgs.add("產品描述請勿空白");
//				}
//				
//				Part part = req.getPart("pointgoodspic");
//				InputStream in = part.getInputStream();
//				byte[] pointgoodspic = null;
//				if (in.available() == 0) {
//					pointgoodspic = pointgoodsVO.getPointgoodspic();
//				} else {
//					byte[] picarr = new byte[in.available()];
//					in.read(picarr);
//					pointgoodspic = picarr;
//					in.close();
//				}
//				
//				Integer pointgoodsstatus = new Integer(req.getParameter("pointgoodsstatus").trim());
//				
//				pointgoodsVO.setPointgoodsno(pointgoodsno);
//				pointgoodsVO.setPointgoodsname(pointgoodsname);
//				pointgoodsVO.setPointgoodsquantity(pointgoodsquantity);
//				pointgoodsVO.setNeedpoints(needpoints);
//				pointgoodsVO.setPointgoodsdesc(pointgoodsdesc);
//				pointgoodsVO.setPointgoodspic(pointgoodspic);
//				pointgoodsVO.setPointgoodsstatus(pointgoodsstatus);
//				
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("pointgoodsVO", pointgoodsVO); // 含有輸入格式錯誤的pointgoodsVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/pointgoods/updatePointGoodsInput.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				//=====開始修改資料=====
////				PointGoodsService pointgoodsSvc = new PointGoodsService();
//				pointgoodsVO = pointgoodsSvc.updatePointGoods(pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus);
//				
//				//=====修改完成準備轉交=====
//				String url = "/back-end/pointgoods/listAllPointGoods.jsp";
//				req.setAttribute("in", 2);
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/pointgoods/updatePointGoodsInput.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//		}
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
