<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/25/2022
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <title>View Users</title>
</head>
<body>
<sql:setDataSource var="userdb" url="jdbc:derby:C://Temp//userdb"  user="" password="" />

<sql:query var="result" dataSource="${userdb}">
    select * from hanoi_user
</sql:query>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
        <th>Del</th>
    </tr>
    <c:forEach var="row" items="${result.rows}">
        <tr>
            <td>${row.username}</td>
            <td><c:out value="${row.password}"/></td>
            <td>${row.email}</td>
            <td><a href="Save?action=del&user=${row.username}">Del</a></td>

        </tr>

    </c:forEach>
</table>
<div><a href="register.html">Add New</a> </div>

</body>
</html>
