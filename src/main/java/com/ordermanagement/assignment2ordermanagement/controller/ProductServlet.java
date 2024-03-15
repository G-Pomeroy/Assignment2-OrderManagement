package com.ordermanagement.assignment2ordermanagement.controller;

import com.ordermanagement.assignment2ordermanagement.model.Product;
import com.ordermanagement.assignment2ordermanagement.model.ProductDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/category?category=all");

            // Process the request as usual
            String category = request.getParameter("category");
            List<Product> products = ProductDAO.getProductsByCategory(category);
            request.setAttribute("products", products);
            request.setAttribute("selectedCategory", category);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category?category=all");
            dispatcher.forward(request, response);
        }
    }
