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
 * Servlet implementation class teacher_enroll_subject_info_loader
 */
@WebServlet("/teacher_enroll_subject_info_loader")
public class teacher_enroll_subject_info_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		// Subject array
		ArrayList<subject> allSubjects = new ArrayList<subject>();

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Get teacher assigned subjects
		allSubjects = subjectDBUtilInstance.getTeachersUnassignedSubjects(TeacherID);

		// Send to teacher_enroll_subjects.jsp
		request.setAttribute("AllSubjects", allSubjects);
		RequestDispatcher dis = request.getRequestDispatcher("teacher_enroll_subjects.jsp");
		dis.forward(request, response);

	}

}
