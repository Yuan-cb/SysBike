<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/1/6
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统--单车租借</title>
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript">
        function beforePage(page) {
            var key = "${key}";
            if(key == null || key == ""){
                window.location = "AdminBikesShowServlet?flag=rent&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }else{
                window.location = "AdminBikesShowServlet?flag=rent&key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }
        }

        function afterPage(page) {
            var key = "${key}";
            if(key == null || key == ""){
                window.location = "AdminBikesShowServlet?flag=rent&flag=rent&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }else{
                window.location = "AdminBikesShowServlet?flag=rent&key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }
        }

        function searchBike() {
            var key = $('input[name="choose"]:checked').val();
            var value = $("#choose").val();
            if(value != ""){
                window.location.href="AdminBikesShowServlet?flag=rent&key=" + key + "&value=" + value;
                return;
            }
            window.location.href="AdminBikesShowServlet?flag=rent";
        }

        function rent(obj) {
            var id = $("#rent").val();
            window.location = "RentServlet?bike_Id=" + id;
        }

        function first() {
            if("${msg}" != "")
                alert("${msg}");
            else
                return;
        }

    </script>
    <link rel="stylesheet" href="./css/bikeListPage.css">
</head>
<body onload="first()" style="background-image: url(./image/backgroundImage_2.jpg);background-size: cover;">
<div style="border-radius: 25px">
    <h2 align="center" style="margin-top: 50px">单车信息表</h2>
    <div class="admitBike" align="center">
        <input name="choose" type="radio" value="bike_Id">Id
        <input name="choose" type="radio" value="kind">种类
        <input name="choose" type="radio" value="bike_price">价格
        <input name="choose" type="radio" value="bike_state">状态
        <input id="choose" type="text">
        <a href="javascript:searchBike()">查找单车</a>
        Id:  <input id="rent" type="text">
        <a class="rent" href="javascript:rent(this)">租  借</a>

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
                        <td class="id">${bike.bike_Id}</td>
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
        <a href="userMainPage.jsp">返 回</a>
    </div>
</div>
</body>
</html>