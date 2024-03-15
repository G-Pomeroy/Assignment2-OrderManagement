package com.ordermanagement.assignment2ordermanagement.controller;

import com.google.gson.Gson;
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

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the selected category from the request parameters
        String category = request.getParameter("category");

        // If no category parameter is present or it's "all", retrieve all products
        List<Product> products;
        if (category == null || category.equals("all")) {
            products = ProductDAO.getAllProducts();
        } else {
            // If a category parameter is present, filter products by category
            products = ProductDAO.getProductsByCategory(category);
        }

        // Set the product list as an attribute in the request
        request.setAttribute("products", products);

        // Forward the request to the products.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
        dispatcher.forward(request, response);
    }
}

