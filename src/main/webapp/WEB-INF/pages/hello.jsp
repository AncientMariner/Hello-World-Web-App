<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World for logged user</title>
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
                <li><a href="${pageContext.request.contextPath}/users/logout">Logout</a></li>
            </ul>

        </div>
    </div>
</div>

<div class="container">
    <div class="content">
        <div class="row">
            <div class="login-form">
                <form action="">
                    <fieldset>
                        <h2>Hello World Team Online for ${user.username}</h2>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>