<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="bean.ShoppingBean" %>
<%@ page import="dao.ShoppingDao" %>
<%@ page import="dao.TypeDao" %>
<%@ page import="bean.TypeBean" %>
<%@ page import="bean.AdminBean" %>
<%@ page import="dao.AdminDao" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
	<meta charset="UTF-8">
	<title>生活消费管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
	<script src="static/jQuery/jquery-3.1.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/bootstrap-dropdown.min.js"></script>
	<%--			<script src="static/ajax-lib/ajaxutils.js"></script>--%>
	<script src="static/js/adminUpdateInfo.js"></script>
	<script src="static/js/adminUpdatePwd.js"></script>

</head>


<body class="bootstrap-admin-with-small-navbar">
<%
	AdminBean admin = new AdminBean();
	String aid = (String) session.getAttribute("aid");
	AdminDao admindao = new AdminDao();
	admin = admindao.get_AidInfo2(aid);

%>
<nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
	 role="navigation">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="collapse navbar-collapse main-navbar-collapse">
					<a class="navbar-brand"><strong>欢迎使用生活消费管理系统</strong></a>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i
									class="glyphicon glyphicon-user"></i> 欢迎您,<%out.print(admin.getName());%> <i
									class="caret"></i></a>

							<ul class="dropdown-menu">
								<li><a href="#updateinfo" data-toggle="modal">个人资料</a></li>
								<li role="presentation" class="divider"></li>
								<li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
								<li role="presentation" class="divider"></li>
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
					<a href="admin_jiudian.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 消费信息</a>
				</li>
				<li>
					<a href="admin_user.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 消费统计</a>
				</li>

			</ul>

		</div>

		<!-- content -->
		<div class="col-md-10">


			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default bootstrap-admin-no-table-panel">
						<div class="panel-heading">
							<div class="text-muted bootstrap-admin-box-title">查询</div>
						</div>
						<div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
							<form class="form-horizontal" action="CountServlet" method="post">
								<input type="hidden" name="tip" value="1">
								<div class="col-lg-8 form-group">
									<label class="col-lg-3 control-label" >消费日期统计</label>
									<div class="col-lg-4">
										<input class="form-control" id="datets" name="date" type="text"
											   placeholder="例如2022-06">
										<label class="control-label"  style="display: none;"></label>
									</div>
									<div class="form-group">
										<div class="col-lg-4">
											<select class="form-control" name="type"
													onPropertyChange="showValue(this.value)">
												<option value="1">请选择</option>
												<%
													TypeDao typedao2 = new TypeDao();
													ArrayList<TypeBean> data2 = (ArrayList<TypeBean>) typedao2.get_ListInfo();
													data2 = (ArrayList<TypeBean>) typedao2.get_ListInfo();
													for (TypeBean bean2 : data2) {
												%>
												<option value="<%= bean2.getTid() %>"><%= bean2.getName() %>
												</option>
												<%} %>
											</select>
											<label class="control-label" for="type" style="display: none;"></label>
										</div>
									</div>
								</div>
								<div class="col-lg-2 form-group">
									<button type="submit" class="btn btn-primary" id="btn_query" onclick="">统计</button>
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
							<th>消费总额</th>
							<th>消费人</th>
							<th>统计的消费时间</th>
							<th>统计的消费类型</th>
						</tr>
						</thead>

						<!---在此插入信息-->
						<%
							HashMap<String, String>  count = ( HashMap<String, String>) request.getAttribute("count");
							if (count != null) {
						%>
						<tbody>
						<td><%= count.get("count") %>
						</td>
						<td><%= count.get("username") %>
						</td>
						<td><%= count.get("date")%>
						</td>
						<td><%= count.get("type") %>
						</td>
						<% } %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>



	<!-------------------------------------------------------------->

	<form class="form-horizontal" method="post" action="AdminServlet">   <!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="updatepwdLabel"
			 aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="updatepwdLabel">
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
								<input type="password" class="form-control" name="password" id="oldPwd"
									   placeholder="请输入原密码">
								<label class="control-label" for="oldPwd" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" name="password2" id="newPwd"
									   placeholder="请输入新密码">
								<label class="control-label" for="newPwd" style="display: none"></label>
							</div>
						</div>

						<!--正文-->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary">
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
		<div class="modal fade" id="updateinfo" tabindex="-1" role="dialog" aria-labelledby="updateinfoLabel"
			 aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="updateinfoLabel">
							个人资料
						</h4>
					</div>

					<div class="modal-body">

						<!--正文-->
						<input type="hidden" name="tip" value="2">
						<input type="hidden" name="url" value="admin_jiudian">
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">真实姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="name" name="name" placeholder="请输入您的真实姓名"
									   value='<% out.print(admin.getName());%>'>
								<label class="control-label" for="name" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入您的手机号"
									   value='<% out.print(admin.getPhone());%>'>
								<label class="control-label" for="phone" style="display: none"></label>
							</div>
						</div>


						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="email" name="email" placeholder="请输入您的邮箱"
									   value='<% out.print(admin.getEmail());%>'>
								<label class="control-label" for="email" style="display: none"></label>
							</div>
						</div>

						<!--正文-->


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary">
							修改
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>

	</form>
	<!-------------------------------------------------------------->


	<div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="modal_infoLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
							aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="modal_infoLabel">提示</h4>
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