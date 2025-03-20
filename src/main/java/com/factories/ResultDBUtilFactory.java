package com.factories;
import com.DBconnection.databaseConnection;
import com.results.resultDBUtil;

public class ResultDBUtilFactory 
{
    // Create and return an instance of resultDBUtil with the default database connection
    public static resultDBUtil createDefaultResultDBUtil() {
        return new resultDBUtil(databaseConnection.DBconnection());
    }

}
