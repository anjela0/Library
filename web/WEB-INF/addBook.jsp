<%@ page import="model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 13.12.2022
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>

<%--<%--%>
<%--    List<Author> authors = (List<Author>) request.getAttribute("authors");--%>
<%--%>--%>

Please input book's data
<form action="/books/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="please input title" /><br>
    <input type="text" name="description" placeholder="please input description" /><br>
    <input type="text" name="price" placeholder="please input price" /><br>
<%--    <%--%>
<%--        for (Author author : authors) {--%>
<%--    %>--%>
<%--    <input type="number" name="author_id" placeholder="please input author id" /><br>--%>
<%--    <% } %>--%>
    <input type="file" name="profilePic" /><br>

<%--    <select name="authorId">--%>
<%--        <%--%>
<%--            for (Author author : authors) {--%>
<%--        %>--%>

<%--        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getEmail()%></option>--%>

<%--        <% } %>--%>
<%--    </select>--%>

    <input type="submit" value="Register">
</form>
</body>
</html>
