package com.user;

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
import com.factories.SubjectDBUtilFactory;
import com.factories.Teacher_DB_Util_Factory;
import com.factories.User_DB_Util_Factory;
import com.students.student;
import com.students.studentsDBUtil;
import com.subjects.subjectDBUtil;
import com.teachers.teacher;
import com.teachers.teacherDBUtil;
import com.user_Exceptions.InvalidRoleException;

/**
 * Servlet implementation class DashboardLoader
 */
@WebServlet("/DashboardLoader")
public class DashboardLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Retrieve the loginrole attribute from the session
		String loginRole = (String) session.getAttribute("loginrole");

		// Retrieve the loginid attribute from the session
		int loginId = (Integer) session.getAttribute("loginId");

		// Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();

		// Classrooms DB Util Factory
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

		// Student DB Factory
		studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

		// Teacher DB Factory
		teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

		// Users DB Factory
		usersDBUtil User_DB_Factory = User_DB_Util_Factory.create_User_DB_Factory();

		// Check the loginRole and take different actions

		try {
			if ("admin".equals(loginRole)) {

				int numberofusers = User_DB_Factory.numberOFusers();
				int number_of_students = User_DB_Factory.numberOFstudents();
				int number_of_teachers = User_DB_Factory.numberOFteachers();
				int number_of_subjects = subjectDBUtilInstance.getNumberOfSubjects();
				int number_of_classes = ClassRoomFactory.numberOfClasses();

				ArrayList<classroom> AllClasses = new ArrayList<classroom>();

				AllClasses = ClassRoomFactory.getAllClasses();

				// Sending Attributes
				request.setAttribute("numberofusers", numberofusers);
				request.setAttribute("number_of_students", number_of_students);
				request.setAttribute("number_of_teachers", number_of_teachers);
				request.setAttribute("number_of_subjects", number_of_subjects);
				request.setAttribute("number_of_classes", number_of_classes);
				request.setAttribute("Classes", AllClasses);

				// Dispatcher
				RequestDispatcher dis = request.getRequestDispatcher("adminDashBoard.jsp");
				dis.forward(request, response);

			} else if ("teacher".equals(loginRole)) {

				teacher teacherInfo = TeacherFactory.getSpecificTeacher(loginId);
				int numberofClasses = ClassRoomFactory.numberOfClassesforTeacher(loginId);
				int NoOfSubjects = subjectDBUtilInstance.getNumberOfSubjectsTeacher(loginId);
				request.setAttribute("TeacherInfo", teacherInfo);
				request.setAttribute("numberofClasses", numberofClasses);
				request.setAttribute("NoOfSubjects", NoOfSubjects);

				RequestDispatcher dis2 = request.getRequestDispatcher("teacherDashBoard.jsp");
				dis2.forward(request, response);

			} else if ("student".equals(loginRole)) {

				// Retrieve the loginid attribute from the session
				int studentId = (Integer) session.getAttribute("loginId");

				student LoggedStudentInfo = StudentFactory.getSpecificStudent(studentId);
				int StudentClassID = StudentFactory.getStudentClass(studentId);
				String ClassName = ClassRoomFactory.getClassName(StudentClassID);
				int NoOfSubjects = subjectDBUtilInstance.getNumberOfEnrolledSubjects(studentId);

				// Sending Attributes
				request.setAttribute("StudentInfo", LoggedStudentInfo);
				request.setAttribute("ClassName", ClassName);
				request.setAttribute("NoOfSubjects", NoOfSubjects);

				RequestDispatcher dis3 = request.getRequestDispatcher("studentDashBoard.jsp");
				dis3.forward(request, response);

			} else {
				throw new InvalidRoleException();
			}

		} catch (InvalidRoleException e) {
			response.sendRedirect("login.jsp");
			e.logExceptionToConsole(loginRole);
		}
	}
}
