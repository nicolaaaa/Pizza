<%@ page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newcss.css" />
        <link rel="icon" href="images/pizza.svg" sizes="any" type="image/svg+xml">
    </head>
    <body>
        <div class="container">
            <h1>Ein Fehler ist aufgetreten</h1>

            <c:choose>
                <c:when test="${not empty pageContext.exception}">
                    <p><strong>Exception:</strong> <c:out value="${pageContext.exception.class.simpleName}" /></p>
                    <p><strong>Message:</strong> <c:out value="${pageContext.exception.message}" /></p>
                    <div class="error-details">
                        <p><strong>Cause:</strong> <c:out value="${pageContext.exception.cause}" /></p>
                        <h3>Stack Trace:</h3>
                        <div class="stacktrace">
                            <c:forEach var="trace" items="${pageContext.exception.stackTrace}" begin="0" end="5">
                                ${trace}<br />
                            </c:forEach>
                        </div>
                    </div>
                </c:when>

                <c:otherwise>
                    <p><strong>Fehlermeldung:</strong> Login fehlgeschlagen. Bitte überprüfen Sie Ihre Anmeldedaten.</p>
                </c:otherwise>
            </c:choose>

            <p><a class="modern-button" href="<c:url value='/' />">Zurück zum Anfang</a></p>
        </div>
    </body>
</html>
