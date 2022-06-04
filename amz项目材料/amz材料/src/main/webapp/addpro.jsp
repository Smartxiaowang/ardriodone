<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加商品</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
    <style type="text/css">
        *{
            margin:0;
            padding: 0;
        }
        #content{
            height: 750px;
            width: 80%;
            background-color: #ffffff;
            padding: 0 150px;
        }
        #list{
            height: 100px;;
            line-height: 100px;
            width: 100%;
            /*background-color: black;*/
        }
        #list ul{
            height: 100%;
            width: 90%;
            margin: 0 auto;
            background-color: #000000;
        }
        li{
            list-style-type: none;
        }
        #list ul li{
            display: block;
            height: 100%;
            width: 25%;
            margin: 0 auto;
            float: left;
            font-family: 楷体;
            font-size: 40px;
            text-align: center;
            color: #ffffff;
            background-color: #666666;
        }
        #list ul li a:link,#list ul li a:visited{
            display: block;
            background-color: #000000;
            color: white;
            text-decoration: none;
        }
        #list ul li a:hover,#list ul li a:active{
            background-color: #727272;
            color: #6fbfff;
            border-bottom: 3px #ff2300 solid;
        }
        #add_pro{
            height:650px;
            width: 90%;
            margin: 3px auto;
            background-color: #d0d0d0;
            display: none;
            border:1px black solid;
        }
        #add_pro ul li{

            float: left;
            margin: 70px 40px 20px 10px;
        }
        .lab{

            font-size: 20px;
            color: #2200ff;
        }
        .ip_text{
            font-size: 20px;
            height: 40px;
            width: 250px;
        }
        #lab_description{
            display: block;
            margin-left: 0px;
            margin-top: -20px;
        }
        #pro_description{
            display: block;
            margin-top: 30px;
            margin-left: 0px;
            height: 70px;
            width: 600px;
            font-size: 30px;
        }
        #pic_li {
            float: left;
            width: 300px;
            height: auto;
            overflow: hidden;
            margin: 30px 90px;
            color: #FFFFFF;
        }
        #pic_span {
            width: 80%;
            height: 50px;
            line-height: 50px;
            text-align: center;
            background: #d8b49c;
            display: block;
            font-size: 30px;
            border-radius: 5px;
            float: left;
            position: relative;
            left: 30px;
        }
        #upload {
            display: block;
            width: 100%;
            height: 40px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
            border-radius: 5px;
        }
        #btn{
            clear: left;
            display: block;
            height: 100px;
            width: 50%;
            position: relative;
            left: 250px;

        }
        #add_btn{
            float: left;
            height: 40px;
            line-height: 0;
            padding: 20px;
            margin-left: 20px;
            font-family: 楷体;
            font-size: 30px;
            border-radius: 4px;
            border: 1px #00ff1e solid;

        }
        #cle_btn{
            float: right;
            height: 40px;
            line-height: 0;
            padding: 20px;
            margin-right: 20px;
            font-family: 楷体;
            font-size: 30px;
            border-radius: 4px;
            border: 1px #00ff1e solid;
        }
        #pic_cvs {
            border: 1px solid #000;
            margin: 20px 0 20px 50px;
        }
    </style>
</head>
<body>
<div id="content">
    <div id="list">
        <ul>
            <li><a href="#" id="add_a">添加商品</a></li>
            <li><a href="#" id="mod_a">修改商品</a></li>
            <li><a href="#">查询商品</a></li>
            <li><a href="#">删除商品</a></li>
        </ul>
    </div>
    <!--序号  商品图片 商品名 简介  颜色  尺码  价格  -->
    <div id="add_pro">
        <form action="../addProduct" id="addform"  method="post">
            <ul>
                <li id="pic_li">
                    <canvas id="pic_cvs" width="200" height="200"></canvas>
                    <span id="pic_span">上传商品图片
                        <input type="file" id="upload" name="pic"/>
                    </span>
                </li>
                <li>
                    <label for="pro_name" class="lab">商品名:</label>
                    <input type="text" id="pro_name" class="ip_text" name="name"/>
                </li>
                <li>
                    <label for="pro_color" class="lab">颜色:</label>
                    <input type="text" id="pro_color" class="ip_text" name="color"/>
                </li>
                <li>
                    <label for="pro_size" class="lab">尺码:&nbsp;&nbsp;&nbsp;</label>
                    <input type="text" id="pro_size" class="ip_text" name="size"/>
                </li>
                <li>
                    <label for="pro_price" class="lab">价格:</label>
                    <input type="text" id="pro_price" class="ip_text" name="price"/>
                </li>
                <li>
                    <label for="pro_description" id="lab_description" class="lab">商品简介:</label>
                    <textarea rols="4" cols="80px" id="pro_description" name="description"></textarea>
                </li>
                <li id="btn">
                    <button type="submit" id="add_btn">添加</button>
                    <button type="button" id="cle_btn">重置</button>
                    <a href="../adminQueryPro">query</a>
                    <a href="../adminDelPro">del</a>
                    <a href="../adminUpdatePro">update</a>
                </li>
            </ul>

        </form>
    </div>
    <div id="mod_pro">

    </div>
</div>
<script type="text/javascript">
    $('#add_a').mouseenter(function(){
        $('#add_pro').slideDown(500);
    });
    $('#mod_a').mouseenter(function(){
        $('#add_pro').slideUp(500);
    });

    var input1 = document.getElementById("upload");
    if (typeof FileReader === 'undefined') {
        input1.setAttribute('disabled', 'disabled');
    } else {
        input1.addEventListener('change', readFile, false);
    }
    function readFile() {
        var file = this.files[0];
        if (!/image\/\w+/.test(file.type)) {
            alert("文件必须为图片！");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e) {
            var image = new Image();
            image.src = e.target.result;
            var max = 200;
            image.onload = function() {
                var canvas = document.getElementById("pic_cvs");
                var ctx = canvas.getContext("2d");
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(image, 0, 0, 200, 200);
            };
        }
    };
</script>
</body>
</html>