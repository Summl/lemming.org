// 界面跳转
function yincang(){
    var list=document.getElementsByClassName("rightboxpage");
    for(var i=0;i<list.length;i++){
        list[i].style.display="none";
    }
}
//导航栏点击背景
function listyincang(){
    var list=document.getElementsByTagName("li")
    for(var i=0;i<list.length;i++){
        list[i].className="";
    }
}
function page1(){
    yincang();
    listyincang();
    document.getElementById("interface-1").style.display="block";
    document.getElementById("li-1").className="active";
}
function page2(){
    yincang();
    listyincang();
    document.getElementById("interface-2").style.display="block";
    document.getElementById("li-2").className="active";
}
function page3(){
    yincang();
    listyincang();
    document.getElementById("interface-3").style.display="block";
    document.getElementById("li-3").className="active";
}
function page4(){
    yincang();
    listyincang();
    document.getElementById("interface-4").style.display="block";
    document.getElementById("li-4").className="active";
}
function page5(){
    yincang();
    listyincang();
    document.getElementById("interface-5").style.display="block";
    document.getElementById("li-5").className="active";
}

//通过界面修改信息
function updateUserBaseInfo() {
    let url ="user?type=info"; //Json文件的url
    let request = new XMLHttpRequest();
    request.open("get",url); //设置请求方法与路径
    request.send(null); //不发送数据到服务器
    request.onload=function (){
        if(request.status === 200){
            let json = JSON.parse(request.responseText);
            let name = json.name;
            let sex = json.sex;
            let phone = json.phone;
            let email=json.email;
            let psot_count=json.post_count;
            let groupname=json.user_group.name;
            let registration_time=json.registration_time;



            let updatename = document.getElementById("updatename");
            let man = document.getElementById("man");
            let woman = document.getElementById("woman");
            let updatephone = document.getElementById("updatephone");
            let updateemail = document.getElementById("updateemail");
            let postcount = document.getElementById("postcount");
            let registertime=document.getElementById("registertime");
            let userlimit = document.getElementById("userlimit");
            let nosex= document.getElementById("no_sex");
            updatename.value = name;
            console.log(sex)
            updateemail.value = email;
            userlimit.value = groupname;

            if(sex === "男"){
                man.checked = true;
            }else if(sex === "女"){
                woman.checked = true;
            }else {
                nosex.checked = true;
            }

            if(phone === undefined){
                updatephone.placeholder = "请输入电话号码";
                updatephone.value = "";
            }else{
                updatephone.value = phone;
            }


            postcount.value = psot_count;
            registertime.value = registration_time;
        }
    }
}
window.onload=function (){
    updateUserBaseInfo();
}
function checkInfo(){
    if($("#updatename").val().trim() === ""){
        $("#textBaseInfo").text("请输入用户名！");
        $("#mymodalBaseInfo").modal();
        return false;
    }
    if($("#updatephone").val().trim() === ""){
        $("#textBaseInfo").text("请输入电话号码！");
        $("#mymodalBaseInfo").modal()
        return false;
    }
    let phoneReg = /^1[3-9][0-9]{9}$/;
    if(!phoneReg.test($("#updatephone").val())){
        $("#textBaseInfo").text("请输入正确的电话号码！");
        $("#mymodalBaseInfo").modal();
        return false;
    }
    if($("#updateemail").val().trim() === ""){
        $("#textBaseInfo").text("请输入邮箱！");
        $("#mymodalBaseInfo").modal();
        return false;
    }
    let emailReg = /\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}/;
    if(!emailReg.test($("#updateemail").val())){
        $("#textBaseInfo").text("请输入正确的邮箱！");
        $("#mymodalBaseInfo").modal()
        return false;
    }
    return true
}