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
import com.classes_Exceptions.ClassRoomOperationException;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.Student_DB_Util_Factory;
import com.student_Exception.StudentOperationException;

@WebServlet("/admin_update_student")
public class admin_update_student extends HttpServlet {
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
		int userid = Integer.parseInt(userIdString);
		String oldClassString = request.getParameter("oldClass");
		String newClassString = request.getParameter("selectedNewClass");
		int newClass = Integer.parseInt(newClassString);

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		//Update student
		boolean isStudentUpdated = StudentFactory.UpdateStudent(BirthDate, userid, firstName, lastName, telephone,
				address, newClass);
		
		//Check for classroom updates
		boolean isStudentRemoved = ClassRoomFactory.RemoveStudentFromClassRoom(oldClassString);
		boolean isStudentAddedToNewClass = ClassRoomFactory.AddStudentToClass(newClass);

		//Check for update status
		try {
			if (isStudentUpdated == true) {

				if (isStudentAddedToNewClass == true) {
					if (isStudentRemoved == true) {
						// Student list
						ArrayList<student> StudentList = new ArrayList<student>();

						// Classrooms
						ArrayList<classroom> Allclasses = new ArrayList<classroom>();

						// Get All Students
						StudentList = StudentFactory.getAllStudents();
						Allclasses = ClassRoomFactory.getAllClasses();

						// Send student list
						request.setAttribute("creation", "success");
						request.setAttribute("Allstudents", StudentList);
						request.setAttribute("Allclasses", Allclasses);

						RequestDispatcher dis = request.getRequestDispatcher("students.jsp");
						dis.forward(request, response);
					} else {
						throw new StudentOperationException();
					}
				} else {
					throw new ClassRoomOperationException();
				}
			} else {
				throw new StudentOperationException();
			}
		} catch (StudentOperationException e) {
			e.logStudentExceptionToConsole("Admin updates student");
		} catch (ClassRoomOperationException e) {
			e.logExceptionToConsole("Add new student to classroom");
		}
	}
}
