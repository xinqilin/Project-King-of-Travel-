package com.Android.mem.controller;
import javax.servlet.http.*;

import com.Android.mem.model.MemVO;

public class GetMemPhto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		res.setContentType("image/gif");
		
		String photo = req.getParameter("memPhoto");
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		
	}
}
