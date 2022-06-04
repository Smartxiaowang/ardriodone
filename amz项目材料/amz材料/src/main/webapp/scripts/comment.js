window.onload=function(){
	nowPage = 1;
	pageTotalJSP = 0;
	getweather()
	 firstLoad()
	 getMessage()
}
//加载天气
function getweather(){
	$("#weatherdiv").empty();
	$.ajax({
		url:"http://v.juhe.cn/weather/index",
		type:"get",
		async:true,
		data:"format=2&cityname=上海&key=246b3506c6044f19b3537d49a88a7e65",
		dataType:"JSONP",
		success: function(list){
			$("#weatherdiv").append("<span id='cityname'>"+list.result.today.city+"</span><br/><span id='date_y'>"+list.result.today.date_y+"</span><span id='week'>&nbsp;&nbsp;&nbsp;"+list.result.today.week+"</span><br/><span id='temperature'>今日温度 :"+list.result.today.temperature+"</span><br/><span id='weather'>今日天气:"+list.result.today.weather+"</span><br/><span id='travel_index'>旅游指数 :"+list.result.today.travel_index+"</span>");
		}
	})
}
//加载留言对象
function getMessage(obj){
	if(obj!=undefined){
		var numId = obj.innerHTML;
		if(obj.innerHTML=="上一页" && nowPage==1){
			return;
		}else if(obj.innerHTML=="上一页"){
			numId = nowPage -1;
			nowPage = nowPage -1;
		} 
		if(obj.innerHTML=="下一页" && nowPage==pageTotalJSP){
			return;
		}else if(obj.innerHTML=="下一页"){
			nowPage++;
			numId = nowPage;
		}
		 if((obj.innerHTML=="确定" && $("#inputNum").val()=="") || $("#inputNum").val()>pageTotalJSP){
			return;
		} else if(obj.innerHTML=="确定"){
			if(isNaN($("#inputNum").val())){
				$("#inputNum").val(1);
			}
			numId = $("#inputNum").val();
			nowPage = numId;
		}
	}else{
		var numId = 1;
	}
	$("#commentul").empty();
	$(".p-page-ul").empty();
	$("#totalPage").remove();
	$.ajax({
		url:"getmessage",
		type:"post",
		async:true,
		data:"currentPage="+numId+"&pageProSize=3",
		dataType:"json",
		success: function(list){
			var pageTotal = list.totalPage;
			for(var i = 0; i < list.list.length ; i++){
				$("#commentul").append("<li><dl>	<dt>内容："+list.list[i].content+"</dt>	<dd class='author'><span>作者："+list.list[i].nick_name+"</span></dd><dd>评论时间："+list.list[i].create_time+"</dd><dd>回复："+list.list[i].reply+"</dd>	<dd>回复时间："+list.list[i].reply_time+"</dd></dl></li>");
			}
			$(".p-page-ul").append("<li class='p-page-ul-li'><a href='javascript:;' onclick='getMessage(this)'>上一页</a></li>");
			for(var i = 1; i <= pageTotal; i++){
				$(".p-page-ul").append(" <li class='p-page-ul-li'><a href='javascript:;' onclick='getMessage(this)' class='choosePage' id='"+i+"'>"+i+"</a></li>");
			}
			$(".p-page-ul").append(" <li class='p-page-ul-li'> <a href='javascript:;' onclick='getMessage(this)'>下一页</a> </li>");
			$(".p-page-ul").after("<span class='p-page-span'  id='totalPage' >共&nbsp;"+pageTotal+"&nbsp;页</span>");
			$("#"+numId+"").css({"background":"#FF0200","color":"white"});
			$("#"+numId+"").parent().siblings().children().css({"background":"white","color":"#999"});
			nowPage = numId;
			pageTotalJSP=pageTotal;
		}
	})
}
//添加cookie 跳转商品list页
function lid(obj){
	$.cookie("like","");
	$.cookie("lid","");
	$.cookie("sid","");
	var lid = ($(obj).attr("id").split("_"))[1];
	$.cookie("lid",lid);
	$(location).attr("href","product-list.jsp");
}
//关键字搜索
function likequery(){
	var like = $("#qname").val();
	if(like=="" || like == undefined){
		alert("请输入搜索内容")
		return;
	}
	$.cookie("lid","");
	$.cookie("sid","");
	$.cookie("like",like)
	$(location).attr("href","product-list.jsp");
}
function sid(obj){
	$.cookie("like","");
	$.cookie("lid","");
	$.cookie("sid","");
	var lid = $(obj).attr("id").split("_");
	$.cookie("lid",lid[0]);
	$.cookie("sid",lid[1]);
	$(location).attr("href","product-list.jsp");
}
//加载商品类别导航
function firstLoad(){
	$.ajax({
		url:"productClassification",
		type:"post",
		async:true,
		dataType:"json",
		success:function(list){
			for(var i = 0; i < list.length; i++){
				$("#categoryDL").append("<dt><a href='javascript:;' id='_"+list[i].id+"' onclick='lid(this)'>"+list[i].name+"</a></dt>");
				for(var j = 0; j < list[i].list.length; j++){
					$("#categoryDL").append("<dd><a href='javascript:;'id='"+list[i].id+"_"+list[i].list[j].id+"' onclick='sid(this)'>"+list[i].list[j].name+"</a></dd>");
				}
			}
		}
	}) 
}

function commentCheck(){
	$("#msgfirstspan").text("");
	$("#msgsecondspan").text("");
	$("#msgthirdspan").text("");
	if($("[name=guestName]").val() == ""){
		$("#msgfirstspan").text("请输入昵称");
		return false;
	}
	if($("[name=guestTitle]").val() == ""){
		$("#msgsecondspan").text("请输入标题");
		return false;
	}
	if($("[name=guestContent]").val() == ""){
		$("#msgthirdspan").text("请输入内容");
		return false;
	}
	return true;
}