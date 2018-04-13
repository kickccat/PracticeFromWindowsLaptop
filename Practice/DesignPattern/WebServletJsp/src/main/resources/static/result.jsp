<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h1 align="center">Beer Recommendations JSP</h1>
<p>
    <%
        List styles = (List) request.getAttribute("styles");
        Iterator it = styles.iterator();
        while (it.hasNext()) {
            System.out.println("<br />try: " + it.next());
        }
    %>
</p>
</body>
</html>
