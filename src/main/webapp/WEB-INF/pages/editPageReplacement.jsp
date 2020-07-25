
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty replacement.number}">
        <title>AddReplacement</title>
    </c:if>
    <c:if test="${!empty replacement.number}">
        <title>EditReplacement</title>
    </c:if>
</head>
<body>
<c:if test="${empty replacement.number}">
    <c:url value="/addReplacement" var="var"/>
</c:if>
<c:if test="${!empty replacement.number}">
    <c:url value="/editReplacement" var="var"/>
</c:if>


<form action="${var}" method="POST">
    <c:if test="${!empty replacement.number}">
        <input type="hidden" name="id" value="${replacement.id}">
    </c:if>

    <label for="groupp">Group</label>
    <input type="text" name="groupp" id="groupp" value="${replacement.groupp}">

    <label for="year">Year</label>
    <input type="text" name="year" id="year" value="${replacement.year}">

    <label for="month">Month</label>
    <input type="text" name="month" id="month" value="${replacement.month}">

    <label for="day">Day</label>
    <input type="text" name="day" id="day" value="${replacement.day}">

    <label for="number">Number</label>
    <input type="text" name="number" id="number" value="${replacement.number}">

    <label for="old_name">Old_name</label>
    <input type="text" name="old_name" id="old_name" value="${replacement.old_name}">

    <label for="old_teacher1">Old_teacher1</label>
    <input type="text" name="old_teacher1" id="old_teacher1" value="${replacement.old_teacher1}">

    <label for="old_teacher2">Old_teacher2</label>
    <input type="text" name="old_teacher2" id="old_teacher2" value="${replacement.old_teacher2}">

    <label for="old_study">Old_Study</label>
    <input type="text" name="old_study" id="old_study" value="${replacement.old_study}">

    <label for="new_name">New_name</label>
    <input type="text" name="new_name" id="new_name" value="${replacement.new_name}">

    <label for="new_teacher1">New_teacher1</label>
    <input type="text" name="new_teacher1" id="new_teacher1" value="${replacement.new_teacher1}">

    <label for="new_teacher2">New_teacher2</label>
    <input type="text" name="new_teacher2" id="new_teacher2" value="${replacement.new_teacher2}">

    <label for="new_study">New_Study</label>
    <input type="text" name="new_study" id="new_study" value="${replacement.new_study}">


    <c:if test="${empty replacement.number}">
        <input type="submit" value="Add new Replacement">
    </c:if>
    <c:if test="${!empty replacement.number}">
        <input type="submit" value="Edit Replacement">
    </c:if>
</form>
</body>
</html>
