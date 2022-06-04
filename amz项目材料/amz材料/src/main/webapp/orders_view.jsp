<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 订单页</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/orders_view.js"
	type="text/javascript"></script>

<style type="text/css">
#oderview {
	font-size: 14px
}
</style>

</head>
<body>
	<%@ include file="index_top.jsp"%>

	<div id="position" class="wrap">
		您现在的位置：<a href="index.jsp">亚马逊</a> &gt; 最新订单
	</div>
	<div class="wrap">
		<div id="shopping">
			<form action="doBuy" method="post">
				<table id="oderview">
					<tr>
						<th>订单时间:</th>
						<th>订单号:</th>
						<th>订单总额:</th>
						<th>
						<th>
					</tr>
					<!-- 根据用户购物车生成列表 -->
					<tr id="product_id_1">
						<td class="thumb">
							<ul>
								<li><img style="width: 100px; height: 100px;"	src="" /><a href="">aaaaa</a><span style='color:red;font-size:18px;'>￥单价：</span><span>X</span></li>
							</ul>
						</td>
						<td class="price">
					
							<span>已付款</span>
						
				
						</td>
						<!-- <td class="number"><span>X</span></td> -->
						<td class="delete">正在发货</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海海文 All Rights Reserved.
	</div>
</body>
</html>

