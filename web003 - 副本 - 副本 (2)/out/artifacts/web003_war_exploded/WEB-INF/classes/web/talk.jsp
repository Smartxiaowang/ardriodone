<%--
  Created by IntelliJ IDEA.
  User: 19813
  Date: 2022/5/27
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body{

       /*background: url("img/动图.gif");*/
       /* background-size: 100%;*/
        background: black;
    }
    .title{
        height: 50px;
        border-bottom: 1px solid #e1e7ec;
        text-align: center;
        color: #86f0b0;

    }
    .title h2{
        margin-top: 10px;
        float: left;
    }
    .title p{
        float: right;
    }
    #box{
        width: 40%;
        height: 380px;
        margin: 50px auto;
        background: rgb(80 76 76 /20%);
        margin-top: 230px;
        background: url("img/太空人1.gif");
        background-size: 100% 100%;
    }
    #content{
        width: 400px;;
        margin: 50px auto;
    }
    .my-input{
        height: 60px;
        line-height: 46px;
    }
    .my-input input{
        box-sizing: border-box;
        padding: 0 25px;
        background: #eef3f5;
        border-radius: 8px;
        width: 100%;
        height: 50px;
        border: 0;
        outline: 0;
        font-size: 14px;
    }
    .primary-button input{
        background: linear-gradient(325deg,#4aa4ff,#1058fa);
        width: 80%;
        height: 42px;
        border-radius: 23px;
        border: 0;
        outline: none;
        color: #fff;
        letter-spacing: 10px;
        font-weight: 500;
        font-size: 16px;
        cursor: pointer;
        margin-top: 10px;
        margin-left: 35px;
        margin-bottom:20px ;
    }
    .robot{
       width: 100%;
        color: #86f0b0;
        text-align: center;
    }

</style>
<body>
<div id="box">
    <div class="title">
        <h2>与机器人对话</h2>
        <p>退出系统请输入3</p>
    </div>
    <form action="${pageContext.request.contextPath}/talkServlet" method="post">
<div id="content">
        <div class="my-input">
        <input type="text" name="my_word" value="" class="my-input" placeholder="嗨 sir">
        </div>

        <div class="primary-button">
          <input type="submit" name="submit" value="发送">
        </div>
</div>
    </form>

<div class="robot">
    机器人说：
    <%=request.getAttribute("Answer")%>
</div>

</div>

</body>
</html>
