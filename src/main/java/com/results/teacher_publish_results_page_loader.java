package com.results;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.SubjectDBUtilFactory;
import com.subjects.subject;
import com.subjects.subjectDBUtil;

/**
 * Servlet implementation class teacher_publish_results_page_loader
 */
@WebServlet("/teacher_publish_results_page_loader")
public class teacher_publish_results_page_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		// Assigned Subjects
		ArrayList<subject> allSubjects = new ArrayList<subject>();

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Get assigned subjects for a teacher
		allSubjects = subjectDBUtilInstance.getTeachersSubjects(TeacherID);

		request.setAttribute("AllSubjects", allSubjects);

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Assigned Classes
		ArrayList<classroom> AssignedClasses = ClassRoomFactory.getTeacherRelatedClasses(TeacherID);

		request.setAttribute("AssignedClasses", AssignedClasses);

		RequestDispatcher dis = request.getRequestDispatcher("teacher_publish_results.jsp");

		dis.forward(request, response);
	}

}
