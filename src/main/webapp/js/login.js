function switchPage(index) {
    let pageLogin = document.getElementById("pageLogin")
    let pageRegister = document.getElementById("pageRegister")
    let switchToLogin = document.getElementById("switchToLogin")
    let switchToRegister = document.getElementById("switchToRegister")
    if (index===1){
        // 注册
        pageLogin.style.display = "none";
        pageRegister.style.display = "block";
        switchToLogin.classList.remove("CurrentPageLi");
        switchToRegister.classList.add("CurrentPageLi")
    }else {
        // 登录
        pageLogin.style.display = "block";
        pageRegister.style.display = "none";
        switchToLogin.classList.add("CurrentPageLi");
        switchToRegister.classList.remove("CurrentPageLi")
    }
}
function changCode(img) {
    img.src = "verifyCode?type=image&r="+Math.random();
}
function getEmailVerifyCode() {
    let getMailCodeBtn = document.getElementById("btn_mailVerifyCode")
    let email = document.getElementById("register_email");
    if (email.value === ""){
        return false;
    }
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'verifyCode?type=email&to='+email.value, true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    getMailCodeBtn.innerText = "正在发送，请稍候"
    httpRequest.onreadystatechange = function () {
        getMailCodeBtn.innerText = "重新发送"
    };
    return false;
}
function checkRegisterForm() {
    if($("#register_name").val() === ""){
        $("#tip").text("用户名不能为空！");
        return false;
    }else if($("#register_pwd").val() === ""){
        $("#tip").text("密码不能为空！");
        return false;
    }else if($("#register_email").val() === ""){
        $("#tip").text("邮箱不能为空！");
        return false;
    }else if($("#register_verifyCode").val() === ""){
        $("#tip").text("验证码不能为空！");
        return false;
    }
    return  true;
}
function checkLoginForm() {
    if($("#login_name").val() === ""){
        $("#tip").text("用户名或邮箱不能为空！");
        return false;
    }else if($("#login_pwd").val() === ""){
        $("#tip").text("密码不能为空！");
        return false;
    }else if($("#login_verifyCode").val() === ""){
        $("#tip").text("验证码不能为空！");
        return false;
    }
    return true;
}
window.onload = function () {
    let switchToLogin = document.getElementById("switchToLogin")
    let switchToRegister = document.getElementById("switchToRegister")
    switchToLogin.addEventListener("click",function () {
        switchPage(0);
    })
    switchToRegister.addEventListener("click",function () {
        switchPage(1);
    })
    switchPage(0);

    let getMailCodeBtn = document.getElementById("btn_mailVerifyCode")
    getMailCodeBtn.addEventListener("click",getEmailVerifyCode)

    let page = window.location.hash
    if (page != null){
        if (page==="#register"){
            switchPage(1)
        }else {
            switchPage(0)
        }
    }
    //选择登录密码是否显示
    $('.show_btn_login').click(function (){
        let $login_pwd = $("#login_pwd");
        let $type1 = $login_pwd.attr('type');
        let $show_btn = $(".show_btn");
        if($type1 == "password"){
            $login_pwd.attr('type','text');
        }else if($type1 == "text"){
            $login_pwd.attr('type','password');
        }
    });
    //选择注册密码是否显示
    $('.show_btn_register').click(function (){
        let $show_btn_register=$('.show_btn_register')
        let $register_pwd = $("#register_pwd");
        let $type2 = $register_pwd.attr('type');
        if($type2 == "password"){
            $register_pwd.attr('type','text');
        }else if($type2 == "text"){
            $register_pwd.attr('type','password');
        }
    });

}
