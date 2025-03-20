package com.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class resetInfoRedirect
 */
@WebServlet("/resetInfoRedirect")
public class resetInfoRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String type = request.getParameter("type");

		request.setAttribute("user", username);
		request.setAttribute("email", email);
		request.setAttribute("type", type);

		RequestDispatcher dispatcher = request.getRequestDispatcher("resetPw.jsp");

		dispatcher.forward(request, response);
	}

}
