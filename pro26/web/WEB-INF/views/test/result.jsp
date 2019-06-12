<%--
  Created by IntelliJ IDEA.
  User: gghg0
  Date: 2019-06-12
  Time: 오후 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%
    request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
    <meta charset=UTF-8">
    <title>결과창</title>
</head>
<body>
<h1>아이디 : ${userID }</h1>
<h1>이름   : ${userName }</h1>
<%-- <h1>아이디 : ${info.userID }</h1>
<h1>이름   : ${info.userName }</h1> --%>
</body>
</html>
