var category;
var type;
var num;
var DETAIL_LIST_PREFIX = "/view/list/";//详情列表连接前后缀
var DETAIL_LIST_SUFFIX = ".html";

$(document).ready(function () {
    ready();

});

var ready = function () {
    a_none_click_showType();//绑定ul
    ul_basic_click_simulate();//模拟点击
};

var ajaxTest = function (num) {
    $.ajax({
        url: "/data/dataList.do",
        type: "get",
        //async:false,
        data: {"category":category,
            "type":type,
            "num":num
        },
        success: function(data) {
            console.log(data);
            if(data.code == 200) {
                ppaging(
                    document.getElementById("page"),
                    num,
                    data.totalPage,
                    data.totalSize
                );
                document.getElementById("show").innerHTML = "";//清空
                for (var i = 0; i < data.data.length; i++) {
                    var d = data.data[i];
                    showData(d);

                    var show = document.getElementById("show").children;
                    $(show[show.length-2]).fadeIn(i*250);//渐显 XXX:会展和快递比较特殊在页面做
                    // $(show[show.length-1]).fadeIn(i*250);
                }
            }else{
                var page = document.getElementById("page");
                if(page != null)
                    page.innerHTML = "";
            }
            a_Page_ready_bindHear();
        },
        error:function () {
            var page = document.getElementById("page");
            if(page != null)
                page.innerHTML = "";
        }
    });
    a_details_click_showList();
};

//绑定分页按钮
var a_Page_ready_bindHear = function (){
    var as = document.getElementById("page").children;
    var z=0;
    for (; z<as.length; ){
        a = as[z];
        z = z+1;
        a.href="javascript:scroll(0,0)";
        a.onclick = function (e){
            t = e.target.title;
            $.ajax({
                url: "/data/dataList.do",
                type: "get",
                // async:false,
                data: {"category":category,
                    "type":type,
                    "num":t
                },
                dataType: "json",
                success: function(data) {
                    if(data.code == 200){
                        ppaging(
                            document.getElementById("page"),
                            t,
                            data.totalPage,
                            data.totalSize
                        );
                        history.pushState(null,null,"?category="+category+"&type="+type+"&num="+t);

                        document.getElementById("show").innerHTML = "";//清空
                        for (var i = 0; i < data.data.length; i++) {
                            var d = data.data[i];
                            showData(d);//填数据

                            var show = document.getElementById("show").children;
                            $(show[show.length-2]).fadeIn(i*250);//渐显 XXX:会展和快递比较特殊在页面做
                        }
                    }else{
                        var page = document.getElementById("page");
                        if(page != null)
                            page.innerHTML = "";
                    }
                    a_Page_ready_bindHear();
                }
            });
            a_details_click_showList();
        };
    }
};

//点击粗条目
var a_none_click_showType = function () {
    var li = document.getElementsByClassName("list-basic")[0].children;
    for (i=0 ; i<li.length ;i++){
        //绑定按钮
        li[i].children[0].onclick = function () {
            category = this.dataset.url;
            $("#list-details").load(DETAIL_LIST_PREFIX+category+"_1"+DETAIL_LIST_SUFFIX,function () {
                history.pushState(null,null,"?category="+category);
                type = 1;
                ajaxTest(1);
            });
        }
    }
    li[6].children[0].onclick = function () {
        category = this.dataset.url;
        type = 1;
        history.pushState(null,null,"?category="+category);
        $("#list-details").load(DETAIL_LIST_PREFIX+category+"_1"+DETAIL_LIST_SUFFIX);
        var page = document.getElementById("page");
        if (page!=null) page.innerHTML = "";
    };
    li[2].children[0].onclick = function () {
        category = this.dataset.url;
        type = 1;
        history.pushState(null,null,"?category="+category);
        $("#list-details").load(DETAIL_LIST_PREFIX+category+"_1"+DETAIL_LIST_SUFFIX);
        var page = document.getElementById("page");
        if (page!=null) page.innerHTML = "";
    }
    li[3].children[0].onclick = function () {
        category = this.dataset.url;
        type = 1;
        history.pushState(null,null,"?category="+category);
        $("#list-details").load(DETAIL_LIST_PREFIX+category+"_1"+DETAIL_LIST_SUFFIX);
        var page = document.getElementById("page");
        if (page!=null) page.innerHTML = "";
    }
    // TODO:这样抽取出来会报错= =JS真是神奇的语言
    // li[3].children[0].onclick = a_none_click_showTypeFunction(this);
    // var a_none_click_showTypeFunction = function (t) {
    //     category = t.dataset.url;
    //     type = 1;
    //     history.pushState(null,null,"?category="+category);
    //     $("#list-details").load(DETAIL_LIST_PREFIX+category+"_1"+DETAIL_LIST_SUFFIX);
    //     var page = document.getElementById("page");
    //     if (page!=null) page.innerHTML = "";
    // }
};


//点击细分条目
var a_details_click_showList = function () {
    var ul = document.getElementsByClassName("details-ul")[0];
    var lis = ul.children;
    for(i=0 ; i<lis.length ; i++){
        //绑定标签
        lis[i].children[0].onclick = function () {
            //切换样式
            var ul = document.getElementsByClassName("details-ul")[0];
            var lis = ul.children;
            for(i=0 ; i<lis.length ; i++){
                var a = lis[i].children[0];
                if(a.classList.contains("details-a-click")){
                    a.classList.remove("details-a-click");
                    a.classList.add("details-a-Nclick");
                    break;
                }
            }
            this.classList.remove("details-a-Nclick");
            this.classList.add("details-a-click");

            //更新数据
            category = document.getElementsByClassName("details-header")[0].dataset.category;
            type = this.dataset.type;
            if (category == "1"){
                // $("#list-details").load(DETAIL_LIST_PREFIX+category+"_"+type+DETAIL_LIST_SUFFIX);
                ajaxTest(1);
            }else if(category == "7" || category == "3" || category=="4"){
                $("#list-details").load(DETAIL_LIST_PREFIX+category+"_"+type+DETAIL_LIST_SUFFIX,function () {
                    if (category=="3" && type=="3"){
                        ajaxTest(1);
                    }
                });
            }else {
                $("#list-details").load(DETAIL_LIST_PREFIX+category+"_"+type+DETAIL_LIST_SUFFIX,function () {
                    ajaxTest(1);
                });
            }
            history.pushState(null,null,"?category="+category+"&type="+type);

        };
    }
};

//模拟点击
var ul_basic_click_simulate = function () {
    c = getQueryString("category");
    t = getQueryString("type");
    n = getQueryString("num");
    if(c != null && t!= null && n!=null){
        category = c;
        if(category == "1") {
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_1"+DETAIL_LIST_SUFFIX,function () {
                var lis = document.getElementsByClassName("details-ul")[0].children;
                for (i=0 ; i<lis.length ; i++){
                    var a = lis[i].children[0];
                    if (a.classList.contains("details-a-click")){
                        a.classList.remove("details-a-click");
                        a.classList.add("details-a-Nclick")
                    }
                    if (a.dataset.type == t){
                        a.classList.remove("details-a-Nclick");
                        a.classList.add("details-a-click")
                    }
                }
                type = t;
                ajaxTest(n);
            });
        }else if(category == "7" || category == "3" || category=="4"){
            type = t;
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_"+t+DETAIL_LIST_SUFFIX,function () {
                a_details_click_showList();
            });
        }else {
            type = t;
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_"+t+DETAIL_LIST_SUFFIX,function () {
                ajaxTest(n);
            });
        }
    }else if(c != null && t!= null){
        category = c;
        if(category == "1") {
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_1"+DETAIL_LIST_SUFFIX,function () {
                var lis = document.getElementsByClassName("details-ul")[0].children;
                for (i=0 ; i<lis.length ; i++){
                    var a = lis[i].children[0];
                    if (a.classList.contains("details-a-click")){
                        a.classList.remove("details-a-click");
                        a.classList.add("details-a-Nclick")
                    }
                    if (a.dataset.type == t){
                        a.classList.remove("details-a-Nclick");
                        a.classList.add("details-a-click")
                    }
                }
                type = t;
                ajaxTest(1);
            });
        }else if(category == "7" || category == "3" || category=="4"){
            type = t;
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_"+t+DETAIL_LIST_SUFFIX,function () {
                a_details_click_showList();
            });
        }else {
            type = t;
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_"+t+DETAIL_LIST_SUFFIX,function () {
                ajaxTest(1);
            });
        }
    }else if(c != null){
        category = c;
        if(category == "7" || category == "3"){
            type = 1;
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_"+1+DETAIL_LIST_SUFFIX,function () {
                a_details_click_showList();
            });
        }
        else{
            $("#list-details").load(DETAIL_LIST_PREFIX+c+"_1"+DETAIL_LIST_SUFFIX,function () {
                type = 1;
                ajaxTest(1);
            });
        }
    }else{
        $("#list-details").load(DETAIL_LIST_PREFIX+1+"_1"+DETAIL_LIST_SUFFIX,function () {
            category = 1;
            type = 1;
            ajaxTest(1);
        });
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
