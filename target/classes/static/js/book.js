$(function () {

	$("#addBookBtn").on("click",function(){

		$("#addBookForm").submit();

	});
	var pagenum=$("#pager > p >span:last-child").text();
	if(pagenum=="1"){
		$("#homepage").attr("disabled","true");
	};
	$("#updatebook").on("hidden.bs.modal",function(){
		$(this).removeData("bs.modal");//每次模态框隐藏就清除里面的内容
	});
	/*$("#updatebook").on("shown.bs.modal",function(){
		$("#updatebook .selectpicker").selectpicker();//每次模态框显示就清除里面的内容
	});*/

	$("#delallbook").on("click",function () {
		var checkall=$(".clickone:checked")//获取所有的demo对象
		if (checkall.length==0){
			alert("请勾选所要删除的书籍");
		}else {
			var bookid=new Array();
			checkall.each(function () {
				bookid.push(this.value);
			})
			var bookidJson=JSON.stringify(bookid);
			if(confirm("确定要删除所选书籍?")){
				$.ajax({
					url:"/admin/delbatchbook.html",
					type:"post",
					data:{"bookidJson":bookidJson},
					success:function(data){
						if(data=="success"){
							$(location).attr("href","/admin/book.html");
						}

					},
					error:function(){

					}
				});
			}else {

			}
		}
	});

})
function updatesubmit(){

	$("#from").submit();
}
function delbook(data) {
	if(confirm("确定要删除此书籍吗")){
		var $this=$(data);
		var bookid=$this.parent().prev().prev().prev().prev().prev().text();
		$.ajax({
			url:"/admin/deleteOne.html",
			type:"get",
			data:{"bookid":bookid},
			success:function(data){
				if(data=="success"){
					$(location).attr("href","/admin/book.html");
				}

			},
			error:function(){

			}

		});
	}else{
		return false;
	}
}


function chone(){
	var flag=true;//充当一个标记
	$(".clickone").each(function(index, el){
		var $this=$(el);
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
