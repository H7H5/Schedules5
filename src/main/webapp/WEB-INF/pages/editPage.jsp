
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty lessonParseSchedule.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty lessonParseSchedule.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty lessonParseSchedule.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty lessonParseSchedule.name}">
    <c:url value="/edit" var="var"/>
</c:if>


<form action="${var}" method="POST">
    <c:if test="${!empty lessonParseSchedule.name}">
        <input type="hidden" name="id" value="${lessonParseSchedule.id}">
    </c:if>

    <label for="groupp">Group</label>
    <input type="text" name="groupp" id="groupp" value="${lessonParseSchedule.groupp}">

    <label for="day">Day</label>
    <input type="text" name="day" id="day" value="${lessonParseSchedule.day}">

    <label for="number">Number</label>
    <input type="text" name="number" id="number" value="${lessonParseSchedule.number}">

    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${lessonParseSchedule.name}">

    <label for="teacher">Teacher</label>
    <input type="text" name="teacher" id="teacher" value="${lessonParseSchedule.teacher}">

    <label for="teacher">Teacher2</label>
    <input type="text" name="teacher2" id="teacher2" value="${lessonParseSchedule.teacher2}">

    <label for="study">Study</label>
    <input type="text" name="study" id="study" value="${lessonParseSchedule.study}">

    <label for="numerator">Numerator</label>
    <input type="text" name="numerator" id="numerator" value="${lessonParseSchedule.numerator}">




    <c:if test="${empty lessonParseSchedule.name}">
        <input type="submit" value="Add new Lesson">
    </c:if>
    <c:if test="${!empty lessonParseSchedule.name}">
        <input type="submit" value="Edit Lesson">
    </c:if>
</form>
</body>
</html>