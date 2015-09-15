<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal create form</title>
</head>
<body>
    <h2>Meal create form</h2>
    <a href="/topjava">To main</a><br/>

    <form method="post">
        Desciption: <input name="description" type="text"/><br/>
        Calories: <input name="calories" type="text" /><br/>
        Datetime: <input name="dateTime" type="text" placeholder="YYYY-MM-DDTHH:MM"/><br/>
        Is exceed: <input name="exceed" type="checkbox" /><br/>
        <input type="submit" value="Create"/>
    </form>
</body>
</html>
