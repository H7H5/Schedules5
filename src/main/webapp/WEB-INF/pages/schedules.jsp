<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>LESSONS</title>
</head>
<body>
<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new lesson</a>

<h2>Lessons</h2>
<table>
    <tr>
        <th>id</th>
        <th>group</th>
        <th>day</th>
        <th>number</th>
        <th>name</th>
        <th>teacher</th>
        <th>teacher2</th>
        <th>study</th>
        <th>numerator</th>
    </tr>
    <c:forEach var="lesson" items="${lessonsList}">
        <tr>
            <td>${lesson.id}</td>
            <td>${lesson.groupp}</td>
            <td>${lesson.day}</td>
            <td>${lesson.number}</td>
            <td>${lesson.name}</td>
            <td>${lesson.teacher}</td>
            <td>${lesson.teacher2}</td>
            <td>${lesson.study}</td>
            <td>${lesson.numerator}</td>
            <td>
            <a href="/edit/${lesson.id}">edit</a>
            <a href="/delete/${lesson.id}">delete</a>
        </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>