<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/23/2022
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Date" %>
<html>
<head>
    <title>Welcome to our Website</title>
</head>
<body
                         <%--Example1--%>
<marquee>
    <font size="3" color="red">Hello World <%=request.getParameter("j_username")%></font>
<h1>${say}</h1>
    <font color="#0000FF">Session Id: <%=session.getId() %></font>
</marquee>
<font color="#0000FF">Hostname: <%=request.getRemoteHost()%></font>
<%--                            &lt;%&ndash;Example2_Date_Sum&ndash;%&gt;--%>
<%--<h1>Displaying sum</h1>--%>
<%--<%--%>
<%--    int sum = 0;--%>
<%--    for (int i = 0; i < 25; i++) {--%>
<%--        sum += i ;--%>
<%--    }--%>
<%--%>--%>
<%--Sum of number is: <%=sum %>--%>
<%--                         &lt;%&ndash;Example3&ndash;%&gt;--%>
<%--<%response.setHeader("Refresh","6"); %>--%>
<%--<LI>Today's date is:<%=new Date().toString() %></LI>--%>
<%--<LI>Session Id:<%=session.getId() %></LI>--%>
</body>

</html>
