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
//        HttpSession session = request.getSession(false);

        int productId = Integer.parseInt(request.getParameter("productId"));

        // Retrieve the product details from the database
        Product product = ProductDAO.getProductById(productId);
        User user = (User) request.getSession().getAttribute("user");

        // Check if the product is available
        if (product != null && product.getStock() > 0) {
            // Retrieve shipping provider from the form
            String shippingProvider = request.getParameter("shippingProvider");

            // Calculate shipping cost based on the selected shipping provider
            BigDecimal shippingCost = BigDecimal.valueOf(calculateShippingCost(shippingProvider));

            // Calculate total amount
            BigDecimal productCost = new BigDecimal(String.valueOf(product.getCost()));
            BigDecimal totalAmount = productCost.add(shippingCost);

            // Set attributes to pass to the order confirmation page
            request.setAttribute("product", product);
            request.setAttribute("user", user);
            request.setAttribute("shippingProvider", shippingProvider);
            request.setAttribute("shippingCost", shippingCost);
            request.setAttribute("totalAmount", totalAmount);

            // Forward the user to the order confirmation page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmOrder.jsp");
            dispatcher.forward(request, response);
        } else {
            // Product is not available, display an error message
            String errorMessage = "Sorry, the product is out of stock!";
            request.setAttribute("errorMessage", errorMessage);
            // Forward the user back to the product page or display an error page
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
