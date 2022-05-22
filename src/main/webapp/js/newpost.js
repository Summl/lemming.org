let editor
function updateContent(){
    let hide_input = document.getElementById("content")
    hide_input.value = editor.getValue();
    return hide_input.value.replace("\n","").replace(" ","") !== "";
}
function checkSubmit(){
    if($("#post_title").val().trim() === ""){
        alert("请输入标题！");
        return false;
    }
    if($("#post_brief").val().trim() === ""){
        alert("请输入简介！");
        return false;
    }

}
window.onload = function () {
    // 初始化编辑器
    editor = new Vditor("editor", {
        upload: {
            accept: 'image/*',
            token: 'test',
            url: 'post?type=image',
            linkToImgUrl: 'post?type=image',
            success(e, msg){
                editor.insertValue("![](data/images/"+msg+")")
                editor.tip("上传图片成功",1)
            }
        },
        "outline": {
            "enable": true
        }


    })

    let submit = document.getElementById("submit_btn")
    submit.addEventListener("click",updateContent)
}


