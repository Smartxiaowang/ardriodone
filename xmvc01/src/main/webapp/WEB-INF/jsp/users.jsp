<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<div class="container">
		<div class="row" style="margin-top: 40px">
			<div class="col-md-8 col-md-offset-2 ">
				<div class="panel panel-primary">
					<div class="panel-heading">用户列表</div>

					<table class="table table-bordered table-hover text-center">
						<thead>
							<tr class="info">
								<td>用户名称</td>
								<td>用户密码</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="u" items="${userList}">
								<tr>
									<td>${u.userName }</td>
									<td>${u.password }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>

	</div>

</body>
</html>