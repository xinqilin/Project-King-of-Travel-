package com.all;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Loginhandler.do")
public class Loginhandler extends HttpServlet {
	private static final long serialVersionUID = 2L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected boolean allowUser(String account, String password) {
		MemService memSvc = new MemService();
		MemVO memVO = memSvc.getEmail(account);
		if(memVO==null)
		{
			return false;
		}
		System.out.println(memVO);
		System.out.println(memVO.getE_mail());
		System.out.println(memVO.getMemPassWd());
		if ((memVO.getE_mail().equals(account)) && (memVO.getMemPassWd().equals(password)))
			return true;
		else
			return false;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		System.out.println(account);
		System.out.println(password);
		
		// 【檢查該帳號 , 密碼是否有效】
		if (allowUser(account, password)==true) {
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getEmail(account);
			HttpSession session = req.getSession();
			session.setAttribute("account", memVO.getNickName()); // *工作1: 才在session內做已經登入過的標識
			session.setAttribute("memno", memVO.getMemNo());
			session.setAttribute("accountVO", memVO);
			
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
	
		else {
			out.println("<HTML><HEAD><TITLE>帳號密碼錯誤</TITLE>");
			out.println("<script type=\"text/javascript\">");
			out.println("onload=function(){setTimeout(go, 2000);}");
			out.println("function go(){location.href='" + req.getContextPath() + "/login.jsp';}</script></HEAD>");
			out.println("<BODY>你的帳號 , 密碼打錯了啦 !<BR>");
			out.println("3秒後自動跳轉，或按此重新登入 <A HREF=" + req.getContextPath() + "/login.jsp>重新登入</A>");
			out.println("</BODY></HTML>");
		}
	}

}
