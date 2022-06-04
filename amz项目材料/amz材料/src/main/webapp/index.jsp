<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊-首页</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
	<style type="text/css">
	.p-page{
            width: 500px;
            height:20px;
            margin: 0  auto;
            margin-bottom:10px;
        }
        .p-page-ul{
            list-style-type: none;
        }
        .p-page-ul-li{
            float: left;
            margin: 0 3px;
        }
        .p-page-ul-li a:link,.p-page-ul-li a:visited{
             display: block; 
             line-height: 20px;
            background-color: white;
            text-decoration: none;
             padding: 0 3px; 
             padding-right:4px;
            border: 1px solid #999;
            font-size: 11px;
            color: #999;
        }
         .p-page-ul-li a:hover, .p-page-ul-li a:active{
            color: white;
            background-color: #FF0200;
        }
        .p-page-span{
            float: left;
            margin-left: 20px;
            line-height: 20px;
        }
        .p-page-span>a:link,.p-page-span>a:visited{
            display: block;
            padding: 0 11px;
            background-color: white;
            text-decoration: none;
            border: 1px solid #999;
            font-size: 11px;
            color: #999;
            margin-left: -10px;
        }
        .p-page-span>a:hover,.p-page-span>a:active{
            color: white;
            background-color: #FF0200;
        }
      
	
	</style>
</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="middle">
		<div class="p_left">
			<!--商品分类-->
			<%@ include file="index_product_sort.jsp"%>
			<!--商品分类结束-->

			<div class="pre_look">
				<h3>最近浏览</h3>
			</div>
		</div>
		<div class="p_center">
			<div id="wrapper">
				<div id="focus">
					<ul>
						<li><a href="#"><img src="images/T1.jpg" /></a></li>
						<li><a href="#"><img src="images/T2.jpg" /></a></li>
						<li><a href="#"><img src="images/T3.jpg" /></a></li>
						<li><a href="#"><img src="images/T4.jpg" /></a></li>
						<li><a href="#"><img src="images/T5.jpg" /></a></li>
					</ul>
				</div>
			</div>
			<div class="p_list">
				<div class="p_info">
					<img src="images/icon_sale.png" />商品列表
				</div>
			</div>
			<ul class="product2">	</ul>
		</div>

		<div id="p_right">
			<%@ include file="index_news.jsp"%>
			<%@ include file="hotproduct.jsp"%>
		</div>
	</div>
<div class="p-page">
    <ul class="p-page-ul">
        <li class="p-page-ul-li">
            <a href="javascript:;" onclick="choosePage(this)">上一页</a>
        </li>
        <li class="p-page-ul-li sortpage">
            <a href="javascript:;" onclick="choosePage(this)">下一页</a>
        </li>
    </ul>
    <span class="p-page-span">
        去第
    <input type="text" size="3" id="inputNum"/>
        页
    </span>
    <span class="p-page-span">
    <a href="javascript:;" id="givePage" onclick="choosePage(this)">确定</a>
    </span>
</div> 
	<div id="foot">Copyright © 2018 上海海文 All Rights Reserved.</div>
</body>
<script type="text/javascript">

</script>
</html>

