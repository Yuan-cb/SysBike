<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/28
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统——用户登录</title>
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript">
        <%--var reLogin = "${reLogin}";--%>
        <%--if (reLogin != "")--%>
        <%--    alert(reLogin);--%>
    </script>
    <style type="text/css">
        body {
            background-image: url(./image/backgroundImage_1.jpg);
            background-size: cover;
        }

        body>div{
            background:rgba(75,61,139,0.75);
            height: 300px;
            width: 500px;
            margin: auto;
            border-radius: 25px;
        }
        .sub{
            margin-left: 20px;
            margin-right: 20px;
            width: 100px;
            height: 40px;
            border-radius: 50px;
        }
        span{
            display: inline-block;
            width: 100px;
            font-weight: bold;
        }
        h1{
            margin-top: 200px;
            color: aqua;
        }
    </style>
</head>
<body>
<div align="center">
    <h1 align="center">用 户 登 录</h1>
    <form action="LoginServlet" method="post">
        <div class="login" style="margin-top: 50px;">
            <p style="height: 50px;" align="left">
                <span style="margin-left: 22%;color: aquamarine;">邮&nbsp;&nbsp;&nbsp;箱：</span>
                <input type="text" name="email" maxlength="20">
                <span style="display: inline;color: red" class="email_msg" id="1">
                    ${show1}
                </span>
            </p>
            <p style="height: 50px" align="left">
                <span style="color: aquamarine;margin-left: 22%">密&nbsp;&nbsp;&nbsp;码：</span>
                <input type="password" name="password" maxlength="20">
                <span style="display: inline;color: red" class="password_msg" id="2">
                    ${show2}
                </span>
            </p>
            <p>
                <input class="sub" type="submit" name="login" value="登  录">
                <input class="sub" type="submit" name="register" value="注  册">
            </p>
        </div>
    </form>
</div>
</body>
</html>
