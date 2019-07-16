    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%--
          Created by IntelliJ IDEA.
          User: azangue
          Date: 7/12/19
          Time: 11:04 AM
          To change this template use File | Settings | File Templates.
        --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Menu page</title>
    </head>

    <body>
        <div class="container">
            <header class="text-center">Today's Menu</header>
        </div>
        <div class="container">
            <h2>BreakFast</h2>
            <hr>
            <c:forEach items="${menu}" var="menu">
                <div class="col-md-4">
                    <div><c:out value="${menu.image}"></c:out></div>
                    <div><c:out value="${menu.price}"></c:out></div>
                    <div><c:out value="${menu.description}"></c:out></div>
                </div>
            </c:forEach>
        </div>
    </body>

    </html>