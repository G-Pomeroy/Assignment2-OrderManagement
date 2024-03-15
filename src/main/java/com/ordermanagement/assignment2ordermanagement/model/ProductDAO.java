package com.ordermanagement.assignment2ordermanagement.model;

import com.ordermanagement.assignment2ordermanagement.database.DBConn;
import com.ordermanagement.assignment2ordermanagement.model.Product;
import com.ordermanagement.assignment2ordermanagement.model.ProductDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DBConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT p.product_id, p.category_id, p.review_id, p.name, p.cost, p.description, p.stock, i.data " +
                             "FROM product p " +
                             "JOIN images i ON p.image_id = i.image_id");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int categoryId = resultSet.getInt("category_id");
                int reviewId = resultSet.getInt("review_id");
                String name = resultSet.getString("name");
                BigDecimal cost = resultSet.getBigDecimal("cost");
                String description = resultSet.getString("description");
                int stock = resultSet.getInt("stock");
                String imageUrl = resultSet.getString("data");

                Product product = new Product(productId, categoryId, imageUrl, reviewId, name, cost, description, stock);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database or retrieving product details:");
            e.printStackTrace();
        }
        return productList;
    }

    public static List<Product> getProductsByCategory(String category) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DBConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT p.product_id, p.category_id, p.review_id, p.name, p.cost, p.description, p.stock, i.data " +
                             "FROM product p " +
                             "JOIN images i ON p.image_id = i.image_id " +
                             "WHERE p.category_id = (SELECT category_id FROM product_category WHERE category_name = ?)")) {

            statement.setString(1, category);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    int categoryId = resultSet.getInt("category_id");
                    int reviewId = resultSet.getInt("review_id");
                    String name = resultSet.getString("name");
                    BigDecimal cost = resultSet.getBigDecimal("cost");
                    String description = resultSet.getString("description");
                    int stock = resultSet.getInt("stock");
                    String imageUrl = resultSet.getString("data");

                    Product product = new Product(productId, categoryId, imageUrl, reviewId, name, cost, description, stock);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database or retrieving product details by category:");
            e.printStackTrace();
        }
        return productList;
    }

    public static Product getProductById(int productId) {
        Product product = null;

        try (Connection connection = DBConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM product WHERE product_id = ?")) {

            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int categoryId = resultSet.getInt("category_id");
                    String imageUrl = resultSet.getString("image_id");
                    int reviewId = resultSet.getInt("review_id");
                    String name = resultSet.getString("name");
                    BigDecimal cost = resultSet.getBigDecimal("cost");
                    String description = resultSet.getString("description");
                    int stock = resultSet.getInt("stock");


                    product = new Product(productId, categoryId, imageUrl, reviewId, name, cost, description, stock);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving product from the database: " + e.getMessage());
            e.printStackTrace();
        }

        return product;
    }
    public static boolean decreaseStock(int productId, int stock) {
        String query = "UPDATE product SET stock = stock - ? WHERE product_id = ?";
        try (Connection connection = DBConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stock);
            statement.setInt(2, productId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean purchaseProduct(int productId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConn.getConnection();

            String sql = "UPDATE product SET stock = stock - 1 WHERE product_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}