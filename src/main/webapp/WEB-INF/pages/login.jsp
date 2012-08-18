<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Hello World Team Online Login Page</title>
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
            <ul class="nav">
            </ul>
            <ul class="nav pull-right">
                <li>
                    <a href="${pageContext.request.contextPath}/users/register">Sign Up</a>
                </li>
            </ul>

        </div>
    </div>
</div>
<div class="container">
    <div class="content">
        <div class="row">
            <div class="login-form">
                <h2>Enter your data to sign in</h2>

                <c:if test="${not empty userNotFoundMarker}">
                    <div class="alert alert-error">
                        <strong>Error </strong>User does not exist.
                    </div>
                </c:if><c:if test="${not empty passwordIsNotInvalidMarker}">
                <div class="alert alert-error">
                    <strong>Error </strong>Password does not match for current user.
                </div>
            </c:if>
                <fieldset>
                    <form:form action="${pageContext.request.contextPath}/users/login"
                               modelAttribute="loginUser" method="POST">
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
                        <br/>
                        <input type="submit" value="sign in" class="btn">
                    </form:form>
                </fieldset>
            </div>
        </div>
    </div>
</div>
</body>
</html>