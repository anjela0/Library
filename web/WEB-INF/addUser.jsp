<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 16.12.2022
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
  <form action="/users/add" method="post">
    <input type="text" name="name" placeholder="Please input name">
    <input type="text" name="surname" placeholder="Please input surname">
    <input type="email" name="email" placeholder="Please input email">
    <input type="password" name="password" placeholder="Please input password">
    <input type="submit" value="Register">
  </form>
</body>
</html>
