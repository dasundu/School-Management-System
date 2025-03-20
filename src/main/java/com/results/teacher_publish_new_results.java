package com.results;

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

import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.ResultDBUtilFactory;
import com.factories.SubjectDBUtilFactory;
import com.result_Exceptions.ResultOperationException;
import com.subjects.subject;
import com.subjects.subjectDBUtil;

/**
 * Servlet implementation class teacher_publish_new_results
 */
@MultipartConfig(maxFileSize = 10485760)
@WebServlet("/teacher_publish_new_results")
public class teacher_publish_new_results extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");
		
		
		String resultDescription = request.getParameter("resultDescription");

		String subjectIdString = request.getParameter("subjectId");
		int subjectId = Integer.parseInt(subjectIdString);

		String ClassIdString = request.getParameter("classId");
		int classId = Integer.parseInt(ClassIdString);

		// Get Pdf
		Part ResultPdf = request.getPart("ResultSheet");
		InputStream PdfSource = ResultPdf.getInputStream();

		// Use the factory to create an instance of resultDBUtil
		resultDBUtil resultDBUtilInstance = ResultDBUtilFactory.createDefaultResultDBUtil();

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Publish new result
		boolean isResultPublished = resultDBUtilInstance.AddNewResult(resultDescription, subjectId, classId, PdfSource,
				TeacherID);

		// Check publish status
		try {
			if (isResultPublished == true) {
				// Assigned Subjects
				ArrayList<subject> allSubjects = new ArrayList<subject>();

				// Create instance of subjectDButil using a factory
				subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

				// Get assigned subjects for a teacher
				allSubjects = subjectDBUtilInstance.getTeachersSubjects(TeacherID);

				request.setAttribute("AllSubjects", allSubjects);
				request.setAttribute("creation", "success");

				// Assigned Classes
				ArrayList<classroom> AssignedClasses = ClassRoomFactory.getTeacherRelatedClasses(TeacherID);

				request.setAttribute("AssignedClasses", AssignedClasses);

				RequestDispatcher dis = request.getRequestDispatcher("teacher_publish_results.jsp");

				dis.forward(request, response);
			} else {
				throw new ResultOperationException();
			}

		} catch (ResultOperationException e) {
			e.logResultException("Teacher publishes new result.");
		}

	}

}
