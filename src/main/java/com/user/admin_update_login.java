package com.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.User_DB_Util_Factory;
import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class admin_update_login
 */
@WebServlet("/admin_update_login")
public class admin_update_login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String useridString = request.getParameter("userid");
		int userid = Integer.parseInt(useridString);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Update login
		boolean isLoginUpdated = User_DB_Factory.updateLogin(userid, username, email, password, role);

		// Check login update status
		try {
			if (isLoginUpdated == true) {
				// Users list
				ArrayList<user> usersList = User_DB_Factory.getAllUsers();

				request.setAttribute("creation", "success");
				request.setAttribute("Allusers", usersList);
				RequestDispatcher dis = request.getRequestDispatcher("logins_manage.jsp");
				dis.forward(request, response);
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("Admin Update Login Failed");
		}

	}

}
