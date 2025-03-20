package com.assignments;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.AssignmentDBUtilFactory;

/**
 * Servlet implementation class admin_assignment_loader
 */
@WebServlet("/admin_assignment_loader")
public class admin_assignment_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<assignment> AllAssignments = new ArrayList<assignment>();

		// Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();

		AllAssignments = AssignmentFactory.getAllAssignments();

		request.setAttribute("AllAssignments", AllAssignments);

		RequestDispatcher dis = request.getRequestDispatcher("admin_assignment_manage.jsp");

		dis.forward(request, response);

	}

}
