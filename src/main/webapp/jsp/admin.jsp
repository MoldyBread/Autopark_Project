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
    <title>Title</title>
</head>
<body>

<form action="" method="post">
    <input type="hidden" name="action" value="lang">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
    </select>
</form>

<form action="" method="post">
    <input type="hidden" name="action" value="logout">
    <input type="submit" value="<fmt:message key="logout"/>">
</form>

<h1><fmt:message key="welcome"/>, ${login}</h1>


<table border="1">
    <caption><fmt:message key="routes"/></caption>
    <tr>
        <th>Id</th>
        <th><fmt:message key="mileage"/></th>
        <th><fmt:message key="busesonroute"/></th>
    </tr>
    <c:forEach items="${routes}" var="route">
        <tr>
            <td>${route.id}</td>
            <td>${route.mileage}</td>
            <td>
                <c:forEach items="${route.busPlates}" var="bus">
                    ${bus}<br/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>

</table>


<form style="margin-top: 50px" action = "" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${bus.id}">
    <input type="submit" value=<fmt:message key="edit"/>>
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


</body>
</html>