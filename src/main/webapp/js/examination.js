/*var json = '{"course": {"name": "JavaScript","author": "http://c.biancheng.net/","year": 2021,"genre": "Getting Started tutorial","bestseller": true},"fruits": ["Apple","Banana","Strawberry","Mango"]}';
var obj = JSON.parse(json);
console.log(obj.course);
console.log(obj.fruits);*/

window.onload=function(){
    var url = "js/my.json";     //json文件的url
    var request = new XMLHttpRequest();
    request.open("get",url);    //设置请求方法与路径
    request.send(null);     //不发送数据到服务器
    request.onload = function (){
        if(request.status===200){
            var json = JSON.parse(request.responseText);
            for(var i=0;i<json.length;i++){
                document.write(json[i].id);
                document.writeln(json[i].title);
                document.write('<br>');
                for(var j=0;j<json[i].option.length;j++) {
                    document.writeln(json[i].option[j]);
                    document.write('<br>');
                }
            }
        }
    }
}

