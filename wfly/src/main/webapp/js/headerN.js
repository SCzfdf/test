var loginOut_url = '/login/logout.do';

$(document).ready(function () {
	$.ajax({
		type: 'get',
		url: '/login/isLogin.do',
		async: true,
		success: function (data, textStatus) {
			data;
			if (data.code == 200) {
				$("#loginArea").empty();

				var a = document.createElement("a");
				a.setAttribute("href", "/view/user/personal.html"); // 个人中心->由id查询->连接为user/{id}.html 或者 {id}/user.html
				a.append(data.data.nick_name + "的个人中心");

				var logout = document.createElement("a");
				logout.setAttribute("href", loginOut_url);
				logout.classList.add("marginL10");
				logout.append("注销");

				$("#loginArea").append(a);
				$("#loginArea").append(logout);
			}
		},
		error:function(data, textStatus){
			console.log(textStatus,data);
		}
	});
});
