<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <spring:url value="/resources/test-main.css" var="testMainCSS"/>
    <link href="${testMainCSS}" rel="stylesheet"/>
    <title>Test Results</title>
</head>

<body>

<div align="center">
    <h1>@SessionAttribute Test Results</h1>
    <h3>${timeHeading}, ${durationHeading}</h3>
    <hr>
    <h3>No of page visits in this session: <c:out value="${visitorCount.count}"/> </h3>
    <h3>List of Visitors to this site</h3>

    <ul>
        <c:forEach var="visitor" items="${visitorData.visitors}">
            <li><b><c:out value="${visitor.name}"/>, <c:out value="${visitor.email}"/></b></li>
        </c:forEach>
    </ul>
    <br/>
    <br/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <a href="${contextPath}/visitorRegister/home" style="font-size: 20px;">Generate Another Visitor</a>
</div>

</body>

</html>
