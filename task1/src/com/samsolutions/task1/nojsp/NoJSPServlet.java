package com.samsolutions.task1.nojsp;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoJSPServlet
 */
@WebServlet("/noJspServlet")
public class NoJSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = "<title>Task 1 :: Servlet without JSP</title>";
		String head = new StringBuilder() //
				.append("<head>") //
				.append(title) //
				.append("</head>") //
				.toString();
		String text = "Hello, SaM Solutions!";
		String bodyOpen = "<body>";
		String bodyClose = "</body>";
		String body = new StringBuilder() //
				.append(bodyOpen).append(text) //
				.append(bodyClose) //
				.toString();
		String beginHtml = "<html>";
		String endHtml = "</html>";
		String html = new StringBuilder() //
				.append(beginHtml) //
				.append(head) //
				.append(body) //
				.append(endHtml) //
				.toString();

		response.getWriter().append(html);
	}

}
