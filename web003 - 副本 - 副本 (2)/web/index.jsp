<%--
  Created by IntelliJ IDEA.
  User: 19813
  Date: 2022/5/26
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>21软件工程专升本购物</title>
      <style>
          body{
              background: url("img/14.jpg");
              background-size: 100% 100%;
          }
          #box{
              width: 50%;
              height: 380px;
              margin: 50px auto;
              background: rgb(80 76 76 /20%);
              margin-top: 230px;
          }
          #content{
              width: 400px;;
              margin: 0px auto;
          }
          .form-input{
              height: 46px;
              line-height: 46px;
              margin-top: 10px;;
              border-top: 1px solid #e1e7ec;
          }
          .form-input input{
              box-sizing: border-box;
              padding: 0 25px;
              background: #eef3f5;
              border-radius: 8px;
              width: 100%;
              height: 100%;
              border: 0;
              outline: 0;
              font-size: 14px;

          }
          .title{
              width: 100%;
              height: 50px;
              border-bottom: 1px solid #e1e7ec;
              text-align: center;
              color: #fff;
              line-height: 50px;


          }
          .primary-button{
              width: 100%;
              line-height: 50px;
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
              margin-top: 40px;
              margin-left: 35px;
              margin-bottom:20px ;
          }
          .primary-h a{
              color: #cded74;
              margin-left: 100px;
          }
      </style>
  </head>
  <body>
<%--  ${pageContext.request.contextPath}--%>
<%--<form method="post" action="http://localhost:8080/web003_war_exploded/LoginServlet">--%>
<div id="box">
    <div class="title">
        <p>购物管理系统登录界面</p>
    </div>
<form method="post" action="${pageContext.request.contextPath}/LoginServlet">
    <div id="content">

    <div class="form-input">
        <input type="text" name="name" placeholder="请输入手机号/邮箱" class="form-input"><br/>
    </div>
        <div class="form-input">
        <input type="password" name="pwd" placeholder="请输入密码" class="form-input"><br>
    </div>

    <div class="primary-button">
        <input type="submit" value="登录"><br>
    </div>

    <div class="primary-h">
     <a href="Register.jsp">还没有账号？点击注册</a>
    </div>

</div>
</form>
</div>
</body>
</html>
