<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 产品显示</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/product_view.js"></script>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />


</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="position" class="wrap">
		您现在的位置：<a href="index.jsp" class="positionA" id="positionA">亚马逊</a> &gt; <a href="javascript:;" id="_${productDetail.majorId}" onclick="lid(this)">${productDetail.mcategory}</a> &gt; <a
			href="javascript:;" id="${productDetail.majorId}_${productDetail.minorId}" onclick="sid(this)">${productDetail.scategory}</a>
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<%@ include file="index_product_sort.jsp"%>
		</div>
		<div id="product" class="main">
			<h1>商品名称:</h1>
			<div class="infos">
				<div class="thumb">
					<img style="width: 100px; height: 100px;" src="${productDetail.abc}" />
				</div>
				<div class="buy">
					<p>
						商城价：<span class="price" id="">￥<span id="product_view_price">${productDetail.price}</span></span>
					</p>
					<c:if test="${!(productDetail.stock == '0')}">
					<p>
						库 存：<span id="stock">${productDetail.stock}</span>(有货)
					</p>
					</c:if>
					<c:if test="${productDetail.stock == '0'}">
					<p>
						库 存：无货
						</p>
						</c:if>
						 <input type="button" onclick="productNum(this)" id="minus" value='&nbsp;<&nbsp;' width="3px">
						<input type="text" id="count" name="count" value="1" maxlength="5" size="3" style="text-align: center; vertical-align: middle">
						<input type="button" onclick="productNum(this)" id="add" value="&nbsp;>&nbsp;" width="3px">
						<span id="stock_msg" style="color:red;"></span>
						<div class="button">
							<input type="button"  onclick='buyNow()' style="background: url(images/buyNow.png)" /> 
							<input type="image" onclick="addCar()" name="imageField" src="images/cartbutton.png" />
						</div>
						<input type="hidden" value="${productDetail.id}">
				</div>
				<div class="clear"></div>
			</div>
			<div class="introduce">
				<h2>
					<strong>商品详情</strong>
				</h2>
				<div class="text">
					商品名字：${productDetail.name}<br /> 商品描述：${productDetail.description}<br />
					商品价格：￥${productDetail.price}<br /> 商品库存：${productDetail.stock}<br />
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海海文 All Rights Reserved.
	</div>
</body>
</html>

