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
function first_usersManage(msg) {
    if (msg != "" || msg != null)
        alert(msg);
    else
        return;
}
