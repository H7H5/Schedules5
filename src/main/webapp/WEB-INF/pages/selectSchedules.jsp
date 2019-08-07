
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url value="/schedules" var="schedules"/>
<a href="${schedules}">All Schedules</a>

<c:url value="/select" var="var"/>
<form action="${var}" method="GET">
    <label>Group</label>
    <input type="hidden" name="groupp" value="groups">
    <input type="submit" value="Select">
</form>
<c:url value="/select" var="var"/>
<form action="${var}" method="GET">
    <label>Teacher</label>
    <input type="hidden" name="groupp" value="teachers">
    <input type="submit" value="Select">
</form>
<c:url value="/select" var="var"/>
<form action="${var}" method="GET">
    <label>GroupJSON</label>
    <input type="hidden" name="groupp" value="groups_json">
    <input type="submit" value="Select">
</form>
<c:url value="/select" var="var"/>
<form action="${var}" method="GET">
    <label>TeachersJSON</label>
    <input type="hidden" name="groupp" value="teachers_json">
    <input type="submit" value="Select">
</form>
</body>
</html>
