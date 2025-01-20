
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" %>
<jsp:useBean id="webCtrl" class="com.mycompany.ctrl.Controller" scope="application" />
<%@page import="com.mycompany.model.FoodItem"%>

<!DOCTYPE html>
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
            <c:set var="currentPage" value="${(empty param.page) ? null : param.page}"/>

            <%@ include file="jspf/nav.jspf" %>

            <br>
            <div class="text-row header">Beste Pizza und anderes Essen</div>
            <div class="text-row">Menü</div>


            <form action="" method="post">
                <div class="food-items">

                    <c:forEach items="${applicationScope.menu}" var="menuItem">
                        <div class="food-item" data-price="${menuItem.price}">
                            <div>
                                <h3>${menuItem.name}</h3>
                                <input type="hidden" name="foodItems[${status.index}].name" value="${menuItem.name}">

                                <p>${menuItem.price}</p>
                                <input type="hidden" name="foodItems[${status.index}].price" value="${menuItem.price}">
                            </div>

                            <div class="counter">
                                <button type="button" class="decrease">-</button>
                                <input type="number" name="foodItems[${status.index}].count" value="${(itemCounts[menuItem.getName()] == null) ? 0 : itemCounts[menuItem.getName()]}" min="0" class="count-input">
                                <button type="button" class="increase">+</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!--</div>-->
                <div class="total-container">
                    <h3>Total: <input type="text" id="total-price" name="total" readonly="true" value="${(order.getOrderPrice() == null) ? 0 : order.getOrderPrice()}"></h3>
                </div>

                <%@ include file="jspf/navButtons.jspf" %>
            </form>



        </div>
        <%@ include file="jspf/changeMenu.jspf" %>
    </body>
</html>
