<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/1/6
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统--用户界面</title>
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
<script type="text/javascript">
    if("${msg}" != "")
        alert("${msg}");
</script>
<div align="center">
    <form action="" method="get">
        <div class="list" name="list" align="center">
            <h1>欢迎您--${session_user.name}</h1>
            <ul type="none" style="padding-left: 2%">
                <li class="users">
                    <a class="name" href="UsersManageServlet?manage=self&search_msg=${session_user.name}" >信息管理</a>
                </li>
                <li class="bike">
                    <a href="AdminBikesShowServlet?flag=rent">单车租借</a>
                </li>
                <li class="order">
                    <a href="PayServlet">订单结算</a>
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