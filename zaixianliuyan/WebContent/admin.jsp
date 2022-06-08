<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "com.edu.entity.AdminDao" %>
<%@ page import="com.edu.entity.AdminBean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
	<meta charset="UTF-8">
	<title>影评管理系统后台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
	<script src="static/jQuery/jquery-3.1.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/bootstrap-dropdown.min.js"></script>
	<script src="static/js/adminUpdateInfo.js"></script>
	<script src="static/js/adminUpdatePwd.js"></script>

</head>


<body class="bootstrap-admin-with-small-navbar">
<%
	AdminBean admin = new AdminBean();
	String aid = (String)session.getAttribute("aid");
	AdminDao admindao = new AdminDao();
	admin = admindao.get_AidInfo2(aid);

%>
<nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="collapse navbar-collapse main-navbar-collapse">
					<a class="navbar-brand"><strong>影评管理系统</strong></a>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i> 欢迎您,<%out.print(admin.getName());%> <i class="caret"></i></a>

							<ul class="dropdown-menu">
								<li><a href="#updateinfo" data-toggle="modal">个人资料</a></li>
								<li role="presentation" class="divider"></li>
								<li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
								<li role="presentation" class="divider"></li>
								<!-- href="#identifier"  来指定要切换的特定的模态框（带有 id="identifier"）。-->
								<li><a href="login.jsp">退出</a></li>
							</ul>

						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</nav>

<div class="container">
	<!-- left, vertical navbar & content -->
	<div class="row">
		<!-- left, vertical navbar -->
		<div class="col-md-2 bootstrap-admin-col-left">
			<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">

				<li>
					<a href="admin.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 用户管理</a>
				</li>

			</ul>

		</div>

		<!-- content -->
		<div class="col-md-10">


			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default bootstrap-admin-no-table-panel">
						<div class="panel-heading">
							<div class="text-muted bootstrap-admin-box-title">用户管理</div>
						</div>
						<div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
							<form class="form-horizontal" action="/jiudain/AddUserServlet" method="post">

								<div class="col-lg-3 form-group">

									<button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" data-target="#addUserModal">添加用户</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-lg-12">
					<table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
						<thead>
						<tr>
							<th>账号</th>
							<th>姓名</th>
							<th>密码</th>
							<th>邮箱</th>
							<th>手机号</th>
							<th>操作</th>

						</tr>
						</thead>


						<!---在此插入信息-->
						<%
							ArrayList<AdminBean> data2 = new ArrayList<AdminBean>();
							data2 = (ArrayList<AdminBean>)request.getAttribute("data");
							if(data2==null){

								data2 = (ArrayList<AdminBean>)admindao.get_ListInfo();
							}

							for (AdminBean bean : data2){
						%>
						<tbody>
						<td><%= bean.getUsername() %></td>
						<td><%= bean.getName() %></td>
						<td><%= bean.getPassword() %></td>
						<td><%= bean.getEmail() %></td>
						<td><%= bean.getPhone() %></td>
						<td><button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateModal"
									id="btn_update" onclick="showInfo2(
											'<%= bean.getAid() %>',
											'<%= bean.getName() %>',
											'<%= bean.getUsername() %>',
											'<%= bean.getEmail() %>',
											'<%= bean.getPassword() %>',
											'<%= bean.getPhone() %>')">修改</button>
							<button type="button" class="btn btn-danger btn-xs" onclick="deleteuser(<%= bean.getAid() %>)">删除</button>
						</td>
						</tbody>
						<%} %>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function showInfo2(aid,name,username,email,password,phone) {
			document.getElementById("updateaid").value = aid;
			document.getElementById("nameto").value = name;
			document.getElementById("username").value = username;
			document.getElementById("emails").value = email;
			document.getElementById("password").value = password;
			document.getElementById("phone").value = phone;
		}
		function deleteuser(aid) {
			con=confirm("是否删除?");
			if(con==true){
				location.href = "deleteUserServlet?aid="+aid;
			}
		}
	</script>


	<!-- 修改模态框（Modal） -->
	<!-------------------------------------------------------------->

	<!-- 修改模态框（Modal） -->
	<form class="form-horizontal" method="post" action="updateUserServlet">   <!--保证样式水平不混乱-->
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="updateModalLabel">
							修改用户信息
						</h4>
					</div>
					<div class="modal-body">

						<!---------------------表单-------------------->
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">账号</label>
							<div class="col-sm-7">
								<input type="hidden" id="updateaid" name="aid">
								<input type="text" class="form-control" id="username" name="username"  placeholder="请输入当前预订数">
								<label class="control-label" for="username" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="nameto" name="name"  placeholder="请输入当前预订数">
								<label class="control-label" for="name" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="password" name="password"  placeholder="请输入当前预订数">
								<label class="control-label" for="password" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="emails" name="email"  placeholder="请输入当前预订数">
								<label class="control-label" for="email" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="phone" name="phone"  placeholder="请输入最大预订数">
								<label class="control-label" for="phone" style="display: none;"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary" >
							修改
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>

	</form>
	<!-------------------------------------------------------------->







	<!--------------------------------------添加的模糊框------------------------>
	<form class="form-horizontal" method="post" action="AddUserServlet">   <!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="addUserLabel">
							添加新用户
						</h4>
					</div>
					<div class="modal-body">

						<!---------------------表单-------------------->

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">账号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="username" id="addUserName" required="required" placeholder="请输入账号">
								<label class="control-label" for="addUserName" style="display: none;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="name" id="addName" required="required"  placeholder="请输入姓名">
								<label class="control-label" for="addName" style="display: none;"></label>
							</div>
						</div>



						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="email" id="addEmail" required="required"  placeholder="请输入邮箱">
								<label class="control-label" for="addEmail" style="display: none;"></label>
							</div>
						</div>


						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="phone" id="addPhone" required="required"  placeholder="请输入手机号">
								<label class="control-label" for="addPhone" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="password" id="addPassword" required="required"   placeholder="请输入密码">
								<label class="control-label" for="addPassword" style="display: none;"></label>
							</div>
						</div>
						<!---------------------表单-------------------->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary" >
							添加
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>

	</form>
	<!--------------------------------------添加的模糊框------------------------>







	<!-------------------------------------------------------------->

	<form class="form-horizontal" method="post" action="/jiudain/AdminServlet">   <!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							修改密码
						</h4>
					</div>
					<div class="modal-body">

						<!--正文-->
						<input type="hidden" name="tip" value="1">
						<input type="hidden" name="url" value="index">
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">原密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" name="password" id="oldPwd"  placeholder="请输入原密码">
								<label class="control-label" for="oldPwd" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" name="password2" id="newPwd"  placeholder="请输入新密码">
								<label class="control-label" for="newPwd" style="display: none"></label>
							</div>
						</div>

						<!--正文-->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary" >
							修改
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>

	</form>
	<!-------------------------------------------------------------->

	<!-------------------------个人资料模糊框------------------------------------->

	<form class="form-horizontal" method="post" action="/jiudain/AdminServlet">   <!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="updateinfo" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="ModalLabel">
							个人资料
						</h4>
					</div>

					<div class="modal-body">

						<!--正文-->
						<input type="hidden" name="tip" value="2">
						<input type="hidden" name="url" value="admin_user">
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">真实姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="name" name="name" placeholder="请输入您的真实姓名" value='<% out.print(admin.getName());%>'>
								<label class="control-label" for="name" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入您的手机号" value='<% out.print(admin.getPhone());%>'>
								<label class="control-label" for="phone" style="display: none"></label>
							</div>
						</div>


						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="email" name="email"  placeholder="请输入您的邮箱" value='<% out.print(admin.getEmail());%>'>
								<label class="control-label" for="email" style="display: none"></label>
							</div>
						</div>

						<!--正文-->


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary" >
							修改
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>

	</form>
	<!-------------------------------------------------------------->





	<div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="infoModalLabel">提示</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12" id="div_info"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>