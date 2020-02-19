package com.spot.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GetSpotPhoto extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
			req.setCharacterEncoding("UTF-8");
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
			
			try {
				Statement stmt = con.createStatement();
				String spotNo = req.getParameter("spotno").trim();
				System.out.println(spotNo);
				ResultSet rs = stmt.executeQuery(
						"SELECT SPOTPHOTO FROM SPOTLIST WHERE SPOTNO = '"+ spotNo +"'");
				if(rs.next()) {
					System.out.println("ok");
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(1));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
//					res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in = getServletContext().getResourceAsStream("/NoData/NoSpotPhoto.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
//				System.out.println(e);
				InputStream in = getServletContext().getResourceAsStream("/NoData/NoSpotPhoto.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
				e.printStackTrace();
			}
		}
		public void init() throws ServletException {
			
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
				con = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void destroy() {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
}
