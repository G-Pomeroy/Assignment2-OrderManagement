package com.ordermanagement.assignment2ordermanagement.controller;

import com.ordermanagement.assignment2ordermanagement.model.Product;
import com.ordermanagement.assignment2ordermanagement.model.ProductDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = ProductDAO.getProductById(productId);

        if (product != null) {
            HttpSession session = request.getSession(true);

            Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            if (cart.containsKey(product)) {
                cart.put(product, cart.get(product) + 1);
            } else {
                cart.put(product, 1);
            }

            session.setAttribute("cart", cart);

            request.setAttribute("cart", cart);

            response.sendRedirect(request.getContextPath() + "/cart.jsp");
        } else {
            // If the product is not found, handle the error
            System.out.println("Theres an issue lol");
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}

