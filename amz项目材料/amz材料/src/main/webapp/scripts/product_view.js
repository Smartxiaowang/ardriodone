window.onload=function(){
	getweather()
	firstLoad();
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
//立即购买
function buyNow(){
	if($("#stock").text()<1){
		$("#stock_msg").text("没有库存,再看看别的吧~")
		return;
	}
	var carId = $("input:hidden").val();
	var count = parseInt($("#count").val());
	var max = parseInt($("#stock").text());
	
	if(count <= 0 || isNaN(count)){
		$("#count").val(1);
		count = $("#count").val();
	}
	if(count > max){
		$("#count").val(max);
		count = $("#count").val();
	}
	var money = count*parseInt($("#product_view_price").text());
	$(location).attr("href","buynow?carId="+carId+"&count="+count+"&money="+money);
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
//修改购买商品数量
function productNum(obj){
	var count = parseInt($("#count").val());
	var max = parseInt($("#stock").text());
	if(isNaN(count)){
		$("#count").val(1);
		return;
	}
	if(isNaN(max)){
		$("#stock_msg").text("没有库存,再看看别的吧~");
		return;
	}
	if(obj.id=="add"){
		if(count == max || count > max){
			$("#count").val(max);
			$("#stock_msg").text("已经最大了")
			return;
		}else{
			if(count <= 0){
				$("#count").val(1);
				return;
			}
			$("#count").val(++count);
			return;
		}
	}
	if(obj.id=="minus"){
		if(count == 1 || count < 1){
			$("#count").val(1);
			$("#stock_msg").text("已经最小了")
			return;
		}else{
			$("#count").val(--count);
		}
	}
}

//添加购物车,判断库存,决定是否像servlet发送商品ID
function addCar(){
	if($("#stock").text()<1){
		$("#stock_msg").text("没有库存,再看看别的吧~")
		return;
	}
	var carId = $("input:hidden").val();
	var count = parseInt($("#count").val());
	var max = parseInt($("#stock").text());
	
	if(count <= 0 || isNaN(count)){
		$("#count").val(1);
		count = $("#count").val();
	}
	if(count > max){
		$("#count").val(max);
		count = $("#count").val();
	}
	$(location).attr("href","addCar?carId="+carId+"&count="+count);
}







/*function addToCart(pid) {
	
	var stock=$("#stock").html()
	var count=$("#count").val()
	
	if(parseInt(count)>parseInt(stock)){
		alert("您选择的数量超过库存!")
	}else{
		$.ajax({
			url : "addToCart",// 请求的servlet地址
			type : "GET",// 请求方式
			data : "" + pid+"_"+count,// 发送到服务器的数据
			dataType : "text",// 设置返回数据类型
			success : function(total) {
				$("#cartCount").html(total);
				alert("成功添加到购物车!")
			},// 响应成功后执行的回调方法data响应文本
			complete : function(XMLHttpRequest, statusText) {
				
			},// 响应完成后执行的回调方法
			error : function(XMLHttpRequest, statusText) {
				alert("添加到购物车失败!")
			}// 响应失败后执行的回调方法
		})
	}

}

function goingToBuy(pid) {
	window.location.href="goingToBuy?"+pid+"_"+$("#count").val();
	
}

//-按钮事件
function minus(){

	if($("#count").val()==1){
		$("#count").val(1)
	}else if($("#count").val()>=2){
		var old=$("#count").val()
		$("#count").val(parseInt(old)-1)
	}
}

//+按钮事件
function add(){
	var stock=$("#stock").html()
	var old=$("#count").val()
	if(parseInt(old)<parseInt(stock)){
		$("#count").val(parseInt(old)+1)
	}else{
		alert("您选择的数量超过库存!")
	}
	
}



function checkStock(){
	var stock=$("#stock").html()
	var old=$("#count").val()
	if(parseInt(old)>parseInt(stock)){
		alert("您选择的数量超过库存!")
	}
}



function remaind() {
	alert("请先登录亚马逊！")
	window.location.href="login.jsp"
}




*/