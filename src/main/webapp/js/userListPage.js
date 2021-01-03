function beforePage(page) {
    window.location = "UserServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
}

function afterPage(page) {
    window.location = "UserServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
}

function updateUser() {
    var user_Id;
    user_Id = prompt("请输入需要修改的用户id", "");
    if (user_Id) {
        window.location.href = "UsersManageServlet?manage=update&user_Id=" + user_Id;
    } else
        alert("id不能为空");
}

function deleteUser() {
    var user_Id;
    user_Id = prompt("请输入要删除的用户id", "");
    if (user_Id) {
        window.location.href = "UsersManageServlet?manage=delete&user_Id=" + user_Id;
    } else
        alert("id不能为空");
}

function first_userList(msg) {
    if (msg != null || msg != "")
        alert(msg);
    else
        return;
}
