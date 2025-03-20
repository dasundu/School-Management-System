package com.classes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.Classroom_DB_Util_Factory;
import com.students.student;

/**
 * Servlet implementation class assign_class_rep
 */
@WebServlet("/assign_class_rep")
public class assign_class_rep extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String classIdString = request.getParameter("classSelect");
		int classid = Integer.parseInt(classIdString);

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		String classOnSelect = ClassRoomFactory.getClassName(classid);

		// Student list
		ArrayList<student> StudentList = new ArrayList<student>();

		// Gett All Students
		StudentList = ClassRoomFactory.getNonReps(classid);

		// Send student list
		request.setAttribute("Allstudents", StudentList);

		// Reload classes
		ArrayList<classroom> AllClasses = new ArrayList<classroom>();

		AllClasses = ClassRoomFactory.getAllClasses();

		request.setAttribute("Allclasses", AllClasses);

		// Set selected class
		request.setAttribute("SelectedClass", classid);

		request.setAttribute("classOnSelect", classOnSelect);

		RequestDispatcher dis = request.getRequestDispatcher("assign_reps.jsp");

		dis.forward(request, response);
	}

}
