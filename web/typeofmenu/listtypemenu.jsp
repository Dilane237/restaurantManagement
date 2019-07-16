<%--
  Created by IntelliJ IDEA.
  User: azangue
  Date: 7/11/19
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Types of Menus</title>

</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Image</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${type_of_menu}" var="typemenu">
        <tr>
            <td><c:out value="${typemenu.name}" /></td>
            <td><c:out value="${typemenu.description}" /></td>
            <td><c:out value="${typemenu.image}" /></td>
            <td><a href="UserController?action=edit&typemenuId=<c:out value="${typemenu.name}"/>">Update</a></td>
            <td><a href="UserController?action=delete&typemenuId=<c:out value="${typemenu.name}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="TypeMenuController?action=insert">Add User</a></p>
</body>
</html>
