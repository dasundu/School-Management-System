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
import com.factories.AssignmentDBUtilFactory;
import com.factories.Student_DB_Util_Factory;
import com.students.studentsDBUtil;

/**
 * Servlet implementation class student_submit_assignments
 */
@MultipartConfig(maxFileSize = 10485760)
@WebServlet("/student_submit_assignments")
public class student_submit_assignments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int StudentId = (Integer) session.getAttribute("loginId");

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		// Factory for assignment DB util
		assignmentDBUtil AssignmentFactory = AssignmentDBUtilFactory.createAssignmentDBUtilInstance();

		String AssignmentIdString = request.getParameter("AssignmentId");
		int AssignmentId = Integer.parseInt(AssignmentIdString);

		// Get Pdf
		Part assignmentPdf = request.getPart("submittedAssignment");
		InputStream PdfSource = assignmentPdf.getInputStream();

		//Submite assignment
		boolean isAssignmentSubmitted = AssignmentFactory.SubmitAssignment(StudentId, AssignmentId, PdfSource);
		
		
		//Check submission status
		try {
			if (isAssignmentSubmitted == true) {
				request.setAttribute("creation", "success");
			}else {
				throw new AssignmentOperationException();
			}
			
		}catch(AssignmentOperationException e)
		{
			e.logExceptionToConsole("Student submits assignment");
		}
		

		// Retrieve the loginid attribute from the session
		int Studentid = (Integer) session.getAttribute("loginId");

		// Student Class
		int classid = StudentFactory.getStudentClass(Studentid);

		// Get Related assignments
		ArrayList<assignment> RelatedAssignments = AssignmentFactory.getAllAssignmentsForClass(classid, Studentid);

		request.setAttribute("RelatedAssignments", RelatedAssignments);

		RequestDispatcher dis = request.getRequestDispatcher("student_new_assignments.jsp");
		dis.forward(request, response);

	}

}
