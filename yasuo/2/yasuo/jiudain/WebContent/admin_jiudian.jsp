<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "bean.TypeBean,dao.AdminDao,dao.TypeDao,bean.JiudianBean,dao.JiudianDao,bean.AdminBean" %>
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
                                <div class="text-muted bootstrap-admin-box-title">查询</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal" action="/jiudain/selectServlet" method="post">
                                <input type="hidden" name="tip" value="1">	<!--tip=1表示管理员查询页面-->
                        			<div class="col-lg-8 form-group">
                                        <label class="col-lg-3 control-label" for="queryByname">客房名称</label>
                                        <div class="col-lg-4">
                                            <input class="form-control" id="jiudianName" name="name" type="text" placeholder="请输入客房名称">
                                            <label class="control-label" for="queryByname" style="display: none;"></label>
                                        </div>
                                        <label class="col-lg-2 control-label" for="queryBycard">客房号</label>
                                        <div class="col-lg-3">
                                            <input class="form-control" id="jiudianCard" name="card" type="text" placeholder="请输入客房号">
                                            <label class="control-label" for="queryBycard" style="display: none;"></label>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 form-group">

                                        <button type="submit" class="btn btn-primary" id="btn_query" onclick="">查询</button>
                                    </div>
                                    <div class="col-lg-3 form-group">

                                        <button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" data-target="#addModal">添加客房</button>
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
                                <th>客房号</th>
                                <th>客房类型</th>
                                <th>客房名称</th>
								<!---<th>作者名称</th><th>出版社</th>-->

                                <th>当前库存数量</th>
                                <th>客户名</th>
                                <th>预订时间</th>
                                <th>操作</th>
                                
                            </tr>
                            </thead>
                            
                            
                            <!---在此插入信息-->
                             <%
                             ArrayList<JiudianBean> jiudiandata = new ArrayList<JiudianBean>();
                             jiudiandata = (ArrayList<JiudianBean>)request.getAttribute("data");
                           if(jiudiandata==null){
                        	   JiudianDao jiudiandao = new JiudianDao();
                        	   jiudiandata = (ArrayList<JiudianBean>)jiudiandao.get_ListInfo();
                           }
	
  for (JiudianBean bean : jiudiandata){
  %>                 
								<tbody>
	                         	   	<td><%= bean.getCard() %></td>
	                                <td><%= bean.getType() %></td>
	                                <td><%= bean.getName() %></td>
	                                <td><%= bean.getcurrentNum() %></td>
                                    <td><%= bean.getBorrowUser() %></td>
                                    <td><%= bean.getBorrowTime() %></td>
<td><button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateModal" 
id="btn_update" onclick="showInfo2('<%= bean.getBid() %>','<%= bean.getCard() %>','<%= bean.getType() %>','<%= bean.getName() %>'
,'<%= bean.getAutho() %>','<%= bean.getPress() %>','<%= bean.getcurrentNum() %>','<%= bean.getBorrowUser()%>','<%= bean.getBorrowTime()%>')">修改</button>
<button type="button" class="btn btn-danger btn-xs" onclick="deleteJiudian(<%= bean.getBid() %>)">删除</button>
	</td>                                            
                          	  </tbody>
                       <%} %> 
                        </table>
                    </div>
                </div>
        </div>
    </div>
    <script type="text/javascript">
    function showInfo2(bid,card,type,name,autho,press,currentnum,borrowuser,borrowtime) {
        document.getElementById("updateISBN").value = card;
        document.getElementById("updateJiudianName").value = name;
        document.getElementById("updateAutho").value = autho;
        document.getElementById("updatePress").value = press;
        document.getElementById("updateJiudianType").value = type;
        document.getElementById("updateNum").value = currentnum;
        document.getElementById("updateJiudianId").value = bid;
        document.getElementById("updateBorrowUser").value = borrowuser;
        document.getElementById("updateBorrowTime").value = borrowtime;
    }
    function deleteJiudian(bid) {
    	con=confirm("是否删除?"); 
    	if(con==true){
    		location.href = "/jiudain/deleteServlet?bid="+bid;
    	}
    }
    </script>
    
     
                                     <!-- 修改模态框（Modal） -->
                                     <!-------------------------------------------------------------->  
                                
                                        <!-- 修改模态框（Modal） -->
                               <form class="form-horizontal" method="post" action="/jiudain/updateJiudianServlet">   <!--保证样式水平不混乱-->
									<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="updateModalLabel">
														修改客房信息
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">客房号</label>
												<div class="col-sm-7">
												<input type="hidden" id="updateJiudianId" name="updatebid">
													<input type="text" class="form-control" id="updateISBN" name="card"  placeholder="请输入书号">
												<label class="control-label" for="updateISBN" style="display: none;"></label>
												</div>
										</div>
										
											
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">客房名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateJiudianName" name="name"  placeholder="请输入客房名称">
												<label class="control-label" for="updateJiudianName" style="display: none;"></label>
												</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">客房类型</label>
											<div class="col-sm-7">
												 <select class="form-control" id="updateJiudianType" name="type" onPropertyChange="showValue(this.value)">
                                           				 <option value="-1">请选择</option> 
                                           				   <%
TypeDao typedao = new TypeDao();
ArrayList<TypeBean> data = (ArrayList<TypeBean>)typedao.get_ListInfo();
  data = (ArrayList<TypeBean>)typedao.get_ListInfo();
  for (TypeBean bean : data){
  %>                 <option value="<%= bean.getName() %>"><%= bean.getName() %></option>                        <%} %>                                        
                                      			  </select>
											<label class="control-label" for="updateJiudianType" style="display: none;"></label>
											</div>
										</div>
											
										<%--<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">作者名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateAutho" name="autho" placeholder="请输入作者名称">
												<label class="control-label" for="updateAutho" style="display: none;"></label>
												</div>
										</div>--%>
										
										
										<%--<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">出版社</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updatePress" name="press"  placeholder="请输入出版社">
												<label class="control-label" for="updatePress" style="display: none;"></label>
												</div>
										</div>	--%>
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">库存数量</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateNum" name="num"  placeholder="请输入库存数量">
												<label class="control-label" for="updateNum" style="display: none;"></label>
												</div>
										</div>
                                        <div class="form-group">
                                             <label for="firstname" class="col-sm-3 control-label">客户名</label>
                                                  <div class="col-sm-7">
                                                      <input type="text" class="form-control" id="updateBorrowUser" name="borrowUser"  placeholder="请输入客户名">
                                                      <label class="control-label" for="updateBorrowUser" style="display: none;"></label>
                                                  </div>
                                         </div>
                                        <div class="form-group">
                                            <label for="firstname" class="col-sm-3 control-label">预订日期</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" id="updateBorrowTime" name="borrowTime"  placeholder="请输入预订日期,如2020-01-01">
                                                <label class="control-label" for="updateBorrowTime" style="display: none;"></label>
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
                                    </div>
                                 </form>
                                   <!-------------------------------------------------------------->
 
    
    
    
    
    
    
     <!--------------------------------------添加的模糊框------------------------>  
                                 <form class="form-horizontal" method="post" action="/jiudain/AddJiudianServlet">   <!--保证样式水平不混乱-->
                                        <!-- 模态框（Modal） -->
									<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="addModalLabel">
														添加客房
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">客房号</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addISBN" required="required" name="card" placeholder="请输入书号">
												<label class="control-label" for="addISBN" style="display: none;"></label>	
												</div>
										</div>
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">客房名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addJiudianName" required="required" name="name"  placeholder="请输入客房名称">
													<label class="control-label" for="addJiudianName" style="display: none;"></label>
												</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">客房类型</label>
											<div class="col-sm-7">
												 <select class="form-control" id="addJiudianType" name="type">
                                           				 <option value="无分类">请选择</option>
                                           				  <%
	
  data = (ArrayList<TypeBean>)typedao.get_ListInfo();
  for (TypeBean bean : data){
  %>                 <option value="<%= bean.getName() %>"><%= bean.getName() %></option>                        <%} %>
                                      			  </select>
												<label class="control-label" for="addJiudianType" style="display: none;"></label>
											</div>
										</div>
											
										<%--<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">作者名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addAutho" required="required" name="autho"  placeholder="请输入作者名称">
												<label class="control-label" for="addAutho" style="display: none;"></label>	
												</div>
										</div>--%>
										
										
										<%--<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">出版社</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addPress" required="required" name="press"  placeholder="请输入出版社">
												<label class="control-label" for="addPress" style="display: none;"></label>	
												</div>
										</div>--%>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">总数量</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addNum" required="required" name="num" placeholder="请输入客房总数量">
												<label class="control-label" for="addNum" style="display: none;"></label>	
												</div>
										</div>
                                        <div class="form-group">
                                            <label for="firstname" class="col-sm-3 control-label">客户名</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" id="addBorrowUser" required="required" name="borrowUser" placeholder="请输入客户名">
                                                <label class="control-label" for="addBorrowUser" style="display: none;"></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="firstname" class="col-sm-3 control-label">预订日期</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" id="addBorrowTime" required="required" name="borrowTime" placeholder="请输入预订日期,如2020-01-01">
                                                <label class="control-label" for="addBorrowTime" style="display: none;"></label>
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