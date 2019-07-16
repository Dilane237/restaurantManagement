<%--
  Created by IntelliJ IDEA.
  User: azangue
  Date: 7/11/19
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new type menu</title>
</head>
<body>
<form method="POST" action='TypeMenuController' name="frmAddTypeMenu">
    <% String action = request.getParameter("action");
        System.out.println(action);
    %>
    <% if (action.equalsIgnoreCase("edit")) {%>
    Name : <input type="text" name="name"
                  value="<c:out value="${typemenu.name}" />"/> <br />
    <%} else {%>
    Description : <input type="text" name="description"
                         value="<c:out value="${typemenu.description}" />" /> <br />
    <%}%>
    Image : <input type="file" name="image"
                   value="<c:out value="${typemenu.image}" />" /> <br />

    <input  type="submit" value="Submit" />
</form>
</body>
</html>
