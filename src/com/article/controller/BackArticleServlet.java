package com.article.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.article.model.*;

@WebServlet("/back-end/article/article.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BackArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// --------------------------------------接受請求參數--------------------------------------
				String str = req.getParameter("articleno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入遊記名稱");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String articleno = null;
				try {
					articleno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("遊記名稱格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// -----------------------------------------開始查詢--------------------------------------
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(articleno);
				if (articleVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// --------------------------------------查詢完成準備轉交----------------------------------
				req.setAttribute("articleVO", articleVO);
				String url = "/back-end/article/b_select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				// ----------------------------------------其他可能的錯誤處理-------------------------------------
			} catch (Exception e) {
				errorMsgs.add("" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String articleno = new String(req.getParameter("articleno"));
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(articleno);
				req.setAttribute("articleVO", articleVO);
				
				String url = "/back-end/article/b_update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得修改資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String articleno = new String(req.getParameter("articleno").trim());
				String memno = new String(req.getParameter("memno"));
				String tripno = new String(req.getParameter("tripno"));
				Integer daysofstaying = new Integer(req.getParameter("daysofstaying").trim());
				Integer timeofviews = new Integer(req.getParameter("timeofviews").trim());
				Integer kindoftrip = new Integer(req.getParameter("kindoftrip").trim());
				Integer articlestatus = new Integer(req.getParameter("articlestatus").trim());
				
				String articletitle = req.getParameter("articletitle");
				if (articletitle == null || articletitle.trim().length() == 0) {
					errorMsgs.add("遊記名稱編號勿空白");
				}
System.out.println("126");
				java.sql.Date dayofstart = null;
				try {
					dayofstart = java.sql.Date.valueOf(req.getParameter("dayofstart").trim());
				} catch (IllegalArgumentException e) {
					dayofstart = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期");
				}
System.out.println("134");
				java.sql.Date dayofend = null;
				try {
					dayofend = java.sql.Date.valueOf(req.getParameter("dayofend").trim());
				} catch (IllegalArgumentException e) {
					dayofend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期");
				}
System.out.println("142");
				long time_now = new java.util.Date().getTime();
				java.sql.Date dayoflastedit = new java.sql.Date(time_now);
System.out.println("145");
				byte[] articlepic=null;
				Collection<Part> parts=req.getParts();
				for(Part pic:parts)
				{
					if(pic.getContentType()!=null)
					{
					System.out.println("152pic");
						InputStream in =pic.getInputStream();
					System.out.println(articlepic);
						articlepic=new byte[in.available()];
					System.out.println(articlepic);
						in.read(articlepic);
						in.close();
					}
				}
System.out.println("161");				
				java.sql.Date dayofcreate = null;
				try {
					dayofcreate = java.sql.Date.valueOf(req.getParameter("dayofcreate").trim());
				}catch(IllegalArgumentException e) {
					dayofcreate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("建立日期出錯");
				}
System.out.println("169");				
				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticleno(articleno);
				articleVO.setMemno(memno);
				articleVO.setTripno(tripno);
				articleVO.setArticletitle(articletitle);
				articleVO.setDaysofstaying(daysofstaying);
				articleVO.setDayofstart(dayofstart);
				articleVO.setDayofend(dayofend);
				articleVO.setArticlestatus(articlestatus);
				articleVO.setDayoflastedit(dayoflastedit);
				articleVO.setTimeofviews(timeofviews);
				articleVO.setKindoftrip(kindoftrip);
				articleVO.setArticlepic(articlepic);
				articleVO.setDayofcreate(dayofcreate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_update_article_input.jsp");
					failureView.forward(req, res);
					return;
				}

				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.updateArticle(articleno, memno, tripno, articletitle, daysofstaying, dayofstart,
						dayofend, articlestatus, dayoflastedit, timeofviews, kindoftrip, articlepic, dayofcreate);

				req.setAttribute("articleVO", articleVO);
				String url = "/back-end/article/b_select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_update_article_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
System.out.println("198");
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
System.out.println("215");
				String memno = req.getParameter("memno");
				if (memno == null || memno.trim().length() == 0) {
					errorMsgs.add("會員編號勿空白");
				}
System.out.println("220");
				String tripno = req.getParameter("tripno").trim();
				if (tripno == null || tripno.trim().length() == 0) {
					errorMsgs.add("行程編號勿空白");
				}
System.out.println("225");				
				String articletitle = req.getParameter("articletitle").trim();
				if (articletitle == null || articletitle.trim().length() == 0) {
					errorMsgs.add("遊記名稱勿空白");
				}
System.out.println("230");
				Integer daysofstaying = null;
				try {
					daysofstaying = new Integer(req.getParameter("daysofstaying").trim());
				} catch (NumberFormatException e) {
					daysofstaying = 0;
					errorMsgs.add("總天數不得為空");
				}
System.out.println("238");
				java.sql.Date dayofstart = null;
				try {
					dayofstart = java.sql.Date.valueOf(req.getParameter("dayofstart").trim());
				} catch (IllegalArgumentException e) {
					dayofstart = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期");
				}
System.out.println("246");
				java.sql.Date dayofend = null;
				try {
					dayofend = java.sql.Date.valueOf(req.getParameter("dayofend").trim());
				} catch (IllegalArgumentException e) {
					dayofend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期");
				}
System.out.println("254");
				Integer articlestatus = null;
				try {
					articlestatus = new Integer(req.getParameter("articlestatus").trim());
				} catch (NumberFormatException e) {
					articlestatus = 0;
					errorMsgs.add("遊記狀態不得為空");
				}
System.out.println("262");				
				Integer timeofviews = null;
				try {
					timeofviews = new Integer(req.getParameter("timeofviews").trim());
				} catch (NumberFormatException e) {
					timeofviews = 0;
					errorMsgs.add("遊記狀態不得為空");
				}
System.out.println("270");				
				java.sql.Date dayoflastedit = null;
				try {
					dayoflastedit = java.sql.Date.valueOf(req.getParameter("dayoflastedit").trim());
				} catch (IllegalArgumentException e) {
					dayoflastedit = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入編輯日期");
				}
System.out.println("278");
				Integer kindoftrip = new Integer(req.getParameter("kindoftrip"));
System.out.println("280");				
				byte[] articlepic=null;
				Collection<Part> parts=req.getParts();
				for(Part pic:parts)
				{
					if(pic.getContentType()!=null)
					{
System.out.println("297pic");
						InputStream in =pic.getInputStream();
System.out.println(articlepic);
						articlepic=new byte[in.available()];
System.out.println(articlepic);
						in.read(articlepic);
						in.close();
					}
				}
System.out.println("296");
				long time_now = new java.util.Date().getTime();
				java.sql.Date dayofcreate =  new java.sql.Date(time_now);
System.out.println("299");				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setMemno(memno);
				articleVO.setTripno(tripno);
				articleVO.setArticletitle(articletitle);
				articleVO.setDaysofstaying(daysofstaying);
				articleVO.setDayofstart(dayofstart);
				articleVO.setDayofend(dayofend);
				articleVO.setArticlestatus(articlestatus);
				articleVO.setDayoflastedit(dayoflastedit);
				articleVO.setTimeofviews(timeofviews);
				articleVO.setKindoftrip(kindoftrip);
				articleVO.setArticlepic(articlepic);
				articleVO.setDayofcreate(dayofcreate);
System.out.println("314");
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_addArticle.jsp");
					failureView.forward(req, res);
System.out.println("319");
					return;
				}
System.out.println("322");
				ArticleService articleSvc = new ArticleService();
//				articleVO = articleSvc.addArticle(memno, tripno, articletitle, daysofstaying, dayofstart,
//						dayofend, articlestatus, dayoflastedit, timeofviews, kindoftrip, articlepic, dayofcreate);

				req.setAttribute("articleVO", articleVO);
				String url = "/back-end/article/b_listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String articleno = new String(req.getParameter("articleno"));
				
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(articleno);
				
				String url = "/back-end/article/b_listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/b_listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
