
<%@page import="com.mycompany.model.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="webCtrl" class="com.mycompany.ctrl.Controller" scope="application" />

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

            <h2 class="header">Gib deine Daten ein</h2>

            <form action="" method="post">
                <div class="form-group">
                    <label for="firstname">Vorname</label>
                    <input type="text" id="firstname" name="firstname" placeholder="" value="${(currentUser == null) ? '' : currentUser.getFirstname()}" >
                </div>
                <div class="form-group">
                    <label for="lastname">Nachname</label>
                    <input type="text" id="lastname" name="lastname" placeholder="" value="${(currentUser == null) ? '' : currentUser.getLastname()}" >
                </div>
                <div class="form-group">
                    <label for="lastname">Adresse</label>
                    <input type="text" id="adress" name="adress" placeholder="" value="${(currentUser == null) ? '' : currentUser.getAdress()}" >
                </div>


                <%@ include file="jspf/navButtons.jspf" %>

            </form>

        </div>



    </body>
</html>
