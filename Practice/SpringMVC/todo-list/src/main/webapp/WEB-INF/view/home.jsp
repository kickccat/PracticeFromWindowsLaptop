<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="io.sample.util.Mappings" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Todo List Application</title>
</head>
<body>
<div align="center">
    <c:url var="itemLink" value="${Mappings.ITEMS}"/>
    <h2><a href="${itemLink}">Show Todo Items</a></h2>
</div>
</body>
</html>
