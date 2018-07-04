<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>@ModelAttribute test</title>
    <style>
        h1, h2 {
            color: #08298A;
            text-align: center;
        }
    </style>
</head>
<body>
<h1>@ModelAttribute Test Results</h1>
<hr/>
<div align="center">
    <c:if test="${testData5A != null && testData5B != null}">
        <h3 style="color: #DF0101">City: ${testData5A}</h3>
        <h3 style="color: #DF0101">Zip Code: ${testData5B}</h3>
    </c:if>
</div>
</body>
</html>
