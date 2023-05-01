<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 14.12.2022
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>

<%
  List<Book> books = (List<Book>) request.getAttribute("books");
%>

  <table border="1">
    <tr>
    <th>id</th>
    <th>title</th>
    <th>description</th>
    <th>price</th>
    <th>author</th>
    <th>profilePic</th>
    <th>action</th>
    </tr>

    <%
      for (Book book : books) {
    %>
      <tr>
    <td><%=book.getId()%></td>
    <td><%=book.getTitle()%></td>
    <td><%=book.getDescription()%></td>
    <td><%=book.getPrice()%></td>
    <td>
      <%
        if(book.getAuthors() != null && !book.getAuthors().isEmpty()) {
      %>
      <%
        for (Author author : book.getAuthors()) {
      %>
      <%=author.getName()%> <%=author.getEmail()%> ,
        <% } %>
      <% } else { %>
      <span style="color: red">there is no author</span>
      <% } %>
    </td>
    <td><%=book.getProfilePic()%></td>
    <td>
      <a href="/books/remove?bookId=<%=book.getId()%>">Remove</a> |
      <a href="/books/edit?bookId=<%=book.getId()%>">Edit</a>
    </td>
      </tr>
    <% } %>

  </table>

</body>
</html>
