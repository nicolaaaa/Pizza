

<%@page import="com.mycompany.model.UserData"%>
<%@page import="com.mycompany.model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="webCtrl" class="com.mycompany.ctrl.Controller" scope="application" />
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
            <div class="text-row">Vielen Dank für Ihre Bestellung!</div>


        </div>

        <% if (signin.isLogedIn(request)) {%>
        <%@ include file="jspf/adminButtons.jspf" %>
        <%}%>
    </body>
</html>
