// function updateBigPhoto(){
//     let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
//     httpRequest.open('GET', 'https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN', true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
//     httpRequest.send();//第三步：发送请求  将请求参数写在URL中
//     /**
//      * 获取数据后的处理程序
//      */
//     httpRequest.onreadystatechange = function () {
//         if (httpRequest.readyState === 4 && httpRequest.status === 200) {
//             let json = JSON.parse(httpRequest.responseText);//获取到json字符串，还需解析
//             console.log(json)
//             let imgUrl = json.url;
//             let title = json.title;
//             let imageDiv = document.getElementsByTagName("body")[0]
//             imageDiv.style.backgroundImage="url("+imgUrl+")"
//         }
//     };
// }

let CurrentPage = 1

function buildItemBox(title, brief, image) {
    let res = document.createElement("div")
    res.classList.add("itemBox")
    let title_h3 = document.createElement("h3")
    title_h3.innerText=title
    title_h3.classList.add("itemTitle")
    let brief_p = document.createElement("p")
    brief_p.innerText=brief
    title_h3.classList.add("itemBrief")
    if (image === undefined){
        res.classList.add("itemBoxSmail")
    }else {
        res.classList.add("itemBoxBig")
        let image_div = document.createElement("div")
        image_div.classList.add("postImage")
        image_div.style.backgroundImage = "url(data/images/"+image+")"
        res.append(image_div)
    }
    res.append(title_h3)
    res.append(brief_p)
    return res
}

function loadPost() {
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'post?type=list&page='+CurrentPage.toString(), true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            let json = JSON.parse(httpRequest.responseText);//获取到json字符串，还需解析
            let list = json.list
            CurrentPage = json.page + 1
            for (let i in list) {
                console.log(list[i])
                let read = list[i].readNum
                let title = list[i].title
                let brief = list[i].brief
                let image = list[i].imageFilename
                let boxType
                if (brief === undefined){
                    brief = ""
                }

                let contentList = document.getElementsByClassName("contentList")[0]
                contentList.append(buildItemBox(title,brief,image))
            }
        }
    };
}
window.onload = function () {
    loadPost()
}