package com.factories;

import com.DBconnection.databaseConnection;
import com.teachers.teacherDBUtil;

public class Teacher_DB_Util_Factory {

    // Create and return an instance of resultDBUtil with the default database connection
    public static teacherDBUtil create_Teacher_DB_Factory() {
        return new teacherDBUtil(databaseConnection.DBconnection());
    }
}
