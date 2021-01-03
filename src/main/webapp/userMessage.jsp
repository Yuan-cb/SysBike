<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/1/2
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统--用户信息</title>
    <style type="text/css">
        body{
            background-image: url("image/backgroundImage_3.jpg");
            background-size: cover;
        }
        div{
            background:rgba(113,222,195,0.75);
            width: 50%;
            height: 500px;
            margin: auto;
            border-radius: 25px;
        }
        div>*{
            display: block;
            padding-left: 250px;
        }
        p{
            padding-top: 20px;
            margin: auto;
        }
        a{
            display: inline-block;
            padding: 5px;
            background-color: white;
            margin-left: 300px;
            margin-top: 50px;
            text-decoration: none;
            color: black;
            border-radius: 25px;
            width: 80px;
            text-align: center;
        }
        a:hover{
            color: red;
        }
    </style>
</head>
<body>
<div>
        <p style="padding-top: 50px">用户Id: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${userSearch.user_Id}</p>
        <p>用户名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${userSearch.name}</p>
        <p>邮 箱：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${userSearch.email}</p>
        <p>电 话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${userSearch.phone}</p>
        <p>注册时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <fmt:formatDate value="${userSearch.creat_Date}" pattern="yyyy-MM-dd"/></p>
        <p>角 色：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${userSearch.role}</p>
        <p>租车订单id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${order_Id}</p>
        <a href="UsersShowServlet">返 回</a>
</div>
</body>
</html>
