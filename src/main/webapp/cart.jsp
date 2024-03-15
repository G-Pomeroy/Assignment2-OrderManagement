<%@ page import="java.util.Map" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Product" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<header class="header">
    <h1>Clothing Company Co.</h1>
    <nav>
        <a href="category?category=all">All Products</a>
        <a href="cart.jsp">View Cart</a>
    </nav>
</header>

<h1>Shopping Cart</h1>
<div id="cart-container">
    <%
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if (cart != null && !cart.isEmpty()) {
    %>
    <table>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>
        <%
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
        %>
        <tr>
            <td><%= product.getName() %></td>
            <td><%= String.format("$%.2f", product.getCost())  %></td>
            <td><%= quantity %></td>
            <td><%= String.format("$%.2f", product.getCost().doubleValue() * quantity) %></td>
        </tr>
        <% } %>
    </table>
    <p>Total Price: <%= getTotalPrice(cart) %></p>
    <form action="checkout" method="post">
        <input type="submit" value="Proceed to Checkout">
    </form>
    <% } else { %>
    <p>Your cart is empty! Go check out some amazing deals!</p>
    <% } %>
</div>

<%!
    String getTotalPrice(Map<Product, Integer> cart) {
        double totalPrice = 0;

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int stock = entry.getValue();
            double cost = product.getCost().doubleValue();

            totalPrice += cost * stock;
        }

        return String.format("$%.2f", totalPrice);
    }
%>
</body>
</html>
