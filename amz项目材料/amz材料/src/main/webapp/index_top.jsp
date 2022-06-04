<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	double num = Math.random();
%>

<div id="header">
	<div class="login_menu">
		<div class="login_container">
			<%-- <c:set value="${sessionScope.user }" var="user"></c:set> --%>
			
			<c:if test="${empty user}">
				<ul class="m_left">
					<li><a href="${pageContext.request.contextPath }/login.jsp" class="c_red">请登录</a>&nbsp;&nbsp;&nbsp;</li>
					<li><a href="${pageContext.request.contextPath }/register.jsp">请注册</a></li>
				</ul>
			</c:if>
		
			<c:if test="${!empty user}">
				<ul class="m_left">
					<li><a href="javascript:;" class="c_red">${user.uname}</a>&nbsp;&nbsp;&nbsp;</li>
					<li><a href="${pageContext.request.contextPath }/logout">注销</a></li>
				</ul>
			</c:if>
			
			<ul class="m_right">
			<c:if test="${!empty user}">
			<li><a href="${pageContext.request.contextPath }/orders_view.jsp">我的订单</a></li>
			</c:if>
				<li><img src="images/icon_3.png"><a
					href="${pageContext.request.contextPath }/shopping.jsp" class="c_red">购物车</a></li>
				<li><img src="images/icon_4.png"><a
					href="javascript:AddFavorite('我的网站',location.href)">收藏</a></li>
				<li><img src="images/icon_2.png"><a href="guestbook.jsp">留言</a></li>
				<li><img src="images/icon_1.png"><a href="index.jsp">首页</a></li>
			</ul>
		</div>
	</div>
	<div class="logo_search">
		<div class="logo">
			<img src="images/LOGO.png">
		</div>
		<div class="search">
			<input type="text" placeholder="输入宝贝" id="qname" />
			<button class="query_button" onclick="likequery()">搜索</button>
		</div>
		<div id='weatherdiv'>
			
		</div>
	</div>
	<div class="nav_bar">
		<div class="nav_bar_container">
			<ul>
				<li><a href="productDetails?id=3">联想笔记本电脑</a></li>
				<li><a href="productDetails?id=8">爱国者MP4</a></li>
				<li><a href="productDetails?id=17">达派高档拉杆箱</a></li>
				<li><a href="productDetails?id=62">GPS</a></li>
			</ul>
		</div>
	</div>
</div>
