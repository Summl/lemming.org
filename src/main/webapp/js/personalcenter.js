// 界面跳转
function yincang(){
    let list=document.getElementsByClassName("rightboxpage");
    for(let i=0;i<list.length;i++){
        list[i].style.display="none";
    }
}
//导航栏点击背景
function listyincang(){
    let list=document.getElementsByTagName("li")
    for(let i=0;i<list.length;i++){
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

// function updateUserImg() {
//     let url ="user?type=info"; //Json文件的url
//     let request = new XMLHttpRequest();
//     request.open("get",url); //设置请求方法与路径
//     request.send(null); //不发送数据到服务器
//     request.onload=function (){
//         if(request.status === 200) {
//             let json = JSON.parse(request.responseText);
//             let img_filename = json.img_filename;
//             // String uuid = img_filename.value;
//             // $(".leftimage").src=
//
//         }
//     }

//获取邮箱
function getEmailVerifyCode2() {
    let getMailCodeBtn2 = document.getElementById("emailcodebtn")
    let email2 = document.getElementById("newemail");
    if (email2.value === ""){
        return false;
    }
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'verifyCode?type=email&to='+email2.value, true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    getMailCodeBtn2.innerText = "正在发送，请稍候"
    httpRequest.onreadystatechange = function () {
        getMailCodeBtn2.innerText = "重新发送"
    };
    return false;
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

    let uploadimage = document.getElementById("uploadimage")
    let inputimage = document.getElementById("inputimage")
    //let showimg = document.getElementById("showimg")
    uploadimage.addEventListener("click",function () {
        inputimage.click()

    })

    inputimage.addEventListener("change",function (){
        let reader = new FileReader()
        let file = this.files[0]
        reader.readAsDataURL(file)
        reader.addEventListener("loadend",function () {
            uploadimage.style.backgroundImage = "url("+this.result+")"
            uploadimage.style.border = "4px dashed wheat;"
            //$("#showimg").src=this.result;
            //uploadimage.style.border = "border: 1px solid #ffa200;";
            //uploadimage.style.boxShadow = "box-shadow: 0 0 5px #ffa200;"
        })
    })
    let getMailCodeBtn2 = document.getElementById("emailcodebtn")
    getMailCodeBtn2.addEventListener("click",getEmailVerifyCode2)

}
function updateImg() {
    $("#secletImg").submit();
}
function updateBaseInfo(){
    if($("#updatename").val().trim() === ""){
        $("#textBaseInfo").text("请输入用户名！");
        $("#mymodalBaseInfo").modal();
        return;
    }
    if($("#updatephone").val().trim() === ""){
        $("#textBaseInfo").text("请输入电话号码！");
        $("#mymodalBaseInfo").modal();
        return;
    }
    let phoneReg = /^1[3-9][0-9]{9}$/;
    if(!phoneReg.test($("#updatephone").val())){
        $("#textBaseInfo").text("请输入正确的电话号码！");
        $("#mymodalBaseInfo").modal();
        return;
    }
    if($("#updateemail").val().trim() === ""){
        $("#textBaseInfo").text("请输入邮箱！");
        $("#mymodalBaseInfo").modal();
        return;
    }
    let emailReg = /\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}/;
    if(!emailReg.test($("#updateemail").val())){
        $("#textBaseInfo").text("请输入正确的邮箱！");
        $("#mymodalBaseInfo").modal();
        return;
    }
    $("#selectBaseinfo").submit();
}
function updatePassword(){
    if($("#oldpassword").val().trim() === ""){
        $("#textUpdatePassword").text("请输入原始密码！");
        $("#mymodalUpdatePassword").modal();
        return;
    }
    if($("#newpassword").val().trim() === ""){
        $("#textUpdatePassword").text("请输入新密码！");
        $("#mymodalUpdatePassword").modal();
        return;
    }
    if($("#renewpassword").val().trim() === ""){
        $("#textUpdatePassword").text("请重新输入新密码！");
        $("#mymodalUpdatePassword").modal();
        return;
    }
    if($("#newpassword").val() != $("#renewpassword").val()){
        $("#textUpdatePassword").text("您两次输入的新密码不一致！");
        $("#mymodalUpdatePassword").modal();
        return;
    }

    $("#selectUpdatePassword").submit();
}

//修改邮箱
function saveEmail(){
    if($("#oldemail").val().trim() === ""){
        $("#textUpdateEmail").text("请输入原始邮箱地址！");
        $("#mymodalUpdateEmail").modal();
        return;
    }
    if($("#newemail").val().trim() === ""){
        $("#textUpdateEmail").text("请输入新邮箱地址！");
        $("#mymodalUpdateEmail").modal();
        return;
    }
    if($("#emailcode").val().trim() === ""){
        $("#textUpdateEmail").text("请输入邮箱验证码！");
        $("#mymodalUpdateEmail").modal();
        return;
    }
    $("#selectUpdateEmail").submit();
}

// 验证码
function changCode(img) {
    img.src = "verifyCode?type=image&r="+Math.random();
}
//用户注销
function buttonrevoke(){
    if($("#revokepassword").val().trim() === ""){
        $("#textRevoke").text("请输入您的密码！");
        $("#mymodalRevoke").modal();
        return;
    }
        $("#selectrevokebutton").submit();
}


