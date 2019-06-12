<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TextBundle"/>

<html>
<head>
    <style>
        <%@include file="stylesheet/index.css" %>
    </style>
    <title>Autopark</title>
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



<p><fmt:message key="greet"/></p>
<form class="formctr" action = "/login" method="get">
    <input class="button" type="submit" value="Log in">
</form>
</body>
</html>
