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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.factories.User_DB_Util_Factory;
import com.user_Exceptions.InvalidRoleException;
import com.user_Exceptions.User_Operation_Exception;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/user_update_profile")
public class user_update_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String userid = request.getParameter("userid");
		int userIntId = Integer.parseInt(userid);

		// Get Image
		Part newProPic = request.getPart("pic");
		InputStream imageSource = newProPic.getInputStream();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Check if the uploaded file is empty
		if (newProPic.getSize() > 0) {
			User_DB_Factory.UpdateProfilePicture(userid, imageSource);
		}

		// Get session using logged user
		HttpSession loginSession = request.getSession();

		// Retrieve the logirole attribute from the session
		String role = (String) loginSession.getAttribute("loginrole");

		// Update profile
		boolean isProfileUpdated = User_DB_Factory.UpdateProfile(userid, username, email);

		try {
			if (isProfileUpdated == true) {
				// Update session
				loginSession.setAttribute("loginuser", username);
				loginSession.setAttribute("loginEmail", email);
				loginSession.setAttribute("loginPass", password);

				// get user image from database
				String UserImage = User_DB_Factory.getProfilePic(userIntId);

				request.setAttribute("creation", "success"); // Set success message

				// Set image
				loginSession.setAttribute("userImage", UserImage);

				// Redirect user accrording to rol
				if ("admin".equals(role)) {
					RequestDispatcher dis = request.getRequestDispatcher("admin_update_account.jsp");
					dis.forward(request, response);
				} else if ("teacher".equals(role)) {
					RequestDispatcher dis = request.getRequestDispatcher("teacher_update_account.jsp");
					dis.forward(request, response);
				} else if ("student".equals(role)) {
					RequestDispatcher dis = request.getRequestDispatcher("student_update_account.jsp");
					dis.forward(request, response);
				} else {
					throw new InvalidRoleException();
				}

			} else {
				throw new User_Operation_Exception();
			}
		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("Admin update profile failed !!");
		} catch (InvalidRoleException e) {
			e.logExceptionToConsole("");
		}
	}
}
