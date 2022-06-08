<%@ page import="java.util.List" %>
<%@ page import="model.Product" %><%--
  Created by IntelliJ IDEA.
  User: 19813
  Date: 2022/6/1
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="img/shortcut.png" rel="shortcut icon" type="image/x-icon"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <title>购物车信息</title>
</head>
<body>
<div class="container">
    <h1 class="text-center text-danger">购物车信息</h1>
    <!--这里给出了购物车中一个产品的显示格式-->
    <%
        List<Product> list;
        float allProductPrice = 0;
        list = (List<Product>)session.getAttribute("cartList");
        if (null == list){
            out.println("<hr><h1>购物车现在还没有添加任何信息！</h1></hr>");
            return;
        }
        for (Product p : list){
            allProductPrice += p.getProductPrice() * p.getProductQty();
    %>
    <div class="cartProduct">
        <img src="img/<%= p.getProductImg()%>" width="120" height="120">
        <hr>
        产品名称：卫龙辣条 <%= p.getProductName()%>
        <br>产品单价：<%= p.getProductPrice()%>
        <br>包装单位：<%= p.getProductUnit()%>
        <br>购买数量：<%= p.getProductQty()%>
        <br><b class="text-primary">小计金额：<%= p.getProductPrice() * p.getProductQty()%> </b>
    </div>
    <%
        }
    %>

    <hr>
    <h3 class="text-danger">总计金额：<%=allProductPrice%></h3>
    <h3 class="text-danger">立即结算<%=allProductPrice%></h3>
</div>
</body>
</html>
