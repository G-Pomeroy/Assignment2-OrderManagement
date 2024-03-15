package com.ordermanagement.assignment2ordermanagement.model;
import com.ordermanagement.assignment2ordermanagement.database.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAO {
    public static boolean createOrderDetails(OrderDetails orderDetails) {
        String query = "INSERT INTO order_detail (order_id, product_id, quantity, price) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderDetails.getOrderId());
            statement.setInt(2, orderDetails.getProductId());
            statement.setInt(3, orderDetails.getQuantity());
            statement.setDouble(4, orderDetails.getTotalPrice());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}