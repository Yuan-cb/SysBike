<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/28
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>单车管理系统——用户注册</title>
        <script type="text/javascript" src="./js/jquery.min.js"></script>
        <script type="text/javascript" src="./js/register-JS.js"></script>
        <style type="text/css">
            body {
                background-image: url(./image/backgroundImage_2.jpg);
                background-size: cover;
            }

            body>div{
                background:rgba(75,61,139,0.75);
                height: 500px;
                width: 600px;
                margin: auto;
                border-radius: 25px;
            }
            .sub{
                margin-left: 20px;
                margin-right: 20px;
                margin-top: 20px;
                width: 100px;
                height: 40px;
                border-radius: 50px;
            }
            span{
                display: inline-block;
                width: 150px;
                margin-top: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
    <div align="center">
        <h1  style="margin-top: 150px;color: aqua">用 户 注 册</h1>
        <form action="/SystemBike/RegisterServlet" method="post" onmousemove="registerSub()">
            <div class="register" style="margin-top: 50px;margin-left: 22%">
                <p align="left">
                    <span style="width: 120px;">用 户 名：</span>
                    <input type="text" id="name" name="name" maxlength="20" onblur="ajaxPrecess_msg(this,1)">
                    <span style="font-weight: normal" class="name_msg 1" id="1"></span>
                </p>
                <p align="left">
                    <span style="display: inline-block;width: 120px">邮 箱：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input type="text" id="email" name="email" maxlength="20" onblur="ajaxPrecess_msg(this,2)">
                    <span style="font-weight: normal" class="email_msg" id="2"></span>
                </p>
                <p align="left">
                    <span style="display: inline-block;width: 120px">密 码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input type="password" id="password" name="password" maxlength="20" onblur="ajaxPrecess_msg(this,3)">
                    <span style="font-weight: normal" class="password_msg" id="3"></span>
                </p>
                <p align="left">
                    <span style="display: inline-block;width: 120px">确认密码：</span>
                    <input type="password" id="password2" name="password2" maxlength="20" onblur="ajaxPrecess_msg(this,4)">
                    <span style="font-weight: normal" class="password2_msg" id="4"></span>
                </p>
                <p  align="left">
                    <span style="display: inline-block;width: 120px">电 话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input type="text" id="phone" name="phone" maxlength="11" onblur="ajaxPrecess_msg(this,5)">
                    <span style="font-weight: normal" class="phone_msg" id="5"></span>
                </p>
                <p align="left">
                    <input class="sub" type="submit" name="register" id="register" value="注  册">
                    <input class="sub" type="submit" name="cancel" value="取  消">
                </p>
            </div>
        </form>
    </div>

    </body>
</html>
