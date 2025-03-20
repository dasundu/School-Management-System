package com.classes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.factories.Classroom_DB_Util_Factory;

/**
 * Servlet implementation class teacher_classes_info_loader
 */
@WebServlet("/teacher_classes_info_loader")
public class teacher_classes_info_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Get teacher assigned classes
		ArrayList<classroom> AssignedClasses = ClassRoomFactory.getTeacherRelatedClasses(TeacherID);

		// Set request attribute
		request.setAttribute("AssignedClasses", AssignedClasses);

		// Send the request attribute
		RequestDispatcher dis = request.getRequestDispatcher("teacher_classes_info.jsp");
		dis.forward(request, response);

	}

}
