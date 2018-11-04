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
            <td>������<input type="text" size="4" name="name" value="${param['name']}"/>&nbsp;</td>
            <td>��<input type="radio" size="4" name="gender" value="��" <c:if
                    test="${param['gender'].equals('��')}"> checked="checked"</c:if>/>
                Ů<input type="radio" size="4" name="gender" value="Ů" <c:if
                        test="${param['gender'].equals('Ů')}"> checked="checked"</c:if>/>&nbsp;
            </td>
            <td>���䣺<input type="text" size="2" name="age" value="${param['age']}"/> &nbsp;</td>
            <td>�绰��<input type="text" size="10" name="phone" value="${param['phone']}"/> &nbsp;</td>
            <td>���䣺<input type="text" size="14" name="email" value="${param['email']}"/>&nbsp;</td>
            <td>QQ��<input type="text" size="10" name="qq" value="${param['qq']}"> &nbsp;</td>
            <%--
            select �ύ���������� д��select��������ڵ���
            ������option
            --%>
            <td>
                <select name="deptId">
                    <option value="0">��ѡ</option>
                    <c:forEach items="${allDepartment}" var="allDepartment">
                        <option value="${allDepartment.deptId}" <c:if
                                test="${param['deptId']==allDepartment.deptId}"> selected="selected"</c:if> > ${allDepartment.name}</option>
                    </c:forEach>
                </select>
                &nbsp;
            </td>
            <td><input type="submit" value="��ѯ" onclick="changeCurrentPage()"/></td>
        </tr>

    </table>

</form>
<div>
    <table border="1px" align="center" width="800px">
        <tr>
            <th>���</th>
            <th>����</th>
            <th>�Ա�</th>
            <th>����</th>
            <th>�绰</th>
            <th>����</th>
            <th>QQ</th>
            <th>������</th>
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
                     ����
                         1�� �����ǰҳ����ҳ����ô���ܵ������ҳ���͡���һҳ����������Ե��
                         2) �����ǰҳ��ĩҳ����ô���ܵ������һҳ���͡�ĩҳ����������Ե��
                      --%>
            <td align="center" colspan="8">
                <c:choose>
                    <%-- �����ǰҳ����ĩҳ������ʾĩҳ����һҳ--%>
                    <c:when test="${pageBean.currentPage==pageBean.firstPage}">
                        <a href="javascript:void(0)" onclick="toPage(${pageBean.nextPage})"> ��һҳ&nbsp;</a>
                        <a href="javascript:void(0)" onclick="toPage(${pageBean.totalPage})"> ĩҳ&nbsp;</a>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <%--�����ǰҳ����ĩҳ������ʾ��ҳ����һҳ--%>
                            <c:when test="${pageBean.currentPage==pageBean.totalPage}">
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.firstPage})">��ҳ&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.prePage})">��һҳ&nbsp;</a>
                            </c:when>
                            <%--������ʾȫ��--%>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.firstPage})">��ҳ&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.prePage})">��һҳ&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.nextPage})"> ��һҳ&nbsp;</a>
                                <a href="javascript:void(0)" onclick="toPage(${pageBean.totalPage})"> ĩҳ&nbsp;</a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                ��ǰ�ǵ�${pageBean.currentPage}ҳ/��${pageBean.totalPage}ҳ
                һ����${pageBean.totalCount}������&nbsp;
                ÿҳ��ʾ<input type="text" size="1" id="pageSize" value="${pageBean.pageSize}" onblur="atuoSubmit()">������
                &nbsp;��ת��<input type="text" size="1" id="curPage" onblur="jumpPage()"/> ҳ
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
            alert("��������������1-2λ����")
        }

    }

    function jumpPage() {
        var currentPage = document.getElementById("curPage").value;
        var reg = /^[0-9]{1,2}$/;
        if (reg.test(currentPage)) {
            if (currentPage >${pageBean.totalPage}) {
                alert("������С����ҳ���ҳ��")
            } else {

                document.getElementById("currentPage").value = currentPage;
                var queryForm = document.getElementById("queryForm");
                queryForm.submit();
            }

        } else {
            alert("��������������1-2λ����");
        }

    }

    function toPage(pageNo) {
        //����ǰҳ����Ϊ��ת��ҳ��
        document.getElementById("currentPage").value = pageNo;
        //javascript �ύ��
        var queryForm = document.getElementById("queryForm");
        queryForm.submit();
    }

    function changeCurrentPage() {
        document.getElementById("currentPage").value = 1;
    }
</script>
</html>
