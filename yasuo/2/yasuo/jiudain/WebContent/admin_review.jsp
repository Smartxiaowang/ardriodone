<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "dao.AdminDao,dao.ReviewDao,bean.JiudianBean,dao.JiudianDao,bean.AdminBean,bean.ReviewBean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
	<meta charset="UTF-8">
	<title>酒店住房管理系统</title>
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
String aid = (String)session.getAttribute("aid");
AdminDao admindao = new AdminDao();
admin = admindao.get_AidInfo2(aid);

%>
    <nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="collapse navbar-collapse main-navbar-collapse">
                        <a class="navbar-brand"><strong>欢迎使用酒店住房管理系统</strong></a>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i> 欢迎您,<%out.print(admin.getName());%> <i class="caret"></i></a>
                            
                                 <ul class="dropdown-menu">
                                     <li><a href="#updateinfo" data-toggle="modal">个人资料</a></li>
                                      <li role="presentation" class="divider"></li>
                                       <li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
                                        <li role="presentation" class="divider"></li>
                                    <li><a href="/jiudain/login.jsp">退出</a></li>
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
                        <a href="/jiudain/admin_jiudian.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 客房管理</a>
                    </li>
                    <li>
                        <a href="/jiudain/admin_user.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 用户管理</a>
                    </li>
                    <li>
                        <a href="/jiudain/admin_jiudiantype.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 客房分类管理</a>
                    </li>
					<li>
						<a href="/jiudain/admin_review.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 客房预订管理</a>
					</li>
                    <li>
                        <a href="/jiudain/admin_borrow.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 客房预订信息</a>
                    </li>
                    <li>
                        <a href="/jiudain/admin_history.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 客房退房信息</a>
                    </li>
                </ul>
                
            </div>

            <!-- content -->
            <div class="col-md-10">


				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">客房预订管理</div>
							</div>

						</div>
					</div>
				</div>
                
                
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>客房号</th>
                                <th>客房名称</th>
								<th>申请预订人账号</th>
                                <th>申请预订人姓名</th>
                                <th>申请时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            
                            
                            <!---在此插入信息-->
                             <%
                             ArrayList<ReviewBean> reviewdata = new ArrayList<>();
                             ReviewDao reviewDao = new ReviewDao();
                             //获取所有待审核的申请信息
                             reviewdata = (ArrayList<ReviewBean>)reviewDao.getReviewStatusInfo(0);
	
  for (ReviewBean bean : reviewdata){
  %>                 
								<tbody>
	                         	   	<td><%= bean.getCard() %></td>
	                                <td><%= bean.getJiudianname()%></td>
									<td><%= bean.getUsername()%></td>
									<td><%= bean.getAdminname()%></td>
									<td><%= bean.getApplication_time()%></td>
<td><button type="button" class="btn btn-primary btn-xs" onclick="YES(<%= bean.getRid() %>)">同意</button>
<button type="button" class="btn btn-warning btn-xs" onclick="NO(<%= bean.getRid() %>)">不同意</button>
	</td>                                            
                          	  </tbody>
                       <%} %> 
                        </table>
                    </div>
                </div>
        </div>
    </div>
    <script type="text/javascript">
    function YES(rid){
    	con=confirm("确定同意?");
    	if(con==true){			//status=1代表同意
    		location.href = "/jiudain/ReviewServlet?status=1&rid="+rid;
		}
	}

    function NO(rid) {
    	con=confirm("确定不同意?");
    	if(con==true){			//result=2代表不同意
    		location.href = "/jiudain/ReviewServlet?status=2&rid="+rid;
    	}
    }
    </script>
<!-------------------------------------------------------------->  
                 
                   <form class="form-horizontal" method="post" action="/jiudain/AdminServlet">   <!--保证样式水平不混乱-->
                                     <!-- 模态框（Modal） -->
				<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="updatepwdLabel" aria-hidden="true">
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
				<div class="modal fade" id="updateinfo" tabindex="-1" role="dialog" aria-labelledby="updateinfoLabel" aria-hidden="true">
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
    
    
    
    <div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="modal_infoLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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