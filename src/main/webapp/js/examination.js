/*var json = '{"course": {"name": "JavaScript","author": "http://c.biancheng.net/","year": 2021,"genre": "Getting Started tutorial","bestseller": true},"fruits": ["Apple","Banana","Strawberry","Mango"]}';
var obj = JSON.parse(json);
console.log(obj.course);
console.log(obj.fruits);*/

window.onload = function(){
    let url = "exam?type=get";     //json文件的url
    let request = new XMLHttpRequest();
    request.open("get",url);    //设置请求方法与路径
    request.send();     //不发送数据到服务器
    request.onload = function (){
        if(request.status===200){
            let json = JSON.parse(request.responseText);
            console.log(json);
            for(let i=0;i<json.length;i++){
                let out = document.getElementById("out");
                let title = document.createElement("h4");
                title.innerText = i+1 +". "+ json[i].title;
                title.classList.add("title");
                out.append(title);
                console.log(json[i].id);
                for(let j=0;j<json[i].option.length;j++) {
                    let optionbox  = document.createElement("div");
                    let optiontext = document.createElement("span");    //题目内容
                    let option = document.createElement("input");   //单选按钮
                    option.name= "ti"+json[i].id;
                    option.type = "radio";
                    option.value = j.toString();
                    console.log(option.value);
                    optiontext.innerText = json[i].option[j];
                    optionbox.append(option);
                    optionbox.append(optiontext);
                    optionbox.classList.add("option");
                    out.append(optionbox);

                    optionbox.addEventListener("click",function () {
                        option.checked = true;
                    })
                }
            }
        }
    }
}



