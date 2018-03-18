$(document).ready(function(){ready();});

var ready = function () {

    var toIndex = $(".logo").click(function () { img_logo_click_toIndex();  })
    var login = $("#button").click(function () {  a_button_click_login();   });

    $("#header_html").load("./header.html");
    $("#bottom_html").load("./bottom.html");
};

var a_button_click_login = function(){
    var user = $("#u").val();
    var pass1 = $("#p").val();


    if(user.length < 2 || 20 <= user.length ){
        alert("用户名长度错误,请输入大于2小于20的字符");
        return null;
    }
    if(pass1.length < 6 || 20 <= pass1.length ){
        alert("密码长度错误,请输入大于6小于20的字符");
        return null;
    }

    var form = {
        u:user,
        p:md5(pass1),
        save:$("#save").val()
    };
    jQuery.ajax({
        type:'post',
        url:'login/doLogin.do',
        data:form,
        // success:function (msg) {
        //     window.location=msg;
        // },
        success:function (jqXHR) {
            if(jqXHR.code==200)
                window.location=jqXHR.msg;
            else
                alert(jqXHR.msg);
        }
    })
};
var img_logo_click_toIndex = function(){
    window.open("./index.html");
};



