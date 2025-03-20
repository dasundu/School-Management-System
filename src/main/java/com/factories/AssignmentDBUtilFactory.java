package com.factories;

import com.DBconnection.databaseConnection;
import com.assignments.assignmentDBUtil;

public class AssignmentDBUtilFactory {

	// Create and return an instance of resultDBUtil with the default database
	// connection
	public static assignmentDBUtil createAssignmentDBUtilInstance() {
		return new assignmentDBUtil(databaseConnection.DBconnection());
	}

}
