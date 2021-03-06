<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 购物车</title>
	<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="position" class="wrap">
	您现在的位置：<a href="ProductServlet">亚马逊</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<div class="shadow">
			<em class="corner lb"></em>
			<em class="corner rt"></em>
			<div class="box">
				<div class="msg">
					<p>恭喜：购买成功！</p>
					<p>正在查看最新订单...</p>
					<script type="text/javascript">
						setTimeout("location.href='processorder'", 3000);
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	Copyright © 2018 上海海文 All Rights Reserved.
</div>
</body>
</html>

