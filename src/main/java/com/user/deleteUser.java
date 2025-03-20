package com.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.User_DB_Util_Factory;
import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class deleteUser
 */
@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Get removing user id
		String userId = request.getParameter("DeletedUser");

		// Remove user
		boolean isUserDeleted = User_DB_Factory.deleteUser(userId);

		// Check deletion status
		try {
			if (isUserDeleted == true) {
				response.sendRedirect("PageLoadUsersManagement");
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("Admin user deletion has failed !!");
		}

	}

}
