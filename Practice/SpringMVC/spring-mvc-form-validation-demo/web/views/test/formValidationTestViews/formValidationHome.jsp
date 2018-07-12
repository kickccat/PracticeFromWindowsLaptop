<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <spring:url value="/resources/test-main.css" var="testMainCSS"/>
    <link href="${testMainCSS}" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <title>Test Bed</title>
</head>
<body>
    <div align="center">
        <h1 class="ch1">Welcome to the Form Validation Test Bed</h1>
        <hr/>
        <h3>Please register yourself as your Organizations Representative.</h3>
        <%--@elvariable id="orgRep" type="io.sample.springdemo.domain.test"--%>
        <form:form action="registerOrgRep" modelAttribute="orgRep">
            <table>
                <tr>
                    <td><form:label path="firstName" cssClass="clabel">First Name</form:label></td>
                    <td><form:input path="firstName" placeholder="Enter First Name" cssClass="cinput"/></td>
                    <td><form:errors path="firstName" cssClass="cb" /></td>
                </tr>

                <tr>
                    <td></td>
                    <td align="center"><input type="submit" value="Submit" class="csubmit" /></td>
                </tr>
            </table>
            <hr/>
            <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
            <%--@elvariable id="formErrors" type="io.sample.springdemo.domain.test.OrganizationRepresentative"--%>
            <c:if test="${formErrors ne null}">
                <a href="${contextPath}/formValidationDemo/home" style="font-size: 17px;">Click here to start a fresh form!</a>
            </c:if>
        </form:form>
    </div>
</body>
</html>
