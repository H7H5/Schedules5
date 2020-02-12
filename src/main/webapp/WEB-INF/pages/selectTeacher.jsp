
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url value="/schedules" var="schedules"/>
<a href="${schedules}">All Schedules</a>
<table>
    <tr>
        <th>teacher</th>
    </tr>
    <c:forEach var="teacher" items="${teacher}">
        <tr>
            <td>${teacher}</td>
            <td>
                <a href="/selectT/${teacher}">Select</a>
            </td>
            <td>
                <a href="/editTeacher/${teacher}">Edit</a>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
