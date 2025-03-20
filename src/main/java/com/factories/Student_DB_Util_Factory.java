package com.factories;

import com.DBconnection.databaseConnection;
import com.students.studentsDBUtil;

public class Student_DB_Util_Factory {

    // Create and return an instance of resultDBUtil with the default database connection
    public static studentsDBUtil create_DB_Util_For_Student() {
        return new studentsDBUtil(databaseConnection.DBconnection());
    }
}
