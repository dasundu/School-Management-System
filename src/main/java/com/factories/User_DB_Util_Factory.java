package com.factories;

import com.DBconnection.databaseConnection;
import com.user.usersDBUtil;

public class User_DB_Util_Factory {

    // Create and return an instance of resultDBUtil with the default database connection
    public static usersDBUtil create_User_DB_Factory() {
        return new usersDBUtil(databaseConnection.DBconnection());
    }

}
