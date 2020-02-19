package com.articleDetails.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/articleDetails/ArticleDetailsPic")
public class ArticleDetailsPic extends HttpServlet{

	Connection con;
	
	private static final long serialVersionUID = 1L;
	public ArticleDetailsPic() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Statement stmt = con.createStatement();
			String articleno = req.getParameter("articleno").trim();
			String spotno = req.getParameter("spotno").trim();
			ResultSet rs = stmt.executeQuery("SELECT articledetailspic FROM articledetails WHERE articleno = '"+articleno+"' and  spotno = '"+spotno+"'");
			if(rs.next()) {
				
				BufferedInputStream bis = new BufferedInputStream(rs.getBinaryStream("articledetailspic"));
				byte[] b = new byte[4 * 1024];
				int len;
				while((len = bis.read(b)) != -1) {
					out.write(b, 0, len);
				
				}
				
				bis.close();
			
			}else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
			rs.close();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
	
	public void init() throws ServletException{
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			if(con != null) 
				con.close();	
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
