package com.students;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classes.classDBUtil;
import com.classes.classroom;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.Student_DB_Util_Factory;
import com.factories.User_DB_Util_Factory;
import com.student_Exception.StudentOperationException;
import com.user.usersDBUtil;
import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class admin_delete_student
 */
@WebServlet("/admin_delete_student")
public class admin_delete_student extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get student info
		String useridString = request.getParameter("userid");
		String studentClass = request.getParameter("studentClass");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Remove student
		boolean isStudentRemoved = ClassRoomFactory.RemoveStudentFromClassRoom(studentClass);

		// Remove related login
		boolean isLoginDeleted = User_DB_Factory.deleteUser(useridString);

		// Check remove status
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
		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("User deletion failed.");
		} catch (StudentOperationException e) {
			e.logStudentExceptionToConsole("Admin remove student");
		}

		// Student list
		ArrayList<student> StudentList = new ArrayList<student>();

		// Classrooms
		ArrayList<classroom> Allclasses = new ArrayList<classroom>();

		// Get All Students
		StudentList = StudentFactory.getAllStudents();
		Allclasses = ClassRoomFactory.getAllClasses();

		// Send student list
		request.setAttribute("Allstudents", StudentList);
		request.setAttribute("Allclasses", Allclasses);

		// Send to students.jsp
		RequestDispatcher dis = request.getRequestDispatcher("students.jsp");
		dis.forward(request, response);

	}

}
