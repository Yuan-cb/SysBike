function beforePage(page) {
    var key = "${key}";
    if (key == null || key == "") {
        window.location = "AdminBikesShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
    } else {
        window.location = "AdminBikesShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
    }
}

function afterPage(page) {
    var key = "${key}";
    if (key == null || key == "") {
        window.location = "AdminBikesShowServlet?currentPage=" + page + "&pageSize=${pageBean.pageSize}";
    } else {
        window.location = "AdminBikesShowServlet?key=${key}&value=${value}&currentPage=" + page + "&pageSize=${pageBean.pageSize}";
    }
}

function updateBike() {
    var bike_Id = prompt("请输入需要修改的单车id", "");
    if (bike_Id) {
        window.location.href = "BikesManageServlet?manage=update&bike_Id=" + bike_Id;
    } else
        alert("id不能为空");
}

function deleteBike() {
    var bike_Id = prompt("请输入要删除的单车id", "");
    if (bike_Id) {
        window.location.href = "BikesManageServlet?manage=delete&bike_Id=" + bike_Id;
    } else
        alert("id不能为空");
}

function searchBike() {
    var search_chose = prompt("请输入查找方式（id/种类/价格/状态/日期/）", "");
    if (search_chose == "id") {
        var id = prompt("请输入id", "");
        if (id)
            window.location.href = "AdminBikesShowServlet?key=bike_Id&value=" + id;
        return;
    }
    if (search_chose == "种类") {
        var kind = prompt("请输入种类", "");
        if (kind)
            window.location.href = "AdminBikesShowServlet?key=kind&value=" + kind;
        return;
    }
    if (search_chose == "价格") {
        var price = prompt("请输入价格", "");
        if (price)
            window.location.href = "AdminBikesShowServlet?key=bike_price&value=" + price;
        return;
    }
    if (search_chose == "状态") {
        var state = prompt("请输入状态", "");
        if (state)
            window.location.href = "AdminBikesShowServlet?key=bike_state&value=" + state;
        return;
    }
    if (search_chose == "日期") {
        var date = prompt("请输入日期", "");
        if (date)
            window.location.href = "AdminBikesShowServlet?key=add_Date&value=" + date;
        return;
    }
    window.location.href = "AdminBikesShowServlet";
}

function first() {
    if ("${msg}" != "")
        alert("${msg}");
    else
        return;
}