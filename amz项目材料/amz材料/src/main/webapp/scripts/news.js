window.onload = function(){
	getweather()
	getNews();
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
//加载新闻动态
function getNews(){
	$("#newsListUl").empty();
	$("#news").empty();
	$.ajax({
		url:"getNews",
		type:"post",
		async:true,
		data:"news=getall",
		dataType:"json",
		success: function(list){
			var id = $.cookie("new");
			for(var i = 0; i < list.length; i++){
				$("#newsListUl").append("<li><a href='newsview?id="+list[i].id+"'>"+list[i].title+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>");
				if(list[i].id == id){
					$("#news").append("<h1>"+list[i].title+"</h1><div class='content'><p style='text-align: right;'>创建时间："+list[i].create_time+"</p>"+list[i].content+"</div>");
				}
			}
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


