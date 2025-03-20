package com.results;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factories.ResultDBUtilFactory;

/**
 * Servlet implementation class student_results_info
 */
@WebServlet("/student_results_info")
public class student_results_info extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{
		// Use the factory to create an instance of resultDBUtil
		resultDBUtil resultDBUtilInstance = ResultDBUtilFactory.createDefaultResultDBUtil();

		// Get all results
		ArrayList<result> AllResults = resultDBUtilInstance.getAllResults();

		// Set request attribute for all results
		request.setAttribute("AllResults", AllResults);

		// Send to student_results_panel.jsp
		RequestDispatcher dis = request.getRequestDispatcher("student_results_panel.jsp");
		dis.forward(request, response);

	}

}
