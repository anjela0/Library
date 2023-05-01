<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 13.12.2022
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Main page</title>
    <link href="/css/main.css" rel="stylesheet">
    <link href="/css/riot-slider.min.css" rel="stylesheet">
    <script src="/js/myJs.js"></script>
    <script src="/js/riot-slider.min.js"></script>
    <script src="/js/jquery-3.6.3.js"></script>
  </head>
  <body>

  <%
    User user = (User) session.getAttribute("user");
  %>

  <div id="mainDiv">
      <div>
        <ul class="riot-slider">
          <li><img src="/img/defaultProfilePic.png" /></li>
          <li><img src="/img/thumb (1).jpg" /></li>
          <li><img src="/img/thumbb-02 (1).jpg" /></li>
          <li>
            <p>hello world!</p>
          </li>
        </ul>
      </div>
    <div>
      <%
        if(user != null) {
      %>
      <a href="/books/add">Add book</a><br>
      <a href="/authors/add">Add author</a><br>
      <% } %>
      <a href="/authors">Show all authors</a>
      <a href="/books">Show all books</a>
      <a href="/login">Login</a><br>
      <a href="/users/add">Register</a><br>
    </div>
    <div style="width: 200px; margin: 0 auto;">
<%--      <button id="redBtn">Red</button>--%>
<%--      <button id="orangeBtn">Orange</button>--%>
<%--      <button id="blackBtn">Black</button>--%>
       <button class="colorBtn" onclick="onBtnClick(this)">Red</button>
      <button class="colorBtn" onclick="onBtnClick(this)">Orange</button>
      <button class="colorBtn" onclick="onBtnClick(this)">Black</button>

    </div>
    <div style="width: 200px; height: 200px; border: 1px solid rebeccapurple; background: aquamarine;" id="myDiv"></div>
  </div>

<%--  <script>--%>

<%--  // function onBtnClick(e) {--%>
<%--  //   let color = e.textContent;--%>
<%--  //   let myDiv = $('#myDiv');--%>
<%--  //   myDiv.css("background-color", color)--%>
<%--  // }--%>

<%--    // let colorBtn = document.getElementsByClassName("colorBtn");--%>
<%--    // colorBtn.onclick = function (e) {--%>
<%--    //   let color = e.target.textContent;--%>
<%--    //   let myDiv = document.getElementById("myDiv");--%>
<%--    //   myDiv.style.backgroundColor = color;--%>
<%--    // }--%>

<%--    // let redBtn = document.getElementById("redBtn");--%>
<%--    // redBtn.onclick = function () {--%>
<%--    // let myDiv = document.getElementById("myDiv");--%>
<%--    // myDiv.style.backgroundColor = 'red';--%>
<%--    // }--%>

<%--    // let orangeBtn = document.getElementById("orangeBtn");--%>
<%--    // orangeBtn.onclick = function () {--%>
<%--    // let myDiv = document.getElementById("myDiv");--%>
<%--    // myDiv.style.backgroundColor = 'orange';--%>
<%--    // }--%>

<%--    // let blackBtn = document.getElementById("blackBtn");--%>
<%--    // blackBtn.onclick = function () {--%>
<%--    // let myDiv = document.getElementById("myDiv");--%>
<%--    // myDiv.style.backgroundColor = 'black';--%>
<%--    // }--%>

<%--  </script>--%>

  </body>
</html>
