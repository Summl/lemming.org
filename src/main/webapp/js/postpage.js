const lute = Lute.New();
const html2MdRenderer = {
    renderLinkDest: function (node, entering) {
        if (entering) {
            console.log('重写 LinkDest 节点', node.__internal_object__.typ, node.TokensStr(), entering);
            return [node.TokensStr(), Lute.WalkContinue]
        } else {
            return ["", Lute.WalkContinue]
        }
    },
    renderBang: function (node, entering) {
        if (entering) {
            console.log('重写 Bang 节点', node.TokensStr(), entering);
            return ["!", Lute.WalkContinue]
        } else {
            return ["", Lute.WalkContinue]
        }
    },
};
lute.SetJSRenderers({
    "renderers": {
        "HTML2Md": html2MdRenderer,
    }
});

function mdToHtml(markdownText) {
    return lute.MarkdownStr("", markdownText)
}

// 点击事件触发生生元素等一系列动作的初始状态
let bombFlag = true;
// body节点
function addLikeAnimation (){
        // 随机产生文字颜色
        let color1 = Math.floor((Math.random()*255));
    let color2 = Math.floor((Math.random()*255));
    let color3 = Math.floor((Math.random()*255));
        // 初始化定时器
    let _timer = null;
        // 创建节点
    let elSpan = document.createElement("span");
        // 添加内容到节点
        elSpan.innerHTML = "+1";
        // 初始节点化样式
        elSpan.style.zIndex = "999";
        elSpan.style.position = "absolute";
        elSpan.style.left = "5px";
        elSpan.style.color = 'rgb('+color1+','+color2+','+color3+')';
        elSpan.style.fontWeight = "bold";
        elSpan.className = "floatSpan";
        // 将元素追加到body中
        document.getElementById("btn_like").append(elSpan);
    let cur_y = -10;
        // 透明度
    let cur_opacity = 1;
        // 字体大小
    let cur_fontSize = 14;
        _timer = setInterval(function(){
            // 渐变变量
            cur_y += -10;
            cur_opacity -= 0.1;
            cur_fontSize += 1;
            // 渐变变量赋值（因为有单位的关系 所以不能直接加减 通过中间变量来赋值）
            elSpan.style.top = cur_y + "px";
            elSpan.style.opacity = cur_opacity;
            elSpan.style.fontSize = cur_fontSize + "px";
        },50);
        // 时间到了之后清空定时器 清除刚才添加的元素 并且恢复点击触发事件
        setTimeout(function(){
            clearInterval(_timer);
            document.getElementById("like_number").removeChild(elSpan);
            bombFlag = true;
        },500);
    // 暂停点击触发事件
    // bombFlag = false;
}

window.onload = function () {
    let btn_like = document.getElementById("btn_like")
    btn_like.addEventListener("click",function () {
        console.log("like")
        let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
        httpRequest.open('GET', 'post?type=like&post='+postID, true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
        httpRequest.send();//第三步：发送请求  将请求参数写在URL中
        httpRequest.onreadystatechange = function (){
            if (httpRequest.readyState === 4 && httpRequest.status === 200) {
                let json = JSON.parse(httpRequest.responseText)
                if (json.error !== undefined){
                    return
                }
                document.getElementById("like_number").innerText = json.like_num
                addLikeAnimation();
            }
        };
    })

}