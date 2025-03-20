package com.results;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.factories.ResultDBUtilFactory;

/**
 * Servlet implementation class teacher_result_panel_loader
 */
@WebServlet("/teacher_result_panel_loader")
public class teacher_result_panel_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		// Use the factory to create an instance of resultDBUtil
		resultDBUtil resultDBUtilInstance = ResultDBUtilFactory.createDefaultResultDBUtil();

		// Get related teacher's results
		ArrayList<result> AllResults = resultDBUtilInstance.getAllResultsForTeacher(TeacherID);

		// Set request attribute for teacher's results
		request.setAttribute("AllResults", AllResults);

		// Send to teacher_results_panel.jsp
		RequestDispatcher dis = request.getRequestDispatcher("teacher_results_panel.jsp");
		dis.forward(request, response);
	}
}
