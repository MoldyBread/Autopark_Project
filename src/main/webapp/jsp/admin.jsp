<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 23.05.2019
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TextBundle"/>


<html>
<head>
    <style>
        <%@include file="stylesheet/admin.css" %>
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

<form style="float: left;" action="" method="post">
    <input type="hidden" name="action" value="logout">
    <input class="button" type="submit" value="<fmt:message key="logout"/>">
</form>

<div class="info">
    <h1><fmt:message key="welcome"/>, ${login}</h1>

    <a style="font-size: 18px; text-decoration: none;" class="button" href="routes">
        <fmt:message key="routes"/>
    </a>
    <a style="font-size: 18px; text-decoration: none;" class="button" href="drivers?page=1">
        <fmt:message key="drivers"/>
    </a>

    <c:choose>

    <c:when test="${language == 'en'}">
    <form style="margin-top: 50px; margin-right: 300px;" action="" method="post">
        </c:when>
        <c:otherwise>
        <form style="margin-top: 50px; margin-right: 250px;" action="" method="post">
            </c:otherwise>
            </c:choose>

            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" value="${bus.id}">
            <input style="font-size: 18px" class="button" type="submit" value=<fmt:message key="edit"/>>
        </form>

        <table border="1">
            <caption><fmt:message key="buses"/></caption>
            <tr>
                <th>Id</th>
                <th><fmt:message key="plate"/></th>
                <th><fmt:message key="driverid"/></th>
                <th><fmt:message key="routeid"/></th>
            </tr>
            <c:forEach items="${buses}" var="bus">
                <tr>
                    <td>${bus.id}</td>
                    <td>${bus.plate}</td>
                    <td>
                        <c:if test="${bus.driverId>-1}">
                            ${bus.driverId}
                        </c:if>
                        <c:if test="${bus.driverId<0}">
                            <fmt:message key="nodriver"/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${bus.routeId>-1}">
                            ${bus.routeId}
                        </c:if>
                        <c:if test="${bus.routeId<0}">
                            <fmt:message key="noroute"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <div class="pagination">
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:if test="${page == i}">
                    <li style="display: inline-block">
                        <a class="active" style="pointer-events: none; cursor: default;">${i}</a>
                    </li>
                </c:if>
                <c:if test="${page != i}">
                    <li style="display: inline-block">
                        <a href="admin?page=${i}">${i}</a>
                    </li>
                </c:if>
            </c:forEach>
        </div>
</div>
</body>
</html>
