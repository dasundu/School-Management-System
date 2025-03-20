package com.students;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.User_DB_Util_Factory;
import com.student_Exception.StudentOperationException;
import com.user.usersDBUtil;
import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class teacher_delete_student
 */
@WebServlet("/teacher_delete_student")
public class teacher_delete_student extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get student info
		String useridString = request.getParameter("userid");
		String studentClass = request.getParameter("studentClass");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Remove student
		boolean isStudentRemoved = ClassRoomFactory.RemoveStudentFromClassRoom(studentClass);

		// Remove login
		boolean isLoginDeleted = User_DB_Factory.deleteUser(useridString);

		// Check student remove status
		try {
			if (isLoginDeleted == true) {
				if (isStudentRemoved == true) {
					request.setAttribute("deletion", "success");
				} else {
					throw new StudentOperationException();
				}
			} else {
				throw new User_Operation_Exception();
			}
		} catch (StudentOperationException e) {
			e.logStudentExceptionToConsole("Teacher remove student.");
		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("Student login deletion failed");
		}

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginid attribute from the session
		int TeacherID = (Integer) session.getAttribute("loginId");

		ArrayList<classroom> AssignedClasses = ClassRoomFactory.getTeacherRelatedClasses(TeacherID);

		request.setAttribute("AssignedClasses", AssignedClasses);

		RequestDispatcher dis = request.getRequestDispatcher("teacher_manage_students.jsp");

		dis.forward(request, response);

	}

}
