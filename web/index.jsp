<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.9.11
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Contact System</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/ListContactServlet" id="queryForm">
    <table align="center">
        <tr>
            <input type="hidden" id="currentPage" name="currentPage" value="${pageBean.currentPage}"/>
            <input type="hidden" id="pageSizeId" name="pageSize" value="${pageBean.pageSize}"/>
            <td>姓名：<input type="text" size="4" name="name" value="${param['name']}"/>&nbsp;</td>
            <td>男<input type="radio" size="4" name="gender" value="男" <c:if
                    test="${param['gender'].equals('男')}"> checked="checked"</c:if>/>
                女<input type="radio" size="4" name="gender" value="女" <c:if
                        test="${param['gender'].equals('女')}"> checked="checked"</c:if>/>&nbsp;
            </td>
            <td>年龄：<input type="text" size="2" name="age" value="${param['age']}"/> &nbsp;</td>
            <td>电话：<input type="text" size="10" name="phone" value="${param['phone']}"/> &nbsp;</td>
            <td>邮箱：<input type="text" size="14" name="email" value="${param['email']}"/>&nbsp;</td>
            <td>QQ：<input type="text" size="10" name="qq" value="${param['qq']}"> &nbsp;</td>
            <%--
            select 提交参数的名字 写在select这个父级节点中
            而不是option
            --%>
            <td>
                <select name="deptId">
                    <option value="0">不选</option>
                    <c:forEach items="${allDepartment}" var="allDepartment">
                        <option value="${allDepartment.deptId}" <c:if
                                test="${param['deptId']==allDepartment.deptId}"> selected="selected"</c:if> > ${allDepartment.name}</option>
                    </c:forEach>
                </select>
                &nbsp;
            </td>
            <td><input type="submit" value="查询" onclick="changeCurrentPage()"/></td>
        </tr>

    </table>

</form>
<div>
    <table border="1px" align="center" width="800px">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>电话</th>
            <th>邮箱</th>
            <th>QQ</th>
            <th>部门名</th>
        </tr>
        <c:forEach items="${pageBean.contactList}" var="contactList" varStatus="contactListStatus">
            <tr>
                <td>${contactListStatus.count}</td>
                <td>${contactList.name}</td>
                <td>${contactList.gender}</td>
                <td>${contactList.age}</td>
                <td>${contactList.phone}</td>
                <td>${contactList.email}</td>
                <td>${contactList.QQ}</td>
                <td>${contactList.department.name}</td>
            </tr>
        </c:forEach>
        <tr>
            <%--
                     需求：
                         1） 如果当前页是首页，那么不能点击“首页”和“上一页”，否则可以点击
                         2) 如果当前页是末页，那么不能点击“下一页”和“末页”，否则可以点击
                      --%>
            <td align="center" colspan="8">
                <c:choose>
                    <%-- 如果当前页等于末页，则显示末页与下一页--%>
                    <c:when test="${pageBean.currentPage==pageBean.firstPage}">
                        <a href="javascript:void(0)" onclick="toPage(${pageBean.nextPage})"> 下一页&nbsp;</a>
                        <a href="javascript:void(0)" onclick="toPage(${pageBean.totalPage})"> 末页&nbsp;</a>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <%--如果当前页等于末页，则显示首页与上一页--%>
                            <c:when test="${pageBean.currentPage==pageBean.totalPage}">
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.firstPage})">首页&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.prePage})">上一页&nbsp;</a>
                            </c:when>
                            <%--否则显示全部--%>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.firstPage})">首页&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.prePage})">上一页&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.nextPage})"> 下一页&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.totalPage})"> 末页&nbsp;</a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                当前是第${pageBean.currentPage}页/共${pageBean.totalPage}页
                一共有${pageBean.totalCount}条数据&nbsp;
                每页显示<input type="text" size="1" id="pageSize" value="${pageBean.pageSize}" onblur="atuoSubmit()">条数据
                &nbsp;跳转到<input type="text" size="1" id="curPage" onblur="jumpPage()"/> 页
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">
    function atuoSubmit() {
        var pageSize = document.getElementById("pageSize").value;
        var reg = /^[0-9]{1,2}$/;
        if (reg.test(pageSize)) {
            document.getElementById("pageSizeId").value = pageSize;
            document.getElementById("currentPage").value = 1;
            var queryForm = document.getElementById("queryForm");
            queryForm.submit();
        } else {
            alert("输入有误，请输入1-2位数字")
        }

    }

    function jumpPage() {
        var currentPage = document.getElementById("curPage").value;
        var reg = /^[0-9]{1,2}$/;
        if (reg.test(currentPage)) {
            if (currentPage >${pageBean.totalPage}) {
                alert("请输入小于总页面的页数")
            } else {

                document.getElementById("currentPage").value = currentPage;
                var queryForm = document.getElementById("queryForm");
                queryForm.submit();
            }

        } else {
            alert("输入有误，请输入1-2位数字");
        }

    }

    function toPage(pageNo) {
        //将当前页设置为跳转的页面
        document.getElementById("currentPage").value = pageNo;
        //javascript 提交表单
        var queryForm = document.getElementById("queryForm");
        queryForm.submit();
    }

    function changeCurrentPage() {
        document.getElementById("currentPage").value = 1;
    }
</script>
</html>
