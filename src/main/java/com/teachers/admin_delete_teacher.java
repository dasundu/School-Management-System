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
 * Servlet implementation class admin_delete_teacher
 */
@WebServlet("/admin_delete_teacher")
public class admin_delete_teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get logged user
		String userIdString = request.getParameter("userid");
		int userid = Integer.parseInt(userIdString);

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		// Delete Teacher
		boolean isTeacherDeleted = TeacherFactory.DeleteTeacher(userid);

		// Check deletion status
		try {
			if (isTeacherDeleted == true) {
				request.setAttribute("deletion", "success");
			} else {
				throw new TeacherOperationException();
			}
		} catch (TeacherOperationException e) {
			e.logTeacherExceptionToConsole("Admin deletes a teacher.");
		}

		// Student list
		ArrayList<teacher> teachersList = new ArrayList<teacher>();

		// Gett All Students
		teachersList = TeacherFactory.getAllTeachers();

		// Send Teacher list
		request.setAttribute("AllTeachers", teachersList);
		RequestDispatcher dis = request.getRequestDispatcher("teachers.jsp");
		dis.forward(request, response);
	}

}
