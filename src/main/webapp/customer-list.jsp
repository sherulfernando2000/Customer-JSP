<%@ page import="lk.ijse.customerjsp.CustomerDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Sonali
  Date: 1/11/2025
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>

</head>

<body>
  <h1>Customer List</h1>
<%
    List<CustomerDTO> dataList = (List<CustomerDTO>) request.getAttribute("customers");
    if (dataList != null && !dataList.isEmpty()) {
%>


  <table border="1">
      <thead>
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Address</td>
                <td>Email</td>
            </tr>
      </thead>

      <tbody>
        <%
            for (CustomerDTO customerDTO:dataList){

        %>
        <tr>
            <td><%=customerDTO.getId()%></td>
            <td><%=customerDTO.getName()%></td>
            <td><%=customerDTO.getAddress()%></td>
            <td><%=customerDTO.getEmail()%></td>
        </tr>

        <%
          }
        %>

      </tbody>


  </table>


<%
    }
%>



</body>
</html>
