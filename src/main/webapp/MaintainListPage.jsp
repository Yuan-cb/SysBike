<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/1/2
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统--维护管理</title>
    <script type="text/javascript">
        function beforePage(page) {
            var key = "${key}";
            if(key == null || key == ""){
                window.location = "MaintainShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }else{
                window.location = "MaintainShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }
        }

        function afterPage(page) {
            var key = "${key}";
            if(key == null || key == ""){
                window.location = "MaintainShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }else{
                window.location = "MaintainShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
            }
        }

        function back() {
            var bike_Id = prompt("请输入要重新出租的单车id", "");
            if(bike_Id){
                window.location.href = "MaintainManageServlet?manage=back&bike_Id=" + bike_Id;
            }
        }

        function deleteBike() {
            var bike_Id = prompt("请输入要销毁的单车id", "");
            if (bike_Id) {
                window.location.href = "MaintainManageServlet?manage=delete&bike_Id=" + bike_Id;
            } else
                alert("id不能为空");
        }
        function searchMaintain() {
            var search_chose = prompt("请输入查找方式（维护id/单车id/价格/日期/）", "");
            if(search_chose == "维护id"){
                var maintain_Id = prompt("请输入维护id", "");
                if(maintain_Id)
                    window.location.href="MaintainShowServlet?key=maintain_Id&value=" + maintain_Id;
                return;
            }
            if(search_chose == "单车id"){
                var bike_Id = prompt("请输入单车id", "");
                if(bike_Id)
                    window.location.href="MaintainShowServlet?key=bike_Id&value=" + bike_Id;
                return;
            }
            if(search_chose == "价格"){
                var price = prompt("请输入价格", "");
                if(price)
                    window.location.href="MaintainShowServlet?key=maintain_price&value=" + price;
                return;
            }
            if(search_chose == "日期"){
                var date = prompt("请输入日期", "");
                if(date)
                    window.location.href="MaintainShowServlet?key=maintain_Date&value=" + date;
                return;
            }
            window.location.href="MaintainShowServlet";
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
    <h2 align="center" style="margin-top: 50px">单车维护表</h2>
    <div class="maintain" align="center">
        <a href="javascript:back()">修复出租</a>
        <a href="javascript:deleteBike()">销毁单车</a>
        <a href="javascript:searchMaintain()">查找信息</a>
    </div>
    <div align="center" class="list">
        <table border="1px">
            <thead align="center">
            <td width="50">维护id</td>
            <td width="100">单车id</td>
            <td width="100">维护价格（RMB/H）</td>
            <td width="100">损坏原因</td>
            <td width="100">维护日期</td>

            </thead>
            <c:if test="${not empty requestScope.pageBean.list}">

                <c:forEach items="${requestScope.pageBean.list}" var="maintain">
                    <tbody align="center">
                    <tr>
                        <td>${maintain.maintain_Id}</td>
                        <td>${maintain.bike_Id}</td>
                        <td>${maintain.maintain_price}</td>
                        <td>${maintain.break_reason}</td>
                        <td><fmt:formatDate value="${maintain.maintain_Date}" pattern="yyyy-MM-dd"/></td>
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
