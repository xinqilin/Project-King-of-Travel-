package com.wishitem.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishitem.model.WishItemService;
import com.wishitem.model.WishItemVO;

@WebServlet("/getDatabaseServlet.do")
public class GetDatabasePicServet extends HttpServlet{
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			res.setContentType("image/gif");
			String wishItemNo = req.getParameter("wishItemNo").trim();


			WishItemService wishItemSvc = new WishItemService();
			WishItemVO wishItemVO = new WishItemVO();
			


			if(wishItemNo != null) {
				wishItemVO = wishItemSvc.getOneWishItem(wishItemNo);
				byte[] buf = wishItemVO.getWishItemPicture();
				out.write(buf);
			}
			

			//buf = lBPVO.getLBP_PHOTO(); // 4K buffer
			//out.write(buf);
		}catch (Exception e) {
						
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();

		}
	}
}
