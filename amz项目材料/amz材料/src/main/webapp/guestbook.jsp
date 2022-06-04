<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 留言</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/comment.js"></script>
<style type="text/css">
	.p-page{
            width: 500px;
            height:20px;
            margin: 0  auto;
            margin-bottom:10px;
        }
        .p-page-ul{
            list-style-type: none;
        }
        .p-page-ul-li{
            float: left;
            margin: 0 3px;
        }
        .p-page-ul-li a:link,.p-page-ul-li a:visited{
             display: block; 
             line-height: 20px;
            background-color: white;
            text-decoration: none;
             padding: 0 3px; 
             padding-right:4px;
            border: 1px solid #999;
            font-size: 11px;
            color: #999;
        }
         .p-page-ul-li a:hover, .p-page-ul-li a:active{
            color: white;
            background-color: #FF0200;
        }
        .p-page-span{
            float: left;
            margin-left: 20px;
            line-height: 20px;
        }
        .p-page-span>a:link,.p-page-span>a:visited{
            display: block;
            padding: 0 11px;
            background-color: white;
            text-decoration: none;
            border: 1px solid #999;
            font-size: 11px;
            color: #999;
            margin-left: -10px;
        }
        .p-page-span>a:hover,.p-page-span>a:active{
            color: white;
            background-color: #FF0200;
        }
	</style>
</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="position" class="wrap">
	您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">亚马逊</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<%@ include file="index_product_sort.jsp" %>
	</div>
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul id="commentul">
			</ul>
			
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
				</ul>
			</div>
			
			<div id="reply-box">
				<form action="createmessage" method="post" onsubmit="return commentCheck()">
					<table>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" /><span id="msgfirstspan" style="color:red;margin-left:15px;"></span></td>
						</tr>
						<tr>
							<td class="field">留言标题：</td>
							<td><input class="text" type="text" name="guestTitle" /><span id="msgsecondspan" style="color:red;margin-left:15px;"></span></td>
						</tr>
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent"></textarea><span id="msgthirdspan" style="color:red;margin-left:15px;"></span></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="p-page">
    <ul class="p-page-ul">
        <li class="p-page-ul-li">
            <a href="javascript:;" onclick="getMessage(this)">上一页</a>
        </li>
        <li class="p-page-ul-li sortpage">
            <a href="javascript:;" onclick="getMessage(this)">下一页</a>
        </li>
    </ul>
    <span class="p-page-span">
        去第
    <input type="text" size="3" id="inputNum"/>
        页
    </span>
    <span class="p-page-span">
    <a href="javascript:;" id="givePage" onclick="getMessage(this)">确定</a>
    </span>
</div> 
		</div>
	</div>
	<div class="clear"></div>
	
</div>
<div id="footer">
	Copyright &copy; 2018 上海海文 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
