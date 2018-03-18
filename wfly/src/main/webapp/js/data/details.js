var startCount = 0;
var input = document.getElementById("startP"); //星星 的默认值 -> 后续通过js获取评价 -> 默认4分

$(document).ready(function () {
    ready();
    a_none_ready_simulationClick();
    li_list_basic_onclick_clickLi();
});

var ready = function () {

    //选项框
    $.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click", "0");
    $(".classname img").addClass("carousel-inner img-responsive img-rounded");

    //绑定评价星星
    li_starts_ready_bindingOfStarts();

};


//	#tab_demo 父级id
//	#tab_demo .tabBar span 控制条
//	#tab_demo .tabCon 内容区
//	click 事件 点击切换，可以换成mousemove 移动鼠标切换
//	1	默认第2个tab为当前状态（从0开始）
jQuery.Huitab = function (tabBar, tabCon, class_name, tabEvent, i) {
    var $tab_menu = $(tabBar);
    // 初始化操作
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

//绑定评价星星
var li_starts_ready_bindingOfStarts = function(){
    var s = document.getElementById("pingStar");
        m = document.getElementById("dir"),
        n = s.getElementsByTagName("li");
    
    for (var i = 0; i < n.length; i++) {
        n[i].onclick = function () {
            startCount++;
            if(startCount>=1){
                return ;
            }
            var q = this.getAttribute("rel");
            clearAllStart();
            input.value = q;
            for (var i = 0; i < q; i++) {
                n[i].className = "on";
            }
            m.innerHTML = this.getAttribute("title");
        }
        n[i].onmouseover = function () {
            if(startCount>=1){
                return ;
            }
            var q = this.getAttribute("rel");
            clearAllStart();
            for (var i = 0; i < q; i++) {
                n[i].className = "on";
            }
            m.innerHTML = this.getAttribute("title");
        }
        n[i].onmouseout = function () {
            if(startCount>=1){
                return ;
            }
            clearAllStart();
            for (var i = 0; i < input.value; i++) {
                n[i].className = "on";
            }
    
        }
    }
    
    clearAllStart = function () {
        for (var i = 0; i < n.length; i++) {
            n[i].className = "";
        }
    }
    //模拟一次滑动给星星上色
    n[input.value-1].onmouseover();
}

var a_none_ready_simulationClick = function() {
    //category
    //DataId
    var c = getQueryString("c");
    var d = getQueryString("d");

    $.ajax({
        url: "/data/getData.do",
        type: "post",
        data: {"c":c,
            "d":d
        },
        dataType: "json",
        success: function(data) {
            console.log(data);
            //填数据
            if(data.code==200){
                var i = document.createElement("img");
                i.src=data.data.image_url;
                i.setAttribute("style","height: 130px;width: 180;")
                // i.height="75px";i.width="75px";
                document.getElementById("companyImage").appendChild(i);
                document.getElementById("company").innerHTML=data.data.shop_name;
                document.getElementById("phone").innerHTML="手机号码："+data.data.phone;
                document.getElementById("address").innerHTML="地址："+data.data.address;
                var newDate = new Date(data.data.up_date * 1000);
                document.getElementById("update").innerHTML="更新时间："+newDate.toLocaleDateString();
            }
        }
    })


};

//获取参数地址栏参数
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

var li_list_basic_onclick_clickLi = function () {
    var lis = document.getElementsByClassName("list-basic")[0].getElementsByTagName("li");
    for (i=0; i<lis.length; i++){
        lis[i].firstElementChild.onclick = function () {
            var url = this.dataset.url;
            window.location.href= "/view/data/index2N.html?category="+url;
        }
    }
};






















