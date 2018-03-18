var startCount = 0;
var Education = {//学历: 0:无 1:高中 2:大专 3:本科 4:本科以上
    education0: '无',
    education1: '高中',
    education2: '大专',
    education3: '本科',
    education4: '本科以上'
};
$(document).ready(function () {
    ready();
    a_none_ready_simulationClick();
});

var ready = function () {
    $(".img").addClass("carousel-inner img-responsive img-rounded");
    li_list_basic_onclick_clickLi();
};



var a_none_ready_simulationClick = function() {
    //category
    //DataId
    var d = getQueryString("d");

    $.ajax({
        url: "/data/getResume.do",
        type: "post",
        data: {
            "d":d
        },
        dataType: "json",
        success: function(data) {
            //填数据
            if(data.code==200){
                var d = data.data;
                var basic0 = document.getElementsByClassName("basic")[0].children;
                var education = "无";
                switch (d.education){
                    case 0 : break;
                    case 1 :    education = Education.education1; break;
                    case 2 :    education = Education.education1; break;
                    case 3 :    education = Education.education1; break;
                    case 4 :    education = Education.education1; break;
                }
                basic0[0].innerHTML = d.name;
                basic0[1].innerHTML = d.sex ? "男" : "女";
                basic0[2].innerHTML = d.age;
                basic0[3].innerHTML = education;
                basic0[4].innerHTML = d.job_area;
                basic0[5].innerHTML = d.experience;

                var basic1 = document.getElementsByClassName("basic")[1];
                // var pC = ;
                var dName = document.createElement("h1");dName.setAttribute("font-weight","bolder");dName.innerHTML = d.name;
                basic1.appendChild(dName);
                var dSex = document.createElement("p");dSex.innerHTML = "性别："+(d.sex ? "女" : "男");
                basic1.appendChild(dSex);
                var dAge = document.createElement("p");dAge.innerHTML = "年龄："+d.age;
                basic1.appendChild(dAge);
                var dEducation = document.createElement("p");dEducation.innerHTML = "学历："+education;
                basic1.appendChild(dEducation);
                var dArea = document.createElement("p");dArea.innerHTML = "籍贯："+d.natives;
                basic1.appendChild(dArea);
                var dExperience = document.createElement("p");dExperience.innerHTML = "工作经验："+d.experience;
                basic1.appendChild(dExperience);
                // var i = document.createElement("img");
                // i.src=data.data.image_url;
                // i.setAttribute("style","height: 130px;width: 180;")
                // // i.height="75px";i.width="75px";
                // document.getElementById("companyImage").appendChild(i);
                // document.getElementById("company").innerHTML=data.data.shop_name;
                // document.getElementById("phone").innerHTML="手机号码："+data.data.phone;
                // document.getElementById("address").innerHTML="地址："+data.data.address;
                // var newDate = new Date(data.data.up_date * 1000);
                // document.getElementById("update").innerHTML="更新时间："+newDate.toLocaleDateString();
                var jobType = "全职";
                if(d.job_type == 1)  jobType = "兼职";
                var reinforce = document.getElementById("job-type").innerHTML = jobType;
                var reinforce = document.getElementById("job-intention").innerHTML = d.job_intention;
                var reinforce = document.getElementById("job-area").innerHTML = d.job_area;
                // var reinforce = document.getElementById("job-type").innerHTML = "预留";//d.job_type;
                var reinforce = document.getElementById("job_treatment").innerHTML = d.job_treatment;
                // var reinforce = document.getElementById("phone").innerHTML = d.phone;
                var reinforce = document.getElementById("speciality").innerHTML = d.speciality;
                var reinforce = document.getElementById("self-assessment").innerHTML = d.self_assessment;
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























