<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="static/main.css" rel="stylesheet">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<table>
    <thead>
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td>${meal.getDate()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td><a href="meals">Update</a> </td>
            <td><a href="meals?action=delete&id=${meal.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="updateCreate.jsp">Add</a>
</body>
</html>
