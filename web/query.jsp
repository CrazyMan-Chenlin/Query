<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.9.14
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>query</title>
</head>
<body>

<form method="get" action="${pageContext.request.contextPath}/itemQueryServlet">
    <table align="center">
        <tr>
            <td>性别:&nbsp;<input type="text" size="2" name="gender" value="${queryBean.gender}"/>&nbsp;&nbsp;</td>
            <td> 年龄:&nbsp;<input type="text" size="2" name="age" value="${queryBean.age}"/></td>
            <td>&nbsp;&nbsp;&nbsp;<input type="submit" value="查询"/></td>
        </tr>
    </table>
</form>
<table border="1px" align="center" width="800px">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>电话</th>
        <th>邮箱</th>
        <th>QQ</th>
    </tr>
    <c:forEach items="${contactList}" var="contactList" varStatus="contactListStatus">

        <tr>
            <td>${contactListStatus.count}</td>
            <td>${contactList.name}</td>
            <td>${contactList.gender}</td>
            <td>${contactList.age}</td>
            <td>${contactList.phone}</td>
            <td>${contactList.email}</td>
            <td>${contactList.QQ}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
