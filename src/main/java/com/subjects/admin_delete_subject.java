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
 * Servlet implementation class admin_delete_subject
 */
@WebServlet("/admin_delete_subject")
public class admin_delete_subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get subject id
		String subjectid = request.getParameter("subid");

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Delete subject
		boolean isSubjectDeleted = subjectDBUtilInstance.DeleteSubject(subjectid);

		//Chech subject deletion status
		try {
			if (isSubjectDeleted == true) {
				request.setAttribute("deletion", "success");
			} else {
				throw new SubjectOperationException();
			}
		} catch (SubjectOperationException e) {
			e.logSubjectExceptionToConsole("Admin deletes subject");
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
