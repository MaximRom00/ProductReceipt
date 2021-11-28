<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/static/main.css?<?php echo time(); ?>" />
</head>
<body>
<div>
    <table border="1" class="tabb">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Count</th>
            <th>Operations</th>
        </tr>
        <c:forEach var="prod" items="${products}">
            <tr>
                <td>${prod.id}</td>
                <td>${prod.name}</td>
                <td>${prod.price}</td>
                <td>${prod.count}</td>

                <td>
                    <a href="${pageContext.request.contextPath}/update?id=${prod.id}">Update</a>
                    <a href="${pageContext.request.contextPath}/delete?id=${prod.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="/add"> <button id="button1" type="button">Add</button> </a>
</div>
</body>
</html>
