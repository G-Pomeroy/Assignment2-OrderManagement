package com.ordermanagement.assignment2ordermanagement.controller;

import com.ordermanagement.assignment2ordermanagement.model.Product;
import com.ordermanagement.assignment2ordermanagement.model.ProductDAO;

import com.ordermanagement.assignment2ordermanagement.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = ProductDAO.getProductById(productId);
        User user = (User) request.getSession().getAttribute("user");

        //Ensure product is available
        if (product != null && product.getStock() > 0) {
            String shippingProvider = request.getParameter("shippingProvider");
            BigDecimal shippingCost = BigDecimal.valueOf(calculateShippingCost(shippingProvider));
            BigDecimal productCost = new BigDecimal(String.valueOf(product.getCost()));
            BigDecimal totalAmount = productCost.add(shippingCost);
            request.setAttribute("product", product);
            request.setAttribute("user", user);
            request.setAttribute("shippingProvider", shippingProvider);
            request.setAttribute("shippingCost", shippingCost);
            request.setAttribute("totalAmount", totalAmount);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmOrder.jsp");
            dispatcher.forward(request, response);
        } else {
            String errorMessage = "Sorry, the product is out of stock!";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category?category=all");
            dispatcher.forward(request, response);
        }
    }

    private double calculateShippingCost(String shippingProvider) {
        double shippingCost = 0.0;

        switch (shippingProvider) {
            case "fast":
                shippingCost = 12.99;
                break;
            case "faster":
                shippingCost = 27.99;
                break;
            case "fastest":
                shippingCost = 50.00;
                break;
        }
        return shippingCost;
    }
}
