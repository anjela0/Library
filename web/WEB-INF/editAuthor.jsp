<%@ page import="java.util.List" %>
<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 16.12.2022
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Author</title>
</head>
<body>
    <%
        Author authors = (Author) request.getAttribute("author");
    %>
    Please update authors' data
    <form action="/authors/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="authorId" value="<%=authors.getId()%>">
        <input type="text" name="name" value="<%=authors.getName()%>">
        <input type="text" name="surname" value="<%=authors.getSurname()%>">
        <input type="email" name="email" value="<%=authors.getEmail()%>">
        <input type="number" name="age" value="<%=authors.getAge()%>">
        <input type="text" name="profilePic" value="<%=authors.getProfilePic()%>">
        <input type="submit" value="Update">
    </form>
</body>
</html>
