<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/1/3
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>单车管理--订单管理</title>
</head>
<script type="text/javascript">
    function beforePage(page) {
        var key = "${key}";
        if(key == null || key == ""){
            window.location = "OrderShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }else{
            window.location = "OrderShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }
    }

    function afterPage(page) {
        var key = "${key}";
        if(key == null || key == ""){
            window.location = "OrderShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }else{
            window.location = "OrderShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }
    }

    function back() {//结算
        var Id = prompt("请输入要结算的订单id", "");
        if(Id){
            window.location.href = "OrderManageServlet?manage=back&order_Id=" + Id;
        }
    }

    function deleteOrder() {
        var id = prompt("请输入要删除的订单id", "");
        if (id) {
            window.location.href = "OrderManageServlet?manage=delete&order_Id=" + id;
        } else
            alert("id不能为空");
    }
    function searchOrder() {
        var search_chose = prompt("请输入查找方式（订单id/单车id/用户id/日期/）", "");
        if(search_chose == "订单id"){
            var Order_Id = prompt("请输入订单id", "");
            if(Order_Id)
                window.location.href="OrderShowServlet?key=order_Id&value=" + Order_Id;
            return;
        }
        if(search_chose == "单车id"){
            var bike_Id = prompt("请输入单车id", "");
            if(bike_Id)
                window.location.href="OrderShowServlet?key=bike_Id&value=" + bike_Id;
            return;
        }
        if(search_chose == "用户id"){
            var user_Id = prompt("请输入用户id", "");
            if(user_Id)
                window.location.href="OrderShowServlet?key=user_Id&value=" + user_Id;
            return;
        }
        if(search_chose == "日期"){
            var date = prompt("请输入日期", "");
            if(date)
                window.location.href="OrderShowServlet?key=order_Date&value=" + date;
            return;
        }
        window.location.href="OrderShowServlet";
    }

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
    }

    .maintain {
        height: 50px;
        border-radius: 25px;
    }

    .maintain a {
        padding: 10px;
        background-color: greenyellow;
        margin: 20px;
        text-decoration: none;
        color: black;
    }

    .maintain a:hover{
        color: red;
    }

    .list {
        margin-bottom: 20px;
    }
    .page a {
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

</style>
</head>
<body onload="first()" style="background-image: url(./image/backgroundImage_2.jpg);background-size: cover;">
<div style="border-radius: 25px">
    <h2 align="center" style="margin-top: 50px">单车订单表</h2>
    <div class="maintain" align="center">
        <a href="javascript:back()">订单结算</a>
        <a href="javascript:deleteOrder()">删除订单</a>
        <a href="javascript:searchOrder()">查找订单</a>
    </div>
    <div align="center" class="list">
        <table border="1px">
            <thead align="center">
            <td width="100">订单id</td>
            <td width="100">单车id</td>
            <td width="100">用户id</td>
            <td width="100">状 态</td>
            <td width="150">下单日期</td>

            </thead>
            <c:if test="${not empty requestScope.pageBean.list}">

                <c:forEach items="${requestScope.pageBean.list}" var="order">
                    <tbody align="center">
                    <tr>
                        <td>${order.order_Id}</td>
                        <td>${order.bike_Id}</td>
                        <td>${order.user_Id}</td>
                        <td>${order.order_state}</td>
                        <td><fmt:formatDate value="${order.order_Date}" pattern="yyyy-MM-dd HH:MM:SS"/></td>
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
        <a href="adminMainPage.jsp">返 回</a>
    </div>
</div>
</body>
</html>
