package com.assignments;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.factories.AssignmentDBUtilFactory;
import com.factories.Student_DB_Util_Factory;
import com.students.studentsDBUtil;

/**
 * Servlet implementation class student_new_assignment_loader
 */
@WebServlet("/student_new_assignment_loader")
public class student_new_assignment_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int Studentid = (Integer) session.getAttribute("loginId");

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		// Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();

		// Student's Class
		int classid = StudentFactory.getStudentClass(Studentid);

		// Get Related assignments
		ArrayList<assignment> RelatedAssignments = AssignmentFactory.getAllAssignmentsForClass(classid, Studentid);

		request.setAttribute("RelatedAssignments", RelatedAssignments);

		RequestDispatcher dis = request.getRequestDispatcher("student_new_assignments.jsp");
		dis.forward(request, response);

	}

}
