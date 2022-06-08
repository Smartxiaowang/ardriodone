<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>

<h1 style="text-align: center">用户信息列表</h1>
<div class="container">
    <div class="row" style="margin-top: 40px">
        <div class="col-md-8 col-md-offset-2 ">
            <div class="panel panel-primary">
                <div class="panel-heading">用户列表
                    <button onclick="add()" style="color: black;margin-left: 600px;">添加信息</button>
                </div>

                <table class="table table-bordered table-hover text-center">
                    <thead>
                    <tr class="info">
                        <td>用户id</td>
                        <td>用户名称</td>
                        <td>用户密码</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="u" items="${userList}">
                        <tr>
							<td>${u.id }</td>
                            <td>${u.userName }</td>
                            <td>${u.password }</td>
                            <td>
                                <button onclick="upd()">
                                    修改
                                </button>
                                <button onclick="del()">
                                    删除
                                </button>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

</div>
<script>
	function add() {
		window.location="insert";
	}
	function del() {
		window.location="deluser";
	}
	function upd() {
		window.location="upduser";
	}

</script>
</body>
</html>