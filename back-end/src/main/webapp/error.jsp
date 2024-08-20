<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>An error occurred</h1>
    <!-- 예외 메시지 -->
    <p><%= (request.getAttribute("errorMessage") != null) ? request.getAttribute("errorMessage") : "An unexpected error occurred." %></p>
    <p>Please contact the administrator if this problem persists.</p>
</body>
</html>
