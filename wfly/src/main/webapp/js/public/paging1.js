var ppaging = function (element, pageNo, totalPage, totalSize) {
    //传入形参
    this.options = {
        element : element,
        pageNo: pageNo||1,
        totalPage: totalPage,
        totalSize:totalSize
        // callback:callback
    };
    creatHtml(options);
    // bindEvent(options);
}
var creatHtml = function(options) {
    var content = "";
    var current = options.pageNo;
    var nextCurrent = parseInt(current) + 1;
    var total = options.totalPage;
    var totalNum = options.totalSize;
    if(current > 1){
        content += "<a id=\"firstPage\" title='1'>首页</a><a id='prePage' title="+(current-1)+">上一页</a>";
    }else{
        content += "<a id=\"firstPage\" title='1'>首页</a><a id='prePage' title="+(1)+">上一页</a>";
	}
    //总页数大于6时候
    if(total > 6) {
        //当前页数小于5时显示省略号
        if(current < 5) {
            for(var i = 1; i < 6; i++) {
                if(current == i) {
                    content += "<a class='current' title='"+i+"'>" + i + "</a>";
                } else {
                    content += "<a title='"+i+"'>" + i + "</a>";
                }
            }
            content += ". . .";
            content += "<a title='"+parseInt((total+i)/2)+"'>" + parseInt((total+i)/2) + "</a>";
        } else {
            //判断页码在末尾的时候
            if(current < total - 3) {
                for(var i = current - 2; (i - current) < 3; i++) {
                    if(current == i) {
                        content += "<a class='current' title='"+i+"'>" + i + "</a>";
                    } else {
                        content += "<a title='"+i+"'>" + i + "</a>";
                    }
                }
                content += ". . .";
                content += "<a title='"+total+"'>" + total + "</a>";
                //页码在中间部分时候
            } else {
                content += "<a title='"+ parseInt((total+1)/2) +"'>" + parseInt((total+1)/2) + "</a>";
                content += ". . .";
                for(var i = total - 4; i < total + 1; i++) {
                    if(current == i) {
                        content += "<a class='current' title='"+i+"'>" + i + "</a>";
                    } else {
                        content += "<a title='"+i+"'>" + i + "</a>";
                    }
                }
            }
        }
        //页面总数小于6的时候
    } else {
        for(var i = 1; i < total + 1; i++) {
            if(current == i) {
                content += "<a class='current' title='"+i+"'>" + i + "</a>";
            } else {
                content += "<a title='"+i+"'>" + i + "</a>";
            }
        }
    }
    content += "<a id='nextPage' title='"+nextCurrent+"'>下一页</a>";
    content += "<a id=\"lastPage\" title='"+(total)+"'>尾页</a>";
    content += "<span class='totalPages'> 共<span>"+total+"</span>页 </span>";
    content += "<span class='totalSize'> 共<span>"+totalNum+"</span>条记录 </span>";
    options.element.innerHTML = content;
};
//添加页面操作事件
// var bindEvent = function(options) {
//         var num = $(this).html();
//         var id=$(this).attr("id");
//         if(id == "prePage") {
//             if(me.options.pageNo == 1) {
//                 me.options.pageNo = 1;
//             } else {
//                 me.options.pageNo = +me.options.pageNo - 1;
//             }
//         } else if(id == "nextPage") {
//             if(me.options.pageNo == me.options.totalPage) {
//                 me.options.pageNo = me.options.totalPage
//             } else {
//                 me.options.pageNo = +me.options.pageNo + 1;
//             }
//
//         } else if(id =="firstPage") {
//             me.options.pageNo = 1;
//         } else if(id =="lastPage") {
//             me.options.pageNo = me.options.totalPage;
//         }else{
//             me.options.pageNo = +num;
//         }
//         me.creatHtml();
//         if(me.options.callback) {
//             me.options.callback(me.options.pageNo);
//         }
//     });
// };