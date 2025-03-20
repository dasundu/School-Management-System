package com.factories;

import com.DBconnection.databaseConnection;
import com.subjects.subjectDBUtil;

public class SubjectDBUtilFactory {
	
    // Create and return an instance of resultDBUtil with the default database connection
    public static subjectDBUtil createSubjectDBUtil() {
    	
        return new subjectDBUtil(databaseConnection.DBconnection());
    }

}
