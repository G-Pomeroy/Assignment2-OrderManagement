package com.ordermanagement.assignment2ordermanagement.controller;

import com.ordermanagement.assignment2ordermanagement.database.DBConn;
import com.ordermanagement.assignment2ordermanagement.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = getUserByUsernameAndPassword(username, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user",user);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", username);

            response.sendRedirect(request.getContextPath() + "/category?category=all");
        } else {
            response.sendRedirect(request.getContextPath() + "/loginError.jsp");
        }
    }

    private User getUserByUsernameAndPassword(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DBConn.getConnection();

            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_Id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNum = resultSet.getString("phone_num");
                String email = resultSet.getString("email");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                String province = resultSet.getString("province");
                String postalCode = resultSet.getString("postal_code");

                // Create a new User object and populate it with the retrieved data
                user = new User(userId, username, password, firstName, lastName, phoneNum, email, street,
                        city, province, postalCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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

        return user;
    }
}