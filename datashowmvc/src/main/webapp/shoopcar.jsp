<%@ page import="com.neu.pojo.CarBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<h1>欢迎光临网上书店</h1>
<p>下面是您已经购买的书</p>
<%
    List<CarBean> carBeanList = (List<CarBean>) request.getSession().getAttribute("carList");
    for (CarBean c : carBeanList) {
%>
<p><%= c.getBookid()%> 作者：<%= c.getAuthor()%> <%= c.getPrice()%>
</p>
<%
    }
%>
<p></p>
<a href="ShowBook">回到主页面</a>
</body>
</html>
