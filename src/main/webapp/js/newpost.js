let editor
function updateContent(){
    let hide_input = document.getElementById("content")
    hide_input.value = editor.getValue();
    return hide_input.value.replace("\n","").replace(" ","") !== "";
}
function checkSubmit(){
    if($("#post_title").val().trim() === ""){
        $("#textinfo").text("请输入标题！");
        $("#mymodal").modal();
        return false;
    }
    if($("#post_brief").val().trim() === ""){
        $("#textinfo").text("请输入简介！");
        $("#mymodal").modal();
        return false;
    }
    updateContent()
    $("#select").submit();
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


    let chooseCover = document.getElementById("chooseCover")
    let inputChooser = document.getElementById("inputChooseCover")
    chooseCover.addEventListener("click",function () {
            inputChooser.click()
    })

    inputChooser.addEventListener("change",function (){
        let reader = new FileReader()
        let file = this.files[0]
        reader.readAsDataURL(file)
        reader.addEventListener("loadend",function () {
            chooseCover.style.backgroundImage = "url("+this.result+")"
        })
    })
}


