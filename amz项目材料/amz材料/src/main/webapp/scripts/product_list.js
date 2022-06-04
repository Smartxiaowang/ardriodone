window.onload=function(){
	nowPage = 1;
	pageTotalJSP = 0;
	getweather()
	firstLoad();
	getcookie();
	productbycategory()
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
//跳转商品类别展示页-分页
function productbycategory(obj){
	if(obj!=undefined){
		var numId = obj.innerHTML;
		if(obj.innerHTML=="上一页" && nowPage==1){
			alert("已经是第一页了")
			return;
		}else if(obj.innerHTML=="上一页"){
			numId = nowPage -1;
			nowPage = nowPage -1;
		} 
		if(obj.innerHTML=="下一页" && nowPage==pageTotalJSP){
			alert("最后一页了")
			return;
		}else if(obj.innerHTML=="下一页"){
			nowPage++;
			numId = nowPage;
		}
		 if((obj.innerHTML=="确定" && $("#inputNum").val()=="") || $("#inputNum").val()>pageTotalJSP){
			return;
		} else if(obj.innerHTML=="确定"){
			numId = $("#inputNum").val();
			nowPage = numId;
		}
		
	}else{
		var numId = 1;
	}
	
	$("#position").empty();
	$(".p-page-ul").empty();
	$("#totalPage").remove();
	$("#noproduct").remove();
	$("#allproductul").empty();
	var data = "currentPage="+numId+"&pageProSize=8&like=";
	if($.cookie("like")!=undefined){
		data = "currentPage="+numId+"&pageProSize=8&like="+$.cookie("like")+"" ;
	}
	$.ajax({
		url:"getproductbycategory",
		type:"post",
		async:true,
		data:data,
		dataType:"json",
		success:function(list){

			var lid = $.cookie("lid");
			var sid = $.cookie("sid");
			for(var key in list){
				if( key=='like'){
					$("#position").append("您现在的位置：<a href='index.jsp'>亚马逊</a> &gt; <a href='javascript:;'>"+$.cookie("like")+"</a> &gt;")
				}else if(sid==""){
					$("#position").append("您现在的位置：<a href='index.jsp'>亚马逊</a> &gt; <a href='javascript:;' id='_"+lid+"' onclick='lid(this)'>"+key+"</a> &gt;")
				}else{
					var name = key.split("_");
					$("#position").append("您现在的位置：<a href='index.jsp'>亚马逊</a> &gt; <a href='javascript:;' id='_"+lid+"' onclick='lid(this)'>"+name[0]+"</a> &gt;"+name[1]+"")
				}
			
				if(list[key]==""){
					$("#cleardiv").append("<span id='noproduct'></span>")
					$("#noproduct").text("本类别暂无商品,敬请期待")
				}else{
					for(var i = 0; i < list[key].length; i++){
						$("#allproductul").append("<li class='allproductli' style='float:left;margin-left:13px;margin-bottom:15px;'><dl><dt><a href='productDetails?id="+list[key][i].id+"'><img src='"+list[key][i].abc+"' /></a></dt><dd class='title'><a href='productDetails?id="+list[key][i].id+"'>"+list[key][i].name+"</a></dd><dd class='price'>￥:"+list[key][i].price+"</dd></dl></li>");
					}
				}
				var totalPage = $.cookie("totalPage");
				$(".p-page-ul").append("<li class='p-page-ul-li'><a href='javascript:;' onclick='productbycategory(this)'>上一页</a></li>");
				for(var i = 1; i <= totalPage; i++){
					$(".p-page-ul").append(" <li class='p-page-ul-li'><a href='javascript:;' onclick='productbycategory(this)' class='choosePage' id='"+i+"'>"+i+"</a></li>");
				}
				$(".p-page-ul").append(" <li class='p-page-ul-li'> <a href='javascript:;' onclick='productbycategory(this)'>下一页</a> </li>");
				if(totalPage==undefined){
					$(".p-page-ul").after("<span class='p-page-span'  id='totalPage' >共&nbsp;1&nbsp;页</span>");
				}else{
					$(".p-page-ul").after("<span class='p-page-span'  id='totalPage' >共&nbsp;"+totalPage+"&nbsp;页</span>");
				}
				
				$("#"+numId+"").css({"background":"#FF0200","color":"white"});
				$("#"+numId+"").parent().siblings().children().css({"background":"white","color":"#999"});
				
				nowPage = numId;
				pageTotalJSP=totalPage;
			}
			
			/*<ul class="product clearfix" id="allproductul">
<li><dl><dt><a href='productDetails?id="+list[key][i].id+"'><img src='' /></a></dt><dd class='title'><a href='productDetails?id="+list[key][i].id+"'>商品名称</a></dd><dd class='price'>￥12.34</dd></dl></li>*/
		}
	})
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
//添加cookie 跳转商品list页
function lid(obj){
	$.cookie("like","");
	$.cookie("lid","");
	$.cookie("sid","");
	var lid = ($(obj).attr("id").split("_"))[1];
	$.cookie("lid",lid);
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
//加载最近浏览
function getcookie(){
	
	if($.cookie("browse") == undefined){
		
		return;
	}
		$(".last-view").empty();
		var pro_id = $.cookie("browse");
	$.ajax({
		url:"productBrowse",
		type:"post",
		async:true,
		data:"browse="+pro_id,
		dataType:"json",
		success: function(list){
			
			$(".last-view").append("<h2 class='pre_lookh3'>最近浏览</h2>");
			for(var i =  list.length-1; i >= 0; i--){
				$(".pre_lookh3").after("<dl class='clearfix'>" +
						"<dt>" +
							"<img style='width: 54px; height: 54px;' src='"+list[i].abc+"' />" +
						"</dt>" +
							"<dd>" +
								"<a href='productDetails?id="+list[i].id+"'>"+list[i].name+"</a>" +
							"</dd>" +
						"</dl>");
			}
		}
	})
}