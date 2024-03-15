package com.ordermanagement.assignment2ordermanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBTest {

    public static void main(String[] args) {
        testDatabaseConnection();
    }

    public static void testDatabaseConnection() {
        try {
            // Attempt to get a database connection using your DBConn class
            Connection connection = DBConn.getConnection();

            // If connection is successful, print success message
            System.out.println("Database connection successful!");

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            // If connection fails, print error message and stack trace
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }
}
