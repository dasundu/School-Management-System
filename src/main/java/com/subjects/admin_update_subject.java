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
import com.subject_Exceptions.SubjectOperationException;

/**
 * Servlet implementation class admin_update_subject
 */
@WebServlet("/admin_update_subject")
public class admin_update_subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Subject info
		String subjectid = request.getParameter("subid");
		String subjectName = request.getParameter("subname");
		String subjectDes = request.getParameter("subdes");
		String subjectKey = request.getParameter("subenkey");
		String subjectHours = request.getParameter("hours");

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Update subject
		boolean isSubjectUpdated = subjectDBUtilInstance.updateSubject(subjectid, subjectName, subjectDes, subjectKey,
				subjectHours);

		// Check subject update status
		try {

			if (isSubjectUpdated == true) {
				request.setAttribute("creation", "success"); // Set success message
			} else {
				throw new SubjectOperationException();
			}
		} catch (SubjectOperationException e) {
			e.logSubjectExceptionToConsole("Admin updates subject");
		}

		// Get relevent data for admin_Subject_Manage.jsp
		ArrayList<subject> allSubjects = new ArrayList<subject>();
		allSubjects = subjectDBUtilInstance.getAllSubjects();
		request.setAttribute("AllSubjects", allSubjects);

		// Send to admin_Subject_Manage.jsp
		RequestDispatcher dis = request.getRequestDispatcher("admin_Subject_Manage.jsp");
		dis.forward(request, response);

	}

}
