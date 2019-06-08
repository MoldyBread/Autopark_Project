<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 08.06.2019
  Time: 0:51
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
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>

<form action="" method="post">
    <input type="hidden" name="action" value="back">
    <input type="submit" value=<fmt:message key="back"/>>
</form>

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
</body>
</html>
