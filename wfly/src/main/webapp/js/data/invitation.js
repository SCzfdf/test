var startCount = 0;
var errorPaga = "http://rocketship.com.au/404/";

$(document).ready(function () {
    ready();
    a_none_ready_simulationClick();
});

var ready = function () {
    $(".classname img").addClass("carousel-inner img-responsive img-rounded");
    li_list_basic_onclick_clickLi();
};


var a_none_ready_simulationClick = function() {
    var d = getQueryString("d");

    $.ajax({
        url: "/data/getInvitation.do",
        type: "get",
        data: {
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
                document.getElementById("company-name").innerHTML = data.data.company_name;
                document.getElementsByClassName("invitation-name")[0].innerHTML = data.data.jod_name;
                document.getElementsByClassName("treatment")[0].innerHTML = data.data.treatment_leve;
                document.getElementsByClassName("ask")[0].innerHTML = data.data.recruitment_pepole;
                document.getElementsByClassName("ask")[1].innerHTML = data.data.degree_required;
                document.getElementsByClassName("ask")[2].innerHTML = data.data.gender_required;
                document.getElementsByClassName("ask")[3].innerHTML = data.data.age_requirement;
                document.getElementsByClassName("ask")[4].innerHTML = data.data.work_experience;

                //电话 联系人->先写一个
                // document.getElementById("phone").innerHTML =
                // document.getElementById("people").innerHTML =
                document.getElementById("address").innerHTML = data.data.jod_release_address;

                // document.getElementsByClassName("treatment")[0].innerHTML = data.data.jod_name;
                // document.getElementsByClassName("treatment")[0].innerHTML = data.data.jod_name;
                // document.getElementsByClassName("treatment")[0].innerHTML = data.data.jod_name;
                // document.getElementsByClassName("treatment")[0].innerHTML = data.data.jod_name;

                document.getElementById("contact").innerHTML = data.data.job_description;

                // var newDate = new Date(data.data.up_date * 1000);
                // document.getElementById("update").innerHTML="更新时间："+newDate.toLocaleDateString();
            }else{
                window.location.href = errorPaga;
            }
        },
        error: function () {
            window.location.href = errorPaga;
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






















