package com.results;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.DBconnection.databaseConnection;
import com.classes.classDBUtil;
import com.commonFunctions.ImageConvertor;
import com.factories.Classroom_DB_Util_Factory;
import com.factories.SubjectDBUtilFactory;
import com.factories.Teacher_DB_Util_Factory;
import com.mysql.cj.jdbc.Blob;
import com.subjects.subjectDBUtil;
import com.teachers.teacherDBUtil;

public class resultDBUtil {

	// Get Connection to the database
	public static Connection con = databaseConnection.DBconnection();
	
	// Teacher DB Factory
	private teacherDBUtil TeacherFactory = Teacher_DB_Util_Factory.create_Teacher_DB_Factory();

	// Default Constructor
	public resultDBUtil(Connection dBconnection) {
	}

	// Add New Result
	public boolean AddNewResult(String description, int subjectId, int classid, InputStream pdfFile, int uploaderid) {
		boolean isResultAdded = false;

		try {
			PreparedStatement pst = con.prepareStatement(
					"insert into results(resultDescription,classid,subjectid,uploaderid,resultSheet) values(?,?,?,?,?)");
			pst.setString(1, description);
			pst.setInt(2, classid);
			pst.setInt(3, subjectId);
			pst.setInt(4, uploaderid);
			pst.setBlob(5, pdfFile);

			if (pst.executeUpdate() > 0) {
				isResultAdded = true;
				return isResultAdded;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isResultAdded;
	}

	// Get results
	public ArrayList<result> getAllResults() {

		ArrayList<result> AllResults = new ArrayList<result>();

		try {

			PreparedStatement pst = con.prepareStatement("SELECT * FROM results");
			ResultSet rs = pst.executeQuery();

			//Create instance of subjectDButil using a factory
			subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();
			
	    	//Classrooms DB Util Factory 
			classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();
			
			
			result temp;

			int resultid;
			String ResultDescription;
			String className;
			String SubjectName;
			String uploaderName;
			String sheet;
			String releasedDate;

			while (rs.next()) {
				resultid = rs.getInt("resultid");
				ResultDescription = rs.getString("resultDescription");
				className = ClassRoomFactory.getClassName(rs.getInt("classid"));
				SubjectName = subjectDBUtilInstance.getSubjectName(rs.getInt("subjectid"));
				uploaderName = TeacherFactory.getTeacherName(rs.getInt("uploaderid"));
				sheet = ImageConvertor.getImage((Blob) rs.getBlob("resultSheet"));
				releasedDate = rs.getString("released_date");

				temp = new result(resultid, ResultDescription, className, SubjectName, uploaderName, sheet,
						releasedDate);

				AllResults.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllResults;

	}

	// Get results for a specific teacher
	public ArrayList<result> getAllResultsForTeacher(int uploader) {
		ArrayList<result> AllResults = new ArrayList<result>();

		try {

			PreparedStatement pst = con.prepareStatement("SELECT * FROM results Where uploaderid = ?");
			pst.setInt(1, uploader);
			ResultSet rs = pst.executeQuery();
			
			//Create instance of subjectDButil using a factory
			subjectDBUtil subjectDBUtilInstance = SubjectDBUtilFactory.createSubjectDBUtil();
			
	    	//Classrooms DB Util Factory 
			classDBUtil ClassRoomFactory = Classroom_DB_Util_Factory.createClassDBUtil();

			result temp;

			int resultid;
			String ResultDescription;
			String className;
			String SubjectName;
			String uploaderName;
			String sheet;
			String releasedDate;

			while (rs.next()) {
				resultid = rs.getInt("resultid");
				ResultDescription = rs.getString("resultDescription");
				className = ClassRoomFactory.getClassName(rs.getInt("classid"));
				SubjectName = subjectDBUtilInstance.getSubjectName(rs.getInt("subjectid"));
				uploaderName = TeacherFactory.getTeacherName(rs.getInt("uploaderid"));
				sheet = ImageConvertor.getImage((Blob) rs.getBlob("resultSheet"));
				releasedDate = rs.getString("released_date");

				temp = new result(resultid, ResultDescription, className, SubjectName, uploaderName, sheet,
						releasedDate);

				AllResults.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return AllResults;

	}

	// Delete new results
	public boolean DeleteResult(String resultId) {
		boolean isResultDeleted = false;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement("DELETE FROM  results WHERE resultid = ?");
			pst.setString(1, resultId);

			if (pst.executeUpdate() > 0) {
				isResultDeleted = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isResultDeleted;
	}
}
