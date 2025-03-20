package com.assignments;

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
import com.factories.SubjectDBUtilFactory;
import com.subjects.subject;
import com.subjects.subjectDBUtil;

/**
 * Servlet implementation class add_new_assignment_loader
 */
@WebServlet("/add_new_assignment_loader")
public class add_new_assignment_loader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
        // Get the session
        HttpSession session = request.getSession();
        
        // Retrieve the loginid attribute from the session
        int teahcerid = (Integer) session.getAttribute("loginId");
        
		//Create instance of subjectDButil using a factory
		subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();
        
        //Get assigned subjects
        ArrayList<subject>TeacherSubjects = subjectDBUtilInstance .getTeachersSubjects(teahcerid);
        
        
    	//Classrooms DB Util Factory 
		classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();
		
        //Get classes
        ArrayList<classroom>AllClasses = ClassRoomFactory.getAllClasses();
        
        
        request.setAttribute("Subjects", TeacherSubjects);
        request.setAttribute("AllClasses", AllClasses);
        
        RequestDispatcher dis = request.getRequestDispatcher("add_new_assignment.jsp");
        
        dis.forward(request, response);
	
	}


}
