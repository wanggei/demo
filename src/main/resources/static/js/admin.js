$(function(){
	$('.collapse_all').on('shown.bs.collapse', function () {
		var a=$(this).prev();//获取上一个demo对象
		
		var i=a.children();//获取到当前demo包含的所有子节点
		i.removeClass('icon-jiahao');
		i.addClass('icon-jianhao');
	});
	$('.collapse_all').on('hidden.bs.collapse', function () {
		var a=$(this).prev();
		var i=a.children();
		i.removeClass('icon-jianhao');
		i.addClass('icon-jiahao');
	});
	$(".collapse_all >li >a").click(function(el){
		el.preventDefault();//消除原先a标签上面的所有功能
		var $this=$(this);
		$(".collapse_all >li >a").removeClass('navactive');
		$this.addClass('navactive');
		var html=$this.data("html");

		$("#frame").attr('src',html);

		var text=$this.text();
		var mnva=$this.parent().parent().prev().text();
		
		$("#nav_pager > ol >li:last-child").html(text);
		$("#nav_pager > ol >li:last-child").prev().html(mnva);
		
	});
});