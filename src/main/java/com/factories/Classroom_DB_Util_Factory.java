package com.factories;

import com.DBconnection.databaseConnection;
import com.classes.classDBUtil;

public class Classroom_DB_Util_Factory {

    // Create and return an instance of resultDBUtil with the default database connection
    public static classDBUtil createClassDBUtil() {
        return new classDBUtil(databaseConnection.DBconnection());
    }
}
