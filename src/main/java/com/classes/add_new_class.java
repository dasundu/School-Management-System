package com.classes;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classes_Exceptions.ClassRoomOperationException;
import com.factories.Classroom_DB_Util_Factory;

/**
 * Servlet implementation class add_new_class
 */
@WebServlet("/add_new_class")
public class add_new_class extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String className = request.getParameter("classname");
		String roomNumer = request.getParameter("roomnumber");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Add new class
		boolean isClassAdded = ClassRoomFactory.AddNewClassRoom(className, roomNumer);

		// Check status
		try {
			if (isClassAdded == true) {

				request.setAttribute("creation", "success");
				RequestDispatcher dis = request.getRequestDispatcher("add_new_class.jsp");
				dis.forward(request, response);

			} else {
				throw new ClassRoomOperationException();
			}
		} catch (ClassRoomOperationException e) {
			e.logExceptionToConsole("Admin adds new classroom");
		}
	}

}
