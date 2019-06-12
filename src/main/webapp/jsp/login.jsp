<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TextBundle"/>

<html>
<head>
    <style>
        <%@include file="stylesheet/login.css" %>
    </style>
    <title>Login</title>
</head>
<body>
<header>
    <h1 class="auto">Autopark</h1>
    <form class="box" action="" method="post">
        <input type="hidden" name="action" value="lang">
        <select id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ukrainian</option>
        </select>
    </form>
</header>


<c:if test="${notfound == 1}">
    <p style="color: red">Not found</p>
</c:if>

<form class="login"  action="" method="post">
    <input type="hidden" name="action" value="login">
    <table style="with: 50%">
        <tr>
            <td><p><fmt:message key="login"/></p></td>
        </tr>
        <tr>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td><p><fmt:message key="password"/></p></td>
        </tr>
        <tr>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>
    <input class="button" type="submit" value=<fmt:message key="enter"/>>
</form>

</body>
</html>
