package com.articleDetails.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.articleDetails.model.*;
import com.spot.model.SpotListService;
import com.spot.model.SpotListVO;

@WebServlet("/front-end/articleDetails/ArticleDetails.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 50 * 5 * 1024 * 1024)
public class ArticleDetailsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ArticleDetailsServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求
				String str = req.getParameter("articleno");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入遊記編號");
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

				//開始查詢
				
System.out.println(articleno);
				ArticleDetailsService articleDetailsSvc = new ArticleDetailsService();
				List<ArticleDetailsVO> list = articleDetailsSvc.getfindByArticleno(articleno);
				if (list == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢完成準備轉交
				
				req.setAttribute("list", list);
System.out.println("87 : list = " + list);				
				String url = "/front-end/articleDetails/listOneArticleDetails.jsp";///front-end/articleDetails/listOneArticleDetails.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				//其他可能的錯誤
			
			}catch(Exception e) {
				errorMsgs.add("" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String articleno = req.getParameter("articleno");
				if (articleno == null || articleno.trim().length() == 0) {
					errorMsgs.add("遊記編號勿空白");
				}

				String spotno = req.getParameter("spotno").trim();
				if (spotno == null || spotno.trim().length() == 0) {
					errorMsgs.add("會員編號勿空白");
				}
			
				String articlenotes = req.getParameter("articlenotes").trim();

				byte[] articleDetailspic=null;
				Collection<Part> parts=req.getParts();
				for(Part pic:parts)
				{
					if(pic.getContentType()!=null)
					{
						InputStream in =pic.getInputStream();
						articleDetailspic=new byte[in.available()];
						in.read(articleDetailspic);
						in.close();
					}
				}
				
				Integer articletriporderby = null;
				try {
					articletriporderby = new Integer(req.getParameter("articlestatus").trim());
				} catch (NumberFormatException e) {
					articletriporderby = 0;
					errorMsgs.add("遊記狀態不得為空");
				}
				
				String picnote = req.getParameter("picnote").trim();
				
				Integer tripdayorder = null;
				try {
					tripdayorder = new Integer(req.getParameter("tripdayorder").trim());
				} catch (NumberFormatException e) {
					tripdayorder = 0;
					errorMsgs.add("遊記狀態不得為空");
				}

					
				ArticleDetailsVO articleDetailsVO = new ArticleDetailsVO();
				articleDetailsVO.setArticleno(articleno);
				articleDetailsVO.setSpotno(spotno);
				articleDetailsVO.setArticlenotes(articlenotes);
				articleDetailsVO.setArticledetailspic(articleDetailspic);
				articleDetailsVO.setArticletriporderby(articletriporderby);
				articleDetailsVO.setPicnote(picnote);
				articleDetailsVO.setTripdayorder(tripdayorder);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleDetailsVO", articleDetailsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				ArticleDetailsService articleDetailsSvc = new ArticleDetailsService();
				articleDetailsVO = articleDetailsSvc.addArticleDetails(articleno, spotno, articlenotes, articleDetailspic, articletriporderby, picnote, tripdayorder);

				req.setAttribute("articleDetailsVO", articleDetailsVO);
				String url = "/front-end/article/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update_ArticleDetails".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {		
				List<ArticleDetailsVO> list = new ArrayList<ArticleDetailsVO>();
				int j = 0; //傳回來的陣列跑迴圈用
				String[] articleno = req.getParameterValues("articleno");
				String[] spotno = req.getParameterValues("spotno");
				String[] articlenotes = req.getParameterValues("articlenotes");
				
				List<byte[]> picbuf = new ArrayList<>();
				byte[] articledetailspic=null;
				Collection<Part> parts=req.getParts();
				for(Part pic:parts) {
					if(pic.getContentType()!=null) {

						InputStream in =pic.getInputStream();
						articledetailspic=new byte[in.available()];
						in.read(articledetailspic);
						picbuf.add(articledetailspic);
System.out.println("SVT 203 : picbuf = " + picbuf);
						in.close();	
					}
				}
				
				String[] articletriporderby = req.getParameterValues("articletriporderby");
			
				String[] picnote = req.getParameterValues("picnote");

				String[] tripdayorder = req.getParameterValues("tripdayorder");

				//跑迴圈取傳過來的陣列
				ArticleDetailsService articleDetailsSvc = new ArticleDetailsService();
				ArticleDetailsVO articleDetailsVO = null;
				Iterator<byte[]> iterator = picbuf.iterator(); //用迭代取資料
				do {
					articleDetailsVO = new ArticleDetailsVO();
					articleDetailsVO.setArticleno(articleno[j]);
					articleDetailsVO.setSpotno(spotno[j]);
					articleDetailsVO.setArticlenotes(articlenotes[j]);
					articleDetailsVO.setArticledetailspic(iterator.next());  //跑迭代的next()
					articleDetailsVO.setArticletriporderby(new Integer(articletriporderby[j]));
					articleDetailsVO.setPicnote(picnote[j]);
					articleDetailsVO.setTripdayorder(new Integer(tripdayorder[j]));
					articleDetailsVO  = articleDetailsSvc.updateArticleDetails(articleno[j], spotno[j], articlenotes[j], articleDetailsVO.getArticledetailspic(), new Integer(articletriporderby[j]), picnote[j], new Integer(tripdayorder[j]));			
					list.add(articleDetailsVO);
					j++;
				}while(articleno.length-1 >= j);		//陣列長度-1小於j就停	

//				for(byte[] bufs:picbuf) {
//				do {
//					articleDetailsVO.setArticleno(articleno[j]);
//					articleDetailsVO.setSpotno(spotno[j]);
//					articleDetailsVO.setArticledetailspic(bufs);
//					articleDetailsVO = articleDetailsSvc1.addArticleDetailsPic(bufs, articleno[j], spotno[j]);
//System.out.println("SVT 242 : bufs = " + bufs);
//				}
//				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("list", list);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
	
				req.setAttribute("list", list);
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals("action")) {
			
		}
		
		if("getArticle_For_Update".equals(action)) {
System.out.println("SVT 267 : getArticle_For_Update進來");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String articleno = new String(req.getParameter("articleno"));
				ArticleDetailsService articleDetailsSvc = new ArticleDetailsService();
				List<ArticleDetailsVO> list = articleDetailsSvc.getfindByArticleno(articleno);
				
				SpotListService spotSvc = new SpotListService();
				List<SpotListVO> list2 = spotSvc.getAll();
				
				req.setAttribute("list", list);
				req.setAttribute("list2", list2);
System.out.println("SVT 275 : list = " + list);
				String url = "/front-end/articleDetails/updateArticleDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得修改資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
	}
}