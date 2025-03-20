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
 * Servlet implementation class teacher_enroll_subject
 */
@WebServlet("/teacher_enroll_subject")
public class teacher_enroll_subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");
		
		// Enrollment info
		String enrollmentKey = request.getParameter("EnrollmentKey");
		String subjectIdString = request.getParameter("subjectId");
		int subjectid = Integer.parseInt(subjectIdString);

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Teacher Enroll for a subject
		boolean isEnrolled = subjectDBUtilInstance.TeacherEnrollSubject(TeacherID, subjectid, enrollmentKey);

		// Check enrollment 
		try {
			if (isEnrolled == true) {
				request.setAttribute("creation", "success");
			}else {
				throw new SubjectOperationException();
			}
		}catch(SubjectOperationException e)
		{
			e.logSubjectExceptionToConsole("Teacher enrolls for subject");
		}

        // Subject array
		ArrayList<subject> allSubjects = new ArrayList<subject>();

		// Get unassigned subject for teacher
		allSubjects = subjectDBUtilInstance.getTeachersUnassignedSubjects(TeacherID);
		request.setAttribute("AllSubjects", allSubjects); // Set unassigned subject attribute

		// Send to teacher_enroll_subjects.jsp
		RequestDispatcher dis = request.getRequestDispatcher("teacher_enroll_subjects.jsp");
		dis.forward(request, response);
	}

}
