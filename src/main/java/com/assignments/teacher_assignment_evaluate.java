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

import com.assignmnet_Exceptions.AssignmentOperationException;
import com.factories.AssignmentDBUtilFactory;

/**
 * Servlet implementation class teacher_assignment_evaluate
 */
@WebServlet("/teacher_assignment_evaluate")
public class teacher_assignment_evaluate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String marks = request.getParameter("marks");
		String submitid = request.getParameter("submitid");

		// Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();

		// Teacher evaluvate assignment
		boolean isEvaluated = AssignmentFactory.evaluateAssignments(marks, submitid);

		// Check evaluvation status
		try {
			if (isEvaluated == true) {
				// Get the session
				HttpSession session = request.getSession();

				// Retrieve the loginid attribute from the session
				int tacherid = (Integer) session.getAttribute("loginId");

				ArrayList<submittedAssignments> AllSubmittedAssignments = AssignmentFactory
						.getSubmittedAssignmentRelatedToTeacher(tacherid);

				request.setAttribute("AllSubmittedAssignments", AllSubmittedAssignments);

				RequestDispatcher dis = request.getRequestDispatcher("teacher_assignment_submissions_manage.jsp");
				dis.forward(request, response);

			} else {
				throw new AssignmentOperationException();
			}

		} catch (AssignmentOperationException e) {
			e.logExceptionToConsole("Teacher evaluvate assignment.");
		}

	}

}
