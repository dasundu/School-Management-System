package com.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.DBconnection.databaseConnection;
import com.factories.Student_DB_Util_Factory;
import com.factories.Teacher_DB_Util_Factory;
import com.students.student;
import com.students.studentsDBUtil;
import com.teachers.teacherDBUtil;

public class classDBUtil {

	// Get Connection to the database
	public static Connection con = databaseConnection.DBconnection();

	// Student DB Factory
	private studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

	// Teacher DB Factory
	private teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

	// Default Constructor
	public classDBUtil(Connection dBconnection) {

	}

	// Get All Classrooms
	public ArrayList<classroom> getAllClasses() {
		ArrayList<classroom> allSubject = new ArrayList<classroom>();
		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from classes");

			// Execute the query
			resultSet = pst.executeQuery();

			// Class temp Object
			classroom temp;

			// Class Info
			int classid;
			String className;
			String roomNum;
			String assignedTeacher;
			String representative;
			int numberOfStudents;

			while (resultSet.next()) {
				classid = resultSet.getInt("classid");
				className = resultSet.getString("classname");
				roomNum = resultSet.getString("roomnum");
				assignedTeacher = TeacherFactory.getTeacherName(resultSet.getInt("teacher"));
				representative = StudentFactory.getStudentName(resultSet.getInt("representative"));
				numberOfStudents = resultSet.getInt("num_of_students");

				// Check if assignedTeacher is empty or null
				if (assignedTeacher == null || assignedTeacher.isEmpty()) {
					assignedTeacher = "Not assigned";
				}

				// Check if representative is empty or null
				if (representative == null || representative.isEmpty()) {
					representative = "Not assigned";
				}

				temp = new classroom(classid, className, roomNum, assignedTeacher, representative, numberOfStudents);

				allSubject.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allSubject;
	}

	// Get students in a class that is not reps
	public ArrayList<student> getNonReps(int classid) {
		ArrayList<student> NonRepStudents = new ArrayList<student>();

		try {

			PreparedStatement pst = con.prepareStatement(
					"select S.id  AS 'id',S.first_name AS 'first',S.last_name 'last'  from students S ,classes C WHERE S.classid = C.classid AND C.classid = ?;");
			pst.setInt(1, classid);

			ResultSet rs = pst.executeQuery();

			// Temp Student
			student temp;

			while (rs.next()) {
				temp = new student();

				temp.setFirstName(rs.getString("first"));
				temp.setId(rs.getInt("id"));
				temp.setLastName(rs.getString("last"));

				NonRepStudents.add(temp);
			}

			// Temp Student object

		} catch (Exception e) {
			e.printStackTrace();
		}

		return NonRepStudents;
	}

	// Get related class students
	public ArrayList<student> getRelatedStudents(int cid) {
		ArrayList<student> RelatedStudents = new ArrayList<student>();

		try {

			PreparedStatement pst = con.prepareStatement("SELECT * FROM students WHERE classid = ?;");
			pst.setInt(1, cid);

			ResultSet rs = pst.executeQuery();

			// Student temp Object
			student temp;

			// Student info;
			int id;
			int classid;
			String birthDay;
			String FirstName;
			String LastName;
			String Telephone;
			String address;

			while (rs.next()) {
				id = rs.getInt("id");
				classid = rs.getInt("classid");
				birthDay = rs.getString("birthday");
				FirstName = rs.getString("first_name");
				LastName = rs.getString("last_name");
				Telephone = rs.getString("telephone");
				address = rs.getString("address");

				temp = new student(id, classid, birthDay, FirstName, LastName, Telephone, address);

				RelatedStudents.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return RelatedStudents;

	}

	// Get teacher assigned classes
	public ArrayList<classroom> getTeacherRelatedClasses(int teacherid) {
		ArrayList<classroom> TeacherRelatedClasses = new ArrayList<classroom>();

		classroom temp;
		int classid;
		String className;
		String roomNum;
		String assignedTeacher;
		String representative;
		int numberOfStudents;

		try {

			PreparedStatement pst = con.prepareStatement("select * from classes where teacher = ?;");
			pst.setInt(1, teacherid);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				classid = rs.getInt("classid");
				className = rs.getString("classname");
				roomNum = rs.getString("roomnum");
				assignedTeacher = TeacherFactory.getTeacherName(rs.getInt("teacher"));
				representative = StudentFactory.getStudentName(rs.getInt("representative"));
				numberOfStudents = rs.getInt("num_of_students");

				// Check if assignedTeacher is empty or null
				if (assignedTeacher == null || assignedTeacher.isEmpty()) {
					assignedTeacher = "Not assigned";
				}

				// Check if representative is empty or null
				if (representative == null || representative.isEmpty()) {
					representative = "Not assigned";
				}

				temp = new classroom(classid, className, roomNum, assignedTeacher, representative, numberOfStudents);

				TeacherRelatedClasses.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TeacherRelatedClasses;
	}

	// Assign a rep
	public boolean AssignRep(int studentid, int classid) {
		boolean isAssign = false;

		try {

			PreparedStatement pst = con.prepareStatement("UPDATE classes SET representative = ? WHERE classid = ?");
			pst.setInt(1, studentid);
			pst.setInt(2, classid);

			if (pst.executeUpdate() > 0) {
				isAssign = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAssign;
	}

	// Assign a teacher
	public boolean AssignTeacher(int teacher, int classid) {
		boolean isAssign = false;

		try {

			PreparedStatement pst = con.prepareStatement("UPDATE classes SET teacher = ? WHERE classid = ?");
			pst.setInt(1, teacher);
			pst.setInt(2, classid);

			if (pst.executeUpdate() > 0) {
				isAssign = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAssign;
	}

	// Get classes without teacher assinged
	public ArrayList<classroom> getUnassignedClasses() {
		ArrayList<classroom> unAssignedClasses = new ArrayList<classroom>();

		try {

			PreparedStatement pst = con.prepareStatement("select * from classes where teacher is null");

			ResultSet rs = pst.executeQuery();

			// Temp classroom and info
			classroom temp;

			int classid;
			String className;
			String roomNum;
			String assignedTeacher;
			String representative;
			int numberOfStudents;

			while (rs.next()) {
				classid = rs.getInt("classid");
				className = rs.getString("classname");
				roomNum = rs.getString("roomnum");
				assignedTeacher = TeacherFactory.getTeacherName(rs.getInt("teacher"));
				representative = StudentFactory.getStudentName(rs.getInt("representative"));
				numberOfStudents = rs.getInt("num_of_students");

				temp = new classroom(classid, className, roomNum, assignedTeacher, representative, numberOfStudents);

				unAssignedClasses.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return unAssignedClasses;

	}

	// Get specific classname
	public String getClassName(int classid) {
		String className = null;

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select classname from classes where classid = ?");
			pst.setInt(1, classid);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				className = rs.getString("classname");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return className;
	}

	// Add Student to a class
	public boolean AddStudentToClass(int classid) {
		boolean isStudentAdded = false;
		int numberOfStudents = 0;

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select num_of_students from classes where classid = ?");
			pst.setInt(1, classid);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				numberOfStudents = rs.getInt("num_of_students");
			}

			numberOfStudents++;

			// Prepare SQL Statement
			PreparedStatement pst2 = con.prepareStatement("UPDATE classes SET num_of_students = ? WHERE classid = ?");
			pst2.setInt(1, numberOfStudents);
			pst2.setInt(2, classid);

			if (pst2.executeUpdate() > 0) {
				isStudentAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isStudentAdded;
	}

	// Add new classroom
	public boolean AddNewClassRoom(String className, String RoomNo) {
		boolean isClassAdded = false;

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("INSERT INTO classes(classname,roomnum) VALUES(?,?)");
			pst.setString(1, className);
			pst.setString(2, RoomNo);

			if (pst.executeUpdate() > 0) {
				isClassAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isClassAdded;
	}

	// Remove a student from a classroom
	public boolean RemoveStudentFromClassRoom(String classid) {
		boolean isRemoved = false;
		int numberOfStudents = 0;

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select num_of_students from classes where classid = ?");
			pst.setString(1, classid);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				numberOfStudents = rs.getInt("num_of_students");

			}

			numberOfStudents = numberOfStudents - 1;

			// Prepare SQL Statement
			PreparedStatement pst2 = con.prepareStatement("UPDATE classes SET num_of_students = ? WHERE classid = ?");
			pst2.setInt(1, numberOfStudents);
			pst2.setString(2, classid);

			if (pst2.executeUpdate() > 0) {
				isRemoved = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isRemoved;
	}

	// Update class
	public boolean UpdateClass(String cid, String name, String rnumber) {
		boolean isClassUpdated = false;

		try {

			// Prepare SQL Statement
			PreparedStatement pst2 = con
					.prepareStatement("UPDATE classes SET classname = ? , roomnum = ? WHERE classid = ?");
			pst2.setString(1, name);
			pst2.setString(2, rnumber);
			pst2.setString(3, cid);
			;

			if (pst2.executeUpdate() > 0) {
				isClassUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isClassUpdated;
	}

	// Delete a class
	public boolean DeleteClass(String cid) {
		boolean isClassDeleted = false;

		try {

			// Prepare SQL Statement
			PreparedStatement pst2 = con.prepareStatement("DELETE FROM  classes WHERE classid = ?");
			pst2.setString(1, cid);
			;

			if (pst2.executeUpdate() > 0) {
				isClassDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isClassDeleted;
	}

	// GET number of classes
	public int numberOfClasses() {
		int number_of_classes = 0;

		try {

			// Prepare SQL Statement
			PreparedStatement pst2 = con.prepareStatement("select count(classid) as 'numberOfClasses' from classes;");
			ResultSet rs = pst2.executeQuery();

			if (rs.next()) {
				number_of_classes = rs.getInt("numberOfClasses");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return number_of_classes;
	}

	// GET number of classes
	public int numberOfClassesforTeacher(int teacherid) {
		int number_of_classes = 0;

		try {

			// Prepare SQL Statement
			PreparedStatement pst2 = con
					.prepareStatement("select count(classid) as 'numberOfClasses' from classes where teacher = ?;");
			pst2.setInt(1, teacherid);
			ResultSet rs = pst2.executeQuery();

			if (rs.next()) {
				number_of_classes = rs.getInt("numberOfClasses");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return number_of_classes;
	}

}
