<%@ page import="java.util.Random" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Order" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.User" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.OrderDetails" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<h1>Order Confirmation</h1>
<%
    Order order = (Order) session.getAttribute("order");
    User user = (User) session.getAttribute("user");
    if (user != null) {
        String street = user.getStreet();
        String city = user.getCity();
        String province = user.getProvince();
        String postalCode = user.getPostalCode();
    }
    List<OrderDetails> orderDetailsList = (List<OrderDetails>) session.getAttribute("orderDetailsList");

    // Generate a random tracking code
    String trackingCode = generateTrackingCode();
%>

<h2>Order Summary</h2>
<p><strong>Order ID:</strong> <%= order.getOrderId() %></p>
<p><strong>Shipping Address:</strong> <%= user.getStreet() %></p>
<%= user.getCity() %>
<%= user.getProvince() %>
<%= user.getPostalCode() %>

<h2>Total Amount</h2>
<p><strong>Total:</strong> <%= formatTotal(orderDetailsList) %></p>

<h2>Order Tracking</h2>
<p><strong>Tracking Code:</strong> <%= trackingCode %></p>
<br>
<p>Please save this information for your records</p>
<br>
<a href ="category?category=all">Back to main page</a>
</body>
</html>

<%!
    private String generateTrackingCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder trackingCode = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            trackingCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return trackingCode.toString();
    }
%>

<%!
    private String formatTotal(List<OrderDetails> orderDetailsList) {
        double total = calculateTotal(orderDetailsList);
        return String.format("$%.2f", total);
    }

    private double calculateTotal(List<OrderDetails> orderDetailsList) {
        double total = 0;
        for (OrderDetails orderDetails : orderDetailsList) {
            total += orderDetails.getQuantity() * orderDetails.getTotalPrice();
        }
        return total;
    }
%>

