<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 产品列表</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/product_list.js"></script>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
	<style type="text/css">
		#noproduct{
			color:blue;
			font-size:40px;
			display:block;
			height:50px;
			width:500px;
			margin: 150px auto;
		}
		#allproductul{
			text-align: center;
			list-style-type: none;
		}
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
	<div id="position" class="wrap">
		您现在的位置：<a href="index.jsp">亚马逊</a> &gt; <a
			href="product-list.jsp">图书音像</a> &gt; 图书
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<%@ include file="index_product_sort.jsp"%>
			<div class="spacer"></div>
			<div class="last-view">
				<h2>最近浏览</h2>
				<%-- <dl class='clearfix'>

					<dt>
						<img style="width: 54px; height: 54px;" src="images/product/0.jpg" />
					</dt>
					<dd>
						<a href="#">商品名称</a>
					</dd>

					
						<c:forEach items="" var="p">
							<dt>
								<img style="width: 54px; height: 54px;" src="" />
							</dt>
							<dd>
								<a href=""></a>
							</dd>
						</c:forEach>
				

				</dl> --%>
			</div>
		</div>
		<div class="main">
			<div class="product-list">
				<h2>全部商品</h2>
				<div class="clear" id="cleardiv"></div>
				<span id="noproduct"></span>
				<ul class="product clearfix" id="allproductul">

					<li>
						<dl>
							<dt>
								<a href="#" target="_self"><img src="images/product/0.jpg" /></a>
							</dt>
							<dd class="title">
								<a href="#" target="_self">商品名称</a>
							</dd>
							<dd class="price">￥12.34</dd>
						</dl>
					</li>

					<li>
						<dl>
							<dt>
								<a href="#" target="_self"><img src="images/product/0.jpg" /></a>
							</dt>
							<dd class="title">
								<a href="#" target="_self">商品名称</a>
							</dd>
							<dd class="price">￥12.34</dd>
						</dl>
					</li>


				</ul>
				<div class="clear"></div>
				<div class="p-page">
    <ul class="p-page-ul">
        <li class="p-page-ul-li">
            <a href="javascript:;" onclick="productbycategory(this)">上一页</a>
        </li>
        <li class="p-page-ul-li sortpage">
            <a href="javascript:;" onclick="productbycategory(this)">下一页</a>
        </li>
    </ul>
    <span class="p-page-span">
        去第
    <input type="text" size="3" id="inputNum"/>
        页
    </span>
    <span class="p-page-span">
    <a href="javascript:;" id="givePage" onclick="productbycategory(this)">确定</a>
    </span>
</div> 
				<!-- <div class="pager">
					<ul class="clearfix">
						<li><a href="#">上一页</a></li>
						<li>2</li>


						<li><a href="#">3</a></li>


						<li><a href="#">下一页</a></li>
					</ul>
				</div> -->
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海海文 All Rights Reserved.
		京ICP证1000001号</div>
</body>
</html>

