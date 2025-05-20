
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String message = (String) request.getAttribute("message"); %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1 style="color: red; ">Error</h1>
<p><%= message %></p>
</body>
</html>
