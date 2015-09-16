<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meal list</title>
    <style>
        .table {
            border-collapse: collapse;
        }

        .table td, .table th {
            padding: 5px;
            text-align: center;
            border: 1px #ccc solid;
        }

        .is-exceed {
            background-color: #ffcfcc;
        }

        .is-not-exceed {
            background-color: #9eaa85;
        }

    </style>
</head>
<body>
<h2>Meal list</h2>
<a href="/topjava">To main</a><br/>
<a href="./meals/create/">Create new meal</a><br/><br/>
<table class="table">
    <thead>
        <tr>
            <th>#</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Date</th>
            <th>isExceed</th>
            <th>Edit link</th>
            <th>Remove link</th>
        </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <c:set var="id" value="${meals.indexOf(meal)}"/>

        <tr class = "${meal.cssClass}">
            <td><c:out value="${id}" /></td>
            <td><c:out value="${meal.description}" /></td>
            <td><c:out value="${meal.calories}" /></td>
            <td><c:out value="${meal.dateTime}" /></td>
            <td><c:out value="${meal.exceed}" /></td>
            <td><a href="./meals/edit/${id}">edit</a></td>
            <td><a href="./meals/remove/${id}">remove</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
