<%--
  Created by IntelliJ IDEA.
  User: Sonali
  Date: 1/11/2025
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Customer</title>
</head>

<body>
<h1>Save Customer</h1>

<%
    String message = request.getParameter("message");
    String error = request.getParameter("error");

%>

<%
    if (message != null) {
%>

<div style="color: green"><%= message%></div>

<%
    }
%>

<%
    if (error != null) {
%>

<div style="color: red"><%= error%></div>

<%
    }
%>

<form action="customer-save" method="post">
    <label for="nameL">Name:</label><br>
    <input type="text" id="nameL" name="customer_name"><br><br>

    <label for="addressL">Address:</label><br>
    <input type="text" id="addressL" name="customer_address" ><br><br>

    <label for="emailL">Email:</label><br>
    <input type="text" id="emailL" name="customer_email"><br><br>

    <button type="submit">Save customer</button>

</form>


</body>
</html>
