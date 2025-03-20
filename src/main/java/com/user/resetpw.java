package com.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.factories.User_DB_Util_Factory;
import com.user_Exceptions.User_Operation_Exception;

@WebServlet("/resetpw")
public class resetpw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Catch password
		String newPassword = request.getParameter("new");

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Update Password
		boolean isPassWordUpdated = User_DB_Factory.resetpw(newPassword, email);

		RequestDispatcher dispatcher = null;

		// Check Reset Status
		try {
			if (isPassWordUpdated == true) {
				request.setAttribute("status", "updated");
				dispatcher = request.getRequestDispatcher("resetPw.jsp");
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			dispatcher = request.getRequestDispatcher("login.jsp");
			e.logExceptionToConsole("User change password thorugh profile failed !");
		}

		dispatcher.forward(request, response);

	}

}
