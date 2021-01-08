<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/30
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统--用户管理</title>
    <script>
        function beforePage(page) {
            window.location = "UsersShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }
        function afterPage(page) {
            window.location = "UsersShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }
        function east() {
            window.location = "adminMainPage.jsp";
        }
        function updateUser() {
            var user_Id;
            user_Id = prompt("请输入需要修改的用户id", "");
            if(user_Id){
                window.location.href="UsersManageServlet?manage=update&user_Id="+user_Id;
            }else
                alert("id不能为空");
        }
        function deleteUser() {
            var user_Id = prompt("请输入要删除的用户id", "");
            if(user_Id){
                window.location.href="UsersManageServlet?manage=delete&user_Id="+user_Id;
            }else
                alert("id不能为空");
        }
        function searchUser() {
            var search_msg = prompt("请输入要查询用户的(name/email/phone)", "");
            if(search_msg){
                window.location.href="UsersManageServlet?manage=search&search_msg="+search_msg;
            }else
                alert("内容不能为空");
        }
        //初始化
        function first() {
            if("${msg}" != "")
                alert("${msg}");
            else
                return;
        }
    </script>
    <style type="text/css">

        body>div{
            background:rgba(113,222,195,0.75);
            width: 80%;
            height: 80%;
            margin: auto;
            border-radius: 25px;
        }

        .admitUser {
            height: 50px;
            border-radius: 25px;
        }

        .admitUser a{
            padding: 10px;
            background-color: greenyellow;
            margin: 20px;
            text-decoration: none;
            color: black;
        }

        .admitUser a:hover{
            color: red;
        }

        .list {
            margin-bottom: 20px;
        }

        .page a{
            display: inline-block;
            padding: 5px;
            background-color: white;
            margin: 20px;
            text-decoration: none;
            color: black;
            border-radius: 25px;
            width: 80px;
        }
        .page a:hover{
            color: red;
        }
        .name{
            color: black;
        }
        .name:hover{
            color: red;
        }

    </style>

</head>
<body onload="first()" style="background-image: url(./image/backgroundImage_1.jpg);background-size: cover;">
<div>
    <h2 align="center" style="margin-top: 50px">用户信息表</h2>
    <div class="admitUser" align="center">
        <a href="UsersManageServlet?manage=add">添加用户</a>
        <a href="javascript:updateUser()">修改用户</a>
        <a href="javascript:deleteUser()">删除用户</a>
        <a href="javascript:searchUser()">查找用户</a>
    </div>
    <div align="center" class="list">
        <table border="1px">
            <thead align="center">
            <td width="80">id</td>
            <td width="100">用户名</td>
            <td width="200">邮&nbsp;&nbsp;箱</td>
            <td width="150">电  话</td>
            <td width="120">注册时间</td>
            <td width="80">角  色</td>

            </thead>
            <c:if test="${not empty requestScope.pageBean.list}">

                <c:forEach items="${requestScope.pageBean.list}" var="user">
                    <tbody align="center">
                    <tr>
                        <td>${user.user_Id}</td>
                        <td><a class="name" href="UsersManageServlet?manage=search&search_msg=${user.name}">${user.name}</a></td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td><fmt:formatDate value="${user.creat_Date}" pattern="yyyy-MM-dd"/></td>
                        <td>${user.role}</td>
                    </tr>
                    </tbody>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <div align="center" class="page">
        <a href="javascript:beforePage('${pageBean.beforePage}')">上一页</a>
        共${pageBean.totalRows}行 共${pageBean.totalPages}页
        每页${pageBean.pageSize}行 第${pageBean.currentPage}页
        <a href="javascript:afterPage('${pageBean.afterPage}')">下一页</a>
        <a href="javascript:east()">返 回</a>
    </div>
</div>

</body>
</html>
