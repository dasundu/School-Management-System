package com.subjects;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.SubjectDBUtilFactory;

/**
 * Servlet implementation class adminSubjectsPageLoader
 */
@WebServlet("/adminSubjectsPageLoader")
public class adminSubjectsPageLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Subject arrayList
		ArrayList<subject> allSubjects = new ArrayList<subject>();

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		//Get all subjects
		allSubjects = subjectDBUtilInstance.getAllSubjects();

		//Set request subjects attribute 
		request.setAttribute("AllSubjects", allSubjects);

		//Send to admin_Subject_Manage.jsp
		RequestDispatcher dis = request.getRequestDispatcher("admin_Subject_Manage.jsp");
		dis.forward(request, response);

	}
}
