package com.classes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.Classroom_DB_Util_Factory;
import com.factories.Teacher_DB_Util_Factory;
import com.teachers.teacher;
import com.teachers.teacherDBUtil;

/**
 * Servlet implementation class assign_class_teacher_loader
 */
@WebServlet("/assign_class_teacher_loader")
public class assign_class_teacher_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		// Get classes
		ArrayList<classroom> AllClasses = new ArrayList<classroom>();
		AllClasses = ClassRoomFactory.getUnassignedClasses();
		request.setAttribute("UnAssignedclasses", AllClasses);

		// Get all teachers
		ArrayList<teacher> teachersList = new ArrayList<teacher>();
		teachersList = TeacherFactory.getAllTeachers();
		request.setAttribute("Allteachers", teachersList);

		RequestDispatcher dis = request.getRequestDispatcher("assign_class_teacher.jsp");

		dis.forward(request, response);

	}

}
