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
import com.result_Exceptions.ResultOperationException;

/**
 * Servlet implementation class teacher_result_delete
 */
@WebServlet("/teacher_result_delete")
public class teacher_result_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String resultId = request.getParameter("resid");

		// Use the factory to create an instance of resultDBUtil
		resultDBUtil resultDBUtilInstance = ResultDBUtilFactory.createDefaultResultDBUtil();

		// Delete result
		boolean isResultDeleted = resultDBUtilInstance.DeleteResult(resultId);

		// Check deletion status
		try {
			if (isResultDeleted == true) {

				request.setAttribute("deletion", "success");
			} else {
				throw new ResultOperationException();
			}
		} catch (ResultOperationException e) {
			e.logResultException("Teacher deletes result.");
		}

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		ArrayList<result> AllResults = resultDBUtilInstance.getAllResultsForTeacher(TeacherID);

		request.setAttribute("AllResults", AllResults);

		RequestDispatcher dis = request.getRequestDispatcher("teacher_results_panel.jsp");
		dis.forward(request, response);

	}

}
