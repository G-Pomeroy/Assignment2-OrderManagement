package com.ordermanagement.assignment2ordermanagement.controller;

import com.ordermanagement.assignment2ordermanagement.model.User;
import com.ordermanagement.assignment2ordermanagement.model.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    public SignUpServlet() {
        this.userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user information from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String postalCode = request.getParameter("postalCode");

        // Create a new User object
        User newUser = new User(username, password, firstName, lastName, phoneNum, email, street, city, province, postalCode);

        // Add the new user to the database
        try {
            boolean success = userDAO.addUser(newUser);
            if (success) {
                // User added successfully
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                // Error occurred while adding the user
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
