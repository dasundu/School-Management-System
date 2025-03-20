package com.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnection {

	// Private static instance variable
	private static Connection instance;

	// Private constructor to prevent instantiation from outside the class
	private databaseConnection() {
	}

	// Public method to get the singleton instance (renamed to DBconnection)
	public static Connection DBconnection() {
		if (instance == null) {
			// If no instance exists, create a new one
			instance = createConnection();
		}
		return instance;
	}

	// Private method to create the database connection
	private static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "zoro20010828");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
