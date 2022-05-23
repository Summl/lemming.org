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
                let title = document.createElement("p");
                title.innerText = json[i].title;
                title.classList.add("title");
                out.append(title);
                console.log(json[i].id);
                // document.write(json[i].title);
                // document.write('<br>');
                for(let j=0;j<json[i].option.length;j++) {
                    let option = document.createElement("p");
                    option.innerText = json[i].option[j];
                    option.classList.add("option");
                    out.append(option);
                }
                let br = document.createElement("br");
                br.innerText = "";
                out.append(br);
            }
            let bt = document.createElement("button");
            bt.innerText = "提交";
            out.append(bt);
        }
    }
}

