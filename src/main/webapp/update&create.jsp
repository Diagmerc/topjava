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
    Date <input type="datetime-local" name="date" value="${meal.dateTime}">
    <br>
    Description<input name="description" value="${meal.description}">
    <br>
    Calories<input name="calories" value="${meal.calories}">
    <br>
    <button type="submit">Save</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
