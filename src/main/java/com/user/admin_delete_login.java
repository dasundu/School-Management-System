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

@WebServlet("/admin_delete_login")
public class admin_delete_login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String useridString = request.getParameter("userid");

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Delete Login
		boolean isLoginDeleted = User_DB_Factory.deleteUser(useridString);

		try {
			// Check Login deletion
			if (isLoginDeleted == true) {
				request.setAttribute("deletion", "success");
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("Admin Deletes Login Failed !!");
		}

		// Users list
		ArrayList<user> usersList = User_DB_Factory.getAllUsers();
		request.setAttribute("Allusers", usersList);
		RequestDispatcher dis = request.getRequestDispatcher("logins_manage.jsp");
		dis.forward(request, response);

	}

}
