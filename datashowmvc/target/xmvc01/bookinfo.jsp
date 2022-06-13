<%@ page import="com.neu.pojo.BookBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>图书详细信息</title>
</head>
<%
    BookBean bookInfo = (BookBean) request.getSession().getAttribute("bookInfo");
%>
<body>
<h1>图书详细信息如下</h1>

<div style="width: 200px;">图书编号：
    <b><%= bookInfo.getId()%>
    </b>
</div>
<div style="width: 200px;">ISBN编号：
    <b><%= bookInfo.getIsbn()%>
    </b>
</div>
<div style="width: 200px;">书名：
    <b><%= bookInfo.getBookname()%>
    </b>
</div>
<div style="width: 200px;">作者：
    <b><%= bookInfo.getAuthor()%>
    </b>
</div>
<div style="width: 200px;">价格：
    <b><%= bookInfo.getPrice()%>
    </b>
</div>
<div style="width: 200px;">库存数量：
    <b><%= bookInfo.getNum()%>
    </b>
</div>
<p></p>
<a href="ShowBook">回到主页面</a> <a href="ShopCar?bookid=<%= bookInfo.getId()%>">添加到购物车</a>
</body>

</html>
