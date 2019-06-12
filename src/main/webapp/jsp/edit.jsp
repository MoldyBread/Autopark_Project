<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 31.05.2019
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TextBundle"/>

<html>
<head>
    <style>
        <%@include file="stylesheet/edit.css" %>
    </style>
    <title>Edit</title>
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
    <input type="hidden" name="action" value="back">
    <input class="button" type="submit" value=<fmt:message key="back"/>>
</form>

<div class="info">
    <c:if test="${success == 1}">
        <p style="color: green"><fmt:message key="success"/></p>
    </c:if>

    <form action="" method="post">
        <input type="hidden" name="action" value="save">
        <table style="with: 50%; margin-left: 250px; font-size: 25px;">
            <tr class="highlight"><td></td><td></td></tr>
            <tr style="margin-top: 20px">
                <td><fmt:message key="busid"/>:</td>
                <td><select class="slc" name="busId">
                    <c:forEach items="${buses}" var="bus">
                        <option value=${bus.id}>${bus.id}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr class="highlight"><td></td><td></td></tr>
            <tr style="margin-top: 20px">
                <td><fmt:message key="driverid"/>:</td>
                <td><select class="slc" name="drivers">
                    <option value="-1"><fmt:message key="nodriver"/></option>
                    <c:forEach items="${drivers}" var="driver">
                        <option value=${driver.id}>${driver.id}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr class="highlight"><td></td><td></td></tr>
            <tr style="margin-top: 20px">
                <td><fmt:message key="routeid"/>:</td>
                <td><select class="slc" name="routes">
                    <option value="-1"><fmt:message key="noroute"/></option>
                    <c:forEach items="${routes}" var="route">
                        <option value=${route.id}>${route.id}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
        </table>
        <input class="button" type="submit" value=<fmt:message key="save"/>>
    </form>
</div>

</body>


</html>
