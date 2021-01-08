<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/30
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统——${manage} 用 户</title>
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/register-JS.js"></script>
    <script type="text/javascript">
        //提交按钮
        function manageSub() {
            for (var i = 1; i <= 5; i++) {
                if ($("#" + i).html() != "正确") {
                    document.getElementById("userButton").disabled = true;
                    return;
                }
            }
            document.getElementById("userButton").disabled = false;
        }
        //初始显示
        function first() {
            var msg = "${msg}";
            if(msg != "")
                alert(msg);
            else
                return;
        }
    </script>
</head>
<body onload="first()">
<h1 class="h"  style="margin-top: 150px" align="center">用 户 ${manage}</h1>
<form action="UsersServlet" method="post" onmousemove="manageSub()">
    <div class="register" style="margin-top: 50px;margin-left: 41%">
        <p>
            <span style="display: inline-block;width: 120px;">用户ID：</span>
            <input type="text" id="Id" name="user_Id" value="${user.user_Id}" readonly="readonly">
        </p>
        <p>
            <span style="display: inline-block;width: 120px;">用 户 名：</span>
            <input type="text" id="name" name="name" maxlength="20" onblur="ajaxPrecess_msg(this,1)" value="${user.name}">
            <span class="name_msg 1" id="1"></span>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">邮 箱：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input type="text" id="email" name="email" maxlength="20" onblur="ajaxPrecess_msg(this,2)" value="${user.email}">
            <span class="email_msg" id="2"></span>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">密 码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input type="password" id="password" name="password" maxlength="20" onblur="ajaxPrecess_msg(this,3)" value="${user.password}">
            <span class="password_msg" id="3"></span>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">确认密码：</span>
            <input type="password" id="password2" name="password2" maxlength="20" onblur="ajaxPrecess_msg(this,4)">
            <span class="password2_msg" id="4"></span>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">电 话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input type="text" id="phone" name="phone" maxlength="11" onblur="ajaxPrecess_msg(this,5)" value="${user.phone}">
            <span class="phone_msg" id="5"></span>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">角 色：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input type="radio" id="role" name="role" value="user" checked="checked">用户
            <input type="radio" id="admin" name="role" value="admin">管理员
        </p>
        <p>
            <input type="submit" name="userButton" id="userButton" value="${manage}"  style="margin: 20px;width: 80px;height: 30px">
            <input type="submit" name="cancel" value="取  消"  style="margin: 20px;width: 80px;height: 30px">
        </p>
    </div>
</form>
</body>
</html>
