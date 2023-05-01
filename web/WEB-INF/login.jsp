<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 16.12.2022
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
    <%
        String msg = (String) request.getAttribute("msg");
    %>
    <%
        if(msg != null) {
    %>
    <p style="color: red"><%=msg%></p>
    <% } %>

    <form action="/login" method="post">
        <input type="email" name="email" placeholder="Please input email">
        <input type="password" name="password" placeholder="Please input password">

        <input type="submit" name="login">
    </form>

</body>
</html>
