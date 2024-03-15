package com.ordermanagement.assignment2ordermanagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class VerifyServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

}
