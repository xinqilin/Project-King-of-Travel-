package com.city.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetCityPhoto extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("image/gif");
		String file=req.getParameter("getImgByFile");
		InputStream in=getServletContext().getResourceAsStream(file);
		OutputStream os= res.getOutputStream();
		byte[] buf = new byte[in.available()];
		try{
			in.read(buf);
			os.write(buf);
			
			os.flush();
			os.close();
			in.close();
			
		}catch(Exception e){
			InputStream inNoData=getServletContext().getResourceAsStream("/img/null2.jpg");
			byte[] b=new byte[in.available()];
			inNoData.read(b);
			os.write(b);
			in.close();
		}
	}

}
