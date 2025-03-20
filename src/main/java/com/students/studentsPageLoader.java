package com.students;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.Student_DB_Util_Factory;

/**
 * Servlet implementation class studentsPageLoader
 */
@WebServlet("/studentsPageLoader")
public class studentsPageLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		// Student list
		ArrayList<student> StudentList = new ArrayList<student>();

		// Classrooms
		ArrayList<classroom> Allclasses = new ArrayList<classroom>();

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		// Get All Students
		StudentList = StudentFactory.getAllStudents();
		Allclasses = ClassRoomFactory.getAllClasses();

		// Send student list
		request.setAttribute("Allstudents", StudentList);
		request.setAttribute("Allclasses", Allclasses);

		// Send to students.jsp
		RequestDispatcher dis = request.getRequestDispatcher("students.jsp");
		dis.forward(request, response);

	}

}
