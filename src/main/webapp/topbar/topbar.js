class TopBar {
    userName;
    constructor(id){
        let topBar = document.getElementById(id)
        topBar.append(this.buildTopBar())
    }
    buildTopBar() {
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
        nav.classList.add("col-md-6")
        nav.classList.add("col-xs-10")

        let navRow = document.createElement("div")
        navRow.classList.add("row")

        let item_1 = document.createElement("a")
        item_1.classList.add("nav_item")
        item_1.classList.add("col-xs-2")
        item_1.href="#"
        item_1.innerText="首页"

        let item_2 = document.createElement("a")
        item_2.classList.add("nav_item")
        item_2.classList.add("col-xs-2")
        item_2.href="newpost.jsp"
        item_2.innerText="发帖"

        let item_3 = document.createElement("a")
        item_3.classList.add("nav_item")
        item_3.classList.add("col-xs-2")
        item_3.href="#"
        item_3.innerText="准则"

        let item_4 = document.createElement("a")
        item_4.classList.add("nav_item")
        item_4.classList.add("col-xs-2")
        item_4.href="#"
        item_4.innerText="关于"

        let user = document.createElement("div")
        user.classList.add("user")
        user.classList.add("col-md-3")
        user.classList.add("col-xs-2")

        let headImage = document.createElement("img")
        headImage.id="head-image"
        headImage.src="images/128x128.png"

        let userBox = document.createElement("div")
        userBox.classList.add("userBox")

        this.userName = document.createElement("a")
        this.userName.id="userName"
        this.userName.classList.add("hidden-sm")
        this.userName.classList.add("hidden-xs")
        this.userName.href="login.jsp"
        this.userName.innerText="注册"

        let groupName = document.createElement("span")
        groupName.id="groupName"
        groupName.classList.add("hidden-sm")
        groupName.classList.add("hidden-xs")
        groupName.innerText="游客"

        header.append(row)
        row.append(logo)
        row.append(nav)
        nav.append(navRow)
        navRow.append(item_1)
        navRow.append(item_2)
        navRow.append(item_3)
        navRow.append(item_4)
        row.append(user)
        user.append(headImage)
        user.append(userBox)
        userBox.append(this.userName)
        userBox.append(groupName)

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
        }
    }

})