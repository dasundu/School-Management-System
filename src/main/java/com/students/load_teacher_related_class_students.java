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
 * Servlet implementation class load_teacher_related_class_students
 */
@WebServlet("/load_teacher_related_class_students")
public class load_teacher_related_class_students extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		String classIdString = request.getParameter("classSelect");
		int classid = Integer.parseInt(classIdString);

		// Student list
		ArrayList<student> StudentList = new ArrayList<student>();

		// Gett All Students
		StudentList = ClassRoomFactory.getRelatedStudents(classid);

		// Send student list
		request.setAttribute("Allstudents", StudentList);

		// Reload classes
		ArrayList<classroom> AssignedClasses = ClassRoomFactory.getTeacherRelatedClasses(TeacherID);

		request.setAttribute("AssignedClasses", AssignedClasses);

		// Set selected class
		request.setAttribute("SelectedClass", classid);

		RequestDispatcher dis = request.getRequestDispatcher("teacher_manage_students.jsp");

		dis.forward(request, response);

	}

}
