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
 * Servlet implementation class admin_update_class
 */
@WebServlet("/admin_update_class")
public class admin_update_class extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String classid = request.getParameter("ClassId");
		String classname = request.getParameter("classname");
		String classNumber = request.getParameter("roomnumber");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Update classroom
		boolean isClassUpdated = ClassRoomFactory.UpdateClass(classid, classname, classNumber);

		// Check update status
		try {
			if (isClassUpdated == true) {
				request.setAttribute("creation", "success");
			} else {
				throw new ClassRoomOperationException();
			}

		} catch (ClassRoomOperationException e) {
			e.logExceptionToConsole("Admin updates classroom.");
		}

		
		//Load classroom manage panel data
		ArrayList<classroom> AllClasses = new ArrayList<classroom>();
		AllClasses = ClassRoomFactory.getAllClasses();
		request.setAttribute("Classes", AllClasses);
		RequestDispatcher dis = request.getRequestDispatcher("admin_classes_panel.jsp");
		dis.forward(request, response);
	}

}
