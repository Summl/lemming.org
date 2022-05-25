class TopBar {
    userName;
    groupName;
    user;
    userMenu;
    menuItem;
    headImage;
    topBar;
    constructor(id){
        this.topBar = document.getElementById(id)
        this.topBar.append(this.buildTopBar())
    }
    onTransparent(){
        this.topBar.style.backgroundColor = "#fff"
    }
    offTransparent(){
        this.topBar.style.backgroundColor = "#fffa"
    }
    addMenuItem(text, url){
        this.menuItem = document.createElement("li")
        this.menuItem.innerHTML = text;
        this.menuItem.addEventListener("click",function (){
            window.location.href = url;
        })
        this.userMenu.append(this.menuItem)
    }

    addGoTopButton(){
        let goTopBtn = document.createElement("div")
        goTopBtn.classList.add("goTopBtn")
        goTopBtn.id="goTopBtn"
        goTopBtn.innerHTML="<i class=\"bi bi-arrow-up-square-fill\"></i>"
        document.getElementsByTagName("body")[0].append(goTopBtn)
        goTopBtn.addEventListener("click",function () {
            $("html, body").animate({scrollTop : 0},700);
            $("#goTopBtn").animate({
                top:'-=20px'
            },200,function () {
                $("#goTopBtn").animate({
                    top:'+=20px'
                },200)
            });

        })
    }

    buildTopBar() {
        this.userMenu = document.createElement("ul")
        this.userMenu.classList.add("userMenu")

        let header = document.createElement("header")
        header.classList.add("container")

        let row = document.createElement("div")
        row.classList.add("row")

        let logo = document.createElement("div")
        logo.classList.add("logo")
        logo.classList.add("col-md-2")
        logo.classList.add("hidden-sm")
        logo.classList.add("hidden-xs")
        logo.innerText = "旅鼠"

        let nav = document.createElement("nav")
        nav.classList.add("col-md-7")
        nav.classList.add("col-xs-10")

        let navRow = document.createElement("div")
        navRow.classList.add("row")

        let item_1 = document.createElement("a")
        item_1.classList.add("nav_item")
        item_1.classList.add("col-xs-2")
        item_1.href="index.jsp"
        item_1.innerText="首页"

        let item_2 = document.createElement("a")
        item_2.classList.add("nav_item")
        item_2.classList.add("col-xs-2")
        item_2.href="newpost.jsp"
        item_2.innerText="发帖"

        let item_3 = document.createElement("a")
        item_3.classList.add("nav_item")
        item_3.classList.add("col-xs-2")
        item_3.href="norm.jsp"
        item_3.innerText="准则"

        let item_4 = document.createElement("a")
        item_4.classList.add("nav_item")
        item_4.classList.add("col-xs-2")
        item_4.href="about.jsp"
        item_4.innerText="关于"

        this.user = document.createElement("div")
        this.user.classList.add("user")
        this.user.classList.add("col-md-3")
        this.user.classList.add("col-xs-2")

        this.headImage = document.createElement("img")
        this.headImage.id="head-image"
        this.headImage.src="images/128x128.png"

        let userBox = document.createElement("div")
        userBox.classList.add("userBox")
        userBox.classList.add("hidden-sm")
        userBox.classList.add("hidden-xs")

        this.userName = document.createElement("a")
        this.userName.id="userName"
        this.userName.href="login.jsp"
        this.userName.innerText="登录 / 注册"

        this.groupName = document.createElement("span")
        this.groupName.id="groupName"
        this.groupName.classList.add("hidden-sm")
        this.groupName.classList.add("hidden-xs")
        this.groupName.innerText="游客"

        header.append(row)
        row.append(logo)
        row.append(nav)
        nav.append(navRow)
        navRow.append(item_1)
        navRow.append(item_2)
        navRow.append(item_3)
        navRow.append(item_4)
        row.append(this.user)
        this.user.append(this.headImage)
        this.user.append(userBox)
        this.user.append(this.userMenu)
        userBox.append(this.userName)
        userBox.append(this.groupName)
        return header
    }
}

window.addEventListener("load",function () {
    let topBar = new TopBar("topBar")
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'user?type=info', true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            let json = JSON.parse(httpRequest.responseText);
            topBar.userName.innerText = json.name
            topBar.userName.href = "personalcenter.jsp"
            topBar.groupName.innerText = json.user_group.name;
            topBar.addMenuItem("个人中心","personalcenter.jsp")
            if (json.user_group.id<1){
                topBar.addMenuItem("申请博主","examination.jsp")
            }
            topBar.addMenuItem("退出登录","user?type=exit")
        }
    }
    topBar.headImage.addEventListener("mousemove",function () {
        topBar.userMenu.style.right=topBar.headImage.right
        topBar.userMenu.style.display="block"
    })
    topBar.userMenu.addEventListener("mouseleave",function (){
        topBar.userMenu.style.display="none"
    })
    topBar.topBar.addEventListener("mouseleave",function (){
        topBar.userMenu.style.display="none"
    })

    let isChrome = window.navigator.userAgent.indexOf("Chrome") > -1;
    console.log(isChrome)
    if (isChrome){
        document.addEventListener("scroll",function () {

            if (document.scrollingElement.scrollTop===0){
                topBar.onTransparent()
            }else {
                topBar.offTransparent()
            }
        })
    }
    topBar.addGoTopButton()

})