var startCount = 0;
var errorPaga = "http://rocketship.com.au/404/";
var IMAGE_DELETE_URL = "/image/shoptransfer/";

$(document).ready(function () {
    ready();
    a_none_ready_simulationClick();
    li_list_basic_onclick_clickLi();
});

var ready = function () {
    // $("img").addClass("carousel-inner img-responsive img-rounded");
    tab();
};


var a_none_ready_simulationClick = function() {
    var d = getQueryString("d");
    $.ajax({
        url: "/data/getShoptransfer.do",
        type: "get",
        data: {
            "d":d
        },
        dataType: "json",
        success: function(data) {
            console.log(data);
            //填数据
            if(data.code==200){
                var uli = document.createElement("li");
                var oli = document.createElement("li");
                var a = document.createElement("a");
                a.setAttribute("href","#");
                a.setAttribute("target","_blank");
                var img = document.createElement("img");
                var NodeI = document.createElement("i");

                var ul = document.getElementById("ul");
                var ol = document.getElementById("ol");
                var d = data.data;

                var imgs = d.image_url.split(";");
                for (i=0; i<imgs.length; i++){
                    // if (i <= 4){
                    image = img.cloneNode(true);
                    image.setAttribute("src",IMAGE_DELETE_URL+imgs[i]);
                    image.setAttribute("style","width:auto;height:auto;display: inline-block;");


                    a1 = a.cloneNode(true);
                    uli1 = uli.cloneNode(true);
                    a1.append(image);
                    uli1.append(a1);
                    ul.append(uli1);

                    // image1 = image.cloneNode(true);
                    oli1 = oli.cloneNode(true);
                    oli1.innerHTML = i;
                    // NodeIc = NodeI.cloneNode(true);
                    // oli1.append(NodeIc);
                    // oli1.append(image1);
                    ol.append(oli1);
                    // }
                }
                $(function(){
                    jQuery("#slider-3 .slider").slide({mainCell:".bd ul",titCell:".hd li",trigger:"click",effect:"leftLoop",autoPlay:true,delayTime:700,interTime:7000000,pnLoop:false,titOnClassName:"active"})});

                document.getElementById("gold").innerHTML = d.rental;
                document.getElementById("transferGold").innerHTML = d.transfer_gold;

                var ps1 = document.getElementsByClassName("context")[0].children;
                var ps2 = document.getElementsByClassName("context")[1].children;

                ps1[0].lastElementChild.innerHTML = d.area;
                ps1[1].lastElementChild.innerHTML = d.wide;
                ps1[2].lastElementChild.innerHTML = d.high;
                ps1[3].lastElementChild.innerHTML = d.depth;
                ps1[4].lastElementChild.innerHTML = d.floor;

                ps2[0].lastElementChild.innerHTML = d.payment;
                ps2[1].lastElementChild.innerHTML = d.type;
                ps2[2].lastElementChild.innerHTML = d.tenancy;
                ps2[3].lastElementChild.innerHTML = d.manage_business;
                ps2[4].lastElementChild.innerHTML = d.manage_status;

                var phone = document.getElementsByClassName("context-x")[0];
                phone.getElementsByTagName("p")[0].lastElementChild.innerHTML = d.address;
                phone.getElementsByTagName("a")[0].innerHTML = d.transfer_people+"："+d.people;

                var span = document.createElement("span");
                var content1 = document.getElementsByClassName("box-details-content")[0];
                var attached = d.attached.split(";");
                for (i=0; i<attached.length-1; i++){
                    span1 = span.cloneNode(true);
                    span1.innerHTML = attached[i];
                    content1.append(span1);
                }

                var content2 = document.getElementsByClassName("box-details-content")[1];
                var p = document.createElement("p");
                var p1 = p.cloneNode(true);
                p1.innerHTML = d.introduce;
                content2.append(p1);
            }else{
                // window.location.href = errorPaga;
            }
        },
        error: function () {
            // window.location.href = errorPaga;
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

var tab = function () {
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






















