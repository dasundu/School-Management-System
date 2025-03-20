package com.teachers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.Teacher_DB_Util_Factory;
import com.teacher_Exceptions.TeacherOperationException;

/**
 * Servlet implementation class admin_update_teacher
 */
@WebServlet("/admin_update_teacher")
public class admin_update_teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Capture teacher info
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String BirthDate = request.getParameter("bdate");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		String userIdString = request.getParameter("userid");
		int userid = Integer.parseInt(userIdString);
		String specialization = request.getParameter("speci");

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		// Update Teacher
		boolean isTeacherUpdated = TeacherFactory.UpdateTeacher(userid, BirthDate, firstName, lastName, telephone,
				address, specialization);
		
		// Teacher update status check
		try {
			if (isTeacherUpdated == true) {
				// Send student list
				request.setAttribute("creation", "success");
			}else {
				throw new TeacherOperationException();
			}
			
		}catch(TeacherOperationException e)
		{
			e.logTeacherExceptionToConsole("Admin updates teacher");
		}


		// Student list
		ArrayList<teacher> teachersList = new ArrayList<teacher>();

		// Gett All teachers
		teachersList = TeacherFactory.getAllTeachers();

		// Send student list
		request.setAttribute("AllTeachers", teachersList);
		RequestDispatcher dis = request.getRequestDispatcher("teachers.jsp");
		dis.forward(request, response);

	}

}
