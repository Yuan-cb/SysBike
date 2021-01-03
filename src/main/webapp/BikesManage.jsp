<%@ page import="com.bean.Bike" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/1/1
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>单车管理系统——${manage} 单 车</title>
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript">
        //内容判断
        function Precess_msg(obj, num) {
            if($(obj).val() == ""){
                $("#" + num).text("内容不能为空");
                $("#" + num).css("color", "red");
            }else{
                if((num == 2 || num == 4) && !(/(^[1-9]\d*$)/.test($(obj).val()))){
                    $("#" + num).text("请输入正整数！");
                    $("#" + num).css("color", "red");
                }else
                    $("#" + num).text("");
            }
        }
        function manageSub() {//提交
            for(var i=1; i<=3; i++){
                if($("#" + i).text() != ""){
                    document.getElementById("bikeButton").disabled = true;
                    return;
                }
            }
            document.getElementById("bikeButton").disabled = false;
        }
        //下拉框的值
        function getSelectValue(obj) {
            var sText = obj.options[obj.options.selectedIndex].innerHTML; //这是取文本内容
            if(sText == "维护中"){
                $(".maintain").css("display", "block");
            }
        }
        //初始显示
        function first() {
            var manage = "${manage}";
            if(manage == "添 加"){
                $(".Id").css("display", "none");
            }else{
                $("#bike_state").val("${bike.bike_state}");//直接在下拉菜单中追加值即可。
            }


            //消息
            var msg = "${msg}";
            if (msg != "")
                alert(msg);
            else
                return;

        }
    </script>
</head>
<body onload="first()">
<h1 class="h"  style="margin-top: 150px" align="center">单 车 ${manage}</h1>
<form action="BikesServlet" method="post" onmousemove="manageSub()">
    <div class="register" style="margin-top: 50px;margin-left: 41%">
        <p class="Id">
            <span style="display: inline-block;width: 120px;">单车ID：</span>
            <input type="text" id="Id" name="bike_Id" value="${bike.bike_Id}" readonly="readonly">
        </p>
        <p>
            <span style="display: inline-block;width: 120px;">种 类：</span>
            <input type="text" id="kind" name="kind" onblur="Precess_msg(this,1)" value="${bike.kind}">
            <span class="kind_msg" id="1"></span>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">价 格：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input type="text" id="bike_price" name="bike_price" onblur="Precess_msg(this,2)" value="${bike.bike_price}">
            <span class="price_msg" id="2" style="color: green">(正整数)</span>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">状 态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <select name="bike_state" id="bike_state" onchange="getSelectValue(this);">

                <%
                    String manage = request.getParameter("manage");
                    String bike_state = ((Bike)request.getAttribute("bike")).getBike_state();
                    if(manage.equals("add") ) {
                %>
                <option value="空闲">空闲</option>
                <%
                }else if(manage.equals("update")) {
                        if (bike_state.equals("已租借")) {
                %>
                <option value="已租借">已租借</option>
                <%
                    } else if (bike_state.equals("维护中")) {
                %>
                <option value="维护中">维护中</option>
                <%
                    } else {
                %>
                <option value="空闲">空闲</option>
                <option value="已租借">已租借</option>
                <option value="报修">报修</option>
                <option value="维护中">维护中</option>
                %>
                <%
                    }
                }
                %>

            </select>
        </p>
        <p>
            <span style="display: inline-block;width: 120px">地 址：</span>
            <input type="text" id="address" name="address" onblur="Precess_msg(this,3)"  value="${bike.address}">
            <span class="address_msg" id="3"></span>
        </p>
        <p class="maintain" style="display: none">
            <span style="display: inline-block;width: 120px">维护价格</span>
            <input name="maintain_price" type="text" onblur="Precess_msg(this,4)">
            <span class="price_msg" id="4"></span>
        </p>
        <p class="maintain" style="display: none">
            <span style="display: inline-block;width: 120px">损坏原因</span>
            <textarea name="break_reason" rows="4" cols="50"></textarea>
        </p>
        <p>
            <input type="submit" name="bikeButton" id="bikeButton" value="${manage}"  style="margin: 20px;width: 80px;height: 30px">
            <input type="submit" name="cancel" value="取  消"  style="margin: 20px;width: 80px;height: 30px">
        </p>
    </div>
</form>
</body>
</html>
