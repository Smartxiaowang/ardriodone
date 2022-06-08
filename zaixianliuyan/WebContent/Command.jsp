<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="java.sql.SQLException,
                 java.util.Date,
                 java.text.SimpleDateFormat,
                 java.util.List" %>
<%@ page import="com.edu.entity.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<%
    AdminBean admin = new AdminBean();
    String aid = (String) session.getAttribute("aid");
    AdminDao admindao = new AdminDao();
    admin = admindao.get_AidInfo2(aid);
    String m_id = request.getParameter("id");
    int mId = Integer.parseInt(m_id);
    //当前电影，当前用户
    //int mId = 1;
    int uId = Integer.valueOf(aid);

    //电影的基本信息（296行）
    MovieDao mdao = new MovieDao();
    Movie m = mdao.getMovie(mId);
    if (m != null) {
        request.setAttribute("m", m);
    }

    //属于这个电影的评论（346行）
    CommandDao cdao = new CommandDao();
    List<Command> list = cdao.getCommand(mId,uId);
    if (list != null) {
        request.setAttribute("list", list);
    }
%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${m.mName }</title>
    <link rel="stylesheet" href="font_movie2/iconfont.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
    <script src="static/jQuery/jquery-3.1.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/login.js"></script>
    <script src="static/jQuery/jquery-3.1.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrap-dropdown.min.js"></script>
    <%--			<script src="static/ajax-lib/ajaxutils.js"></script>--%>
    <script src="static/js/adminUpdateInfo.js"></script>
    <script src="static/js/adminUpdatePwd.js"></script>


    <style>
        * {
            margin: 0;
            padding: 0;
            border-collapse: collapse;
        }

        a {
            text-decoration: none;
            cursor: pointer;
        }

        img {
            width: 100%;
            height: 100%;
        }

        li {
            list-style: none;
        }

        @font-face {
            font-family: "iconfont";
            src: url('iconfont.ttf?t=1653197580945') format('truetype');
        }

        .iconfont {
            font-family: "iconfont" !important;
            font-size: 24px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        .clearfix::before,
        .clearfix::after {
            content: "";
            display: table;
        }

        .clearfix::after {
            clear: both;
        }

        .clearfix {
            *zoom: 1;
        }

        .banner {
            margin-top: 0px;
            background-image: url(images/beijing.png);
            height: 376px;
        }

        .banner .wap {
            position: relative;
        }

        .banner .wap .info_left {
            position: relative;
            margin: 0 30px;
            padding-bottom: 40px;
            top: 60px;
            left: 350px;
            overflow: hidden;
            z-index: 9;
        }

        .banner .wap .info_left img {
            margin-bottom: 50px;
            border: 4px solid #fff;
            height: 350px;
            width: 260px;
        }

        .banner .wap .info_right a {
            font-size: 36px;
        }

        .banner .wap .info_right {
            position: absolute;
            width: 900px;
            top: 68px;
            margin-top: 0;
            font-size: 22px;
            left: 720px;
            color: #fff;
            line-height: 36px;
            font-weight: 400;
            margin-bottom: 0;
        }

        .main {
            width: 1200px;
            margin: 80px auto;
        }

        .main .main_nav {
            border-bottom: 1px solid rgb(209, 208, 208);
            padding-bottom: 8px;
        }

        .main .main_nav a {
            font-size: 22px;
            color: rgb(87, 86, 86);
        }

        .main .container {
            margin: 30px auto;
        }

        .main .container .blur {
            font-size: 20px;
            line-height: 36px;
            text-indent: 32px;
        }

        .main .mineCommand {
            margin: 50px auto;
        }

        .main .mineCommand .mine_left {
            cursor: pointer;
            float: left;
            margin-top: 20px;
            margin-right: 20px;
            border-radius: 50%;
            overflow: hidden;
            width: 60px;
            height: 60px;
        }

        .main .mineCommand .mine_right {
            position: relative;
        }

        .main .mineCommand .mine_right textarea {
            margin-top: 22px;
            padding: 15px;
            overflow: hidden;
            border-color: #999;
            border-radius: 7px;
            height: 100px;
            width: 700px;
            font-size: 22px;
            color: #999;
        }

        .main .mineCommand .mine_right button {
            position: absolute;
            top: 105px;
            left: 715px;
            width: 69px;
            height: 34px;
            cursor: pointer;
            background-color: rgb(199, 205, 207);
            border-radius: 5px;
            border-style: none;
        }

        .main .command {
            margin-top: 50px;
        }

        .main .command .hd {
            position: relative;
        }

        .main .command .body {
            margin-top: 15px;
        }

        .main .command .body .portrait {
            cursor: pointer;
            float: left;
            margin-top: 20px;
            margin-right: 20px;
            border-radius: 50%;
            overflow: hidden;
            width: 50px;
            height: 50px;
        }

        .main .command .body .cdMain {
            margin-left: 50px;
            padding-top: 15px;

        }

        .main .command .body .cdMain .user {
            cursor: pointer;
            font-size: 20px;
            color: rgb(199, 148, 39);
        }

        .main .command .body .cdMain .time {
            padding-top: 5px;
            font-size: 14px;
            color: rgb(107, 106, 106);
        }

        .main .command .body .cdMain .cdContent {
            padding: 20px;
            color: rgb(48, 46, 46);
            line-height: 36px;
            border-bottom: 1px solid rgb(207, 202, 202);
        }

        .main .command .body .cdMain .control {
            float: right;
            margin-top: 10px;
            margin-right: 15px;
            cursor: pointer;
            color: #999;
        }

        .main .command .body .cdMain .control:hover {
            color: rgb(197, 65, 65);
        }

        .main .command .body .spread {
            margin: 20px auto;
            cursor: pointer;
            text-align: center;
            color: rgb(97, 97, 97);
            font-size: 18px;
        }

        .footer {
            margin-top: 100px;
            height: 120px;
            background-color: dimgrey;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
     role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <a class="navbar-brand"><strong>影评网</strong></a>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i
                                    class="glyphicon glyphicon-user"></i> 欢迎您,222号 <i class="caret"></i></a>

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
<div class="banner">
    <div class="wap">
        <div class="info_left">
            <img src="images/duzhan.jpg" alt="">

        </div>
        <!-- 电影的基本介绍 -->
        <div class="info_right">
            <h2 style="color: rgb(216, 127, 76);">${m.mName }</h2>
            <span>导演: ${m.mDir }</span><br>
            <span>编剧: ${m.mScr }</span><br>
            <span>主演: ${m.mAct }</span><br>
            <span>类型: ${m.mType }</span><br>
            <span>语言: ${m.mLanguage }</span><br>
            <span>制片国家: ${m.mCountry }</span><br>
            <span>上映时间: ${m.mTime }</span><br>
        </div>
    </div>
</div>
<div class="main clearfix">
    <div class="main_nav">
        <a href="index.jsp">电影</a>
        &nbsp;>&nbsp;
        <a>
            ${m.mName }
        </a>
    </div>
    <div class="container">
        <span class="iconfont">&#xe606;</span>
        <h2 style="display: inline;">简介</h2><br><br>
        <span class="blur">
            <%=m.getmIntro() %>
        </span>
    </div>
    <div class="mineCommand">
        <div class="mine_hd">
            <span class="iconfont">&#xe6ad;</span>
            <h2 style="display: inline;">写评论</h2>
        </div>
        <div class="mine_left">
            <img src="images/touxiang.png" alt="">
        </div>
        <form class="mine_right" action="addCommand?uid=<%= aid %>&mid=<%= mId %>" method="POST">
            <textarea name="content" id="write" cols="30" rows="10">写点啥吧</textarea>
            <!--
                点击后发表评论 在数据库添加一条记录
            -->
            <button id="pul">发表</button>

        </form>
    </div>
    <div class="command">
        <div class="hd">
            <span class="iconfont">&#xe619;</span>
            <h2 style="display: inline;">热门评论</h2>
        </div>
        <div class="body">
            <ul id="UL">
                <c:forEach var="c" items="${list }">
                    <li>
                        <div class="portrait">
                            <img src="images/touxiang.png" alt="">
                        </div>
                        <div class="cdMain clearfix">
                            <a class="user">${c.uName }</a>
                            <span class="uId" style="display: none;">${c.uId }</span>
                            <span class="control" onclick="rem(${c.cId},<%= mId %>)">[删除]</span><br>
                            <span class="time">${c.cTime }</span>
                            <div class="cdContent">
                                    ${c.cWord }
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <p class="spread">展开</p>
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

<form class="form-horizontal" method="post" action="AdminServlet">   <!--保证样式水平不混乱-->
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

<div class="footer"></div>


</body>
<script type="text/javascript">

    // 事件：点击文本框文字消失
    var write = document.querySelector("textarea");
    write.onfocus = function () {
        if (this.value == "写点啥吧") {
            this.value = "";
        }
        this.style.color = "#333";
    }
    write.onblur = function () {
        if (this.value == "") {
            this.value == "写点啥吧";
        }
        this.style.color = "#999";
    }
    //只显示五条评论，多余的隐藏
    var spread = document.querySelector(".spread");
    var lis = UL.getElementsByTagName("li");
    var dis = lis.length;
    if (lis.length > 5) {
        for (var i = 5; i < lis.length; i++) {
            lis[i].style.display = "none";
        }
    } else {
        spread.style.display = "none";
    }
    // 点击展开，显示多的评论
    spread.onclick = function () {
        if (spread.innerHTML == "展开") {
            for (var i = 0; i < lis.length; i++) {
                lis[i].style.display = "block";
            }
            this.innerHTML = "收起";
        } else if (spread.innerHTML == "收起") {
            for (var i = 5; i < lis.length; i++) {
                lis[i].style.display = "none";
            }
            this.innerHTML = "展开";
        }
    }

    //点击删除弹窗
    function rem(cId,mId) {
        var flag = window.confirm("确定要删除这条评论吗？");
        if (flag) {
            window.location.href = "deleteCommand?cId=" + cId+"&mId="+mId;
        }
    }

    //只有属于自己的评论才有删除功能
    var user = document.getElementsByClassName("uId");
    var control = document.getElementsByClassName("control");
    for (var i = 0; i < user.length; i++) {
        if (user[i].innerHTML == <%=uId %>) {
            control[i].style.display = "block";
        } else {
            control[i].style.display = "none";
        }
    }
</script>
</html>