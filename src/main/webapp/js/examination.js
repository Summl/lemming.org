/*var json = '{"course": {"name": "JavaScript","author": "http://c.biancheng.net/","year": 2021,"genre": "Getting Started tutorial","bestseller": true},"fruits": ["Apple","Banana","Strawberry","Mango"]}';
var obj = JSON.parse(json);
console.log(obj.course);
console.log(obj.fruits);*/



window.onload = function(){
    let url = "js/my.json";     //json文件的url
    let request = new XMLHttpRequest();
    request.open("get",url);    //设置请求方法与路径
    request.send(null);     //不发送数据到服务器
    request.onload = function (){
        if(request.status===200){
            let json = JSON.parse(request.responseText);
            for(let i=0;i<json.length;i++){
                let out = document.getElementById("out");
                let inner = document.getElementById("inner");
                let title = document.createElement("p");
                title.innerText = json[i].title;
                title.classList.add("title");
                inner.append(title);

                console.log(json[i].id);
                for(let j=0;j<json[i].option.length;j++) {
                    let optionbox  = document.createElement("div");
                    let optiontext = document.createElement("span");
                    let option = document.createElement("input");
                    option.type = "radio";
                    option.name = json[i].id;

                    optiontext.innerText = json[i].option[j];
                    optionbox.append(option);
                    optionbox.append(optiontext);
                    option.classList.add("option");
                    inner.append(optionbox);
                }
                let br = document.createElement("br");
                br.innerText = "";
                inner.append(br);
            }
        }
    }
}

