<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/30
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统--管理员界面</title>
    <style type="text/css">
        body{
            background-image: url(./image/backgroundImage_1.jpg);
            background-size: cover;
        }
        body>div{
            background:rgba(113,222,195,0.75);
            height: 500px;
            width: 500px;
            margin: auto;
            border-radius: 25px;
        }
        a{
            text-decoration:none;
            font-size: 25px;
            color: black;
        }
        a:hover{
            color: red;
        }
        li{
            padding-top: 20px;
            padding-bottom: 20px;
        }

    </style>
</head>
<body>
<div align="center">
    <form action="" method="get">
        <div class="list" name="list" align="center">
            <h1>欢迎您--管理员</br>${session_user.name}</h1>
            <ul type="none" style="padding-left: 2%">
                <li class="users">
                    <a href="UsersShowServlet" >用户管理</a>
                </li>
                <li class="info">
                    <a href="AdminBikesShowServlet">单车管理</a>
                </li>
                <li class="maintain">
                    <a href="MaintainShowServlet">单车维护</a>
                </li>
                <li class="order">
                    <a href="OrderShowServlet">订单管理</a>
                </li>
                <li class="exit">
                    <a href="login.jsp">退出登录</a>
                </li>
            </ul>
        </div>
    </form>
</div>
</body>
</html>