<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 13.12.2022
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add author</title>
</head>
<body>

<form action="/authors/add" method="post" enctype="multipart/form-data">
  <input type="text" name="name" placeholder="please input name" /><br>
  <input type="text" name="surname" placeholder="please input surname" /><br>
  <input type="email" name="email" placeholder="please input email" /><br>
  <input type="number" name="age" placeholder="please input age" /><br>
  <input type="file" name="profilePic" /><br>
  <input type="submit" value="Add">
</form>

</body>
</html>
