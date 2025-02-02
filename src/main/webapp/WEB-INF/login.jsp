<%--
    Document   : login
    Created on : 22.01.2025, 11:29:17
    Author     : trainer
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newcss.css" />
        <link rel="icon" href="images/pizza.svg" sizes="any" type="image/svg+xml">
    </head>
    <body>

        <div class="container">
            <form action="j_security_check">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" name="j_username" value="" >
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="j_password" value="" >
                </div>
                <input class="modern-button" type="submit" value="Login" />
            </form>
        </div>
    </body>
</html>
