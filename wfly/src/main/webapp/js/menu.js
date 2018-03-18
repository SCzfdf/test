$(function(){
    $(document).ready(function(){
        $.get("/test/t",function(data,status){
            alert("数据：" + data + "\n状态：" + status);
        });
    });
});
