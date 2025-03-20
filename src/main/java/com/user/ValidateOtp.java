package com.user;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class ValidateOtp
 */
@WebServlet("/ValidateOtp")
public class ValidateOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get OTP From user
		int userOtp = Integer.parseInt(request.getParameter("otp"));

		// Get Session otp
		HttpSession session = request.getSession();
		int SessionOtp = (int) session.getAttribute("otp");

		RequestDispatcher dispatcher = null;

		// Validate OTP Code
		try {
			if (userOtp == SessionOtp) {
				dispatcher = request.getRequestDispatcher("resetPw.jsp");
				dispatcher.forward(request, response);

			} else {
				throw new User_Operation_Exception();

			}

		} catch (User_Operation_Exception e) {
			request.setAttribute("checkedEmail", "invalid");
			dispatcher = request.getRequestDispatcher("enterOTP.jsp");
			dispatcher.forward(request, response);
			e.logExceptionToConsole("User OTP Validation Failed !");
		}

	}

}
