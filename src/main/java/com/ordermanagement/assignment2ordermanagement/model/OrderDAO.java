package com.ordermanagement.assignment2ordermanagement.model;

import com.ordermanagement.assignment2ordermanagement.database.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {
    // Insert a new order into the database
    public static boolean createOrder(Order order) {
        String query = "INSERT INTO `order` (user_id, order_date, status, order_price) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getUserId());
            statement.setDate(2, java.sql.Date.valueOf(order.getOrderDate()));
            statement.setString(3, order.getStatus());
            statement.setDouble(4, order.getOrderPrice());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve an order by its ID
    public static int getOrderId(Order order) {
        String query = "SELECT order_id FROM `order` WHERE user_id = ? AND order_date = ? AND status = ? AND order_price = ?";
        try (Connection connection = DBConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getUserId());
            statement.setDate(2, java.sql.Date.valueOf(order.getOrderDate()));
            statement.setString(3, order.getStatus());
            statement.setDouble(4, order.getOrderPrice());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("order_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
