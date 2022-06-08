<%--
  Created by IntelliJ IDEA.
  User: 19813
  Date: 2022/5/26
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
    <style>
body{
    background: url("img/10.jpg");
    background-size: 100% 100%;
}
#box{
    width: 50%;
    height: 380px;
    margin: 50px auto;
    background: rgb(255 251 251 / 20%);
    margin-top: 230px;
    border-radius: 23px;
}
.title{
    text-align: center;
}
.content{
    width: 100%;
    height: 200px;
}
.content a{
    margin-left: 40%;
    color: #cded74;
}
    </style>
</head>
<body>
<div id="box">
<div class="title">
    <h1>欢迎<%=request.getAttribute("id")%>进入到商城</h1>
    <h2>您可以做出以下选择</h2><br>
</div>

<div class="content">
    <a href="talk.jsp">与机器人聊天</a><br><br>
    <a href="goods.jsp">进入购物商城</a>
</div>
</div>

</body>
</html>
