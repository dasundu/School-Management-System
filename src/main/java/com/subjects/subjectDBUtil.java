package com.subjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.DBconnection.databaseConnection;

public class subjectDBUtil {

	// Get Connection to the database
	public static Connection con = databaseConnection.DBconnection();

	// Default Constructor
	public subjectDBUtil(Connection dBconnection) {
	}

	// Get All Subjects
	public ArrayList<subject> getAllSubjects() {
		ArrayList<subject> allSubject = new ArrayList<subject>();
		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from subjects");

			// Execute the query
			resultSet = pst.executeQuery();

			// Subject temp Object
			subject temp;

			// Subject info;
			int SubjectId;
			String SubName;
			String Description;
			int NumOfHours;
			String EnKey;

			while (resultSet.next()) {

				SubjectId = resultSet.getInt("subjectid");
				SubName = resultSet.getString("subjectname");
				;
				Description = resultSet.getString("description");
				NumOfHours = resultSet.getInt("no_of_hours");
				EnKey = resultSet.getString("enrollment_key");

				temp = new subject(SubjectId, SubName, Description, NumOfHours, EnKey);

				allSubject.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allSubject;
	}

	// Get a specific subject
	public subject getSubject(int subjectid) {

		subject temp = null;

		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from subjects where subjectid = ?");
			pst.setInt(1, subjectid);

			// Execute the query
			resultSet = pst.executeQuery();

			// Subject info;
			int SubjectId;
			String SubName;
			String Description;
			int NumOfHours;
			String EnKey;

			SubjectId = resultSet.getInt("subjectid");
			SubName = resultSet.getString("subjectname");
			Description = resultSet.getString("description");
			NumOfHours = resultSet.getInt("no_of_hours");
			EnKey = resultSet.getString("enrollment_key");

			temp = new subject(SubjectId, SubName, Description, NumOfHours, EnKey);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	// Add new subject
	public boolean AddNewSubject(String subName, String subDesc, int noOfHours, String enKey) {
		boolean isSubjectAdded = false;

		try {
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO subjects (subjectname ,description , no_of_hours , enrollment_key) VALUES(?,?,?,?)");
			pst.setString(1, subName);
			pst.setString(2, subDesc);
			pst.setInt(3, noOfHours);
			pst.setString(4, enKey);

			if (pst.executeUpdate() > 0) {
				isSubjectAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSubjectAdded;
	}

	// Assign teacher to a subject
	public boolean AssignSubject(int subid, int teacherid) {
		boolean isAssigned = false;

		try {
			PreparedStatement pst = con
					.prepareStatement("INSERT INTO assignedsubjects (subjectid ,teacherid) VALUES(?,?)");
			pst.setInt(1, subid);
			pst.setInt(2, teacherid);

			if (pst.executeUpdate() > 0) {
				isAssigned = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAssigned;
	}

	// Get assigned subjects for a teacher
	public ArrayList<subject> getTeachersSubjects(int teacherid) {
		ArrayList<subject> TeachersSubject = new ArrayList<subject>();
		ResultSet resultSet = null;

		try {

			PreparedStatement pst = con.prepareStatement(
					"select S.subjectid,S.subjectname,S.description,S.no_of_hours,S.enrollment_key from subjects S ,assignedsubjects SA , teachers T Where S.subjectid = SA.subjectid AND T.id = SA.teacherid AND T.id = ?;");
			pst.setInt(1, teacherid);

			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				subject temp = null;

				// Subject info;
				int SubjectId;
				String SubName;
				String Description;
				int NumOfHours;
				String EnKey;

				SubjectId = resultSet.getInt("subjectid");
				SubName = resultSet.getString("subjectname");
				;
				Description = resultSet.getString("description");
				NumOfHours = resultSet.getInt("no_of_hours");
				EnKey = resultSet.getString("enrollment_key");

				temp = new subject(SubjectId, SubName, Description, NumOfHours, EnKey);

				TeachersSubject.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TeachersSubject;
	}

	// Get unassigned subjects for a teacher
	public ArrayList<subject> getTeachersUnassignedSubjects(int teacherid) {
		ArrayList<subject> TeachersSubject = new ArrayList<subject>();
		ResultSet resultSet = null;

		try {

			PreparedStatement pst = con.prepareStatement(
					"SELECT S.subjectid, S.subjectname, S.description, S.no_of_hours, S.enrollment_key FROM subjects S LEFT JOIN assignedsubjects SA ON S.subjectid = SA.subjectid AND SA.teacherid = ?  WHERE SA.subjectid IS NULL;");
			pst.setInt(1, teacherid);

			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				subject temp = null;

				// Subject info;
				int SubjectId;
				String SubName;
				String Description;
				int NumOfHours;
				String EnKey;

				SubjectId = resultSet.getInt("subjectid");
				SubName = resultSet.getString("subjectname");
				;
				Description = resultSet.getString("description");
				NumOfHours = resultSet.getInt("no_of_hours");
				EnKey = resultSet.getString("enrollment_key");

				temp = new subject(SubjectId, SubName, Description, NumOfHours, EnKey);

				TeachersSubject.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TeachersSubject;
	}

	// Teacher enroll for a subject
	public boolean TeacherEnrollSubject(int teacherid, int subjectid, String EnrollmentKey) {
		boolean isEnrolled = false;

		boolean isKeyValid = false;

		try {

			PreparedStatement pst = con
					.prepareStatement("SELECT * from subjects where subjectid = ? AND enrollment_key = ?");
			pst.setInt(1, subjectid);
			pst.setString(2, EnrollmentKey);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				isKeyValid = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isKeyValid == true) {

			try {
				PreparedStatement pst2 = con
						.prepareStatement("INSERT INTO assignedsubjects (subjectid ,teacherid) VALUES(?,?)");
				pst2.setInt(1, subjectid);
				pst2.setInt(2, teacherid);

				if (pst2.executeUpdate() > 0) {
					isEnrolled = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return isEnrolled;

	}

	// Get assigned subjects for a student
	public ArrayList<subject> getStudentsSubjects(int studentid) {
		ArrayList<subject> StudentSubject = new ArrayList<subject>();
		ResultSet resultSet = null;

		try {

			PreparedStatement pst = con.prepareStatement(
					"select S.subjectid,S.subjectname,S.description,S.no_of_hours,S.enrollment_key from subjects S ,studentsubjects SA , students T Where S.subjectid = SA.subjectid AND T.id = SA.studentid AND T.id = ?;");
			pst.setInt(1, studentid);

			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				subject temp = null;

				// Subject info;
				int SubjectId;
				String SubName;
				String Description;
				int NumOfHours;
				String EnKey;

				SubjectId = resultSet.getInt("subjectid");
				SubName = resultSet.getString("subjectname");
				;
				Description = resultSet.getString("description");
				NumOfHours = resultSet.getInt("no_of_hours");
				EnKey = resultSet.getString("enrollment_key");

				temp = new subject(SubjectId, SubName, Description, NumOfHours, EnKey);

				StudentSubject.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return StudentSubject;
	}

	// Get unassigned subjects for a student
	public ArrayList<subject> getStudentUnassignedSubjects(int studentid) {
		ArrayList<subject> TeachersSubject = new ArrayList<subject>();
		ResultSet resultSet = null;

		try {

			PreparedStatement pst = con.prepareStatement(
					"SELECT S.subjectid, S.subjectname, S.description, S.no_of_hours, S.enrollment_key FROM subjects S LEFT JOIN studentsubjects SA ON S.subjectid = SA.subjectid AND SA.studentid = ?  WHERE SA.subjectid IS NULL;");
			pst.setInt(1, studentid);

			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				subject temp = null;

				// Subject info;
				int SubjectId;
				String SubName;
				String Description;
				int NumOfHours;
				String EnKey;

				SubjectId = resultSet.getInt("subjectid");
				SubName = resultSet.getString("subjectname");
				;
				Description = resultSet.getString("description");
				NumOfHours = resultSet.getInt("no_of_hours");
				EnKey = resultSet.getString("enrollment_key");

				temp = new subject(SubjectId, SubName, Description, NumOfHours, EnKey);

				TeachersSubject.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TeachersSubject;
	}

	// Student enroll for a subject
	public boolean StudentEnrollSubject(int studentid, int subjectid, String EnrollmentKey) {
		boolean isEnrolled = false;

		boolean isKeyValid = false;

		try {

			PreparedStatement pst = con
					.prepareStatement("SELECT * from subjects where subjectid = ? AND enrollment_key = ?");
			pst.setInt(1, subjectid);
			pst.setString(2, EnrollmentKey);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				isKeyValid = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isKeyValid == true) {

			try {
				PreparedStatement pst2 = con
						.prepareStatement("INSERT INTO studentsubjects (studentid ,subjectid) VALUES(?,?)");
				pst2.setInt(1, studentid);
				pst2.setInt(2, subjectid);

				if (pst2.executeUpdate() > 0) {
					isEnrolled = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return isEnrolled;

	}

	// Get a specific subject name
	public String getSubjectName(int subjectid) {

		String subname = null;

		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select subjectname from subjects where subjectid = ?");
			pst.setInt(1, subjectid);

			// Execute the query
			resultSet = pst.executeQuery();

			if (resultSet.next()) {
				subname = resultSet.getString("subjectname");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subname;
	}

	// Get Number of Subjects for a teacher or student
	public int getNumberOfEnrolledSubjects(int userid) {

		int noOfSubjects = 0;

		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement(
					"Select COUNT(subjectid) as 'subjectCount' from studentsubjects WHERE studentid = ?");
			pst.setInt(1, userid);

			// Execute the query
			resultSet = pst.executeQuery();

			if (resultSet.next()) {
				noOfSubjects = resultSet.getInt("subjectCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfSubjects;
	}

	// Update subject
	public boolean updateSubject(String subjectid, String subjectName, String subjectDescription, String enrollmentKey,
			String noOfHours) {
		boolean isSubjectUpdated = false;

		try {
			PreparedStatement pst2 = con.prepareStatement(
					"UPDATE subjects SET subjectname = ? , description = ? ,no_of_hours = ? , enrollment_key = ? WHERE subjectid = ?");
			pst2.setString(1, subjectName);
			pst2.setString(2, subjectDescription);
			pst2.setString(3, noOfHours);
			pst2.setString(4, enrollmentKey);
			pst2.setString(5, subjectid);

			if (pst2.executeUpdate() > 0) {
				isSubjectUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSubjectUpdated;
	}

	// Delete subject
	public boolean DeleteSubject(String subjectid) {
		boolean isSubjectDeleted = false;

		try {
			PreparedStatement pst2 = con.prepareStatement("DELETE FROM subjects WHERE subjectid = ?");
			pst2.setString(1, subjectid);

			if (pst2.executeUpdate() > 0) {
				isSubjectDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSubjectDeleted;
	}

	// Get Number of Subjects
	public int getNumberOfSubjects() {

		int noOfSubjects = 0;

		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("Select COUNT(subjectid) as 'subjectCount' from subjects;");

			// Execute the query
			resultSet = pst.executeQuery();

			if (resultSet.next()) {
				noOfSubjects = resultSet.getInt("subjectCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfSubjects;
	}

	// Get Number of Subjects for teacher
	public int getNumberOfSubjectsTeacher(int teacherid) {

		int noOfSubjects = 0;

		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement(
					"Select COUNT(subjectid) as 'subjectCount' from assignedsubjects where teacherid = ? ;");
			pst.setInt(1, teacherid);

			// Execute the query
			resultSet = pst.executeQuery();

			if (resultSet.next()) {
				noOfSubjects = resultSet.getInt("subjectCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfSubjects;
	}

}
