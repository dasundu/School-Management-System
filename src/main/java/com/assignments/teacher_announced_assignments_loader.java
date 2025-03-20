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
 * Servlet implementation class teacher_announced_assignments_loader
 */
@WebServlet("/teacher_announced_assignments_loader")
public class teacher_announced_assignments_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        // Get the session
        HttpSession session = request.getSession();
        
        
        //Retrieve the loginid attribute from the session
        int TeacherID = (Integer) session.getAttribute("loginId");
		
		//Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();
		
		ArrayList<assignment> AllAssignments = new ArrayList<assignment>();
		
		AllAssignments = AssignmentFactory.getTeacherUploadedAssignments(TeacherID);
		
		request.setAttribute("AllAssignments",AllAssignments);
		
		RequestDispatcher dis = request.getRequestDispatcher("announced_teacher_assignments.jsp");
		
		dis.forward(request, response);
		
	}



}
