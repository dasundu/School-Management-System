package com.students;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.Classroom_DB_Util_Factory;

/**
 * Servlet implementation class add_new_student_page_loader
 */
@WebServlet("/add_new_student_page_loader")
public class add_new_student_page_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Classroom arraylist
		ArrayList<classroom> AllClasses = new ArrayList<classroom>();

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Get all classrooms
		AllClasses = ClassRoomFactory.getAllClasses();

		// Set request attribute for AllClasses
		request.setAttribute("Allclasses", AllClasses);

		// Send to add_new_student.jsp
		RequestDispatcher dis = request.getRequestDispatcher("add_new_student.jsp");
		dis.forward(request, response);

	}

}
