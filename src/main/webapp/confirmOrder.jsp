<%@ page import="java.util.Map" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Product" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.User" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.Order" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.ordermanagement.assignment2ordermanagement.model.OrderDetails" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<%
    DecimalFormat df = new DecimalFormat("#.00");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Order Confirmation</title>
</head>
<body>
<div class="container">
    <h1>Order Confirmation</h1>
    <div class="order-details">
        <div class="address">
            <%
                Order order = (Order) session.getAttribute("order");

                User user = (User) session.getAttribute("user");
                if (user != null) {
                    String street = user.getStreet();
                    String city = user.getCity();
                    String province = user.getProvince();
                    String postalCode = user.getPostalCode();
                }

                List<OrderDetails> orderDetailsList = (List<OrderDetails>) request.getAttribute("orderDetailsList");
            %>

            <h2>Shipping Address</h2>
            <p><%= user.getStreet() %></p>
            <p><%= user.getCity() %>, <%= user.getProvince() %>, <%= user.getPostalCode() %></p>
        </div>

        <div class="items">
            <h2>Items</h2>
            <ul>
                <%
                    double total = 0.0;
                    Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
                    if (cart != null) {
                        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                            Product product = entry.getKey();
                            int stock = entry.getValue();
                            BigDecimal cost = product.getCost();
                            BigDecimal itemTotal = cost.multiply(BigDecimal.valueOf(stock));
                            total += itemTotal.doubleValue();
                %>
                <li class="item">
                    <span><%= product.getName() %> - $<%= new DecimalFormat("0.00").format(product.getCost()) %></span>
                    <span>Quantity: <%= stock %></span>
                    <span>Item Total: $<%= new DecimalFormat("0.00").format(itemTotal) %></span>
                </li>
                <%     }
                }
                %>
            </ul>
        </div>
        <div class="total">
            <p>Total: $<%= new DecimalFormat("0.00").format(total) %></p>
        </div>
    </div>
    <div class="button-container">
        <a href="confirmation.jsp" class="button">Confirm Order</a>
    </div>
</div>
</body>
</html>
