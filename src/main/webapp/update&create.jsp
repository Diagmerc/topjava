<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    Date <input name="date" value="${meal.date}">
    <br>
    Description<input name="description" value="${meal.description}">
    <br>
    Calories<input name="calories" value="${meal.calories}">
    <br>
    <button type="submit">Save</button>
</form>
</body>
</html>
