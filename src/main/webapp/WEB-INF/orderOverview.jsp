<%--
    Document   : orderOverview
    Created on : Jan 20, 2025, 6:58:37 PM
    Author     : Nicola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.mycompany.model.Order" %>
<%@ page import="com.mycompany.model.UserData" %>
<%@ page import="com.mycompany.model.FoodItem" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newcss.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
        <link rel="icon" href="images/pizza.svg" sizes="any" type="image/svg+xml">
    </head>
    <body>

        <div class="container">

            <h2>Order List</h2>

            <table border="1">
                <thead>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Address</th>
                        <th>Food Items</th>
                        <th>Order Price</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through orders -->
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.customer.firstname}</td>
                            <td>${order.customer.lastname}</td>
                            <td>${order.customer.adress}</td>

                            <!-- Loop through order items -->
                            <td>
                                <c:forEach var="item" items="${order.orderItems}">
                                    ${item.name} - ${item.price} <br/>
                                </c:forEach>
                            </td>
                            <td>${order.orderPrice}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
