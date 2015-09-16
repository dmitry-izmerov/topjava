<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal edit form</title>
</head>
<body>
    <h2>Meal edit form</h2>
    <a href="/topjava">To main</a><br/>

    <form action="./meals/edit" method="post">
        Desciption: <input name="description" type="text" value="${meal.description}"/><br/>
        Calories: <input name="calories" type="text" value="${meal.calories}"/><br/>
        Datetime: <input name="dateTime" type="text" value="${meal.dateTime}"/><br/>
        Is exceed: <input name="exceed" type="checkbox" ${ meal.exceed ? "checked" : ""}/><br/>
        <input name="id" type="hidden" value="${id}"/>
        <input type="submit" value="Edit"/>
    </form>
</body>
</html>
