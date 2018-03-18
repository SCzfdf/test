$(document).ready(function () {
	ready();
	
})

var ready = function(){
	a_none_ready_getUser();
	
	//选项框
	$.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click", "0");
	var showUl = $("li").click(function(){	li_navbar_click_showUl($(this))}	);
}

var a_none_ready_getUser = function(){
    $.ajax({
        type: 'get',
        url: '/user/getUser.do',
        async: true,
        success: function (data, textStatus) {
        	data;
            if (data.code == 200) {

                document.getElementById("username").innerHTML=data.data.nick_name;
				document.getElementById("phone").innerHTML=data.data.phone;
				var i = document.createElement("img");
                i.src = "/image/3.png";
                i.classList.add("img-responsive");
                i.classList.add("round");
                i.classList.add("userImage");
                document.getElementById("userImage").append(i);
                // i.classList.add("round userImage")

            }else{
            	data;
            	window.location.href=data.data;
            }
        }
    });
}

//获取元素(div)的height 和img 的高度 动态设置margin
var img_none_ready_topAuto = function(imgId){
	var d2 = document.getElementById(imgId);
	var i = d2.getElementsByTagName("img")[0];
	i.style.marginTop = (d2.clientHeight - i.clientHeight)/2;
};

var li_navbar_click_showUl = function(t) {
	var u = t.find("ul");
	if(u.length==0){
		return null;
	}
	var u2 = document.getElementsByClassName("ul-visibility");
	var strong = t.find("strong");


	var uclass = u[0].classList;

	for(var i=0; i<uclass.length ; i++){
		if(uclass[i]=="ul-visibility"){
			u[0].classList.add("ul-hidden");
			u[0].classList.remove("ul-visibility");
			u[0].parentElement.children[0].children[0].innerHTML = "＋"
				return;
		}
	}


	if(u2.length > 0){
		u2[0].parentElement.children[0].children[0].innerHTML = "＋"
			u2[0].classList.add("ul-hidden");
		u2[0].classList.remove("ul-visibility");
	}

	strong[0].innerHTML = "－";
	u.removeClass("ul-hidden");
	u.addClass("ul-visibility");
};

//#tab_demo 父级id
//#tab_demo .tabBar span 控制条
//#tab_demo .tabCon 内容区
//click 事件 点击切换，可以换成mousemove 移动鼠标切换
//1	默认第2个tab为当前状态（从0开始）
jQuery.Huitab = function (tabBar, tabCon, class_name, tabEvent, i) {
	var $tab_menu = $(tabBar);
//	初始化操作
	$tab_menu.removeClass(class_name);
	$(tabBar).eq(i).addClass(class_name);
	$(tabCon).hide();
	$(tabCon).eq(i).show();

	$tab_menu.bind(tabEvent, function () {
		$tab_menu.removeClass(class_name);
		$(this).addClass(class_name);
		var index = $tab_menu.index(this);
		$(tabCon).hide();
		$(tabCon).eq(index).show()
	})
}