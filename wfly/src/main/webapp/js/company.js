$(document).ready(function () {
    ready();

});

var ready = function(){
    //选项框
    // $.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click", "0");
    // var showUl = $("li").click(function(){	li_navbar_click_showUl($(this))}	);
    var span = document.getElementsByClassName("tabBar")[0].children;
    for (x in span) {
        var a = span[x];
        a.onclick = function (e) {
            var as = this.parentElement.children;
            for (var x = 0; x < as.length; x++) {
                a = as[x];
                if (a.classList.contains("current")) {
                    a.classList.remove("current");
                }
            };
            this.classList.add("current");

            var tabCon = document.getElementsByClassName("tabCon");
            var id = this.getAttribute("id");
            for (var x = 0; x < tabCon.length; x++) {
                var c = tabCon[x];
                if (c.classList.contains("display_block")) {
                    c.classList.remove("display_block");
                    c.classList.add("display_none");
                }
                if (id == x) {
                    c.classList.add("display_block");
                }
            }
        };
    };
};

//#tab_demo 父级id
//#tab_demo .tabBar span 控制条
//#tab_demo .tabCon 内容区
//click 事件 点击切换，可以换成mousemove 移动鼠标切换
//1	默认第2个tab为当前状态（从0开始）
// jQuery.Huitab = function (tabBar, tabCon, class_name, tabEvent, i) {
//     var $tab_menu = $(tabBar);
// //	初始化操作
//     $tab_menu.removeClass(class_name);
//     $(tabBar).eq(i).addClass(class_name);
//     $(tabCon).hide();
//     $(tabCon).eq(i).show();
//
//     $tab_menu.bind(tabEvent, function () {
//         $tab_menu.removeClass(class_name);
//         $(this).addClass(class_name);
//         var index = $tab_menu.index(this);
//         $(tabCon).hide();
//         $(tabCon).eq(index).show()
//     })
// };

/*
var d = new Date();
d.setMinutes();//分
d.setSeconds(0);//秒
d.setMilliseconds(0);//毫秒
while (true){
    if(d <= new Date()){
        document.getElementById('111111').click();
        console.log(document.getElementById('111111').innerText);
        console.log(d);
        break;
    }
}*/
