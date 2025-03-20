package com.assignments;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.DBconnection.databaseConnection;
import com.commonFunctions.ImageConvertor;
import com.factories.Student_DB_Util_Factory;
import com.factories.Teacher_DB_Util_Factory;
import com.mysql.cj.jdbc.Blob;
import com.students.studentsDBUtil;
import com.teachers.teacherDBUtil;

public class assignmentDBUtil {

	// Get Connection to the database
	public static Connection con = databaseConnection.DBconnection();

	// Default Contructor
	public assignmentDBUtil(Connection dBconnection) {

	}

	// Student DB Factory
	private studentsDBUtil StudentFactory = Student_DB_Util_Factory.create_DB_Util_For_Student();

	// Teacher DB Factory
	private teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

	// Add new assignment by teacher
	public boolean AddNewAssignment(String title, int subjectId, int classid, InputStream pdfFile, int uploaderid) {
		boolean isAssignmentAdded = false;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement(
					"insert into assignments(assignment_title,subject_id,class_id,assignment_info,uploader) values(?,?,?,?,?)");
			pst.setString(1, title);
			pst.setInt(2, subjectId);
			pst.setInt(3, classid);
			pst.setBlob(4, pdfFile);
			pst.setInt(5, uploaderid);

			if (pst.executeUpdate() > 0) {
				isAssignmentAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAssignmentAdded;
	}

	// Submit assignment by student
	public boolean SubmitAssignment(int studentId, int assignmentid, InputStream pdfFile) {
		boolean isAssignmentAdded = false;

		try {

			// Check for normal user logins
			PreparedStatement pst = con
					.prepareStatement("insert into submittedassignments(studentid,assignmentid,file) values(?,?,?)");
			pst.setInt(1, studentId);
			pst.setInt(2, assignmentid);
			pst.setBlob(3, pdfFile);

			if (pst.executeUpdate() > 0) {
				isAssignmentAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAssignmentAdded;
	}

	// Get All Assignments
	public ArrayList<assignment> getAllAssignments() {
		ArrayList<assignment> AllAssignments = new ArrayList<assignment>();
		try {

			PreparedStatement pst = con.prepareStatement("SELECT * FROM assignments");
			ResultSet rs = pst.executeQuery();
			assignment temp;

			int assignmentId;
			String title;
			int subjectid;
			int classid;
			String pdfFile;
			String uploader;

			while (rs.next()) {
				assignmentId = rs.getInt("assignment_id");
				title = rs.getString("assignment_title");
				subjectid = rs.getInt("subject_id");
				classid = rs.getInt("class_id");
				pdfFile = ImageConvertor.getImage((Blob) rs.getBlob("assignment_info"));
				uploader = TeacherFactory.getTeacherName(rs.getInt("uploader"));

				temp = new assignment(assignmentId, title, subjectid, classid, pdfFile, uploader);

				AllAssignments.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllAssignments;
	}

	// Get All Assignments that has been not completed by a student
	public ArrayList<assignment> getAllAssignmentsForClass(int clid, int studentid) {
		ArrayList<assignment> AllAssignments = new ArrayList<assignment>();
		try {

			PreparedStatement pst = con.prepareStatement(
					"SELECT A.assignment_id, A.assignment_title, A.subject_id, A.class_id, A.assignment_info, A.uploader FROM assignments A LEFT JOIN submittedassignments S ON A.assignment_id = S.assignmentid AND S.studentid = ? WHERE S.assignmentid IS NULL AND A.class_id = ? AND S.studentid IS NULL");
			pst.setInt(1, studentid);
			pst.setInt(2, clid);
			ResultSet rs = pst.executeQuery();
			assignment temp;

			int assignmentId;
			String title;
			int subjectid;
			int classid;
			String pdfFile;
			String uploader;

			while (rs.next()) {
				assignmentId = rs.getInt("assignment_id");
				title = rs.getString("assignment_title");
				subjectid = rs.getInt("subject_id");
				classid = rs.getInt("class_id");
				pdfFile = ImageConvertor.getImage((Blob) rs.getBlob("assignment_info"));
				uploader = TeacherFactory.getTeacherName(rs.getInt("uploader"));

				temp = new assignment(assignmentId, title, subjectid, classid, pdfFile, uploader);

				AllAssignments.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllAssignments;
	}

	// Get All Assignments related to a teacher
	public ArrayList<assignment> getTeacherUploadedAssignments(int teacherid) {
		ArrayList<assignment> AllAssignments = new ArrayList<assignment>();
		try {

			PreparedStatement pst = con.prepareStatement("SELECT * FROM assignments where uploader = ?");
			pst.setInt(1, teacherid);
			ResultSet rs = pst.executeQuery();
			assignment temp;

			int assignmentId;
			String title;
			int subjectid;
			int classid;
			String pdfFile;
			String uploader;

			while (rs.next()) {
				assignmentId = rs.getInt("assignment_id");
				title = rs.getString("assignment_title");
				subjectid = rs.getInt("subject_id");
				classid = rs.getInt("class_id");
				pdfFile = ImageConvertor.getImage((Blob) rs.getBlob("assignment_info"));
				uploader = TeacherFactory.getTeacherName(rs.getInt("uploader"));

				temp = new assignment(assignmentId, title, subjectid, classid, pdfFile, uploader);

				AllAssignments.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllAssignments;
	}

	// Get all submitted Assignments
	public ArrayList<submittedAssignments> getSubmittedAssignment() {
		ArrayList<submittedAssignments> AllSubmittedAssignments = new ArrayList<submittedAssignments>();

		try {

			int submitid;
			int studentid;
			String studentName;
			int assignmentid;
			String assignmentName;
			String UploadedFile;
			int marks;

			submittedAssignments temp;

			PreparedStatement pst = con.prepareStatement(
					"select S.submittedid,S.studentid,S.assignmentid,S.file,S.marks ,A.assignment_title from submittedassignments S ,assignments A WHERE A.assignment_id = S.assignmentid;");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				submitid = rs.getInt("submittedid");
				studentid = rs.getInt("studentid");
				studentName = StudentFactory.getStudentName(rs.getInt("studentid"));
				assignmentid = rs.getInt("assignmentid");
				assignmentName = rs.getString("assignment_title");
				UploadedFile = ImageConvertor.getImage((Blob) rs.getBlob("file"));
				marks = rs.getInt("marks");

				temp = new submittedAssignments(submitid, studentid, studentName, assignmentid, assignmentName,
						UploadedFile, marks);

				AllSubmittedAssignments.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllSubmittedAssignments;

	}

	// Get all submitted Assignments related to student
	public ArrayList<submittedAssignments> getSubmittedAssignmentForStudent(int stid) {
		ArrayList<submittedAssignments> AllSubmittedAssignments = new ArrayList<submittedAssignments>();

		try {

			int submitid;
			int studentid;
			String studentName;
			int assignmentid;
			String assignmentName;
			String UploadedFile;
			int marks;
			String subjectName;

			submittedAssignments temp;

			PreparedStatement pst = con.prepareStatement(
					"select S.submittedid,S.studentid,S.assignmentid,S.file,S.marks ,A.assignment_title,SUB.subjectname from submittedassignments S ,assignments A ,subjects SUB WHERE studentid = ? AND A.assignment_id = S.assignmentid AND A.subject_id = SUB.subjectid;");
			pst.setInt(1, stid);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				submitid = rs.getInt("submittedid");
				studentid = rs.getInt("studentid");
				studentName = StudentFactory.getStudentName(rs.getInt("studentid"));
				assignmentid = rs.getInt("assignmentid");
				assignmentName = rs.getString("assignment_title");
				UploadedFile = ImageConvertor.getImage((Blob) rs.getBlob("file"));
				marks = rs.getInt("marks");
				subjectName = rs.getString("subjectname");
				if (marks <= 0) {
					marks = -1;
				}

				temp = new submittedAssignments(submitid, studentid, studentName, assignmentid, assignmentName,
						UploadedFile, marks);
				temp.setSubjectName(subjectName);
				AllSubmittedAssignments.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllSubmittedAssignments;

	}

	// Get all submitted Assignments related to student
	public boolean DeleteAssignment(String assignmentId) {

		boolean isAssignmentDeleted = false;

		try {

			PreparedStatement pst = con.prepareStatement("DELETE  FROM assignments WHERE assignment_id = ?");
			pst.setString(1, assignmentId);

			if (pst.executeUpdate() > 0) {
				isAssignmentDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAssignmentDeleted;

	}

	// Get all submitted Assignments related to teacher
	public ArrayList<submittedAssignments> getSubmittedAssignmentRelatedToTeacher(int teacherid) {
		ArrayList<submittedAssignments> AllSubmittedAssignments = new ArrayList<submittedAssignments>();

		try {

			int submitid;
			int studentid;
			String studentName;
			int assignmentid;
			String assignmentName;
			String UploadedFile;
			int marks;
			String subjectName;

			submittedAssignments temp;

			PreparedStatement pst = con.prepareStatement(
					"Select SB.submittedid ,SB.studentid,SB.assignmentid,SB.file,SB.marks,A.assignment_title,SUB.subjectname FROM submittedassignments SB ,assignments A ,subjects SUB WHERE SB.assignmentid = A.assignment_id AND A.uploader = ? and A.subject_id = SUB.subjectid;");
			pst.setInt(1, teacherid);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				submitid = rs.getInt("submittedid");
				studentid = rs.getInt("studentid");
				studentName = StudentFactory.getStudentName(rs.getInt("studentid"));
				assignmentid = rs.getInt("assignmentid");
				assignmentName = rs.getString("assignment_title");
				UploadedFile = ImageConvertor.getImage((Blob) rs.getBlob("file"));
				marks = rs.getInt("marks");
				subjectName = rs.getString("subjectname");

				if (marks <= 0) {
					marks = -1;
				}

				temp = new submittedAssignments(submitid, studentid, studentName, assignmentid, assignmentName,
						UploadedFile, marks);
				temp.setSubjectName(subjectName);

				AllSubmittedAssignments.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllSubmittedAssignments;

	}

	// Teacher evaluate marks
	public boolean evaluateAssignments(String marks, String submitid) {
		boolean isEvaluated = false;

		try {

			PreparedStatement pst = con
					.prepareStatement("UPDATE submittedassignments SET marks = ? WHERE submittedid = ?");
			pst.setString(1, marks);
			pst.setString(2, submitid);

			if (pst.executeUpdate() > 0) {
				isEvaluated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isEvaluated;

	}

}
