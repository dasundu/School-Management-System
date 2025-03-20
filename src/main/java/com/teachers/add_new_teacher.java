package com.teachers;

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

import com.factories.Teacher_DB_Util_Factory;
import com.factories.User_DB_Util_Factory;
import com.teacher_Exceptions.TeacherOperationException;
import com.user.usersDBUtil;

/**
 * Servlet implementation class add_new_teacher
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/add_new_teacher")
public class add_new_teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Get teacher account info and teacher info
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = "teacher";
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String birthday = request.getParameter("birthday");
		String tele = request.getParameter("telephone");
		String address = request.getParameter("address");
		String specialization = request.getParameter("specialization");

		// Get Image
		Part newProPic = request.getPart("pic");
		InputStream imageSource = newProPic.getInputStream();

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Add teacher as an user first
		boolean isSignupSuccess = User_DB_Factory.signup(username, email, password, role, imageSource);

		// Get newly added userid
		int newlyAddedUserId = User_DB_Factory.GetLatestUserId();

		//Add teacher
		boolean isTeacherAdded = TeacherFactory.addNewTeacher(newlyAddedUserId, birthday, firstName, lastName, tele,
				address, specialization);

		// Check staus of new teacher creation
		try {
			if (isSignupSuccess == true  ) 
			{
				if(isTeacherAdded == true)
				{
					request.setAttribute("creation", "success"); // Success message
				}else {
					throw new TeacherOperationException();
				}
			}
		}catch(TeacherOperationException e)
		{
			e.logTeacherExceptionToConsole("Add new teacher by admin");
		}


		// Send to add_new_teacher.jsp
		RequestDispatcher dis = request.getRequestDispatcher("add_new_teacher.jsp");
		dis.forward(request, response);

	}

}
