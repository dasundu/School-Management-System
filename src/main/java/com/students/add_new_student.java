package com.students;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.classes.classDBUtil;
import com.classes.classroom;
import com.classes_Exceptions.ClassRoomOperationException;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.Student_DB_Util_Factory;
import com.factories.User_DB_Util_Factory;
import com.student_Exception.StudentOperationException;
import com.user.usersDBUtil;
import com.user_Exceptions.User_Operation_Exception;

/**
 * Servlet implementation class add_new_student
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/add_new_student")
public class add_new_student extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get student and user account information
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = "student";
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String birthday = request.getParameter("birthday");
		String tele = request.getParameter("telephone");
		String address = request.getParameter("address");
		String classIdString = request.getParameter("classSelect");
		int ClassId = Integer.parseInt(classIdString);
		// Get Image
		Part newProPic = request.getPart("pic");
		InputStream imageSource = newProPic.getInputStream();

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Add student as an user first
		boolean isSignupSuccess = User_DB_Factory.signup(username, email, password, role, imageSource);

		// Get newly added userid
		int newlyAddedUserId = User_DB_Factory.GetLatestUserId();

		// Add new Student
		boolean isStudentAddedSuccessfully = StudentFactory.addNewStudent(newlyAddedUserId, birthday, firstName,
				lastName, tele, address, ClassId);

		try {
			if (isSignupSuccess == true) {
				if (isStudentAddedSuccessfully == true) {
					boolean isStudentAddedToTheClass = ClassRoomFactory.AddStudentToClass(ClassId);

					if (isStudentAddedToTheClass == true) {
						ArrayList<classroom> AllClasses = new ArrayList<classroom>();

						AllClasses = ClassRoomFactory.getAllClasses();

						request.setAttribute("Allclasses", AllClasses);
						request.setAttribute("creation", "success");

						RequestDispatcher dis = request.getRequestDispatcher("add_new_student.jsp");

						dis.forward(request, response);

					} else {
						throw new ClassRoomOperationException();
					}
				} else {
					throw new StudentOperationException();
				}
			} else {
				throw new User_Operation_Exception();
			}
		} catch (User_Operation_Exception e) {
			e.logExceptionToConsole("New user creation failed.");
		} catch (StudentOperationException e) {
			e.logStudentExceptionToConsole("Admin add new student.");
		} catch (ClassRoomOperationException e) {
			e.logExceptionToConsole("Add new student to the classroom.");
		}
	}
}
