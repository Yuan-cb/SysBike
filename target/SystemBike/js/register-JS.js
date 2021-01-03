//异步处理
function ajaxPrecess_msg(obj, num) {

    //文本框判断
    if (num == 1) {
        if (obj.value == "") {
            $(".name_msg").text("用户名不能为空！");
            $(".name_msg").css("color", "red");
        } else {
            if (document.getElementById("userButton").value != "修 改")
                ajaxProcess(obj, "name", ".name_msg");
            else {
                $(".name_msg").text("正确");
                $(".name_msg").css("color", "green");
            }
        }
        return;
    }
    if (num == 2) {
        if (obj.value == "") {
            $(".email_msg").text("email不能为空！");
            $(".email_msg").css("color", "red");
        } else {
            if (trueEmail(obj)) {
                if (document.getElementById("userButton").value != "修 改")
                    ajaxProcess(obj, "email", ".email_msg");
                else {
                    $(".email_msg").text("正确");
                    $(".email_msg").css("color", "green");
                }
            } else {
                $(".email_msg").text("email格式不正确！");
                $(".email_msg").css("color", "red");
            }
        }
        return;
    }
    if (num == 3) {
        if (obj.value == "") {
            $(".password_msg").text("密码不能为空！");
            $(".password_msg").css("color", "red");
        } else {
            if ((obj.value.length < 6 || obj.value.length > 20)) {
                $(".password_msg").text("密码长度为6-20！");
                $(".password_msg").css("color", "red");
            } else {
                $(".password_msg").text("正确");
                $(".password_msg").css("color", "green");
            }
        }
        return;
    }
    if (num == 4) {
        var password = document.getElementById("password").value;
        if (obj.value == password) {
            $(".password2_msg").text("正确");
            $(".password2_msg").css("color", "green");
        } else {
            $(".password2_msg").text("密码不一致！");
            $(".password2_msg").css("color", "red");
        }
        return;
    }
    if (num == 5) {
        if (obj.value == "") {
            $(".phone_msg").text("电话不能为空！");
            $(".phone_msg").css("color", "red");
        } else {
            if (obj.value.length != 11) {
                $(".phone_msg").text("电话长度为11位！");
                $(".phone_msg").css("color", "red");
            } else {
                if (document.getElementById("userButton").value != "修 改")
                    ajaxProcess(obj, "phone", ".phone_msg");
                else {
                    $(".phone_msg").text("正确");
                    $(".phone_msg").css("color", "green");
                }
            }
        }
    }
}

//进行Ajax请求和响应处理
function ajaxProcess(obj, key, show) {
    //获取请求数据
    var data = obj.value;
    //获取json对象
    var stringJson = '{"' + key + '": ""}';
    var json1 = JSON.parse(stringJson);//字符串转换为json对象
    json1[key] = data;

    $.getJSON("AjaxRegisterServlet", json1, function (json) {
        if (json == true) {
            $(show).text("正确");
            $(show).css("color", "green");
        } else {
            $(show).text("已存在");
            $(show).css("color", "red");
        }
    })
    $.get("AjaxRegisterServlet", json1, function (bool) {
        if (eval("(" + bool + ")") == true) {
            $(show).text("正确");
            $(show).css("color", "green");
        } else {
            $(show).text("已存在");
            $(show).css("color", "red");
        }
    })
}

//邮箱合法验证
function trueEmail(email) {
    var pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if (pattern.test(email.value)) {
        return true;
    } else {
        $(".email_msg").text("邮箱格式不正确！");
        $(".email_msg").css("color", "red");
        return false;
    }
}

//提交按钮
function registerSub() {
    for (var i = 1; i <= 5; i++) {
        if ($("#" + i).html() != "正确") {
            document.getElementById("register").disabled = true;
            return;
        }
    }
    document.getElementById("register").disabled = false;
}
