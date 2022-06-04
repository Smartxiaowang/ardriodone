<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊- 购物车</title>
	<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/shoppingcar.js"
	type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
</head>
<style type="text/css">
	.title_css{
	line-height:100px;
		float:left;
		
		margin-left:50px
		
	}
	.title_css1{
	
		float:left;
	}
	#totalpricediv{
		font-size:35px;
		color:#D53402;
	}
	#totalpricediv>span{
	font-size:28px;
	}
	#fristcheckboxspan{
	display:block;
		float:left;
		/* height:20px;
		line-height:20px; */
		 margin-left:5px;
		
	}
	.checkbox{
		margin-top:5px;
		margin-left:5px;
	}
	.checkboxson{
		display:block;
		float:left;
		margin-top:40px;
		margin-left:5px;
	}
</style>
<body>
<%@ include file="index_top.jsp"  %>

<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">亚马逊</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="javascript:;" method="post" id='formtable' onsubmit="return checkout()">
			<div class="button" id="totalpricediv">￥： <span id="totalpricespan">0.0</span></div>
			<input type="hidden" name='hname' id="hh" value=''>
			<div class="button" id="buttondiv">
				<input type="submit" value="" />
			</div>
		</form>
	</div>
</div><!--${pageContext.request.contextPath}/shopping-result.jsp  -->
<div id="footer">
	Copyright &copy; 2018 上海海文 All Rights Reserved.
</div>
</body>
</html>

