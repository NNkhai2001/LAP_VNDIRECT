<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/23/2022
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>El Example</title>
</head>
<body>
<h1>El Example</h1>
<c:forEach var="i" begin="1" end="8" step="1">
    ${i}:
    <c:choose>
        <c:when test="${i %2==0}">Hello ${i}<br></c:when>
        <c:otherwise>not found!<br></c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${param.person != null}">
    <c:out value="hello ${param.person}" escapeXml="true"></c:out>

</c:if>
</body>
</html>
