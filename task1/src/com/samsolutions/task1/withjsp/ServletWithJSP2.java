package com.samsolutions.task1.withjsp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletWithJSP2
 */
@WebServlet("/servletWithJsp2") 
public class ServletWithJSP2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4155510879499110069L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.sendRedirect("hello2.jsp"); // uncomment for demo
		
		// comment for demo
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("hello2.jsp");
		requestDispatcher.forward(request, response);
	}

}
