<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SRegister New User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<h2>Sign Up</h2>
<form action="signup" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br>

    <label for="phoneNum">Phone Number:</label>
    <input type="text" id="phoneNum" name="phoneNum" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="street">Street:</label>
    <input type="text" id="street" name="street" required><br>

    <label for="city">City:</label>
    <input type="text" id="city" name="city" required><br>

    <label for="province">Province:</label>
    <input type="text" id="province" name="province" required><br>

    <label for="postalCode">Postal Code:</label>
    <input type="text" id="postalCode" name="postalCode" required><br>

    <input type="submit" value="Sign Up">
</form>
</body>
</html>
