<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 21.05.2019
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TextBundle"/>


<html lang="${language}">
<head>
    <style>
        <%@include file="stylesheet/driver.css" %>
    </style>
    <title>Main menu</title>
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


<form action="" method="post">
    <input type="hidden" name="action" value="logout">
    <input class="button" style="float: left;" type="submit" value=<fmt:message key="logout"/>>
</form>



<div class="info">
    <p class="welcome"><fmt:message key="welcome"/>, ${name} ${surname} </p>
    <c:if test="${route < 0}">
        <p><fmt:message key="not.assigned"/></p>
    </c:if>
    <c:if test="${route > 0}">
        <p class="workplace"><fmt:message key="workplace"/></p>
        <P><fmt:message key="bus"/> ${plate}</P>
        <p><fmt:message key="route"/> ${route}</p>
        <c:if test="${not accepted}">
            <form action="" method="post">
                <input type="hidden" name="action" value="approve">
                <input class="button" type="submit" value=<fmt:message key="approve"/>>
            </form>
        </c:if>
    </c:if>
</div>

</body>
</html>
