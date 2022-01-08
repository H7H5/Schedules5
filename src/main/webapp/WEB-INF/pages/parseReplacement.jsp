<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ParseReplacement</title>
</head><body>
<h2>Add</h2>
<c:url value="/addReplacement" var="add"/>
<a href="${add}">Add new replacement</a>

<h2>Replacements</h2>
<table>
    <tr>
        <th>id</th>
        <th>groupp</th>
        <th>year</th>
        <th>month</th>
        <th>day</th>
        <th>number</th>
        <th>old_name</th>
        <th>old_teacher1</th>
        <th>old_teacher2</th>
        <th>old_study</th>
        <th>new_name</th>
        <th>new_teacher1</th>
        <th>new_teacher2</th>
        <th>new_study</th>
    </tr>
    <c:forEach var="replacement" items="${replacementsList}">
        <tr>
            <td>${replacement.id}</td>
            <td>${replacement.groupp}</td>
            <td>${replacement.year}</td>
            <td>${replacement.month}</td>
            <td>${replacement.day}</td>
            <td>${replacement.number}</td>
            <td>${replacement.old_name}</td>
            <td>${replacement.old_teacher1}</td>
            <td>${replacement.old_teacher2}</td>
            <td>${replacement.old_study}</td>
            <td>${replacement.new_name}</td>
            <td>${replacement.new_teacher1}</td>
            <td>${replacement.new_teacher2}</td>
            <td>${replacement.new_study}</td>
            <td>
                <a href="/editReplacement/${replacement.id}">edit</a>
                <a href="/deleteReplacement/${replacement.id}">delete</a>
            </td>
        </tr>
    </c:forEach>

</table>
<a href="/JsonReplacement">JsonReplacement</a>
</body>
</html>