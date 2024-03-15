<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Product" %>
<%@ page session="false" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<header class="header">
    <h1>Clothing Company Co.</h1>
    <nav>
        <a href="login.jsp">Login</a>
        <a href="signup.jsp">Sign Up</a>
        <a href="category?category=all">All Products</a>
        <a href="cart.jsp">View Cart</a>
    </nav>
</header>
<br>
<form action="category" method="get">
    <label for="categoryFilter">Search by category:</label>
    <select id="categoryFilter" name="category">
        <option value="all">All Categories</option>
        <option value="jackets">Jacket</option>
        <option value="shirts">Shirt</option>
        <option value="pants">Pants</option>
        <option value="socks">Socks</option>
        <option value="shoes">Shoes</option>
        <option value="watches">Watches</option>
    </select>
    <input type="submit" value="Submit">
</form>

<br>
<br>
<h1>Products</h1>
<div id="product-container">
    <% List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
                String imageUrl = product.getImageUrl();
    %>
    <div class="product-box">
        <div class="product-image">
            <p><strong><%= product.getName() %></strong></p>
            <img src="<%= imageUrl %>" alt="<%= product.getName() %>">
        </div>
        <div class="product-details">
            <p><strong>Name:</strong> <%= product.getName() %></p>
            <p><strong>Description:</strong> <%= product.getDescription() %></p>
            <p><strong>Price:</strong> <%= product.getCost() %></p>

            <!-- Add to Cart Button -->
            <form action="${pageContext.request.contextPath}/addToCart" method="post">
                <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                <input type="submit" value="Add to Cart">
            </form>
        </div>
    </div>
    <% }
    } else {
    %>
    <p>Please select a category from the menu above.</p>
    <% } %>
</div>

    </body>
</html>
