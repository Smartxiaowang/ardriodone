<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="cn">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>登录</title> 
  <meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文,后台管理系统模版,后台模版下载,后台管理系统,后台管理模版" />
  <meta name="description" content="代码家园-www.daimajiayuan.com提供Bootstrap模版,后台管理系统模版,后台管理界面,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  <!-- Stylesheets -->
  <link href="style/bootstrap.css" rel="stylesheet">
  <link rel="stylesheet" href="style/font-awesome.css">
  <link href="style/style.css" rel="stylesheet">
  <link href="style/bootstrap-responsive.css" rel="stylesheet">
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

<!-- Form area -->
<div class="admin-form">
  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <!-- Widget starts -->
            <div class="widget worange">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-lock"></i> 登录<span style="color:red"; id="spanMessage">${requestScope.errorMessage}</span> 
              </div>

              <div class="widget-content">
                <div class="padd">
                  <!-- Login form -->
         <form class="form-horizontal" action="LoginServlet" method="post"
          onsubmit="return  checkInput();">
                    <!-- Email -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="txtLoginName">账号：</label>
    			<div class="col-lg-9">
              <input type="text" class="form-control" id="txtLoginName"  name ="txtLoginName"
               placeholder="账号："  value="${requestScope.LoginName}">
                    <span class="label label-danger" id="spanLoginName"></span>
                      </div>
                    </div>
                    <!-- Password -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="txtpwdName">密码：</label>
                      <div class="col-lg-9">
             <input type="password" class="form-control" id="txtpwdName" name ="txtpwdName" placeholder="密码" >
                      <span class="label label-danger" id="spanpwdName"></span>
                      </div>
                    </div>
                    <!-- Remember me checkbox and sign in button -->
                    <div class="form-group">
					<div class="col-lg-9 col-lg-offset-3">
                      <div >
                        <label>
                          <input type="checkbox" name="rememberLoginName" value="true"> 记住账号&nbsp;&nbsp;&nbsp;
                          <input type="checkbox" name="aoutoLogin" value="true"> 自动登录
                       
                        </label>
						</div>
					</div>
					</div>
                        <div class="col-lg-9 col-lg-offset-2">
							<button type="submit" class="btn btn-danger" >登录</button>
							<button type="reset" class="btn btn-default" onclick="clearSpan()" >取消</button>
						</div>
                    <br />
                  </form>
				  
				</div>
                </div>
              
                <div class="widget-foot">
                  是否注册? <a href="RegisterServlet" >去注册</a>
                </div>
            </div>  
      </div>
    </div>
  </div> 
</div>
	
		

<!-- JS -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
function clearSpan(){
	var spanLoginName=document.getElementById("spanLoginName");
	var spanpwdName=document.getElementById("spanpwdName");
	var spanMessage=document.getElementById("spanMessage");
	spanLoginName.innerText="";
	spanpwdName.innerText="";
	spanMessage.innerText="";
}


function checkInput(){
	var flag=true;
	var txtLoginName=document.getElementById("txtLoginName");
	var txtpwdName=document.getElementById("txtpwdName");
	var spanLoginName=document.getElementById("spanLoginName");
	var spanpwdName=document.getElementById("spanpwdName");
	
	if(txtLoginName.value.length==0){
		//alert("登录名不能为空。");
		spanLoginName.innerText="登录名不能为空。";
		flag=false;
	}
	else if(txtLoginName.value.length<5){
		//alert("登录名不能少于5个字符。");
		spanLoginName.innerText="登录名不能少于5个字符。";
		flag=false;
	}
	else{
		spanLoginName.innerText="";
	}
	
	if(txtpwdName.value.length==0){
		//alert("密码不能为空。");
		spanpwdName.innerText="密码不能为空。";
		flag=false;
	}
	else if(txtpwdName.value.length<6){
		//alert("密码不能少于6个字符。");
		spanpwdName.innerText="密码不能少于6个字符。";
		flag=false;
	}
	else{
		spanpwdName.innerText="";
	}
	
	return flag;
}
</script>
</body>
</html>
