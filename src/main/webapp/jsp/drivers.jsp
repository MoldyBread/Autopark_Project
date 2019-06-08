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
    <caption><fmt:message key="drivers"/></caption>
    <tr>
        <th>Id</th>
        <th><fmt:message key="firstname"/></th>
        <th><fmt:message key="lastname"/></th>
        <th><fmt:message key="accepted"/></th>
        <th></th>
    </tr>
    <c:forEach items="${drivers}" var="driver">
    <tr>
        <td>${driver.id}</td>
        <td>${driver.name}</td>
        <td>${driver.surname}</td>
        <td>
            <c:choose>
                <c:when test="${driver.accepted}">
                    <fmt:message key="yes"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="no"/>
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            <c:if test="${driver.accepted}">
                <form action="" method="post">
                    <input type="hidden" name="action" value="free">
                    <input type="hidden" name="id" value=${driver.id}>
                    <input type="submit" value=<fmt:message key="free"/>>
                </form>
            </c:if>
        </td>
    </tr>
    </c:forEach>

</body>
</html>
