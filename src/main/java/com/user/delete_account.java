package com.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class delete_account
 */
@WebServlet("/delete_account")
public class delete_account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int userID = (Integer) session.getAttribute("loginId");

		boolean accountDeleted = usersDBUtil.AccountDeletion(userID);

		RequestDispatcher dis = null;

		// Delete Account
		try {
			if (accountDeleted == true) {

				request.setAttribute("deletion", "success");
				dis = request.getRequestDispatcher("login.jsp");
				dis.forward(request, response);
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {

			e.logExceptionToConsole("User account deletion has failed !!." + "User ID = " + userID);
		}
	}

}
