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
 * Servlet implementation class teacher_delete_assignment
 */
@WebServlet("/teacher_delete_assignment")
public class teacher_delete_assignment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String assignmentId = request.getParameter("assId");

		// Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();

		// Delete assignment
		boolean isAssignmentDeleted = AssignmentFactory.DeleteAssignment(assignmentId);

		// Deletion status check
		try {

			if (isAssignmentDeleted == true) {
				request.setAttribute("deletion", "success");
			} else {
				throw new AssignmentOperationException();
			}

		} catch (AssignmentOperationException e) {
			e.logExceptionToConsole("Teacher deletes assignment");
		}

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		ArrayList<assignment> AllAssignments = new ArrayList<assignment>();

		AllAssignments = AssignmentFactory.getTeacherUploadedAssignments(TeacherID);

		request.setAttribute("AllAssignments", AllAssignments);

		RequestDispatcher dis = request.getRequestDispatcher("announced_teacher_assignments.jsp");

		dis.forward(request, response);

	}

}
