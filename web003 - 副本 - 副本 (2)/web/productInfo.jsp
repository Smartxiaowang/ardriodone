<%--
  Created by IntelliJ IDEA.
  User: 19813
  Date: 2022/5/31
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="img/shortcut.png" rel="shortcut icon" type="image/x-icon"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <title>产品信息</title>
    <%request.getAttribute("product");%>
</head>
<body>
<h1 class="text-center text-primary">产品信息</h1>
<hr>
<br>

<!--在此div中显示从数据库查询到的一个产品的所有信息-->
<div class="content">
    <br>
    <!--显示产品图片，图片在images文件夹中给出了，以产品的编号命名的图片-->
    <!--这里使用EL表达式显示获取的产品对象的各个信息-->
    <img src="img/${requestScope.product.productImg}" class="pic">

    <!--在EL表达式中，获取会话对象中的属性，使用sessionScope-->
    <!--获取请求对象中的属性，使用requestScope-->
    <h3>产品名称：${requestScope.product.productName}</h3>
    <p>产品单价： ${requestScope.product.productPrice}</p>
    <p>包装单位： ${requestScope.product.productUnit}</p>
    <p>剩余库存： ${requestScope.product.productQty}</p>

    <!--提交表单，请求发送给cs，将产品添加到购物车-->
    <form action="${pageContext.request.contextPath}/CartServlet?request=${requestScope.product}" class="form-inline">
        <b>购买数量：</b>
        <input type="hidden" name="proId" value="${requestScope.product.productId}">
        <input type="hidden" name="proName" value="${requestScope.product.productName}">
        <input type="hidden" name="proPrice" value="${requestScope.product.productPrice}">
        <input type="hidden" name="proUnit" value="${requestScope.product.productUnit}">
        <input type="hidden" name="proImg" value="${requestScope.product.productImg}">

        <input type="number" min="1" name="qty" value="1" class="form-control">
        <br><br>
        <button class="btn btn-sm btn-danger">加入购物车</button>
        &nbsp;
        <button class="btn btn-sm btn-warning">立刻购买</button>
    </form>

</div>

<br>
<a href="cart.jsp" class="cartImg">
    查看购物车<img src="img/cart.png" alt="购物车"/>
</a>
</body>
</html>
