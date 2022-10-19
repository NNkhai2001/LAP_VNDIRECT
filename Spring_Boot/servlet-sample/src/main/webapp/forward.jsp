<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/23/2022
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ISP Example</title>
</head>
<body>
<%
    String agent = request.getHeader("User-Agent");
    if (agent.contains("Cococ")) { %>
<jsp:forward page="path.jsp"/>
<% } else { %>
<jsp:forward page="include.jsp"></jsp:forward>
<%}%>
</body>
</html>
