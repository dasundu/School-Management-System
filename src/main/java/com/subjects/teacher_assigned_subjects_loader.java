package com.subjects;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.factories.SubjectDBUtilFactory;

/**
 * Servlet implementation class teacher_assigned_subjects_loader
 */
@WebServlet("/teacher_assigned_subjects_loader")
public class teacher_assigned_subjects_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		// Subject arraylist
		ArrayList<subject> allSubjects = new ArrayList<subject>();

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Get assign subjects for a teacher
		allSubjects = subjectDBUtilInstance.getTeachersSubjects(TeacherID);

		// Set request attribute
		request.setAttribute("AllSubjects", allSubjects);
		
		// Send to teacher_assigned_subjects.jsp
		RequestDispatcher dis = request.getRequestDispatcher("teacher_assigned_subjects.jsp");
		dis.forward(request, response);

	}
}
