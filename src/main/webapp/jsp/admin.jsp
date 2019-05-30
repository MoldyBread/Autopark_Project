<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 23.05.2019
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome, ${login}</h1>



<table border="1">
    <caption>Routes</caption>
    <tr>
        <th>Id</th>
        <th>Mileage</th>
        <th>Buses on route</th>
    </tr>
    <c:forEach items="${routes}" var="route">
        <tr><td>${route.id}</td><td>${route.mileage}</td><td>
        <c:forEach items="${route.busPlates}" var="bus">
                ${bus}<br/>
        </c:forEach>
        </td></tr>
    </c:forEach>

</table>

<table border="1">
    <caption>Buses</caption>
    <tr>
        <th>Id</th>
        <th>Plate</th>
        <th>Driver id</th>
        <th>Route id</th>
    </tr>
    <c:forEach items="${buses}" var="bus">
        <tr><td>${bus.id}</td><td>${bus.plate}</td><td>
            <c:if test="${bus.driverId>-1}">
                ${bus.driverId}
            </c:if>
            <c:if test="${bus.driverId<0}">
                No driver
            </c:if>
        </td>
            <td>
                <c:if test="${bus.routeId>-1}">
                    ${bus.routeId}
                </c:if>
                <c:if test="${bus.routeId<0}">
                    No route
                </c:if>
        </td></tr>
    </c:forEach>

</table>


</body>
</html>
