package com.teachers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.DBconnection.databaseConnection;

public class teacherDBUtil {

	// Get Connection to the database
	public static Connection con = databaseConnection.DBconnection();

	// Default Constructor
	public teacherDBUtil(Connection dBconnection) {
		// TODO Auto-generated constructor stub
	}

	// Get All Teachers
	public ArrayList<teacher> getAllTeachers() {
		ArrayList<teacher> allTeachers = new ArrayList<teacher>();
		ResultSet resultSet = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from teachers");

			// Execute the query
			resultSet = pst.executeQuery();

			// Teacher temp Object
			teacher temp;

			// Teacher info;
			int id;
			String specialization;
			String birthday;
			String lastName;
			String telephone;
			String address;
			String firstName;

			while (resultSet.next()) {
				id = resultSet.getInt("id");
				specialization = resultSet.getString("specialization");
				birthday = resultSet.getString("birthday");
				lastName = resultSet.getString("last_name");
				telephone = resultSet.getString("telephone");
				address = resultSet.getString("address");
				firstName = resultSet.getString("first_name");

				temp = new teacher(id, specialization, birthday, lastName, telephone, address, firstName);

				allTeachers.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTeachers;
	}

	// add new teacher
	public boolean addNewTeacher(int id, String birthday, String firstname, String lastname, String telephone,
			String Address, String specialization) {
		boolean isTeacherAdded = false;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO teachers (id,birthday,first_name,last_name,telephone,address,specialization) VALUES(?,?,?,?,?,?,?)");
			pst.setInt(1, id);
			pst.setString(2, birthday);
			pst.setString(3, firstname);
			pst.setString(4, lastname);
			pst.setString(5, telephone);
			pst.setString(6, Address);
			pst.setString(7, specialization);

			if (pst.executeUpdate() > 0) {
				isTeacherAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isTeacherAdded;
	}

	// Get specific teacher name
	public String getTeacherName(int teacherid) {
		String teacherName = null;

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from teachers where id = ?");
			pst.setInt(1, teacherid);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				teacherName = rs.getString("first_name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return teacherName;
	}

	// Get a specific teacher
	public teacher getSpecificTeacher(int Teacherid) {

		ResultSet resultSet = null;

		// Teacher temp Object
		teacher teacherInfo = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from teachers where id = ?");
			pst.setInt(1, Teacherid);

			// Execute the query
			resultSet = pst.executeQuery();

			// Teacher info;
			int id;
			String specialization;
			String birthday;
			String lastName;
			String telephone;
			String address;
			String firstName;

			while (resultSet.next()) {
				id = resultSet.getInt("id");
				specialization = resultSet.getString("specialization");
				birthday = resultSet.getString("birthday");
				lastName = resultSet.getString("last_name");
				telephone = resultSet.getString("telephone");
				address = resultSet.getString("address");
				firstName = resultSet.getString("first_name");

				teacherInfo = new teacher(id, specialization, birthday, lastName, telephone, address, firstName);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherInfo;
	}

	// Update teacher
	public boolean UpdateTeacher(int id, String birthday, String firstname, String lastname, String telephone,
			String Address, String specialization) {
		boolean isTeacherUpdated = false;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement(
					"UPDATE teachers SET birthday = ? ,first_name = ? ,last_name = ? ,telephone = ? ,address = ? ,specialization = ? WHERE id = ?");

			pst.setString(1, birthday);
			pst.setString(2, firstname);
			pst.setString(3, lastname);
			pst.setString(4, telephone);
			pst.setString(5, Address);
			pst.setString(6, specialization);
			pst.setInt(7, id);

			if (pst.executeUpdate() > 0) {
				isTeacherUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isTeacherUpdated;
	}

	// Update teacher
	public boolean DeleteTeacher(int id) {
		boolean isTeacherDeleted = false;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("DELETE FROM teachers  WHERE id = ?");

			pst.setInt(1, id);

			if (pst.executeUpdate() > 0) {
				isTeacherDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isTeacherDeleted;
	}

}
