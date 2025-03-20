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
import com.subject_Exceptions.SubjectOperationException;

/**
 * Servlet implementation class student_enroll_subject
 */
@WebServlet("/student_enroll_subject")
public class student_enroll_subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int studentID = (Integer) session.getAttribute("loginId");

		// Get subject enrollment info
		String enrollmentKey = request.getParameter("EnrollmentKey");
		String subjectIdString = request.getParameter("subjectId");
		int subjectid = Integer.parseInt(subjectIdString);

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Enroll for the subject
		boolean isEnrolled = subjectDBUtilInstance.StudentEnrollSubject(studentID, subjectid, enrollmentKey);

		// Check enrollment status
		try {
			if (isEnrolled == true) {
				request.setAttribute("creation", "success");
			} else {
				throw new SubjectOperationException();
			}
		} catch (SubjectOperationException e) {
			e.logSubjectExceptionToConsole("Student enroll for a subject");
		}

		ArrayList<subject> allSubjects = new ArrayList<subject>(); // Suject array

		// Get relevent data for student_enroll_for_subjects.jsp
		allSubjects = subjectDBUtilInstance.getStudentUnassignedSubjects(studentID);
		request.setAttribute("AllSubjects", allSubjects);

		// Send to student_enroll_for_subjects.jsp
		RequestDispatcher dis = request.getRequestDispatcher("student_enroll_for_subjects.jsp");
		dis.forward(request, response);

	}

}
