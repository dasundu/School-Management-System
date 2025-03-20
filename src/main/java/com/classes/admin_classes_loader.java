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

/**
 * Servlet implementation class admin_classes_loader
 */
@WebServlet("/admin_classes_loader")
public class admin_classes_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<classroom> AllClasses = new ArrayList<classroom>();

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		AllClasses = ClassRoomFactory.getAllClasses();

		request.setAttribute("Classes", AllClasses);

		RequestDispatcher dis = request.getRequestDispatcher("admin_classes_panel.jsp");
		dis.forward(request, response);

	}

}
