<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World Team Online Registration Page</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>


<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand">
                Hello Team Online
            </a>

            <ul class="nav pull-right">
                <li><a href="${pageContext.request.contextPath}/users/login">Sign In</a></li>
            </ul>

        </div>
    </div>
</div>
<div class="container">
    <div class="content">
        <div class="row">
            <div class="login-form" class="alert alert-error">
                <h2>Enter your data to sign up</h2>
                <fieldset>
                    <form:form action="${pageContext.request.contextPath}/users/register"
                               modelAttribute="registerUser" method="POST">
                        <p>User name</p>

                        <div class="formField">
                            <form:input path="username" type="text"/>
                            <form:errors path="username"/>
                        </div>
                        <p>Password</p>

                        <div class="formField">
                            <form:input path="password" type="password"/>
                            <form:errors path="password"/>
                        </div>
                        <p>Confirm password</p>

                        <div class="formField">
                            <form:input path="passwordConfirm" type="password"/>
                            <form:errors path="passwordConfirm"/>
                        </div>
                        <br/>
                        <input type="submit" value="sign up" class="btn">
                    </form:form>

                </fieldset>
            </div>
        </div>
    </div>
</div>
<!-- /container -->
</body>
</html>