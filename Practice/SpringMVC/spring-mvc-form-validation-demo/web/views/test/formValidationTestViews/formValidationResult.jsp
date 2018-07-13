<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <spring:url value="/resources/test-main.css" var="testMainCSS"/>
    <link href="${testMainCSS}" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <title>Form validation test result</title>
</head>
<body>
<h1 class="ch1">Congratulations. You are now a registered representative of your organization!</h1>
<h2 class="ch2">Details are below...</h2>
<hr/>
<div style="margin-left: 600px;width: auto;">
    <h3>Name: <b class="cb">${orgRep.firstName} ${orgRep.lastName}</b></h3>
    <h3>Age: <b class="cb">${orgRep.age}</b></h3>
    <h3>Zip Code: <b class="cb">${orgRep.zipCode}</b></h3>
</div>
<hr/>
<div align="center">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <a href="${contextPath}/formValidationDemo/home" style="font-size: 17px;">Click here to go back to the home page</a>
</div>
</body>
</html>
