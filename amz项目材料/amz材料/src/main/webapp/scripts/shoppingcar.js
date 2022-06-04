window.onload=function(){
	getweather()
	loadProCar();
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
//进入购物车页面,加载已添加商品
function loadProCar(){
	$.cookie("like","");
	if($.cookie("carId")==undefined){
		$("#totalpricediv").before("<table id=''table_table'><tr id='thead_tr'><th>商品名称</th><th>商品价格</th><th>购买数量</th><th>操作</th></tr><tr id='product_id_'><td class='thumb'><img style='width: 100px; height: 100px;' class='title_css1' src='' /><a href='javascript:;' class='title_css'>暂未选购商品</a></td><td class='price'>￥<span></span></td><td class='number'><input type='button' id='minus' value='&nbsp;<&nbsp;' width='3px' name='minusButton' ><input type='text' name='number' value='0' maxlength='5' size='3' style='text-align:center; vertical-align:middle'/><input type='button' id='add' value='&nbsp;>&nbsp;' width='3px' name='addButton'><br/><span class='span_msg'></span></td><td class='delete' ><a href='javascript:;'>删除</a></td></tr></table>")
		return;
	}
	$("#table_table").remove();
	var carId = $.cookie("carId");
	$("#totalpricespan").text("0.0");
	$.ajax({
		url:"addcarGetBean",
		type:"post",
		data:"carIds="+carId,
		async:false,
		dataType:"json",
		success:function(list){
			var totalprice = 0;
			$("#totalpricediv").before("<table id='table_table'>" +
					"<tr id='thead_tr'>" +
						"<th><span id='fristcheckboxspan'>全选&nbsp;<input type='checkbox' checked='checked' onclick='checkall()' id='firstcheckbox' class='checkbox' ></span>商品名称</th><th onclick='test()'>商品价格</th><th>购买数量</th><th>操作</th>" +
					"</tr></table>")
			for(var i = list.length - 1; i >= 0; i--){
				$("#thead_tr").after("<tr id='product_id_"+list[i].id+"'>" +
						"<td class='thumb' id='tdfirst"+list[i].id+"'><input type='checkbox' onclick='checkson(this)' class='checkboxson' checked='checked' id='checkboxid"+list[i].id+"'>" +
							"<img style='width: 100px; height: 100px;' class='title_css1' src='"+list[i].abc+"' />" +
							"<a href='productDetails?id="+list[i].id+"' class='title_css'>"+list[i].name+list[i].description+"</a>" +
						"</td>" +
						"<td class='price' id='price_id_"+list[i].id+"'>" +
							"￥<span id='span_"+list[i].id+"'>"+(list[i].price*list[i].count)+"</span>" +
						"</td>" +
						"<td class='number' id='td"+list[i].id+"'>" +
							"<input type='button' id='minus' value='&nbsp;<&nbsp;' width='3px' name='minusButton' onclick='changenum(this)'>" +
							"<input id='count"+list[i].id+"' type='text' name='number' value='"+list[i].count+"' maxlength='5' size='3' onblur='countcheck(this)' style='text-align:center; vertical-align:middle'/>" +
							"<input type='button' id='add' value='&nbsp;>&nbsp;' width='3px' name='addButton'onclick='changenum(this)'><br/>" +
							"<span class='span_msg' id='msg_"+list[i].id+"'></span>" +
						"</td>" +
						"<td class='delete' >" +
							"<a href='javascript:;' onclick='delpro(this)'>删除</a>" +
						"</td>" +
							"<input type='hidden' id='hidden"+list[i].id+"' value='"+list[i].stock+"'>" +
							"<input type='hidden' id='danprice"+list[i].id+"' value='"+list[i].price+"'>" +
						"</tr>")
				totalprice = list[i].price*list[i].count + totalprice;	
			}
			$("#totalpricespan").text(totalprice);
		},
		error : function(XMLHttpRequest, statusText) {
			$("#totalpricediv").before("<table id=''table_table'><tr id='thead_tr'><th>商品名称</th><th>商品价格</th><th>购买数量</th><th>操作</th></tr><tr id='product_id_'><td class='thumb'><img style='width: 100px; height: 100px;' class='title_css1' src='' /><a href='javascript:;' class='title_css'>暂未选购商品</a></td><td class='price'>￥<span></span></td><td class='number'><input type='button' id='minus' value='&nbsp;<&nbsp;' width='3px' name='minusButton' ><input type='text' name='number' value='0' maxlength='5' size='3' style='text-align:center; vertical-align:middle'/><input type='button' id='add' value='&nbsp;>&nbsp;' width='3px' name='addButton'><br/><span class='span_msg'></span></td><td class='delete' ><a href='javascript:;'>删除</a></td></tr></table>")
			return;
		}// 
	})
}
//全选
function checkall(){
	var flag = $("#firstcheckbox").prop("checked")
	$(".checkboxson").prop("checked",flag);
	changetotalprice()
}
//判断选中的复选框,为总金额赋值
function changetotalprice(){
	var totalprice = 0;
	for(var i = 0; i < $(".checkboxson").length; i++){
		if($(".checkboxson")[i].checked){
			var obj = $(".checkboxson")[i]
			var trId = $(obj).parent().attr("id").split("tdfirst")[1];
			
			var price = parseFloat($("#span_"+trId+"").text());//当前价格
			totalprice += price;
		}
	}
	$("#totalpricespan").text(totalprice)
	/*var unitprice = parseFloat($("#danprice"+trId+"").val());//单价
	var count = parseInt($("#count"+trId+"").val());//商品数量
	var stock = parseInt($("#hidden"+trId+"").val());//商品库存
	var price = parseFloat($("#span_"+trId+"").text());//当前价格
	$("#totalpricespan").text(totalprice-price+unitprice*count)*/
}
//复选框点击事件
function checkson(obj){
	if(!$(obj).prop("checked")){
		$("#firstcheckbox").prop("checked",false);
		changetotalprice()
		return;
	}
	var flag = true;
	for(var i = 0; i < $(".checkboxson").length; i++){
		if(!$(".checkboxson")[i].checked){
			flag=false;
		}
	}
	$("#firstcheckbox").prop("checked",flag);
	changetotalprice()
}
//商品数量文本框失去焦点时检查商品数量变更
function countcheck(obj){
	var trId = $(obj).parent().attr("id").split("td")[1];
	$("#msg_"+trId+"").text("");
	var unitprice = parseFloat($("#danprice"+trId+"").val());//单价
	var count = parseInt($("#count"+trId+"").val());//商品数量
	var price = parseFloat($("#span_"+trId+"").text());
	var totalprice = parseFloat($("#totalpricespan").text());
	var stock = parseInt($("#hidden"+trId+"").val());//商品库存
	if(isNaN(count)){
		$("#count"+trId+"").val(1);
		count=1;
	}
	if(isNaN(stock)){
		$("#msg_"+trId+"").text("没有库存,再看看别的吧~");
		return;
	}
	if(count == stock || count > stock){
		$("#count"+trId+"").val(stock);
		$("#msg_"+trId+"").text("已经最大了");
	}
	if(count < 1){
		$("#count"+trId+"").val(1);
		$("#msg_"+trId+"").text("已经最小了")
	}
	var count = parseInt($("#count"+trId+"").val());
	$("#span_"+trId+"").text(unitprice*count);
	changetotalprice()
	//$("#totalpricespan").text(totalprice-price+unitprice*count);
	changecookie(trId,count);
}
//删除商品
function delpro(obj){
	if(confirm("确定要删除此商品吗")){
		var trId = $(obj).parent().prev().attr("id").split("td")[1];
		var count = parseInt($("#count"+trId+"").val());//商品数量
		delcookie(trId,count); 
		$("#product_id_"+trId+"").remove();
		$(location).attr("href","delshopcar?pid="+trId+"");
		
	}else{
		return;
	}
	
}
//增加商品数量或者减少商品数量
function changenum(obj){
	var trId = $(obj).parent().attr("id").split("td")[1];//行id
	$("#msg_"+trId+"").text("");
	var unitprice = parseFloat($("#danprice"+trId+"").val());//单价
	var count = parseInt($("#count"+trId+"").val());//商品数量
	$("#span_"+trId+"").text(unitprice*count);
	var stock = parseInt($("#hidden"+trId+"").val());//商品库存
	var price = parseFloat($("#span_"+trId+"").text());
	var totalprice = parseFloat($("#totalpricespan").text());
	if(isNaN(stock)){
		$("#msg_"+trId+"").text("没有库存,再看看别的吧~");
		return;
	}
	if(obj.id=="add"){
		if(count == stock || count > stock){
			$("#count"+trId+"").val(stock);
			$("#msg_"+trId+"").text("已经最大了")
			return;
		}
		$("#count"+trId+"").val(++count);
		count = parseInt($("#count"+trId+"").val());
		$("#span_"+trId+"").text(unitprice*count);
		changetotalprice()
		//$("#totalpricespan").text(totalprice-price+unitprice*count)
		changecookie(trId,count);
		return;
	}
	if(obj.id=="minus"){
		if(count == 1 || count < 1){
			$("#count"+trId+"").val(1);
			$("#msg_"+trId+"").text("已经最小了")
			return;
		}else{
			$("#count"+trId+"").val(--count);
			count = parseInt($("#count"+trId+"").val());
			$("#span_"+trId+"").text(unitprice*count);
			changetotalprice()
			//$("#totalpricespan").text(totalprice-price+unitprice*count)
			changecookie(trId,count);
			return;
		}
	}
}
//修改商品数量时,更改cookie的值
function changecookie(trId,count){
	var carId = $.cookie("carId").split("-");
	for(var i = 0; i < carId.length; i++){
		if(trId == carId[i]){
			carId.splice(i,1);
			i--;
		}
	}
	var carIdNew = carId.join("-");
	for(var i = 0; i < count; i++){
		carIdNew =  trId+"-"+carIdNew;
	}
	$.cookie("carId",carIdNew,{expires:7});
}
//删除商品修改cookie
function delcookie(trId,count){
	var carId = $.cookie("carId").split("-");
	for(var i = 0; i < carId.length; i++){
		if(trId == carId[i]){
			carId.splice(i,1);
			i--;
		}
	}
	var carIdNew = carId.join("-");
	$.cookie("carId",carIdNew,{expires:7});
}//结算判断
//结算
function checkout(){
	$("#formtable").attr("action","javascript:;");
	$("#hh").attr("value","");
	if($.cookie("carId")==undefined || $.cookie("carId")==""){
		alert("请先选购商品")
		return false;
	}
	
	var obj = [];
	for(var i = 0; i < $(".checkboxson").length; i++){
		if($(".checkboxson")[i].checked){
			obj.push($(".checkboxson")[i]);
		}
	}
	if(obj.length==0){
		alert("请选择要提交的商品")
		return false;
	}
	var id;
	var count;
	var checkouts = [];
	for(var i = 0; i < obj.length; i++){
		id = $(obj[i]).parent().attr("id").split("tdfirst")[1];
		count = parseInt($("#count"+id+"").val());
		checkouts.push([id,count]);
	}
	var checkout = JSON.stringify(checkouts);
	$("#formtable").attr("action","checkout");
	$("#hh").attr("value",checkout);
	
	//$(location).attr("href","checkout?checkout="+checkout)
	return true;
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