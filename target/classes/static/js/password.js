$(function(){
    $("#su").on("click",function(){
        var ps=$(".ps").val();
        var psu=$(".psu").val();
        var psut=$(".psut").val();
        if(ps==""||psu==""||psut==""){
            alert("修改密码不能为空");
        }else{
            if(confirm("确定要修改密码吗?")){
                $.ajax({
                    url:"/user/pswup.html",
                    type:"post",
                    data:$("#form").serialize(),
                    success:function(data){

                        if(data=="success"){

                            alert("修改密码成功");
                            $(location).attr("href","/user/book_back.html");
                        }

                    },
                    error:function(e){
                        alert("出错了");
                    },
                })
            }else{
                return false;
            }
        }
    });
})