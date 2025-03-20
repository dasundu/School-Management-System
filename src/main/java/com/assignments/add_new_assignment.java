package com.assignments;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.assignmnet_Exceptions.AssignmentOperationException;
import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.AssignmentDBUtilFactory;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.SubjectDBUtilFactory;
import com.subjects.subject;
import com.subjects.subjectDBUtil;

/**
 * Servlet implementation class add_new_assignment
 */
@MultipartConfig(maxFileSize = 10485760)
@WebServlet("/add_new_assignment")
public class add_new_assignment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");
		// Get assignment title
		String title = request.getParameter("assignmentTitle");

		String subjectIdString = request.getParameter("subjectId");
		int subjectId = Integer.parseInt(subjectIdString);

		String ClassIdString = request.getParameter("classId");
		int classId = Integer.parseInt(ClassIdString);

		// Get Pdf
		Part assignmentPdf = request.getPart("assignmentInfo");
		InputStream PdfSource = assignmentPdf.getInputStream();

		// Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		boolean isAssignmentAdded = AssignmentFactory.AddNewAssignment(title, subjectId, classId, PdfSource, TeacherID);

		try {
			if (isAssignmentAdded == true) {

				// Create instance of subjectDButil using a factory
				subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

				// Get assigned subjects
				ArrayList<subject> TeacherSubjects = subjectDBUtilInstance.getTeachersSubjects(TeacherID);

				// Get classes
				ArrayList<classroom> AllClasses = ClassRoomFactory.getAllClasses();

				request.setAttribute("creation", "success");
				request.setAttribute("Subjects", TeacherSubjects);
				request.setAttribute("AllClasses", AllClasses);

				RequestDispatcher dis = request.getRequestDispatcher("add_new_assignment.jsp");

				dis.forward(request, response);

			} else {
				throw new AssignmentOperationException();
			}

		} catch (AssignmentOperationException e) {
			e.logExceptionToConsole("Add new assignment.");
		}
	}
}
