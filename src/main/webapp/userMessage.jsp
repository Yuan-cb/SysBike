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
    <script type="text/javascript" src="./js/jquery.min.js"></script>

    <style type="text/css">
        body{
            background-image: url("image/backgroundImage_3.jpg");
            background-size: cover;
        }
        div{
            background:rgba(113,222,195,0.75);
            width: 50%;
            height: 80%;
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
        .a{
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
            <input type="text" value="${userSearch.user_Id}" id="user_Id" readonly="readonly">
        </p>
        <p>用户名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" value="${userSearch.name}" id="name" disabled>
        </p>
        <p>邮 箱：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" value="${userSearch.email}" id="email" disabled>
        </p>
        <p>密 码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" value="${userSearch.password}" id="password" disabled>
        </p>
        <p>电 话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" value="${userSearch.phone}" id="phone" disabled>
        </p>
        <p>注册时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <fmt:formatDate value="${userSearch.creat_Date}" pattern="yyyy-MM-dd"/></p>
        <p>角 色：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${userSearch.role}</p>
        <p>租车订单id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${order_Id}</p>
        <a class="self a" href="javascript:update(this)">修 改</a>
        <a class="a" href="javascript:exit()">返 回</a>
</div>
<script type="text/javascript">
    if("${manage}" == "self")
        $(".self").css("display", "inline-block");
    else
        $(".self").css("display", "none");
    function update(obj) {
        if($(".self").text() == "修 改"){
            $("input[type='text']").attr("disabled", false);
            $(".self").text("保 存");
        }else{
            var name = $("#name").val();
            var email = $("#email").val();
            var password = $("#password").val();
            var phone = $("#phone").val();
            window.location = "SelfServlet?name=" + name +
                    "&email=" + email + "&password=" + password + "&phone=" + phone;
        }
    }
    function exit() {
        if("${manage}" == "search"){
            window.location = "UsersShowServlet";
            return;
        }
        if("${manage}" == "self"){
            window.location = "userMainPage.jsp";
        }
    }
</script>
</body>
</html>
