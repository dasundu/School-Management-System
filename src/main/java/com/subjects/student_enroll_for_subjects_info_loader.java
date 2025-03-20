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
 * Servlet implementation class student_enroll_for_subjects_info_loader
 */
@WebServlet("/student_enroll_for_subjects_info_loader")
public class student_enroll_for_subjects_info_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int studentId = (Integer) session.getAttribute("loginId");

		// Subject arraylist
		ArrayList<subject> allSubjects = new ArrayList<subject>();

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		//Get unassigned subject for a specific student
		allSubjects = subjectDBUtilInstance.getStudentUnassignedSubjects(studentId);

		//Set request attribute and send it to student_enroll_for_subjects.jsp
		request.setAttribute("AllSubjects", allSubjects);
		RequestDispatcher dis = request.getRequestDispatcher("student_enroll_for_subjects.jsp");
		dis.forward(request, response);

	}
}
