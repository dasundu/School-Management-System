package com.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user_Exceptions.User_Operation_Exception;

@WebServlet("/logout")
public class logout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Invalidate the session
		HttpSession session = request.getSession(false);

		// Logout
		try {
			if (session != null) {
				session.invalidate();
				// Set response status to indicate success (HTTP 200 OK)
				response.sendRedirect("login.jsp");
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("Logout failed !!");
		}

	}
}
