package com.students;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.Classroom_DB_Util_Factory;

/**
 * Servlet implementation class teacher_mange_students_loader
 */
@WebServlet("/teacher_mange_students_loader")
public class teacher_mange_students_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();
		
		ArrayList<classroom> AssignedClasses = ClassRoomFactory.getTeacherRelatedClasses(TeacherID);

		request.setAttribute("AssignedClasses", AssignedClasses);

		RequestDispatcher dis = request.getRequestDispatcher("teacher_manage_students.jsp");

		dis.forward(request, response);

	}

}
