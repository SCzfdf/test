var category;
var type;
$(document).ready(function () {
    $("#header_html").load("/header.html");
    $("#bottom_html").load("/bottom.html");
    ready();
    a_none_ready_simulationClick();
});

var ready = function () {
    //点击导航->显示全部
    $(".nav-detailed-li").click(function () {
        //清除li的选中状态
        var a = $(".menu_list_dd_a_click");
        if (a != null) {
            a.removeClass("menu_list_dd_a_click");
            a.addClass("menu_list_dd_a")
        }
        //切换li
        var title = "#" + this.getAttribute("title");
        $(".menu_list_dl").addClass("menu_list_dl_hidden");
        $(title).removeClass("menu_list_dl_hidden");
        $(title).addClass("menu_list_dl");

        var title = this.getAttribute("title");
        category=title[0];
        type=0;
        ajaxTest(1);
        //修改url
        history.pushState(null,null,"/view/data/index2.html?category="+title);
//        window.location.href="#?category="+title
        // $.get("/data/" + title + "/dataList.do", function (data, status) {
        //     $("#list").empty();
        //     for (var i = 0; i < data.data.length; i++) {
        //         nav_detailed_li_click(data.data[i].header, data.data[i].imageUrl, data.data[i].phone, data.data[i].address, data.data[i].context);
        //     }
        // });
    });

    //点击menu->显示该类
    $(".menu_list_dd_a").click(function () {
        $(this).removeClass("menu_list_dd_a");
        $(this).addClass("menu_list_dd_a_click");
        $(this).siblings().removeClass("menu_list_dd_a_click");
        $(this).siblings().addClass("menu_list_dd_a");

        var title = this.getAttribute("title").split("_");
        category=title[0];
        type=title[1];
        ajaxTest(1);
        // $.get("/data/" + title[0] + "/" + title[1] + "/dataList.do", function (data, status) {
        //     $("#list").empty();
        //     for (var i = 0; i < data.data.length; i++) {
        //         nav_detailed_li_click(data.data[i].header, data.data[i].imageUrl, data.data[i].phone, data.data[i].address, data.data[i].context);
        //     }
        // });
    });
};

//从地址栏获取参数 模拟点击
var a_none_ready_simulationClick = function() {
    var category = getQueryString("category");
    var type = getQueryString("type");

    if (type != null) {
        var b2 = document.getElementById(category + "_" + type);
        b2.click();
        return null;
    }

    if (category != null) {
        var b1 = document.getElementById("nav-detailed-li_" + category);
        b1.click();
    }
};


//分页插件
//  var ajaxTest = function(num) {
//  	$.ajax({
//  		url: "/data/dataList.do",
//  		type: "get",
//  		data: {"category":category,
//                 "type":type,
//  		        "num":num
//          },
//  		dataType: "json",
//  		success: function(data) {
//  			// console.log(data);
//  			//分页
//             $("#page").paging({
//                 pageNo: num,
//                 totalPage: data.totalPage,
//                 totalSize: data.totalSize,
//                 // pageNo: 5,
//                 // totalPage: 50,
//                 // totalSize: 500,
//                 callback: function(num) {
//                     ajaxTest(num);
//                 }
//             });
//             // $("#page").paging({
//             //     pageNo:5,
//             //     totalPage: 9,
//             //     totalSize: 300,
//             //     callback: function(num) {
//             //         alert(num)
//             //     }
//             // })
//             //填数据
//             // $("#list").empty();
//             // for (var i=0; i<data.data.length ; i++){
//             //     var d = data.data[i];
//             //     switch (parseInt(category)){
//             //         case 1:
//             //             nav_detailed_li_click(d.shop_name,d.id,d.image_url,d.address,d.phone,d.businessHours);
//             //             break;
//             //         case 5:
//             //             nav_detailed_li_click5(d.jod_name,d.id,d.jod_release_address,d.work_experience,d.treatment_leve,d.job_description)
//             //             break;
//             //     }
//             // }
//             // a_Page_ready_bindHear();
//
//
//  		}
//  	})
//  }
var ajaxTest = function (num) {
    $.ajax({
        url: "/data/dataList.do",
        type: "get",
        async:false,
        data: {"category":category,
            "type":type,
            "num":num
        },
        success: function(data) {
//            console.log(data);
            if(data.code == 200) {
                ppaging(
                    document.getElementById("page"),
                    num,
                    data.totalPage,
                    data.totalSize
                )

                $("#list").empty();
                for (var i = 0; i < data.data.length; i++) {
                    var d = data.data[i];
                    switch (parseInt(category)) {
                        case 1:
                            nav_detailed_li_click(d.shop_name, d.id, d.image_url, d.address, d.phone, d.businessHours);
                            break;
                        case 5:
                            nav_detailed_li_click5(d.jod_name, d.id, d.jod_release_address, d.work_experience, d.treatment_leve, d.job_description)
                            break;
                    }
                }
            }
        }
    });
    a_Page_ready_bindHear();
};

//给#list创建添加节点
var nav_detailed_li_click = function (h, h_href, img, a1, a2, a3, a4) {
    //创建标题节点
    var item_list_h = document.createElement("a");
    item_list_h.href= "/view/data/details.html?d="+h_href+"&c="+category;
    item_list_h.setAttribute("class", "list_item_h ");
    item_list_h.append(h);

    //创建正文图片节点
    var item_list_context_image = document.createElement("div");
    item_list_context_image.setAttribute("class", "list_item_context_img ");
    var image = document.createElement("img");
    image.setAttribute("src", img);
    item_list_context_image.append(image);

    //创建正文文字节点(a)
    var item_list_context_a1 = document.createElement("span");
    item_list_context_a1.append("地址："+a1);
    var item_list_context_a2 = document.createElement("span");
    item_list_context_a2.append("手机："+a2);
    var item_list_context_a3 = document.createElement("span");
    item_list_context_a3.append("营业时间："+a3);
    var item_list_context_a4 = document.createElement("span");
    item_list_context_a4.classList.add("list_item_context_p_context");
    // item_list_context_a4.append("简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介" );
    // item_list_context_a4.append(a4);

    //创建正文文字节点(div)
    var item_list_context_p = document.createElement("div");
    item_list_context_p.setAttribute("class", "list_item_context_p  ");
    item_list_context_p.append(item_list_context_a1);
    item_list_context_p.append(item_list_context_a2);
    item_list_context_p.append(item_list_context_a3);
    item_list_context_p.append(item_list_context_a4);

    //创建正文节点
    var item_list_context = document.createElement("div");
    item_list_context.setAttribute("class", "list_item_context ");
    item_list_context.append(item_list_context_image);
    item_list_context.append(item_list_context_p);

    var item = document.createElement("div");
    item.setAttribute("class", "list_item ");
    item.append(item_list_h);
    item.append(item_list_context);


    $("#list").append(item);
};

//给#list创建添加节点
var nav_detailed_li_click5 = function (h, h_href, a1, a2, a3, a4) {
    //创建标题节点
    var item_list_h = document.createElement("a");
    item_list_h.href= "/view/data/invitation.html?d="+h_href;
    item_list_h.setAttribute("class", "list_item_h ");
    item_list_h.append(h);

    //创建正文图片节点
    // var item_list_context_image = document.createElement("div");
    // item_list_context_image.setAttribute("class", "list_item_context_img ");
    // var image = document.createElement("img");
    // image.setAttribute("src", "/" + img);
    // item_list_context_image.append(image);

    //创建正文文字节点(a)
    var item_list_context_a1 = document.createElement("span");
    item_list_context_a1.append("地址："+a1);
    var item_list_context_a2 = document.createElement("span");
    item_list_context_a2.append("广州经验："+a2);
    var item_list_context_a3 = document.createElement("span");
    item_list_context_a3.append("薪资："+a3);
    var item_list_context_a4 = document.createElement("span");
    item_list_context_a4.classList.add("list_item_context_p_context");
    item_list_context_a4.append(a4);

    //创建正文文字节点(div)
    var item_list_context_p = document.createElement("div");
    item_list_context_p.setAttribute("class", "list_item_context_p");
    item_list_context_p.setAttribute("style", "float: left;width:auto");
    item_list_context_p.append(item_list_context_a1);
    item_list_context_p.append(item_list_context_a2);
    item_list_context_p.append(item_list_context_a3);
    item_list_context_p.append(item_list_context_a4);

    //创建正文节点
    var item_list_context = document.createElement("div");
    item_list_context.setAttribute("class", "list_item_context ");
    // item_list_context.append(item_list_context_image);
    item_list_context.append(item_list_context_p);

    var item = document.createElement("div");
    item.setAttribute("class", "list_item ");
    item.append(item_list_h);
    item.append(item_list_context);


    $("#list").append(item);
};

var a_Page_ready_bindHear = function (){
    var as = document.getElementById("page").children;
    var z=0;
    for (; z<as.length; ){
        a = as[z];
        z = z+1;
        // titie =a.title;
        a.href="javascript:scroll(0,0)";
        a.onclick = function (e){
            t = e.target.title;
            $.ajax({
                url: "/data/dataList.do",
                type: "get",
                async:false,
                data: {"category":category,
                    "type":type,
                    "num":t
                },
                dataType: "json",
                success: function(data) {
                    // console.log(data);
                    //分页
                    // $("#page").paging({
                    //     pageNo: num,
                    //     totalPage: data.totalPage,
                    //     totalSize: data.totalSize,
                    //     callback: function(num) {
                    //         ajaxTest(num)
                    //     }
                    // })
                    if(data.code == 200){
                        ppaging(
                            document.getElementById("page"),
                            t,
                            data.totalPage,
                            data.totalSize
                        );
                        $("#list").empty();
                        for (var i=0; i<data.data.length ; i++){
                            var d = data.data[i];
                            switch (parseInt(category)){
                                case 1:
                                    nav_detailed_li_click(d.shop_name,d.id,d.image_url,d.address,d.phone,d.businessHours);
                                    break;
                                case 5:
                                    nav_detailed_li_click5(d.jod_name,d.id,d.jod_release_address,d.work_experience,d.treatment_leve,d.job_description)
                                    break;
                            }
                        }
                    }
                }
            });
            a_Page_ready_bindHear();
        };
    }
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
