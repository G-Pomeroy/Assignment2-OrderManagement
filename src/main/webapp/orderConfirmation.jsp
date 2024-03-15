<%@ page import="java.util.Map" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Product" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<%
    Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
    User user = (User) session.getAttribute("user");
%>

<header class="header">
    <h1>Clothing Company Co.</h1>
    <nav>
        <a href="signup.jsp">Sign Up</a>
        <a href="category?category=all">All Products</a>
    </nav>
</header>

<h1>Order Confirmation</h1>

<h2>Cart Details</h2>
<%
    for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
        Product product = entry.getKey();
        int quantity = entry.getValue();
%>
<p><strong>Name:</strong> <%= product.getName() %></p>
<p><strong>Description:</strong> <%= product.getDescription() %></p>
<p><strong>Price:</strong> <%= product.getCost() %></p>
<p><strong>Quantity:</strong> <%= quantity %></p>
<%
    }
%>

<h2>User Information</h2>
<p><strong>First Name:</strong> <%= user.getFirstName() %></p>
<p><strong>Last Name:</strong> <%= user.getLastName() %></p>

</body>
</html>
