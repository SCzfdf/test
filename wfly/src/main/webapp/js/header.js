var loginOut_url = '/login/logout.do';

$(document).ready(function () {
    ready();
});

var ready = function () {
    $.ajax({
        type: 'get',
        url: '/login/isLogin.do',
        dataType: 'json',
        contentType: 'application/json;charset=utf-8',
        async: true,
        success: function (data, textStatus) {
            if (data.data.user_id != null) {
                $("#header-user").empty();

                var a = document.createElement("a");
                a.setAttribute("href", "/view/user/personal.html"); // 个人中心->由id查询->连接为user/{id}.html 或者 {id}/user.html
                a.append(data.data.nick_name + "的个人中心");

                var logout = document.createElement("a");
                logout.setAttribute("href", loginOut_url);
                logout.append("注销");

                $("#header-user").append(a);
                $("#header-user").append(logout);
            }
        }
    });
}