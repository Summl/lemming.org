// 页面转换
// function page(a){
//     //导航列表列
//     var bt=document.getElementsByTagName("a");
//     //被切换的界面
//     var pages=document.getElementsById("interface");
//     for(i=0;i<5;i++){
//         bt[i].className="";		//设置样式
//         pages[i].style="display:none";
//     }
//     // 导航栏点击
//     bt[a].className="active";
//     pages[a].style="display:block";
// }
function yincang(){
    var list=document.getElementsByClassName("rightboxpage");
    for(var i=0;i<list.length;i++){
        list[i].style.display="none";

    }

}

function page1(){
    yincang();
    document.getElementById("interface-1").style.display="block";
}
function page2(){
    yincang();
    document.getElementById("interface-2").style.display="block";

}
function page3(){
    yincang();
    document.getElementById("interface-3").style.display="block";

}
function page4(){
    yincang();
    document.getElementById("interface-4").style.display="block";

}
function page5(){
    yincang();
    document.getElementById("interface-5").style.display="block";

}