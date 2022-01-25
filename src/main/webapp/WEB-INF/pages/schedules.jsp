<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>LESSONS</title>
</head>
<body>
<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new lessonParseSchedule</a>

<h2>Lessons</h2>
<table>
    <tr>
        <th>id</th>
        <th>groupp</th>
        <th>night</th>
        <th>number</th>
        <th>name</th>
        <th>teacher</th>
        <th>teacher2</th>
        <th>study</th>
        <th>numerator</th>
    </tr>
    <c:forEach var="lessonParseSchedule" items="${lessonsList}">
        <tr>
            <td>${lessonParseSchedule.id}</td>
            <td>${lessonParseSchedule.groupp}</td>
            <td>${lessonParseSchedule.day}</td>
            <td>${lessonParseSchedule.number}</td>
            <td>${lessonParseSchedule.name}</td>
            <td>${lessonParseSchedule.teacher}</td>
            <td>${lessonParseSchedule.teacher2}</td>
            <td>${lessonParseSchedule.study}</td>
            <td>${lessonParseSchedule.numerator}</td>
            <td>
            <a href="/edit/${lessonParseSchedule.id}">edit</a>
            <a href="/delete/${lessonParseSchedule.id}">delete</a>
        </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>