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
            let groupid=json.groupId;
            let registration_time=json.registration_time;
            let updatename = document.getElementById("updatename");
            let updatesex = document.getElementById("updatesex");
            let man = document.getElementById("man");
            let woman = document.getElementById("woman");
            let updatephone = document.getElementById("updatephone");
            let updateemail = document.getElementById("updateemail");
            let postcount = document.getElementById("postcount");
            let registertime=document.getElementById("registertime");
            updatename.value = name;

            if(sex == null || sex == "男"){
                man.checked = true;
            }else if(sex == "女"){
                woman.checkd = true;
            }

            if(phone == null){
                updatephone.placeholder = "请输入电话号码";
                updatephone.value = "";
            }else{
                updatephone.value = phone;
            }
            updateemail.value = email;
            postcount.value = psot_count;
            registertime.value = registration_time;
        }
    }
}
window.onload=function (){
    updateUserBaseInfo();
}