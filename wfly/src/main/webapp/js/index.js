$(document).ready(function(){

    $("#nav-detailed a").mouseover(function(){
        // $(".menu").attr("style","visibility:visible");
        $(".menu").css("visibility","visible");
        $("#nav-detailed a").css("background-color","#db047a")

        // alert(s);
    });

    $("#nav-detailed a").mouseout(function(){
        $(".menu").attr("style","visibility:hidden");
        // $("#nav-detailed a").css("background-color","#4458db")
    });
    //
    $(".menu").mouseover(function() {
        // $(".menu").attr("style","visibility:visible");
        $(".menu").css("visibility", "visible");
        $("#nav-detailed a").css("background-color","#db047a")
    });
    //
    $(".menu").mouseout(function() {

        $(".menu").css("visibility", "hidden");
        $("#nav-detailed a").css("background-color","#ff008c");
        document.getElementsByClassName("menu-details-visible")


    });
    //
    $(".menu-navigation li").mouseover(function () {
        $(this).css("background-color","#ffbdde")
        var menus = document.getElementsByClassName("menu-details-visible")
        for(var a=0 ; a<menus.length ; a++){
            menus.item(a).setAttribute("class","menu-details");
        }

        var n = $(this).attr("title");
        document.getElementById(n).setAttribute("class","menu-details-visible");
    })

    $(".menu-navigation li").mouseout(function () {
        $(this).css("background-color","");
        var n = $(this).attr("title");
    })

    loadToDayInForMation();
});

var loadToDayInForMation = function () {
    $.ajax({
        type:"get",
        url:"/test/hotList.do",
        success:function(data,status) {
            var d = data.data;
            for (var i = 0; i < d.length; i++) {
                var menu = d[i];
                ready_hot_add_element(menu);
            }
        }
    });


};


var ready_hot_add_element = function(menu){

	var font = document.createElement("font");
    font.append(menu.header);

    var a = document.createElement("a");
    var div_p = document.createElement("div");

    a.setAttribute("href",menu.url);
    if (menu.ifHot){
        var img = document.createElement("img");
        img.setAttribute("src","image/hot.png");
        div_p.setAttribute("class","hot-p-hot");
        a.append(font);
        a.append(img);
    }else{
        div_p.setAttribute("class","hot-p");
        a.append(font);
        a.append(menu.header);
    }

    div_p.append("["+(menu.time.month+1)+" - "+menu.time.date+"]")

    var div = document.getElementById("hot"+menu.category);

    div_p.append(a);
    div.append(div_p);
};






















