<%--
  Created by IntelliJ IDEA.
  User: gghg0
  Date: 2019-06-12
  Time: 오후 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인창</title>
</head>

<body>
<form   method="post"  action="${contextPath}/test/login.do">
    <input  type="hidden"  name="email" value="hong@test.com" />
    <table width="400">
        <tr>
            <td>아이디 <input type="text" name="userID" size="10"></td>
        </tr>
        <tr>
            <td>이름 <input type="text" name="userName2" size="10"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="로그인">
                <input type="reset" value="다시입력">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
