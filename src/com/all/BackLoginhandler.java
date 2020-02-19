package com.all;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BackLoginhandler.do")
public class BackLoginhandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected String allowUser(String account, String password) {
		if ("zzz".equals(account) && "zzz".equals(password))
			return "Back";
		else
			return "NO";
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("backaccount");
		String password = req.getParameter("password");
System.out.println("帳號:"+account+"    密碼:"+password);
		// 【檢查該帳號 , 密碼是否有效】
		if (allowUser(account, password).equals("Back")) {

			HttpSession session = req.getSession();
			session.setAttribute("backaccount", "紅毛猩猩偉銘");

			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}

			res.sendRedirect(req.getContextPath() + "/back-end/trip/b_trip_index.jsp");
		}
		else {
			out.println("<HTML><HEAD><TITLE>帳號密碼錯誤</TITLE>");
			out.println("<script type=\"text/javascript\">");
			out.println("onload=function(){setTimeout(go, 2000);}");
			out.println("function go(){location.href='" + req.getContextPath() + "/login_back.jsp';}</script></HEAD>");
			out.println("<BODY>你的帳號 , 密碼打錯了啦 !<BR>");
			out.println("3秒後自動跳轉，或按此重新登入 <A HREF=" + req.getContextPath() + "/login_back.jsp>重新登入</A>");
			out.println("</BODY></HTML>");
		}
	}

}
