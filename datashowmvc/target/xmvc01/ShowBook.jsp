<%@ page import="com.neu.pojo.BookBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.neu.service.ShopServiceImpl" %>
<%@ page import="com.neu.mapper.ShopMapper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

%>
<html>

<head>
    <title>网上书店</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>

<body>
<h1 style="text-align: center;">欢迎光临网上书店</h1>
<p style="text-align: center;">请选择你购买的书，加入购物车</p>
<%
    String msg = (String) request.getSession().getAttribute("msg");
    if (msg != null && !msg.equals("")) {
%>
<h1 style="text-align: center; font-size: 50px;color: #ff0000"><% out.print(msg);%></h1>
<% }%>


<div class="container">
    <div class="row" style="margin-top: 40px">
        <div class="col-md-8 col-md-offset-2 ">
            <div class="panel panel-primary">
                <div class="panel-heading" style="font-size: 30px;">图书列表
                    <a href="ShopCar" style="color: black;font-size: 15px;margin-left: 480px;">查看购物车</a>
                    <table border="0" class="table table-bordered table-hover text-center">
                        <thead>
                        <tr class="info">
                            <th>图书编号</th>
                            <th>作者</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<BookBean> bookList = (List<BookBean>) request.getSession().getAttribute("bookList");

                            for (BookBean b : bookList) { %>
                        <tr>
                            <td><a href="bookinfo?bookid=<%=b.getId()%>" style="color: black;">
                                <%=b.getId()%>
                            </a>
                            </td>
                            <td><%=b.getAuthor()%>
                            </td>
                            <td><a href="ShopCar?bookid=<%= b.getId()%>" style="color: black;">加入购物车</a></td>
                        </tr>
                        <% }%>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
