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

/**
 * Servlet implementation class change_password
 */
@WebServlet("/change_password")
public class change_password extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginrole attribute from the session
		String loginRole = (String) session.getAttribute("loginrole");

		// Old and new passwords
		String userid = request.getParameter("userid");

		String currentPassword = request.getParameter("currentPassword");

		String newPassword = request.getParameter("newPassword");

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Check password
		boolean isPasswordCorrect = User_DB_Factory.CheckPassword(userid, currentPassword);

		// Update password
		try {
			if (isPasswordCorrect == true) {
				try {
					boolean isUpdated = User_DB_Factory.UpdatePassword(userid, newPassword);

					if (isUpdated == true) {
						request.setAttribute("creation", "success"); // send a success message
					} else {
						throw new User_Operation_Exception();
					}
				} catch (User_Operation_Exception e) {
					e.logExceptionToConsole("User profile update failed.");
				}
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			// Send an error message
			request.setAttribute("deletion", "success");
			e.logExceptionToConsole("Change password failed !! Passwords mismatch.");
		}

		// Redirect to specific user
		try {
			if ("admin".equals(loginRole)) {
				RequestDispatcher dis = request.getRequestDispatcher("admin_update_account.jsp");
				dis.forward(request, response);
			} else if ("teacher".equals(loginRole)) {
				RequestDispatcher dis = request.getRequestDispatcher("teacher_update_account.jsp");
				dis.forward(request, response);
			} else if ("student".equals(loginRole)) {
				RequestDispatcher dis = request.getRequestDispatcher("student_update_account.jsp");
				dis.forward(request, response);
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole(loginRole);
		}
	}
}
