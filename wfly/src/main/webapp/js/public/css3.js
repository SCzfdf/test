window.onload=function(){
    ready();
};

var ready = function () {
    document.getElementById("musicIcon").onclick = audio_musicIcon_onClick_trigger;

    //移动端
    document.addEventListener('touchstart',touch1, false);
    document.addEventListener('touchend',touch3, true);
    document.addEventListener('touchmove',touch2, false);
    //Web端
    document.addEventListener('mousedown',Wtouch1, false);
    document.addEventListener('mouseup',touch3, false);
    document.addEventListener('mousemove',Wtouch2, false);
};

//点击音乐按钮切换播放状态
var audio_musicIcon_onClick_trigger = function () {
    var audio = document.getElementsByTagName("audio")[0];
    var musicIcon = document.getElementById("musicIcon").firstElementChild;

    if(audio.paused){//如果当前是暂停状态
        musicIcon.style.animationPlayState = "running";
        musicIcon.style.WebkitAnimationPlayState = "running";
        audio.play();//播放
    }else{
        musicIcon.style.animationPlayState = "paused";
        musicIcon.style.WebkitAnimationPlayState = "paused";
        audio.pause(); //暂停
    }
};

var startY,endY=0;
var changeY=0;
var key = false;//防止web移动鼠标频繁触发touch3
function touch1 (event){
    var event = event || window.event;
    startY = event.touches[0].clientY;//移动设备不能直接用event 获取client
    key = true;
}
function touch2 (event) {
    // if (key) {
    //     var event = event || window.event;
    //     endY = event.touches[0].clientY;
    //     changeY = endY - startY;
    //     if (changeY < 0) {//向上滑
    //         document.getElementById("page1").style.transform = "translate(0px," + changeY + "px) translateZ(0px) scale(" + ((endY / startY) * 0.3 + 0.7) + ")";
    //     } else {
    //         document.getElementById("page1").style.transform = "translate(0px," + changeY + "px) translateZ(0px) scale(" + ((startY / endY) * 0.3 + 0.7) + ")";
    //     }
    // }
    if (key) {
        var event = event || window.event;
        endY = event.touches[0].clientY;
        changeY = endY - startY;
    }
}
function Wtouch1 (event){
    var event = event || window.event;
    startY = event.clientY;
    key = true;
}
function Wtouch2 (event) {
    if (key) {
        var event = event || window.event;
        endY = event.clientY;
        changeY = endY - startY;
    }
}
function touch3 (event){
    key = false;
    id = 0;

    allDiv = document.getElementById("pageContext").getElementsByTagName("div");
    divSize = allDiv.length;
    for (i=1; i<divSize; i++){//0是音乐div
        d = allDiv[i];
        if (d.classList.contains("show")){
            id = d.dataset.appid;
            break;
        }
    }

    if (startY+70 > endY && startY-70 < endY){ //如果滑动距离太短就不切换
        document.getElementById("page1").style.transform = "translate(0px, 0px) translateZ(0px) scale(1)";
    }else{
        if (startY>endY && id<divSize){//下一页
            thisPage = "page"+id;
            nextPage = "page"+(parseInt(id)+1);
            
            document.getElementById(nextPage).classList.remove("hide");
            document.getElementById(nextPage).classList.add("show");
            document.getElementById(thisPage).style.transform = "translate3d(0,-100%,0)";
            document.getElementById(thisPage).addEventListener("webkitTransitionEnd", function () {
                document.getElementById(thisPage).classList.remove("show");
                document.getElementById(thisPage).classList.add("hide");
            });
        }else if(endY>startY && parseInt(id)>1){//上一页
            thisPage = "page"+id;//这句一定要写,不然page2到page1时 page1会自动换回hide 非常神奇
            lastPage = "page"+(parseInt(id)-1);

            //TODO 很奇怪不知道为什么不用给page id加上hide 就自动切换了
            //TODO 另 上滑不用给下一个添加hide 因为z-index会覆盖它
            document.getElementById(lastPage).classList.remove("hide");
            document.getElementById(lastPage).classList.add("show");
            setTimeout(function(){//定时器->把display属性由none改为block需要等待100毫秒才能执行动画
                document.getElementById(lastPage).style.transform = "translate3d(0,0,0)";
                document.getElementById(lastPage).addEventListener("webkitTransitionEnd", function () {
                    document.getElementById(thisPage).classList.remove("show");
                    document.getElementById(thisPage).classList.add("hide");
                });
            },100);

        }
    }

}
