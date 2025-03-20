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
 * Servlet implementation class admin_submitted_assignment_manage_loader
 */
@WebServlet("/admin_submitted_assignment_manage_loader")
public class admin_submitted_assignment_manage_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();
        
        ArrayList<submittedAssignments> AllSubmittedAssignments = AssignmentFactory.getSubmittedAssignment();
        
        request.setAttribute("AllSubmittedAssignments", AllSubmittedAssignments);
        
        RequestDispatcher dis = request.getRequestDispatcher("admin_manage_assigments_submissions.jsp");
        dis.forward(request, response);
	}



}
