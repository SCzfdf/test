var startCount = 0;
var errorPaga = "http://rocketship.com.au/404/";
var IMAGE_DELETE_URL = "/image/school/";

$(document).ready(function () {
    ready();
    a_none_ready_simulationClick();
    li_list_basic_onclick_clickLi();
});

var ready = function () {
    // $("img").addClass("carousel-inner img-responsive img-rounded");
};


var a_none_ready_simulationClick = function() {
    var d = getQueryString("d");
    $.ajax({
        url: "/data/getSchool.do",
        type: "get",
        data: {
            "d":d
        },
        dataType: "json",
        success: function(data) {
            console.log(data);
            //填数据
            if(data.code==200){
                var d = data.data;
                var img = document.createElement("img");
                img.setAttribute("src",IMAGE_DELETE_URL+d.logo_url);
                document.getElementsByClassName("school-header-image")[0].append(img);
                var header = document.getElementsByClassName("school-header-context")[0];
                var hChildren = header.children;
                hChildren[0].innerHTML = d.school_name;
                hChildren[1].firstElementChild.innerHTML = d.school_type;
                hChildren[2].firstElementChild.innerHTML = d.school_address;
                hChildren[3].firstElementChild.innerHTML = d.school_create_time;
                hChildren[4].firstElementChild.innerHTML = d.school_space;
                hChildren[4].lastElementChild.innerHTML = "18987863843";
                hChildren[5].firstElementChild.innerHTML = d.school_course;

                document.getElementById("context").innerHTML = d.content;
            }else{
                window.location.href = errorPaga;
            }
        },
        error: function () {
            window.location.href = errorPaga;
        }

    })
};

var li_list_basic_onclick_clickLi = function () {
    var lis = document.getElementsByClassName("list-basic")[0].getElementsByTagName("li");
    for (i=0; i<lis.length; i++){
        lis[i].firstElementChild.onclick = function () {
            var url = this.dataset.url;
            window.location.href= "/view/data/index2N.html?category="+url;
        }
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























