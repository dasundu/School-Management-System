package com.students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.DBconnection.databaseConnection;

public class studentsDBUtil {

	// Get Connection to the database
	public static Connection con = databaseConnection.DBconnection();

	// Default Constructor
	public studentsDBUtil(Connection dBconnection) {
		// TODO Auto-generated constructor stub
	}

	// Get All Students
	public ArrayList<student> getAllStudents() {
		ArrayList<student> allStudents = new ArrayList<student>();
		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from students");

			// Execute the query
			resultSet = pst.executeQuery();

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

			while (resultSet.next()) {
				id = resultSet.getInt("id");
				classid = resultSet.getInt("classid");
				birthDay = resultSet.getString("birthday");
				FirstName = resultSet.getString("first_name");
				LastName = resultSet.getString("last_name");
				Telephone = resultSet.getString("telephone");
				address = resultSet.getString("address");

				temp = new student(id, classid, birthDay, FirstName, LastName, Telephone, address);

				allStudents.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allStudents;
	}

	// Get Specific student
	public student getSpecificStudent(int studentid) {

		// Student temp Object
		student temp = null;

		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from students WHERE id = ?");
			pst.setInt(1, studentid);

			// Execute the query
			resultSet = pst.executeQuery();

			// Student info;
			int id;
			int classid;
			String birthDay;
			String FirstName;
			String LastName;
			String Telephone;
			String address;

			while (resultSet.next()) {
				id = resultSet.getInt("id");
				classid = resultSet.getInt("classid");
				birthDay = resultSet.getString("birthday");
				FirstName = resultSet.getString("first_name");
				LastName = resultSet.getString("last_name");
				Telephone = resultSet.getString("telephone");
				address = resultSet.getString("address");

				temp = new student(id, classid, birthDay, FirstName, LastName, Telephone, address);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	// add new student
	public boolean addNewStudent(int id, String birthday, String firstname, String lastname, String telephone,
			String Address, int classId) {
		boolean isStudentAdded = false;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO students (id,birthday,first_name,last_name,telephone,address,classid) VALUES(?,?,?,?,?,?,?)");
			pst.setInt(1, id);
			pst.setString(2, birthday);
			pst.setString(3, firstname);
			pst.setString(4, lastname);
			pst.setString(5, telephone);
			pst.setString(6, Address);
			pst.setInt(7, classId);

			if (pst.executeUpdate() > 0) {
				isStudentAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isStudentAdded;
	}

	// Get Specific student Name
	public String getStudentName(int studentid) {
		String studentName = null;

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from students where id = ?");
			pst.setInt(1, studentid);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				studentName = rs.getString("first_name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return studentName;
	}

	// Get Specific student class
	public int getStudentClass(int studentid) {
		int studentCLass = 0;

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select classid from students where id = ?");
			pst.setInt(1, studentid);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				studentCLass = rs.getInt("classid");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return studentCLass;
	}

	// Update Student By Admin
	public boolean UpdateStudent(String birthDate, int studentId, String firstName, String LastName, String Telephone,
			String Address, int Classid) {
		boolean isStudentUpdated = false;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement(
					"UPDATE students SET birthday = ? ,first_name = ? ,last_name = ? ,telephone = ?,address = ? ,classid = ? WHERE id = ?");
			pst.setString(1, birthDate);
			pst.setString(2, firstName);
			pst.setString(3, LastName);
			pst.setString(4, Telephone);
			pst.setString(5, Address);
			pst.setInt(6, Classid);
			pst.setInt(7, studentId);

			if (pst.executeUpdate() > 0) {
				isStudentUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isStudentUpdated;
	}

	// Update Student By Teacher
	public boolean UpdateStudentByTeacher(String birthDate, int userid, String firstName, String lastName,
			String telephone, String address) {

		boolean isStudentUpdated = false;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement(
					"UPDATE students SET birthday = ? ,first_name = ? ,last_name = ? ,telephone = ?,address = ?  WHERE id = ?");
			pst.setString(1, birthDate);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setString(4, telephone);
			pst.setString(5, address);
			pst.setInt(6, userid);

			if (pst.executeUpdate() > 0) {
				isStudentUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isStudentUpdated;

	}

}
