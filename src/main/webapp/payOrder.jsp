<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.bean.Order" %>
<%@ page import="com.bean.Bike" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/1/6
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>订单结算</title>
    <style type="text/css">
        body>div{
            background:rgba(113,222,195,0.75);
            width: 50%;
            height: 60%;
            margin: auto;
            border-radius: 25px;
            padding-top: 100px;
        }
    </style>
</head>
<body style="background-image: url(./image/backgroundImage_1.jpg);background-size: cover;">
<div align="center">
    <p align="center">
        <p>订单id:${order.order_Id}</p>
        <p>用户id:${order.user_Id}</p>
        <p>单车id:${order.bike_Id}</p>
        <p>价格（RMB/H）:${bike.bike_price}</p>
        <p>下单时间:<fmt:formatDate value="${order.order_Date}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
        <p>支付费用:${pay}</p>
        <a href="PayServlet?pay=true">支 付</a>
        <a href="userMainPage.jsp">取 消</a>
    </p>
</div>
</body>
</html>
