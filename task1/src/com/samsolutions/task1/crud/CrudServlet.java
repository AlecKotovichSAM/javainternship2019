package com.samsolutions.task1.crud;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrudServlet
 */
@WebServlet("/crud")
public class CrudServlet extends HttpServlet implements ICrud<String, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3574634662991671633L;

	private IDataLayer dataLayer;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			dataLayer = new FileDataLayer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			response.getWriter().append(read(Long.valueOf(request.getParameter("id"))));
		} else {
			Collection<String> findAll = findAll();
			response.getWriter()
					.append(findAll //
							.stream() //
							.collect(Collectors.joining(",")));
			request.setAttribute("all", findAll);
			request.getRequestDispatcher("crud.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("_method");
		if (method != null) {
			if ("PUT".equalsIgnoreCase(method)) {
				doPut(request, response);
			}
			if ("DELETE".equalsIgnoreCase(method)) {
				doDelete(request, response);
			}
			return;
		}
		response.getWriter().append(String.valueOf(create(request.getParameter("data"))));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter()//
				.append(update(Long.valueOf(request.getParameter("id")), //
						request.getParameter("newData")));
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		delete(Long.valueOf(request.getParameter("id")));
	}

	@Override
	public Long create(String str) {
		try {
			return dataLayer.appendNewLine(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String read(Long id) {
		try {
			return dataLayer.readLine(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String update(Long id, String newData) {
		try {
			return dataLayer.editLine(id, newData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try {
			dataLayer.deleteLine(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<String> findAll() {
		return dataLayer.readAllLines();
	}

}
