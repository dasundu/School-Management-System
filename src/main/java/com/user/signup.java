package com.user;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.factories.User_DB_Util_Factory;
import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class signup
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");

		// Get Image
		Part newProPic = request.getPart("pic");
		InputStream imageSource = newProPic.getInputStream();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Admin signup an user
		boolean isSignupSuccess = User_DB_Factory.signup(username, email, password, role, imageSource);

		// Check signup status
		try {
			if (isSignupSuccess == true) {
				request.setAttribute("creation", "success");
				request.setAttribute("newUser", username);
				RequestDispatcher dispatcher = request.getRequestDispatcher("add_new_login.jsp");
				dispatcher.forward(request, response);
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("Admin adds new user . FAILED !");
		}

	}
}
