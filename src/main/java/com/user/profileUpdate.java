package com.user;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.factories.User_DB_Util_Factory;
import com.user_Exceptions.User_Operation_Exception;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/profileUpdate")
public class profileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get updated data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		// Get Image
		Part newProPic = request.getPart("propic");
		InputStream imageSource = newProPic.getInputStream();

		// Retrive session
		HttpSession session1 = request.getSession();
		int userId = (Integer) session1.getAttribute("loginId");
		String Acctype = (String) session1.getAttribute("loginrole");

		// Update Session
		session1.setAttribute("loginuser", username);
		session1.setAttribute("loginEmail", email);

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Create user Object
		user profileUpdateUser = new user(userId, username, password, email, Acctype);

		// Update profile
		boolean isProfileUpdated = User_DB_Factory.updateUserByProfile(profileUpdateUser, imageSource);

		// Check Update status
		try {
			if (isProfileUpdated == true) {
				// get updated user image from database
				String UserImage = User_DB_Factory.getProfilePic(userId);

				// Set image
				session1.setAttribute("userImage", UserImage);

				// Set successfull attribute
				String updateStatus = "yes";

				// Send profile update along with the link
				response.sendRedirect("PageLoadProfile?updateMsg=" + URLEncoder.encode(updateStatus, "UTF-8"));
			} else {
				throw new User_Operation_Exception();
			}

		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("User profile update has failed !!");
		}

	}
}
