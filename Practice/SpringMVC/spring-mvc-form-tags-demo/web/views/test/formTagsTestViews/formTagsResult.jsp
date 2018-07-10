<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <spring:url value="/resources/test-main.css" var="testMainCSS"/>
    <link href="${testMainCSS}" rel="stylesheet"/>
    <title>Form Tags Test Result</title>
</head>
<body>
<h1>Congratulations! You have registered your organization</h1>
<h2>Details below...</h2>
<hr/>
<div style="margin-left: 600px;width: auto;">
    <h3>Organization Name: <b>${orgReg.orgName}</b></h3>
    <h3>Country: <b>${orgReg.country}</b></h3>

    <h3>
        Turnover:
        <c:forEach var="entry" items="${turnoverList}">
            <c:if test="${orgReg.turnover eq entry.key}">
                <b>${entry.value}</b>
            </c:if>
        </c:forEach>
    </h3>

    <h3>
        Type:
        <c:forEach var="entry" items="${typeList}">
            <c:if test="${orgReg.type eq entry.key}">
                <b>${entry.value}</b>
            </c:if>
        </c:forEach>
    </h3>

    <h3>
        Oraganization Age:
        <c:forEach var="entry" items="${serviceLengthList}">
            <c:if test="${orgReg.serviceLength eq entry.key}">
                <b>${entry.value}</b>
            </c:if>
        </c:forEach>
    </h3>

    <h3>
        Registered Previously:
        <c:forEach var="entry" items="${registeredPreviouslyList}">
            <c:if test="${orgReg.registeredPreviously eq entry.key}">
                <b>${entry.value}</b>
            </c:if>
        </c:forEach>
    </h3>

    <h3>
        Optional Services signed up for:
        <c:forEach var="entry1" items="${subscriptionList}">
            <c:forEach var="entry2" items="${orgReg.optionalServices}">
                <c:if test="${entry2 eq entry1.key}">
                    <c:set var="optService" value="${optService}${entry1.value}, " scope="request"/>
                </c:if>
            </c:forEach>
        </c:forEach>
        <b>${optService.substring(0, optService.length()-2)}</b>
    </h3>

    <h3>
        Premium Services signed up for:
        <c:forEach var="entry1" items="${premiumServiceList}">
            <c:forEach var="entry2" items="${orgReg.premiumServices}">
                <c:if test="${entry2 eq entry1.key}">
                    <c:set var="premiumService" value="${premiumService}${entry1.value}, " scope="request"/>
                </c:if>
            </c:forEach>
        </c:forEach>
        <b>${premiumService.substring(0, premiumService.length()-2)}</b>
    </h3>

    <h3>Has Overseas Operations: ${orgReg.overseasOperations}</h3>

    <h3>
        Workforce Size:
        <c:forEach var="entry" items="${employeeStrengthList}">
            <c:if test="${orgReg.employeeStrength eq entry.key}">
                <b>${entry.value}</b>
            </c:if>
        </c:forEach>
    </h3>

    <h3>
        Like our Website:
        <c:choose>
            <c:when test="${orgReg.like eq 'yes'}"><b>Like</b></c:when>
            <c:otherwise><b>Do not like</b></c:otherwise>
        </c:choose>
    </h3>

</div>
</body>
</html>
