// JavaScript Document
window.onload=function() {
	nowPage = 1;
	pageTotalJSP = 0;
	//getweather()
	choosePage();
	firstLoad();
	getNews();
	getcookie();
	hotProduct();
	 
	
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
//首页加载商品页面及翻页
function choosePage(obj){
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
		 if((obj.innerHTML=="确定" && $("#inputNum").val()=="") || parseInt($("#inputNum").val())>pageTotalJSP || isNaN($("#inputNum").val())){
			return;
		} else if(obj.innerHTML=="确定"){
			numId = $("#inputNum").val();
			nowPage = numId;
		}
		
	}else{
		var numId = 1;
	}
	$(".product2").empty();
	$(".p-page-ul").empty();
	$("#totalPage").remove();
	$.ajax({
		url:"indexShowPor",
		type:"post",
		async:false,
		data:"currentPage="+numId+"&pageProSize=12&like=",
		dataType:"json",
		success: function(list){
			var pageTotal = list.totalPage;
			for(var i = 0; i < list.list.length; i++){
				$(".product2").append("<li><dl><dt><a href='productDetails?id="+list.list[i].id+"' class='productIndex"+list.list[i].id+"'><img src='"+list.list[i].abc+"'/></a></dt><dd class='title'><a href='productDetails?id="+list.list[i].id+"' class='productIndex"+list.list[i].id+"'>"+list.list[i].name+"</a></dd><dd class='price'>￥"+list.list[i].price+"</dd></dl></li>");
			}
			$(".p-page-ul").append("<li class='p-page-ul-li'><a href='javascript:;' onclick='choosePage(this)'>上一页</a></li>");
			for(var i = 1; i <= pageTotal; i++){
				$(".p-page-ul").append(" <li class='p-page-ul-li'><a href='javascript:;' onclick='choosePage(this)' class='choosePage' id='"+i+"'>"+i+"</a></li>");
			}
			$(".p-page-ul").append(" <li class='p-page-ul-li'> <a href='javascript:;' onclick='choosePage(this)'>下一页</a> </li>");
			$(".p-page-ul").after("<span class='p-page-span'  id='totalPage' >共&nbsp;"+pageTotal+"&nbsp;页</span>");
			$("#"+numId+"").css({"background":"#FF0200","color":"white"});
			$("#"+numId+"").parent().siblings().children().css({"background":"white","color":"#999"});
			nowPage = numId;
			pageTotalJSP=pageTotal;
		}
	})
}
//注册用户名检测
function Checkexist() {
	var userName = $("#userName").val();
	var flag=true;
	$.ajax({
		url : "CheckUserName",// 请求的servlet地址
		type : "POST",// 请求方式
		async: false,
		data : "userName=" + userName,// 发送到服务器的数据
		dataType : "text",// 设置返回数据类型
		success : function(test) {
			if (test == "no") {
				var msgBox = document.getElementById("uName");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "用户名已存在！";
				flag= false;
			} else if(test == "null"){
				var msgBox = document.getElementById("uName");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "用户名不能为空！";
				flag= false;
			}else if(test == "ok"){
				var msgBox = document.getElementById("uName");
				var msgBoxParent =this.parentNode.getElementsByTagName("span")[0];
				msgBoxParent = "";
				msgBox.style.display = "inline";
				msgBox.innerHTML = null;
			}
		},// 响应成功后执行的回调方法data响应文本
		complete : function(XMLHttpRequest, statusText) {

		},// 响应完成后执行的回调方法
		error : function(XMLHttpRequest, statusText) {
			alert("操作失败!")
		}// 响应失败后执行的回调方法
	})
	return flag;
}
//注册用户名身份证号码重复检测
function CheckCardId() {
	var cardId = $("#cardId").val();
	var flag=true;
	$.ajax({
		url : "checkCardId",// 请求的servlet地址
		type : "POST",// 请求方式
		async: false,
		data : "cardId=" +cardId,// 发送到服务器的数据
		dataType : "text",// 设置返回数据类型
		success : function(test) {
			if (test == "no") {
				var msgBox = document.getElementById("cardid");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "该身份证已被使用！";
				flag= false;
			}else if(test == "null"){
				var msgBox = document.getElementById("uName");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "身份证号码不能为空！";
				flag= false;
			} else if(test == "ok"){
				var msgBox = document.getElementById("cardid");
				var msgBoxParent =this.parentNode.getElementsByTagName("span")[0];
				msgBoxParent = "";
				msgBox.style.display = "inline";
				msgBox.innerHTML = null;
			}
		},// 响应成功后执行的回调方法data响应文本
		complete : function(XMLHttpRequest, statusText) {

		},// 响应完成后执行的回调方法
		error : function(XMLHttpRequest, statusText) {
			alert("操作失败!")
		}// 响应失败后执行的回调方法
	})
	return flag;
}
//登陆表单验证
function loginCheck(){
	
	if($("[name=userName]").val()==""){
		alert("请输入用户名！")
		return false;
	}
	
	if($("[name=passWord]").val()==""){
		alert("请输入用户密码！")
		return false;
	}
	
	if($("[name=veryCode]").val()==""){
		alert("请输入验证码！")
		return false;
	}else{
		return checkValidateCode();
	}	
}
//验证码 验证
function checkValidateCode() {
	var veryCode=$("[name=veryCode]").val()
	var flag=true;
	$.ajax({
		url : "checkCode",// 请求的servlet地址
		type : "GET",// 请求方式
		async: false,
		data : "veryCode=" + veryCode,// 发送到服务器的数据
		dataType : "text",// 设置返回数据类型
		success : function(test) {
			if (test == "no") {
				var msgBox = document.getElementById("Code");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "验证码错误！";
				flag= false;
			}else if (test == "ok") {
				var msgBox = document.getElementById("Code");
				var msgBoxParent =this.parentNode.getElementsByTagName("span")[0];
				msgBoxParent = "";
				msgBox.style.display = "inline";
				msgBox.innerHTML = null;
			}
		}
	})
	return flag;
}
function FocusItem(obj) {
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}
//注册表单验证
function CheckItem(obj) {
	obj.parentNode.parentNode.className = "";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	var regEmail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
	var regIdentity = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
	var regMobile = /^1\d{10}$/;
	var regBirth = /^((19\d{2})|(200\d))-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
	var regName = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
	var regPass = /^[a-zA-Z0-9]{4,10}$/;
	switch (obj.name) {
	case "userName":
		if (obj.value == "" || regName.test(obj.value) == false) {
			msgBox.innerHTML = "用户名不能为空并且只能是字母开头和字母数字结尾，长度在4-15之间";
			msgBox.className = "error";
			return false;
		}else{
			return Checkexist();
		}
		break;
	case "passWord":
		if (obj.value == "" || regPass.test(obj.value) == false) {
			msgBox.innerHTML = "密码不能为空并且不能含有非法字符，长度在4-10之间";
			msgBox.className = "error";
			return false;
		}
		break;
	case "rePassWord":
		if (obj.value == "") {
			msgBox.innerHTML = "确认密码不能为空";
			msgBox.className = "error";
			return false;
		} else if (obj.value != document.getElementById("passWord").value) {
			msgBox.innerHTML = "两次输入的密码不相同";
			msgBox.className = "error";
			return false;
		}
		break;
	case "veryCode":
		if (obj.value == "") {
			msgBox.innerHTML = "验证码不能为空";
			msgBox.className = "error";
			return false;
		}else{
			return checkValidateCode();
		}
		break;
	case "birthday":
		if (obj.value == "" || regBirth.test(obj.value) == false) {
			msgBox.innerHTML = "出生日期不能空,格式为（1990-01-01）";
			msgBox.className = "error";
			return false;
		}
		break;
	case "identity":
		if (obj.value == "" || regIdentity.test(obj.value) == false) {
			msgBox.innerHTML = "输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X";
			msgBox.className = "error";
			return false;
		}else{
			return CheckCardId();
		}
		break;
	case "email":
		if (obj.value == "" || regEmail.test(obj.value) == false) {
			msgBox.innerHTML = "电子邮件不能为空,格式为web@sohu.com";
			msgBox.className = "error";
			return false;
		}else{
			return emailExist();
		}
		break;
	case "mobile":
		if (regMobile.test(obj.value) == false) {
			msgBox.innerHTML = "手机不能为空必须为11位并且只能是数字";
			msgBox.className = "error";
			return false;
		}
		break;
	case "address":
		if (obj.value == "") {
			msgBox.innerHTML = "地址不能为空";
			msgBox.className = "error";
			return false;
		}
		break;
	}
	return true;
}

function checkForm(frm) {
	var els = frm.getElementsByTagName("input");

	for (var i = 0; i < els.length; i++) {
		
			if (!CheckItem(els[i]))
				return false;
	}
	return true;
}

function emailExist() {
	var flag=true;
	var email=$("[name=email]").val();
	$.ajax({
		url : "CheckEmail",// 请求的servlet地址
		type : "GET",// 请求方式
		data : "uemail=" + email,// 发送到服务器的数据
		async: false,
		dataType : "text",// 设置返回数据类型
		success : function(test) {
			if (test == "no") {
				var msgBox = document.getElementById("uemail");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "该邮箱已经注册！";
				flag= false;
			}else if (test == "null") {
				var msgBox = document.getElementById("uemail");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "邮箱不能为空！";
				flag= false;
			} else if (test == "ok") {
				var msgBox = document.getElementById("uemail");
				var msgBoxParent =this.parentNode.getElementsByTagName("span")[0];
				msgBoxParent = "";
				msgBox.style.display = "inline";
				msgBox.innerHTML = null;
			}
		},// 响应成功后执行的回调方法data响应文本
		complete : function(XMLHttpRequest, statusText) {

		},// 响应完成后执行的回调方法
		error : function(XMLHttpRequest, statusText) {
			alert("操作失败!")
		}// 响应失败后执行的回调方法
	})
	return flag;
}

//加载最近浏览
function getcookie(){
	if($.cookie("browse") == undefined){
		
		return;
	}
		$(".pre_look").empty();
		var pro_id = $.cookie("browse");
	
	$.ajax({
		url:"productBrowse",
		type:"post",
		async:true,
		data:"browse="+pro_id,
		dataType:"json",
		success: function(list){
			$(".pre_look").append("<h3 class='pre_lookh3'>最近浏览</h3>");
			for(var i =  list.length-1; i >= 0; i--){
				$(".pre_lookh3").after("<dl><dt><img style='width: 54px; height: 54px;' src='"+list[i].abc+"' /></dt><dd><a href='productDetails?id="+list[i].id+"'>"+list[i].name+"</a></dd></dl>");
			}
		}
	})
}

//加载新闻动态
function getNews(){
	$("#newsListUl").empty();
	$.ajax({
		url:"getNews",
		type:"post",
		async:true,
		dataType:"json",
		success: function(list){
			for(var i = 0; i < list.length; i++){
				$("#newsListUl").append("<li><a href='newsview?id="+list[i].id+"'>"+list[i].title+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>");
			}
		}
	})
}
//加载热卖商品展示
function hotProduct(){
	$(".hot_sale_ul").empty();
	$.ajax({
		url:"hotProduct",
		type:"post",
		async:true,
		dataType:"json",
		success: function(list){
			for(var i = 0; i < list.length; i++){
				$(".hot_sale_ul").append("<li><dl><dt><a href='productDetails?id="+list[i].id+"' ><img src='"+list[i].abc+"' /></a></dt><dd class='p_name'><a href='productDetails?id="+list[i].id+"'>"+list[i].name+"</a></dd><dd class='price'>￥"+list[i].price+"</dd></dl></li></ul>");
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





