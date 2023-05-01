<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 16.12.2022
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
  <%
    Book book = (Book) request.getAttribute("book");
      List<Author> authors = (List<Author>) request.getAttribute("authors");
  %>
Please update book`s data
    <form action="/books/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="bookId" value="<%=book.getId()%>">
        <input type="text" name="title" value="<%=book.getTitle()%>">
        <input type="text" name="description" value="<%=book.getDescription()%>">
        <input type="number" name="price" value="<%=book.getPrice()%>">
        <input type="text" name="profilePic" value="<%=book.getProfilePic()%>">
        <select>
            <%
                for (Author author : authors) {
                    if(author.equals(book.getAuthor())) {
            %>
            <option selected value="<%=author.getId()%>"> <%=author.getName()%> <%=author.getEmail()%> </option>
            <% } else { %>
            <option  value="<%=author.getId()%>"> <%=author.getName()%> <%=author.getEmail()%> </option>
            <% }
            } %>
        </select>
    </form>
</body>
</html>
