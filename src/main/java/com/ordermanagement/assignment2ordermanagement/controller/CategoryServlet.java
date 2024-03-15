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

        String category = request.getParameter("category");

        List<Product> products;
        if (category == null || category.equals("all")) {
            products = ProductDAO.getAllProducts();
        } else {
            products = ProductDAO.getProductsByCategory(category);
        }

        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
        dispatcher.forward(request, response);
    }
}

