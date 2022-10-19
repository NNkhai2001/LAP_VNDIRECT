<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/2/2022
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" import="java.io.*"%>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%
Exception exp = (Exception) request.getAttribute("javax.servlet.error.exception");
exp.printStackTrace(new java.io.PrintWriter(out));
%>
</body>
</html>
