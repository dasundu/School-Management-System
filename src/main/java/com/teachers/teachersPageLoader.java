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

/**
 * Servlet implementation class teachersPageLoader
 */
@WebServlet("/teachersPageLoader")
public class teachersPageLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Student list
		ArrayList<teacher> teachersList = new ArrayList<teacher>();

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		// Gett All Teachers
		teachersList = TeacherFactory.getAllTeachers();

		// Send student list
		request.setAttribute("AllTeachers", teachersList);
		RequestDispatcher dis = request.getRequestDispatcher("teachers.jsp");
		dis.forward(request, response);

	}

}
