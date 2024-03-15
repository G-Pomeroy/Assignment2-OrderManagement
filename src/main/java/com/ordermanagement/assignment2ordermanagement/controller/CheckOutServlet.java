package com.ordermanagement.assignment2ordermanagement.controller;

import com.ordermanagement.assignment2ordermanagement.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        int userId = (int) session.getAttribute("userId");

        Order newOrder = new Order();
        newOrder.setUserId(userId);
        newOrder.setStatus("Pending");
        newOrder.setOrderDate(LocalDate.now());

        System.out.println("New Order created: " + newOrder);

        session.setAttribute("order", newOrder);


        System.out.println("Session attributes after adding order: " + session.getAttributeNames());

        boolean orderCreated = OrderDAO.createOrder(newOrder);

        if (orderCreated == true) {
            Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

            int orderId = OrderDAO.getOrderId(newOrder);
            List<OrderDetails> orderDetailsList = new ArrayList<>();

            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int stock = entry.getValue();
                double totalPrice = product.getCost().doubleValue() * stock;

                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderId(orderId);
                orderDetails.setProductId(product.getProductId());
                orderDetails.setQuantity(stock);
                orderDetails.setTotalPrice(totalPrice);

                orderDetailsList.add(orderDetails);

                OrderDetailsDAO.createOrderDetails(orderDetails);

                ProductDAO.decreaseStock(product.getProductId(), stock);
            }

            session.setAttribute("orderDetailsList", orderDetailsList);


            response.sendRedirect(request.getContextPath() + "/confirmOrder.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}

