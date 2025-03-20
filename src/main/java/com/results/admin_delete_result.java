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
import com.result_Exceptions.ResultOperationException;

/**
 * Servlet implementation class admin_delete_result
 */
@WebServlet("/admin_delete_result")
public class admin_delete_result extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Catch result id that going to be deleted
		String resultId = request.getParameter("resultId");

		// Use the factory to create an instance of resultDBUtil
		resultDBUtil resultDBUtilInstance = ResultDBUtilFactory.createDefaultResultDBUtil();

		// Delets results
		boolean isResultDeleted = resultDBUtilInstance.DeleteResult(resultId);

		// Check deletion status
		try {
			if (isResultDeleted == true) {
				request.setAttribute("deletion", "success");
			} else {
				throw new ResultOperationException();
			}
		} catch (ResultOperationException e) {
			e.logResultException("Admin deletes result.");
		}
		
		//Load admin_manage_published_results.jsp related data
		ArrayList<result> AllResults = resultDBUtilInstance.getAllResults();
		request.setAttribute("AllResults", AllResults);
		RequestDispatcher dis = request.getRequestDispatcher("admin_manage_published_results.jsp");
		dis.forward(request, response);
	}

}
