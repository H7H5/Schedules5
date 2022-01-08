
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
        <th>groupp</th>
    </tr>
    <c:forEach var="grup" items="${grup}">
        <tr>
            <td>${grup}</td>
            <td>
                <a href="/select/${grup}">Select</a>
            </td>
            <td>
                <a href="/editGroup/${grup}">Edit</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>






