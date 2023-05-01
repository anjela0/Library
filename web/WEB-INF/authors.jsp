<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 14.12.2022
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>

<%
  List<Author> authors = (List<Author>) request.getAttribute("authors");
  Book book = (Book) session.getAttribute("book");
//  List<Book> books = (List<Book>) request.getAttribute("books");
  String keyword = (String) request.getAttribute("keyword");
%>

<form action="/authors" method="get">
  <input type="text" name="keyword" value="<%=keyword%>">
  Min price:
  <input type="number" name="minPrice" />
  Max price:
  <input type="number" name="maxPrice" />

  <input type="submit" value="search">
</form>

  <table border="1">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>age</th>
    <th>profile_pic</th>
    <th>action</th>
    <th>join</th>
  </tr>
    <tr>
    <%
      for (Author author : authors) {
    %>

    <td><%=author.getId()%></td>
    <td><%=author.getName()%></td>
    <td><%=author.getSurname()%></td>
    <td><%=author.getEmail()%></td>
    <td><%=author.getAge()%></td>
    <td><%=author.getProfilePic()%></td>
    <td>
      <a href="/authors/remove?authorId=<%=author.getId()%>">Remove</a> |
      <a href="/authors/edit?authorId=<%=author.getId()%>">Edit</a>
    </td>
    <td>

<%--      <%--%>
<%--        for (Book book : books) {--%>
<%--      %>--%>
      <%
        if(author.getBooks().contains(book)) {
      %>
      <a href="/authors/cancel?authorId=<%=author.getId()%>">Cancel</a>
      <%break;%>
      <% } else { %>
      <a href="/authors/join?authorId=<%=author.getId()%>">Join</a>
      <%break;%>
      <% } %>
<%--      <%  } %>--%>

    </td>
    <% } %>
  </tr>
  </table>

</body>
</html>
