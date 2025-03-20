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

/**
 * Servlet implementation class student_assignment_marks_loader
 */
@WebServlet("/student_assignment_marks_loader")
public class student_assignment_marks_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int Studentid = (Integer) session.getAttribute("loginId");

		// Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();

		ArrayList<submittedAssignments> AllSubmittedAssignments = AssignmentFactory
				.getSubmittedAssignmentForStudent(Studentid);

		request.setAttribute("AllSubmittedAssignments", AllSubmittedAssignments);

		RequestDispatcher dis = request.getRequestDispatcher("student_assignment_marks.jsp");
		dis.forward(request, response);

	}

}
