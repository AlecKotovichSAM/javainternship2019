package com.samsolutions.task1.threadperrequest;

import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadPerRequestServlet
 */
@WebServlet("/threadPerRequestServlet")
public class ThreadPerRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ThreadLocal<String> myBankCard = new ThreadLocal<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThreadPerRequestServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	private String generateCardNumber() {
		StringBuilder stringBuilder = new StringBuilder();
		IntStream.rangeClosed(1, 16).forEach(x -> //
		stringBuilder.append(new Random().nextInt(10)));
		return stringBuilder.toString();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long currentThread = Thread.currentThread().getId();

		initCardnumberLazily();

		response.getWriter() //
				.append("Served at: ") //
				.append(request.getContextPath()) //
				.append(" by thread #") //
				.append(Long.valueOf(currentThread).toString()) //
				.append(". My bank card is ") //
				.append(this.myBankCard.get());
	}

	private void initCardnumberLazily() {
		if (this.myBankCard.get() == null) {
			this.myBankCard.set(generateCardNumber());
		}
	}

}
