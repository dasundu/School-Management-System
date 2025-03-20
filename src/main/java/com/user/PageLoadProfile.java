package com.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.factories.User_DB_Util_Factory;

/**
 * Servlet implementation class PageLoadProfile
 */
@WebServlet("/PageLoadProfile")
public class PageLoadProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrive session
		HttpSession session1 = request.getSession();

		// Get logged user
		int loginId = (Integer) session1.getAttribute("loginId");

		ArrayList<String> currentUserData = new ArrayList<String>();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		currentUserData = User_DB_Factory.getSpecificUser(loginId);

		String user = currentUserData.get(0);
		String email = currentUserData.get(1);
		String pass = currentUserData.get(2);
		String image = currentUserData.get(3);

		request.setAttribute("testUserName", user);
		request.setAttribute("testemail", email);
		request.setAttribute("testpassword", pass);
		request.setAttribute("testimage", image);

		// Set successfull attribute
		String updateStatus = request.getParameter("updateMsg");
		request.setAttribute("isUpdated", updateStatus);

		RequestDispatcher dis = request.getRequestDispatcher("profile.jsp");
		dis.forward(request, response);

	}

}
