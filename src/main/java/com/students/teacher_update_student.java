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
import com.factories.Student_DB_Util_Factory;
import com.student_Exception.StudentOperationException;

/**
 * Servlet implementation class teacher_update_student
 */
@WebServlet("/teacher_update_student")
public class teacher_update_student extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Capture info
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String BirthDate = request.getParameter("bdate");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		String userIdString = request.getParameter("userid");

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Get student id
		int userid = Integer.parseInt(userIdString);

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		// Update student
		boolean isStudentUpdated = StudentFactory.UpdateStudentByTeacher(BirthDate, userid, firstName, lastName,
				telephone, address);

		// Updates staus check
		try {
			if (isStudentUpdated == true) {
				// Get the session
				HttpSession session = request.getSession();

				// Retrieve the loginid attribute from the session
				int TeacherID = (Integer) session.getAttribute("loginId");

				// Get all assigned classes
				ArrayList<classroom> AssignedClasses = ClassRoomFactory.getTeacherRelatedClasses(TeacherID);

				// Set successful message
				request.setAttribute("creation", "success");
				request.setAttribute("AssignedClasses", AssignedClasses);

				// Send to teacher_manage_students.jsp
				RequestDispatcher dis = request.getRequestDispatcher("teacher_manage_students.jsp");
				dis.forward(request, response);

			} else {
				throw new StudentOperationException();
			}
		} catch (StudentOperationException e) {
			e.logStudentExceptionToConsole("Teacher updates student");
		}
	}
}
