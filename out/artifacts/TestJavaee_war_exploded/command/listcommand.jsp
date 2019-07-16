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
    <title>Show All Users</title>

</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>User Name</th>
        <th>Email</th>
        <th>Registration Date</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.uname}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><fmt:formatDate pattern="dd MMM,yyyy" value="${user.registeredon}" /></td>
            <td><a href="UserController?action=edit&userId=<c:out value="${user.uname}"/>">Update</a></td>
            <td><a href="UserController?action=delete&userId=<c:out value="${user.uname}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="UserController?action=insert">Add User</a></p>
</body>
</html>
