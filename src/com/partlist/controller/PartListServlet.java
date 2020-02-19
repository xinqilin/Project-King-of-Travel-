package com.partlist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.partlist.model.PartListService;
import com.partlist.model.PartListVO;
import com.pointgoods.model.PointGoodsService;
import com.pointgoods.model.PointGoodsVO;

public class PartListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO)session.getAttribute("accountVO");
		
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("hello");

//			try {
				Integer putpoints = new Integer(req.getParameter("putpoints"));
				if (putpoints > memVO.getPoints()) {
					errorMsgs.add("ÈªûÊï∏È§òÈ?ç‰?çË∂≥");
				}
				String memno = memVO.getMemNo();
				String activityno = "ACT0001";
				String oddteam = req.getParameter("oddteam");
				Double oddrate = new Double(req.getParameter("oddrate"));
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity2.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("ok");
				System.out.println(memno);
				System.out.println(activityno);
				System.out.println(oddteam);
				System.out.println(oddrate);
				System.out.println(putpoints);
				Integer getpoints = 10;
				
				// =====??ãÂ?ãÊñ∞Â¢ûË?áÊ??======
				PartListService partlistSvc = new PartListService();
				PartListVO partlistvo = new PartListVO();
				partlistvo = partlistSvc.addPartList(memno, activityno, oddteam, putpoints, getpoints, oddrate);
				
				// =====?ü•Ë©¢Â?åÊ??,Ê∫ñÂ?ôË?â‰∫§======
//				req.setAttribute("pointgoodsVO", pointgoodsVO);
//				String url = "/back-end/pointgoods/listOnePointGoods.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				// =====?Ö∂‰ªñÂèØ?ÉΩ??ÑÈåØË™§Ë?ïË£°=====
//			} catch (Exception e) {
//				errorMsgs.add("?Ñ°Ê≥ïÂ?ñÂ?óË?áÊ??:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgoods/selectPage.jsp");
//				failureView.forward(req, res);
//				return;
//			}
		}
	}
}
