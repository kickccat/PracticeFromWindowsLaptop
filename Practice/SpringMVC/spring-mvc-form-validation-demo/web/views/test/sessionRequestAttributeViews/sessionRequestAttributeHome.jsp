<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <spring:url value="/resources/test-main.css" var="testMainCSS"/>
    <link href="${testMainCSS}" rel="stylesheet"/>
    <%-- <link href="<c:url value="/resources/test-main.css" />" rel="stylesheet"> --%>
    <title>Test bed</title>
</head>
<body>
<div align="center">
    <h1>@SessionAttribute Test Bed</h1>
    <hr>
    <%--@elvariable id="visitorStats" type="io.sample.springdemo.domain.test"--%>
    <form:form action="visitor" modelAttribute="visitorStats">
        <table>
            <tr>
                <td><form:label path="currentVisitorName">Name</form:label></td>
                <td><form:input path="currentVisitorName"/></td>
            </tr>
            <tr>
                <td><form:label path="currentVisitorEmail">Email</form:label></td>
                <td><form:input path="currentVisitorEmail"/></td>
            </tr>
            <tr>
                <td align="center"><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
