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
import com.user_Exceptions.User_Operation_Exception;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Catch user data
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Send user data to users DBUtili Servlet and receive logged user details
		ArrayList<user> loggedUser = User_DB_Factory.login(username, password);

		// Create dispatcher
		RequestDispatcher dispatcher = null;

		// Role variable
		String role;
		int Id;
		String credentials = "valid";
		String email;

		try {
			if (loggedUser.get(0).getId() == 0) {
				throw new User_Operation_Exception();
			} else {
				request.setAttribute("cred", credentials);
				// Start a session using logged user
				HttpSession loginSession = request.getSession();

				// Set session attributes
				role = loggedUser.get(0).getRole();
				Id = loggedUser.get(0).getId();
				email = loggedUser.get(0).getEmail();

				loginSession.setAttribute("loginuser", username);
				loginSession.setAttribute("loginrole", role);
				loginSession.setAttribute("loginId", Id);
				loginSession.setAttribute("loginEmail", email);
				loginSession.setAttribute("loginPass", password);

				// get user image from database
				String UserImage = User_DB_Factory.getProfilePic(Id);

				// Set image
				loginSession.setAttribute("userImage", UserImage);
				response.sendRedirect("DashboardLoader");
			}
		} catch (User_Operation_Exception e) {
			credentials = "invalid";
			request.setAttribute("cred", credentials);
			dispatcher = request.getRequestDispatcher("login.jsp");

			// Send dispatcher
			dispatcher.forward(request, response);
			e.logExceptionToConsole("Login Failed");
		}

	}

}
