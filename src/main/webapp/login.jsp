<%@ page import="java.util.Map" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.User" %>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/styles.css">
</head>
<body>
<%
    String contextPath = request.getContextPath();
    List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");
%>
<header class="header">
    <h1>Clothing Company Co.</h1>
    <nav>
        <a href="<%= contextPath %>/login.jsp">Login</a>
        <a href="<%= contextPath %>/category?category=all">All Products</a>
    </nav>
</header>

<h2>Login</h2>
<form action="<%= contextPath %>/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>
</body>
</html>
