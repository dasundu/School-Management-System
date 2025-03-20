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

@WebServlet("/PageLoadUsersManagement")
public class PageLoadUsersManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Users list
		ArrayList<user> usersList = User_DB_Factory.getAllUsers();

		request.setAttribute("Allusers", usersList);
		RequestDispatcher dis = request.getRequestDispatcher("logins_manage.jsp");
		dis.forward(request, response);
	}

}
