<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <spring:url value="/resources/test-main.css" var="testMainCSS"/>
    <link href="${testMainCSS}" rel="stylesheet"/>
    <title>Test bed</title>
</head>
<body>
<div align="center">
    <h1>Welcome to the Form Tags Test Bed</h1>
    <hr/>
    <h3>Register your Organization with us!</h3>
    <%--@elvariable id="orgRegistration" type="io.sample.springdemo"--%>
    <form:form action="registerOrg" modelAttribute="orgRegistration">
        <table>
            <tr>
                <td><form:label path="orgName">Name of Organization</form:label></td>
                <td><form:input path="orgName" placeholder="Enter Organization name"/></td>
            </tr>
            <tr>
                <td><form:label path="country">Country</form:label></td>
                <td>
                    <form:select path="country">
                        <form:option value="NONE">-------SELECT----------</form:option>
                        <form:option value="Australia">Australia</form:option>
                        <form:option value="India">India</form:option>
                        <form:option value="Sweden">Sweden</form:option>
                        <form:option value="United States">United States</form:option>
                        <form:option value="Egypt">Egypt</form:option>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td><form:label path="turnover">Turnover</form:label></td>
                <td><form:select path="turnover" items="${turnoverList}"/></td>
            </tr>

            <tr>
                <td><form:label path="type">Type</form:label></td>
                <td>
                    <form:select path="type">
                        <form:option value="NONE">-------SELECT--------</form:option>
                        <form:options items="${typeList}"/>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td><form:label path="serviceLength">Organization Age</form:label></td>
                <td><form:select path="serviceLength" items="${serviceLengthList}"/></td>
            </tr>

            <tr>
                <td><form:label path="registeredPreviously">Registered Previously?</form:label></td>
                <td><form:checkbox path="registeredPreviously" items="${registeredPreviouslyList}"/></td>
            </tr>

            <tr>
                <td><form:label path="optionalServices">Optional Services Subscriptions:</form:label></td>
                <td>
                    <form:checkbox path="optionalServices" value="emailService"/>${subscriptionList.emailService}
                    <form:checkbox path="optionalServices" value="promotionService"/>${subscriptionList.promotionService}
                    <form:checkbox path="optionalServices" value="newsLetterService"/>${subscriptionList.newsLetterService}
                </td>
            </tr>

            <tr>
                <td><form:label path="premiumServices">Premium Services</form:label></td>
                <td><form:checkboxes path="premiumServices" items="${premiumServiceList}"/></td>
            </tr>

            <tr>
                <td><form:label path="overseasOperations">Overseas Operations?</form:label></td>
                <td>
                    <form:radiobutton path="overseasOperations" value="Yes"/>Yes
                    <form:radiobutton path="overseasOperations" value="No"/>No
                </td>
            </tr>

            <tr>
                <td><form:label path="employeeStrength">Workforce Size</form:label></td>
                <td><form:radiobuttons path="employeeStrength" items="${employeeStrengthList}"/></td>
            </tr>

            <tr>
                <td><form:label path="like">Like our website?</form:label></td>
                <td><form:checkbox path="like" value="yes"/>Like</td>
            </tr>

            <tr>
                <td></td>
                <td align="center"><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
