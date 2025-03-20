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
import com.teachers.teacher;
import com.teachers.teacherDBUtil;

/**
 * Servlet implementation class subject_assign_loader
 */
@WebServlet("/subject_assign_loader")
public class subject_assign_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<subject> AllSubjects = new ArrayList<subject>();
		ArrayList<teacher> AllTeachers = new ArrayList<teacher>();

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		AllSubjects = subjectDBUtilInstance.getAllSubjects();
		AllTeachers = TeacherFactory.getAllTeachers();

		request.setAttribute("AllSubjects", AllSubjects);
		request.setAttribute("AllTeachers", AllTeachers);

		RequestDispatcher dis = request.getRequestDispatcher("assign_subjects.jsp");

		dis.forward(request, response);

	}
}
