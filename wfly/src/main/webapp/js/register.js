var register = "./login/register.do";

$(document).ready(function () {
    $("#header_html").load("./header.html");
    $("#bottom_html").load("./bottom.html");
    var verifyCode = new GVerify("form_img");



    var toIndex = $(".logo").click(function () { img_logo_click_toIndex($(this));  });
    var toRegister = $("#register").click(function(){ a_Register_click_toRegister($(this)); });
    var revealTips = $("input").blur(function () { input_Input_focus_revealTips($(this));});

//    $("#p1").blur(function(){
//    	var before = $(this).val();
//    	var beforeVal = md5(before);
//    	$("#yanzheng").val(beforeVal);
//    });
});



var img_logo_click_toIndex = function(){
    window.open("./index.html");
};
var a_Register_click_toRegister = function(){
    var user = $("#u").val();
    var pass1 = $("#p1").val();
    var pass2 = $("#p2").val();
    var yanz = $("#yanzheng").val();
    var check = $(".checkbox").children();

    if(user.length < 6 || 20 <= user.length  || user=="请输手机号"){
        alert("用户名长度错误,请输入大于2小于20的字符");
        return null;
    }
    if(pass1.length < 6 || 20 <= pass1.length || pass1=="请输入6-24位密码" ){
        alert("密码长度错误,请输入大于6小于20的字符");
        return null;
	}

	if(pass1 !== pass2){
        alert("密码输入不一致");
        return null;
	}

	if(yanz.toLowerCase() !== sourceCode.toLowerCase() ){
		alert("验证码输入错误");
        return null;
	}

	if(!check[0].checked){
	    alert("请查阅授权协议");
        return null;
    }

    var form = {
        u:user,
        p:md5(pass1)
    };
    $.ajax({
        type:'post',
        url:register,
        data:form,
        success:function (jqXHR) {
            if(jqXHR.code==200){
                alert("创建成功");
                window.location=jqXHR.msg;
            }else
                alert(jqXHR.msg);
        }
    })
}
var input_Input_focus_revealTips = function (t) {
    var val_length = t.val().length;
    tip = t.next();
    var tt=t.val();
    var s = t.attr("id");
    switch (s){
        case "u":

        case "p1":
            if(val_length < 6 || 20 <= val_length || t.val()=="请输手机号" || t.val()=="请输入6-24位密码"){
                tip.attr("class","register_tips")
            }else{
                tip.attr("class","register_tips_hidden")
            }
            break;
        case "p2":
            var pass1 = $("#p1").val();
            if(pass1 !== t.val() ){
                tip.attr("class","register_tips")
            }else{
                tip.attr("class","register_tips_hidden")
            }
            break;
        case "yanzheng":
            if(t.val().toLowerCase() !== sourceCode.toLowerCase() ){
                tip.next().attr("class","register_tips")
            }else{
                tip.next().attr("class","register_tips_hidden")
            }
            break;
    }
};






























