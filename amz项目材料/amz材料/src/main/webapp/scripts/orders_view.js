window.onload=function(){
	getweather()
	getOrder()
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
function getOrder(){
	$("#oderview").empty();
	$.ajax({
		url:"getOrder",
		type:"post",
		async:true,
		dataType:"json",
		success: function(list){
			for(var i = list.length-1; i >=0; i--){
				$("#oderview").append("<tr id='theadtr"+list[i].id+"'><th>订单时间:"+list[i].create_time+"</th><th>订单号:"+list[i].id+"</th><th>订单总额:"+list[i].money+"</th><th><th></tr>");
				$("#theadtr"+list[i].id+"").after("<tr><td class='thumb'><ul id='ul"+list[i].id+"'></ul></td><td class='price'><span>已付款</span></td><td class='delete'>正在发货</td></tr>");
				for(var j = 0; j < list[i].list.length; j++){
					$("#ul"+list[i].id+"").append("<li><img style='width: 100px; height: 100px;'	src='"+list[i].list[j].abc+"' /><a href='productDetails?id="+list[i].list[j].id+"'>"+list[i].list[j].name+"+&nbsp;&nbsp;"+list[i].list[j].description+"</a><span style='color:red;font-size:18px;'>&nbsp;&nbsp;&nbsp;&nbsp;￥单价："+list[i].list[j].price+"</span><span style='color:blue;font-size:18px;'>&nbsp;&nbsp;&nbsp;数量:"+list[i].quantity[j]+"</span></li>")
				}
			}	
		}
	})
}