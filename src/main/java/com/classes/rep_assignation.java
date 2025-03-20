package com.classes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classes_Exceptions.ClassRoomOperationException;
import com.factories.Classroom_DB_Util_Factory;

/**
 * Servlet implementation class rep_assignation
 */
@WebServlet("/rep_assignation")
public class rep_assignation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentString = request.getParameter("studentId");
		String classString = request.getParameter("selectedClass");

		int studentid = Integer.parseInt(studentString);
		int classid = Integer.parseInt(classString);

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Assign Rep
		boolean isAssignationSuccess = ClassRoomFactory.AssignRep(studentid, classid);

		// Assign status
		try {

			if (isAssignationSuccess == true) {

				request.setAttribute("creation", "success");

			} else {
				throw new ClassRoomOperationException();
			}
		} catch (ClassRoomOperationException e) {
			e.logExceptionToConsole("Class rep assign");
		}

		//Load assign_reps.jsp data
		ArrayList<classroom> AllClasses = new ArrayList<classroom>();
		AllClasses = ClassRoomFactory.getAllClasses();
		request.setAttribute("Allclasses", AllClasses);
		RequestDispatcher dis = request.getRequestDispatcher("assign_reps.jsp");
		dis.forward(request, response);
	}

}
