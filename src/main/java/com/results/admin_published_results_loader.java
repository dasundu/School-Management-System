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
 * Servlet implementation class admin_published_results_loader
 */
@WebServlet("/admin_published_results_loader")
public class admin_published_results_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Use the factory to create an instance of resultDBUtil
		resultDBUtil resultDBUtilInstance = ResultDBUtilFactory.createDefaultResultDBUtil();

		// Get all the results
		ArrayList<result> AllResults = resultDBUtilInstance.getAllResults();

		// Set all the results as request attribute
		request.setAttribute("AllResults", AllResults);

		// Send to admin_manage_published_results.jsp
		RequestDispatcher dis = request.getRequestDispatcher("admin_manage_published_results.jsp");
		dis.forward(request, response);

	}

}
