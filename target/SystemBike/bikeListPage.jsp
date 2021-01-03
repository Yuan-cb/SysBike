<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/31
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<script type="text/javascript">
    function beforePage(page) {
        var key = "${key}";
        if(key == null || key == ""){
            window.location = "AdminBikesShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }else{
            window.location = "AdminBikesShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }
    }

    function afterPage(page) {
        var key = "${key}";
        if(key == null || key == ""){
            window.location = "AdminBikesShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }else{
            window.location = "AdminBikesShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
        }
    }

    function updateBike() {
        var bike_Id = prompt("请输入需要修改的单车id", "");
        if (bike_Id) {
            window.location.href = "BikesManageServlet?manage=update&bike_Id=" + bike_Id;
        } else
            alert("id不能为空");
    }

    function deleteBike() {
        var bike_Id = prompt("请输入要删除的单车id", "");
        if (bike_Id) {
            window.location.href = "BikesManageServlet?manage=delete&bike_Id=" + bike_Id;
        } else
            alert("id不能为空");
    }
    function searchBike() {
        var search_chose = prompt("请输入查找方式（id/种类/价格/状态/日期/）", "");
        if(search_chose == "id"){
            var id = prompt("请输入id", "");
            if(id)
                window.location.href="AdminBikesShowServlet?key=bike_Id&value=" + id;
            return;
        }
        if(search_chose == "种类"){
            var kind = prompt("请输入种类", "");
            if(kind)
                window.location.href="AdminBikesShowServlet?key=kind&value=" + kind;
            return;
        }
        if(search_chose == "价格"){
            var price = prompt("请输入价格", "");
            if(price)
                window.location.href="AdminBikesShowServlet?key=bike_price&value=" + price;
            return;
        }
        if(search_chose == "状态"){
            var state = prompt("请输入状态", "");
            if(state)
                window.location.href="AdminBikesShowServlet?key=bike_state&value=" + state;
            return;
        }
        if(search_chose == "日期"){
            var date = prompt("请输入日期", "");
            if(date)
                window.location.href="AdminBikesShowServlet?key=add_Date&value=" + date;
            return;
        }
        window.location.href="AdminBikesShowServlet";
    }

    function first() {
        if("${msg}" != "")
            alert("${msg}");
        else
            return;
    }

</script>
<head>
    <title>单车管理系统--单车管理</title>
    <link rel="stylesheet" href="./css/bikeListPage.css">
</head>
<body onload="first()" style="background-image: url(./image/backgroundImage_2.jpg);background-size: cover;">
<div style="border-radius: 25px">
    <h2 align="center" style="margin-top: 50px">单车信息表</h2>
    <div class="admitBike" align="center">
        <a href="BikesManageServlet?manage=add">添加单车</a>
        <a href="javascript:updateBike()">修改单车</a>
        <a href="javascript:deleteBike()">删除单车</a>
        <a href="javascript:searchBike()">查找单车</a>
    </div>
    <div align="center" class="list">
        <table border="1px">
            <thead align="center">
            <td width="50">id</td>
            <td width="100">种  类</td>
            <td width="100">价  格（RMB/H）</td>
            <td width="100">状  态</td>
            <td width="250">地  址</td>
            <td width="100">添加日期</td>

            </thead>
            <c:if test="${not empty requestScope.pageBean.list}">

                <c:forEach items="${requestScope.pageBean.list}" var="bike">
                    <tbody align="center">
                    <tr>
                        <td>${bike.bike_Id}</td>
                        <td>${bike.kind}</td>
                        <td>${bike.bike_price}</td>
                        <td>${bike.bike_state}</td>
                        <td>${bike.address}</td>
                        <td><fmt:formatDate value="${bike.add_Date}" pattern="yyyy-MM-dd"/></td>
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
