package com.all;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/BackLogouthandler.do")
public class BackLogouthandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BackLogouthandler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/back-end/trip/b_trip_index.jsp");
	}


}
