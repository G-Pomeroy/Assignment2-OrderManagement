package com.ordermanagement.assignment2ordermanagement.database;

import java.sql.Connection;
import java.sql.SQLException;


public class DBTest {

    public static void main(String[] args) {
        testDatabaseConnection();
    }

    public static void testDatabaseConnection() {
        try {
            Connection connection = DBConn.getConnection();

            System.out.println("Database connection successful!");

            connection.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }
}
