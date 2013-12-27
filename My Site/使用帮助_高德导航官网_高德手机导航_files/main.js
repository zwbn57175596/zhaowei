$(function(){
	//头部导航下拉菜单
	$("#more").hover(function(){
		$(".nav li ul ").show();
	},function(){
		$(".nav li ul ").hide();
	});
	$("#more2").hover(function(){
		$(".login  ul ").show();
	},function(){
		$(".login  ul ").hide();
	});
	$(".index_1:eq(2),.index_1:eq(5),.index_2 p:eq(2)").css("margin-right","0");
	$("#slides .slides_container .slides_control div").width($(window).width());
	$("#slides .slides_container .slides_control div a").width($(window).width());
	
	$(window).resize(function(){
		$("#slides .slides_container .slides_control div").width($(window).width());
		$("#slides .slides_container .slides_control div a").width($(window).width()); 
	});
});

/*登录*/
function focusAccount(){
	$("#account").addClass("aaa");
	var vals = $("#account").val();
	if($.trim(vals) === "手机号/邮箱/用户名")
		$("#account").val("");
}
function blurAccount(){
	$("#account").removeClass("aaa");
	var vals = $("#account").val();
	if($.trim(vals) == "")
		$("#account").val("手机号/邮箱/用户名");
}
function focusPassword(){
	$("#passWord").addClass("aaa");
}
function blurPassword(){
	$("#passWord").removeClass("aaa");
}