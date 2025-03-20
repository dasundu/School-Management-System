package com.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.factories.User_DB_Util_Factory;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/recovery1")
public class recovery1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Catch email
		String email = request.getParameter("email");

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Receive recovery account details
		ArrayList<user> recoveryAccount = User_DB_Factory.recoveryDetails(email);

		String CheckEmail = "invalid";

		RequestDispatcher dispatcher = null;

		int otpvalue = 0;
		HttpSession mySession = request.getSession();

		if (!recoveryAccount.isEmpty()) {
			// sending otp
			Random rand = new Random();
			otpvalue = rand.nextInt(1255650);

			String to = email;// change accordingly
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("edusyncreserved@gmail.com", "kthkwioieubkvyid");// Put your email
																										// id and
																										// password here
				}
			});
			// compose message
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email));// change accordingly
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Password Reset For Your EduSync Account");
				message.setText("OTP -  " + otpvalue);
				// send message
				Transport.send(message);
				System.out.println("message sent successfully");
			}

			catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			dispatcher = request.getRequestDispatcher("enterOTP.jsp");

			request.setAttribute("message", "OTP is sent to your email id");

			mySession.setAttribute("otp", otpvalue);
			mySession.setAttribute("email", email);
			dispatcher.forward(request, response);

		} else {
			request.setAttribute("checkedEmail", CheckEmail);
			dispatcher = request.getRequestDispatcher("forgotPWStep1.jsp");
			dispatcher.forward(request, response);
		}

	}

}
