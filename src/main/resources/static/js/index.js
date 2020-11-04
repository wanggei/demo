$(function(){													/* 	$(function(){} 入口控制*/
	//监听屏幕宽度
	changeimg();
	$(window).on("resize",changeimg);


	/* 设置下拉菜单鼠标一上去就下拉 */
	$("#abtn").mouseenter(function(){
		$(this).dropdown("toggle");
	}).mouseleave(function(){
		$(this).dropdown("toggle");
	})
	$("#ulbtn").mouseenter(function(){
		$(this).dropdown("toggle");
	}).mouseleave(function(){
		$(this).dropdown("toggle");
	})
	/*$("#aaa").mouseenter(function(){
		$(this).dropdown("toggle");
	}).mouseleave(function(){
		$(this).dropdown("toggle");
	})
	$("#bbb").mouseenter(function(){
		$(this).dropdown("toggle");
	}).mouseleave(function(){
		$(this).dropdown("toggle");
	})*/
});
//设置在屏幕在不同宽度显示不同的图片
function changeimg(){
	var width=$(window).width();
	
	$("#carousel > #itmes > .item").each(function(index,el){
		var div=$(el);
		var withnum=div.data((width<=768)?"xs-width":"lg-width");
		if(width<=768){
			div.html("<img src='"+withnum+"' alt='hjs'>");
		}else{
			div.html("");
			div.css("background","url('"+withnum+"') no-repeat");//样式控制
		}
		
	});
}
function book(data) {
	var $this=$(data);
	var bookname=$this.parent().prev().prev().children().text();
	$.ajax({
		url:"/user/addbook",
		type:"get",
		data:{"bookname":bookname},
		success:function (data) {
			if (data=="error"){
				alert("库存不足")
			}else{
				$(location).attr("href","/");
			}

		},
		error:function () {
			
		}
	});
}
function bookinfo(data) {
	var $this=$(data);
	var bookname=$this.parent().prev().prev().children().text();
	$.ajax({
		url:"/bookinfo.html",
		type:"post",
		data:{"bookname":bookname},
		success:function (data) {

		},
		error:function () {

		}
	});
}