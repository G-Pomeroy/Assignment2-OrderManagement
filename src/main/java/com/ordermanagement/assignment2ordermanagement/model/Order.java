package com.ordermanagement.assignment2ordermanagement.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
    private int orderId;
    private int userId;
    private LocalDate orderDate;
    private String status;
    private double orderPrice;

    // Constructors, getters, and setters


    public Order() {
    }

    public Order(int orderId, int userId, LocalDate orderDate, String status, double orderPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
