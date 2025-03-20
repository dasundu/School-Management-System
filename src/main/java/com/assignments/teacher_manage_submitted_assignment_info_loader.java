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
 * Servlet implementation class teacher_manage_submitted_assignment_info_loader
 */
@WebServlet("/teacher_manage_submitted_assignment_info_loader")
public class teacher_manage_submitted_assignment_info_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
        // Get the session
        HttpSession session = request.getSession();
        
        
        //Retrieve the loginid attribute from the session
        int tacherid = (Integer) session.getAttribute("loginId");
        
		//Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();
        
        ArrayList<submittedAssignments> AllSubmittedAssignments = AssignmentFactory.getSubmittedAssignmentRelatedToTeacher(tacherid);
        
        request.setAttribute("AllSubmittedAssignments", AllSubmittedAssignments);
        
        RequestDispatcher dis = request.getRequestDispatcher("teacher_assignment_submissions_manage.jsp");
        dis.forward(request, response);
		
		
	}


}
