function updateBigPhoto(){
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'bing', true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    /**
     * 获取数据后的处理程序
     */
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            let json = JSON.parse(httpRequest.responseText);//获取到json字符串，还需解析
            console.log(json)
            let imgUrl = "https://cn.bing.com"+json.images[0].url;
            let title = json.images[0].title;
            let copyright = json.images[0].copyright;
            let imageDiv = document.getElementById("bg_image")
            let imageTitle = document.getElementById("bg_title")
            let imageCopyright = document.getElementById("bg_copyright")
            imageDiv.style.backgroundImage="url("+imgUrl+")"
            imageTitle.innerText=title
            imageCopyright.innerText=copyright
        }
    };
}
let CurrentPage = 0
let IsLoading = false
function buildItemBox(postId, title, brief, image, like, readNum) {
    let res = document.createElement("div")
    res.classList.add("itemBox")
    let title_h3 = document.createElement("h3")
    title_h3.innerText=title
    title_h3.classList.add("itemTitle")
    let brief_p = document.createElement("p")
    brief_p.innerText=brief
    brief_p.classList.add("itemBrief")
    if (image === undefined){
        res.classList.add("itemBoxSmail")
    }else {
        res.classList.add("itemBoxBig")
        let image_div = document.createElement("div")
        image_div.classList.add("postImage")
        image_div.style.backgroundImage = "url(data/images/"+image+")"
        res.append(image_div)
    }
    let underBox = document.createElement("div")
    underBox.classList.add("underBox")
    let like_span = document.createElement("span")
    like_span.innerHTML = "<i class=\"bi bi-heart-fill\"></i> "+like
    underBox.append(like_span)
    let read_span = document.createElement("span")
    read_span.innerHTML = "<i class=\"bi bi-eye-fill\"></i> "+readNum
    underBox.append(read_span)
    res.append(title_h3)
    res.append(brief_p)
    res.append(underBox)
    res.addEventListener("click",function () {
        window.open("post?type=page&post="+postId.toString())
    })
    return res
}

function loadPost() {
    if (IsLoading){
        return;
    }
    IsLoading = true;
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'post?type=list&page='+(CurrentPage+1).toString(), true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            let json = JSON.parse(httpRequest.responseText);//获取到json字符串，还需解析
            if (json.list === undefined) {
                loadPost()
                console.log("200")
                return;
            }
            let list = json.list
            if (CurrentPage === json.page) {
                return;
            }
            CurrentPage = json.page
            for (let i in list) {
                let read = list[i].readNum
                let title = list[i].title
                let brief = list[i].brief
                let image = list[i].imageFilename
                let postID = list[i].id
                if (brief === undefined){
                    brief = ""
                }
                let contentList = document.getElementsByClassName("contentList")[0]
                contentList.append(buildItemBox(postID,title,brief,image,list[i].likeNum,read))
            }
        }
        IsLoading = false
    };
}
window.onload = function () {
    loadPost()
    updateBigPhoto()

    document.addEventListener("scroll",function () {
        if (document.scrollingElement.scrollTop+$(window).height() >= $("body").height()-20){
            loadPost()
        }
    })
}