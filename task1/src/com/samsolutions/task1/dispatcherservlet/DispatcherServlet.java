package com.samsolutions.task1.dispatcherservlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(urlPatterns = { "/mywebapp", "/mywebapp/", "/mywebapp/view1", "/mywebapp/view2", "/mywebapp/view3" })
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4155510879499110069L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String viewName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
		String destination;

		switch (viewName) {
		case "view1":
			destination = "/view1.jsp";
			break;
		case "view2":
			destination = "/view2.jsp";
			break;
		case "view3":
			destination = "/view3.jsp";
			break;
		default:
			response.getWriter() //
					.append("Mywebapp Home Page");
			return;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
		requestDispatcher.forward(request, response);
	}

}
