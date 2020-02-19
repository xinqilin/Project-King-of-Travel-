package com.article.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.article.model.*;
import com.articleDetails.model.ArticleDetailsService;
import com.articleDetails.model.ArticleDetailsVO;
import com.spot.model.SpotListService;
import com.spot.model.SpotListVO;
import com.trip.model.TripService;
import com.trip.model.TripVO;
import com.tripDetails.model.TripDetailsVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ArticleServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// --------------------------------------查詢完成準備轉交----------------------------------
				req.setAttribute("articleVO", articleVO);
				//String url = "/front-end/article/select_page.jsp";
				String url= "/front-end/articleDetails/listOneArticleDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				// ----------------------------------------其他可能的錯誤處理-------------------------------------
			} catch (Exception e) {
				errorMsgs.add("" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//修改遊記細節後無法同步修改遊記資料
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String articleno = new String(req.getParameter("articleno"));
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(articleno);
				req.setAttribute("articleVO", articleVO);
				
				String url = "/front-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得修改資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
System.out.println("110 update進場");
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
System.out.println("129articletitle:"+articletitle);
				java.sql.Date dayofstart = null;
				try {
					dayofstart = java.sql.Date.valueOf(req.getParameter("dayofstart").trim());
				} catch (IllegalArgumentException e) {
					dayofstart = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期");
				}

				java.sql.Date dayofend = null;
				try {
					dayofend = java.sql.Date.valueOf(req.getParameter("dayofend").trim());
				} catch (IllegalArgumentException e) {
					dayofend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期");
				}

				long time_now = new java.util.Date().getTime();
				java.sql.Date dayoflastedit = new java.sql.Date(time_now);

				byte[] articlepic=null;
				Collection<Part> parts=req.getParts();
				for(Part pic:parts)
				{
					if(pic.getContentType()!=null)
					{
System.out.println("155pic");
						InputStream in =pic.getInputStream();
						articlepic=new byte[in.available()];
System.out.println(articlepic);
						in.read(articlepic);
						in.close();
					}
				}
System.out.println("161pic搞定");				
				java.sql.Date dayofcreate = null;
				try {
					dayofcreate = java.sql.Date.valueOf(req.getParameter("dayofcreate").trim());
				}catch(IllegalArgumentException e) {
					dayofcreate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("建立日期出錯");
				}				
				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticleno(articleno);
				articleVO.setMemno(memno);
				articleVO.setTripno(tripno);
				articleVO.setArticletitle(articletitle);
				articleVO.setDaysofstaying(daysofstaying);
				articleVO.setDayofstart(dayofstart);
				articleVO.setDayofend(dayofend);
				articleVO.setArticlestatus(articlestatus);
				articleVO.setTimeofviews(timeofviews);
				articleVO.setDayoflastedit(dayoflastedit);
				articleVO.setKindoftrip(kindoftrip);
				articleVO.setArticlepic(articlepic);
				articleVO.setDayofcreate(dayofcreate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_article_input.jsp");
					failureView.forward(req, res);
					return;
				}

				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.updateArticle(articleno, memno, tripno, articletitle, daysofstaying, dayofstart,
						dayofend, articlestatus, dayoflastedit, timeofviews, kindoftrip, articlepic, dayofcreate);
System.out.println("196articleVO"+articleVO);
				req.setAttribute("articleVO", articleVO);
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_article_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action) || "Insert_By_Tripno".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				//設定參數
				String tripno =req.getParameter("tripno").trim();
				String memno = req.getParameter("memno").trim();

				
				String articletitle = req.getParameter("articletitle").trim();
				if (articletitle == null || articletitle.trim().length() == 0) {
					errorMsgs.add("遊記名稱勿空白");
				}

				Integer daysofstaying = null;
				try {
					daysofstaying = new Integer(req.getParameter("daysofstaying").trim());
				} catch (NumberFormatException e) {
					daysofstaying = 0;
					errorMsgs.add("總天數不得為空");
				}

				java.sql.Date dayofstart = null;
				try {
					dayofstart = java.sql.Date.valueOf(req.getParameter("dayofstart").trim());
				} catch (IllegalArgumentException e) {
					dayofstart = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期");
				}

				java.sql.Date dayofend = null;
				try {
					dayofend = java.sql.Date.valueOf(req.getParameter("dayofend").trim());
				} catch (IllegalArgumentException e) {
					dayofend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期");
				}

				Integer articlestatus = null;
				try {
					articlestatus = new Integer(req.getParameter("articlestatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("遊記狀態不得為空");
				}
				
				Integer timeofviews = null;
				try {
					timeofviews = new Integer(req.getParameter("timeofviews").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("瀏覽次數不得為空");
				}

				long time_latest = new java.util.Date().getTime();
				java.sql.Date dayoflastedit =  new java.sql.Date(time_latest);

				Integer kindoftrip = new Integer(req.getParameter("kindoftrip"));
			
				byte[] articlepic=null;
				Collection<Part> parts=req.getParts();
				for(Part pic:parts)
				{
					if(pic.getContentType()!=null)
					{
						InputStream in =pic.getInputStream();
						articlepic=new byte[in.available()];
						in.read(articlepic);
						in.close();
					}
				}

				long time_now = new java.util.Date().getTime();
				java.sql.Date dayofcreate =  new java.sql.Date(time_now);
	
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
					
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
System.out.println("errorMsgs" + errorMsgs);
					return;
				}
				//新增Article且同時新增ArticleDetails，回傳包含ArticleDetailsVO的list
				ArticleService articleSvc = new ArticleService();
				List<ArticleDetailsVO> list = articleSvc.addArticle(memno, tripno, articletitle, kindoftrip, dayofcreate, dayofcreate, kindoftrip, dayofcreate, kindoftrip, kindoftrip, articlepic, dayofcreate);			
				
				//Import景點資訊回傳給jsp
				SpotListService spotSvc = new SpotListService();
				List<SpotListVO> list2 = spotSvc.getAll();
				
				req.setAttribute("list", list);
				req.setAttribute("list2", list2);
			
				String url = null;
				
				if("insert".equals(action)) {
					url = "/front-end/articleDetails/addArticleDetails.jsp";
				}else if("Insert_By_Tripno".equals(action)) {
					url="/front-end/articleDetails/addArticleDetails.jsp?tripno="+tripno;
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
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
				
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		//查看個人遊記
		if ("getAll_By_Memno".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// --------------------------------------接受請求參數--------------------------------------
				String str = req.getParameter("memno");
				String memno = str;
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				try {
					memno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員名稱格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// -----------------------------------------開始查詢--------------------------------------
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> list = articleSvc.getAllByMemno(memno);
				if (list == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// --------------------------------------查詢完成準備轉交----------------------------------
				req.setAttribute("list", list);
				//String url = "/front-end/article/select_page.jsp";
				String url= "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				// ----------------------------------------其他可能的錯誤處理-------------------------------------
			} catch (Exception e) {
				errorMsgs.add("" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		if("keyword_search".equals(action)) {
			ArticleService articleVO=new ArticleService();
			List<ArticleVO> list=articleVO.getAll();
			String keyword=req.getParameter("keyword");
System.out.println("SVT 431 : keyword = " + keyword);			
			String[] keywords=keyword.split("\\s+");
			System.out.println(Arrays.toString(keywords));
			Stream list2=list.stream();
			for(String key:keywords) {
				Predicate<ArticleVO> predicate=p->p.getArticletitle().contains(key);
				list2=list.stream().filter(predicate);
			}
System.out.println("SVT 439 : list2 = " + list2);	
			list=(List<ArticleVO>)list2.collect(Collectors.toList());
			req.setAttribute("keyword", list);
//			String path = req.getServletPath();
			RequestDispatcher View = req.getRequestDispatcher("/front-end/article/listKeywordArticle.jsp");
			View.forward(req, res);			
		}
		

	}
}