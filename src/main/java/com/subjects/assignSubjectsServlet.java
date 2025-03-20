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
import com.factories.Teacher_DB_Util_Factory;
import com.subject_Exceptions.SubjectOperationException;
import com.teachers.teacher;
import com.teachers.teacherDBUtil;

/**
 * Servlet implementation class assignSubjectsServlet
 */
@WebServlet("/assignSubjectsServlet")
public class assignSubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		// Assign information
		String teacherIDString = request.getParameter("teacherSelect"); // Teacher
		String subjectIDString = request.getParameter("subjectSelect"); // Subject
		int teacherid = Integer.parseInt(teacherIDString);
		int subjectid = Integer.parseInt(subjectIDString);

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		// Assign Subject
		boolean isSubjectAssigned = subjectDBUtilInstance.AssignSubject(subjectid, teacherid);
		
		// Assign subject status
		try {
			if(isSubjectAssigned == true)
			{
				
			}else {
				throw new SubjectOperationException();
			}
			
		}catch(SubjectOperationException e)
		{
			e.logSubjectExceptionToConsole("Admin assign teacher to a subject.");
		}

		ArrayList<subject> AllSubjects = new ArrayList<subject>(); // Subject arraylist
		ArrayList<teacher> AllTeachers = new ArrayList<teacher>(); // Teacher attaylist
		
		// Get relevent data for assign_subjects.jsp
		AllSubjects = subjectDBUtilInstance.getAllSubjects();
		AllTeachers = TeacherFactory.getAllTeachers();

		// Set request attributes 
		request.setAttribute("AllSubjects", AllSubjects);
		request.setAttribute("AllTeachers", AllTeachers);
		request.setAttribute("creation", "success");

		// Send to assign_subjects.jsp
		RequestDispatcher dis = request.getRequestDispatcher("assign_subjects.jsp");
		dis.forward(request, response);

	}
}
