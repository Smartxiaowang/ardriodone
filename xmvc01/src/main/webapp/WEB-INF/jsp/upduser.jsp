<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改窗口</title>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row" style="margin-top: 80px">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">修改用户</div>
					<div class="panel-body">
						<form action="${pageContext.request.contextPath}/upd" method="post"  >
							<div class="form-group">
								<label for="id">用户id</label> <input type="text"
																	class="form-control" name="id" placeholder="请输入用户id">
							</div>
							<div class="form-group">
								<label for="userName">用户名</label> <input type="text"
									class="form-control" name="userName" placeholder="请输入用户名">
							</div>
							<div class="form-group">
								<label for="password">密码</label> <input type="password"
									class="form-control" name="password" placeholder="请输入密码"
									value="" >
							</div>
							<div class="form-inline text-right">
								<button type="submit" class="btn btn-primary">修改</button>
								<button type="reset" class="btn btn-default" focus>清空</button>

							</div>
						</form>
					</div>
				</div>
			</div>

		</div>

	</div>
</body>
</html>