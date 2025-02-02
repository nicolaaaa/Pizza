
<jsp:useBean id="webCtrl" class="com.mycompany.ctrl.Controller" scope="application" />
<%@page import="com.mycompany.model.UserData"%>
<%@page import="com.mycompany.model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.mycompany.model.FoodItem"%>
<jsp:useBean id="signin" scope="session" class="com.mycompany.ctrl.SigninCtrl" />




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newcss.css" />
        <link rel="icon" href="images/pizza.svg" sizes="any" type="image/svg+xml">
    </head>
    <body>
        <div class="container">
            <c:set var="currentPage" value="${(empty param.page) ? null : param.page}"/>

            <%@ include file="jspf/nav.jspf" %>

            <br>
            <div class="text-row">Fast fertig</div>
            <div class="text-row">Überprüfe hier noch mal die Daten deiner Bestellung</div>

            <div class="wrapper">

                <div class="receipt">
                    <h2>Bestellung</h2>
                    <ul class="receipt-items">
                        <c:forEach items="${order.getOrderItems()}" var="orderItem">

                            <li class="receipt-item">
                                <span class="item-name">${orderItem.getName()}</span>
                                <span class="item-price">${orderItem.getPrice()}</span>
                            </li>

                        </c:forEach>
                    </ul>
                    <div class="receipt-total">
                        <span class="total-label">Total:</span>
                        <span class="total-amount">${order.getOrderPrice()}</span>
                    </div>
                </div>

                <div class="user-container">
                    <div class="profile-picture">
                        <img src="${pageContext.request.contextPath}/images/user.jpg" alt="Profile Picture">
                    </div>
                    <div class="user-details">
                        <div class="user-row">
                            <span class="label">First Name:</span>
                            <span class="value">${currentUser.getFirstname()}</span>
                        </div>
                        <div class="user-row">
                            <span class="label">Last Name:</span>
                            <span class="value">${currentUser.getLastname()}</span>
                        </div>
                        <div class="user-row">
                            <span class="label">Address:</span>
                            <span class="value">${currentUser.getAdress()}</span>
                        </div>
                        <div class="user-row">
                            <span class="label">Session</span>
                            <span class="value">${webCtrl.trimToLength(currentUser.getSession(), 10)}</span>
                        </div>
                        <div class="user-row">
                            <span class="label">IP:</span>
                            <span class="value">${currentUser.getIpAdress()}</span>
                        </div>
                    </div>
                </div>

            </div>
            <form action="" method="post">

                <%@ include file="jspf/navButtons.jspf" %>
            </form>
        </div>

        <% if (signin.isLogedIn(request)) {%>
        <%@ include file="jspf/adminButtons.jspf" %>
        <%}%>
    </body>
</html>
