<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="save" modelAttribute="product" method="post">
    <form:hidden path="id"/>
    Name <form:input path="name"/>
    <br>
    Price <form:input path="price"/>
    <br>
    Count <form:input path="count"/>
    <br>
    <input type="submit" value="Ok">
    <a href="${pageContext.request.contextPath}/">Back</a>
</form:form>
</body>
</html>
