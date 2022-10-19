<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/30/2022
  Time: 8:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<html>
<head>

    <title>ListStudent</title>
    <script>
        function view(id) {
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("GET", "json/" + id, true);
            xmlHttp.onload = function () {
                if (this.status != 200) return;
                // console.log(this.responseText);
                var student = JSON.parse(this.responseText);
                document.getElementById('content').innerHTML = 'Name: ' + student.name
                    + '<img src="/student/avatar/' + student.id + '" height="500px"/>';
                var dialog = document.getElementById('viewStudent');
                dialog.show();
            };
            xmlHttp.send();
        }
    </script>
</head>
<body>
<tiles:insertDefinition name="studentTemplate">
    <tiles:putAttribute name="body">
        <h2>List of Students</h2>
        <form method="get" action="list">
            <table border="1">
                <tr>
                    <td colspan="4"><input type="text" name="q" size="30"/></td>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Age</td>
                    <td>Delete</td>
                    <td>Edit</td>
                </tr>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td><a href="delete/${student.id}">Delete</a></td>
                        <td><a href="edit/${student.id}">Edit</a></td>
                        <td><a href="javascript:view(${student.id})">${student.name}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </tiles:putAttribute>
</tiles:insertDefinition>
<dialog id="viewStudent" style="width: 50%;border: 1px dotted blue;">
    <div id="content"></div>
    <button id="hide">Close</button>
</dialog>
<script>
    (function () {
        var dialog = document.getElementById('viewStudent');
        document.getElementById('hide').onclick = function () {
            dialog.close();
        };
    })();
</script>
</body>
</html>
