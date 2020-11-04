$(function () {
    $("#batchArray").on("click",function(){
        var clickone=$(".clickone:checked");

        if(clickone.length==0){
            alert("请勾选要还书的书本");
        }else{
            var array=new Array();
            clickone.each(function(){
                array.push(this.value);
            });
            var flag=qur();
            var arrayjson=JSON.stringify(array);
            if(flag){
                $.ajax({
                    url:"/user/batch.html",
                    type:"post",
                    data:{"bookname":arrayjson},
                    success:function(data){
                        if(data=="success"){
                            $(location).attr("href","/user/book_back.html");
                        }

                    },
                    error:function(){

                    }
                });
            };
        }
    });
})

function chall(){
    $(".clickone").prop('checked',$(".clickall").prop('checked'));
}
function chone(){
    var flag=true;

    $(".clickone").each(function(index,el){
        var $this=$(el);
        if($(el).prop('checked')==false){
            flag=false;
        }
    });
    if(flag){
        $(".clickall").prop('checked',true);
    }else{
        $(".clickall").prop('checked',false);
    }

}
/*$(function(){
	$("#bacth").on("click",function(){
		var clickone=$(".clickone:checked");

		if(clickone.length==0){
			alert("请勾选要还书的书本");
		}else{
			var array=new Array();
			clickone.eache(function(){
				array.push(this.value);
			});
			var flag=qur();
			var arrayjson=JSON.stringify(array);
			if(flag){
				ajax({
					url:"/qihangke/batch.html",
					type:"post",
					data:{"bookname":arrayjson},
					success:function(data){
						if(data=="success"){
							$(location).attr("href","/qihangke/book_back.html");
						}

					},
					error:function(){

					}
				});
			};
		}
	});


})*/
function black(data) {
    if(confirm("确定要还此书吗")){
        var $this=$(data);
        var bookname=$this.parent().prev().prev().text();

        $.ajax({
            url:"/user/black.html",
            type:"get",
            data:{"bookname":bookname},
            success:function(data){
                if(data=="success"){
                    $(location).attr("href","/user/book_back.html");
                }

            },
            error:function(){

            }

        });
    }else{
        return false;
    }
}
function qur() {
    if(confirm("是否还所选书籍")){
        return true;
    }else {
        return false;
    }
}
