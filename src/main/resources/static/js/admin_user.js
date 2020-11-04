$(function(){

    $("#addUserBtn").on("click",function(){

        $("#addUserForm").submit();

    });

    $("#updateUser").on("hidden.bs.modal",function(){
        $(this).removeData("bs.modal");//每次模态框隐藏就清除里面的内容
    });
    $("#updateUser").on("shown.bs.modal",function(){
        $("#updateUser .selectpicker").selectpicker();//每次模态框显示就清除里面的内容
    });

    $("#delalluser").on("click",function(){

        var checkall=$(".clickone:checked")//获取所有的demo对象
        if(checkall.length==0){
            alert("必须勾选要删除的记录");
        }else{
            var userid=new Array();
            checkall.each(function(){

                userid.push(this.value);//获取复选框里面的值，然后放入集合中
            });

            var useridjson=JSON.stringify(userid);//把值转变成json类型

            if(confirm("确定要删除所选记录?")){
                $.ajax({
                    url:"/admin/delbatchuser.html",
                    type:"post",
                    data:{"uids":useridjson},
                    success:function(data){
                        if(data=="success"){
                            $(location).attr("href","/admin/admin_userselect.html");
                        }

                    },
                    error:function(){

                    }
                });
            }else {

            }

        }

    });


    $("#selsubmit").on("click",function(){
        $("#form_contral").submit();
    })

    var pagenum=$("#pager > p >span:last-child").text();
    if(pagenum=="1"){
        $("#homepage").attr("disabled","true");
    };
    /*$("#sub").on("click",function () {
        var flag=delsubmit();
        if(flag){
            return true;
        }else{
            return false
        }
    });*/

})
function chone(){
    var flag=true;//充当一个标记
    $(".clickone").each(function(index, el){
        var $this=$(el);//获取当前的demo对象
        if($(el).prop("checked")==false){
            flag=false;
        }
    });
    if(flag){
        $(".clickall").prop('checked',true);
    }else{
        $(".clickall").prop('checked',false);
    }
}
function chall(){
    $(".clickone").prop("checked",$(".clickall").prop("checked"))
}

function updatesubmit(){
    $("#formlogin").submit();
}
function delsubmit(data){
    if(confirm("确定要删除这条记录吗?")){
            var $this=$(data);
            var uid=$this.parent().prev().prev().prev().prev().prev().text();
            $.ajax({
                url:"/admin/deluser.html",
                type:"get",
                data:{"uid":uid},
                success:function(data){
                    if(data=="success"){
                        $(location).attr("href","/admin/admin_userselect.html");
                    }

                },
                error:function(){

                }

        });
    }else{
        return false;
    }
}