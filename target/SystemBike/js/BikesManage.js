//内容判断
function Precess_msg(obj, num) {
    if ($(obj).val() == "") {
        $("#" + num).text("内容不能为空");
        $("#" + num).css("color", "red");
    } else {
        if ((num == 2 || num == 4) && !(/(^[1-9]\d*$)/.test($(obj).val()))) {
            $("#" + num).text("请输入正整数！");
            $("#" + num).css("color", "red");
        } else
            $("#" + num).text("");
    }
}

function manageSub() {//提交
    for (var i = 1; i <= 3; i++) {
        if ($("#" + i).text() != "") {
            document.getElementById("bikeButton").disabled = true;
            return;
        }
    }
    document.getElementById("bikeButton").disabled = false;
}

//下拉框的值
function getSelectValue(obj) {
    var sText = obj.options[obj.options.selectedIndex].innerHTML; //这是取文本内容
    if (sText == "维护中") {
        $(".maintain").css("display", "block");
    }
}

//初始显示
function first() {
    var manage = "${manage}";
    if (manage == "添 加") {
        $(".Id").css("display", "none");
    }

    //消息
    var msg = "${msg}";
    if (msg != "")
        alert(msg);
    else
        return;
}