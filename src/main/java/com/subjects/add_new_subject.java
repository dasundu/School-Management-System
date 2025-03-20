package com.subjects;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.SubjectDBUtilFactory;
import com.subject_Exceptions.SubjectOperationException;

/**
 * Servlet implementation class add_new_subject
 */
@WebServlet("/add_new_subject")
public class add_new_subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		// Get form data
		String subjectname = request.getParameter("subjectname");
		String description = request.getParameter("description");
		int no_of_hours = Integer.parseInt(request.getParameter("no_of_hours"));
		String enrollment_key = request.getParameter("enrollment_key");

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Add new subject
		boolean isSubjectAdded = subjectDBUtilInstance.AddNewSubject(subjectname, description, no_of_hours,
				enrollment_key);
		
		// Check subject creation status
		try {
			if (isSubjectAdded == true) {
				request.setAttribute("creation", "success");

			} else {
				throw new SubjectOperationException();
			}
		} catch (SubjectOperationException e) {
			e.logSubjectExceptionToConsole("Admin add new subject");
		}

		// Send to add_new_subject.jsp
		RequestDispatcher dis = request.getRequestDispatcher("add_new_subject.jsp");
		dis.forward(request, response);
	}

}
