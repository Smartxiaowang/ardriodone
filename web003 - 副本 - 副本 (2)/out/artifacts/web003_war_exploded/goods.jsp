<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>天猫首页</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="img/shortcut.png" rel="shortcut icon" type="img/x-icon"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
    </head> 
    <body>

        <div class="container">

            <div class="top">
                <div class="top_left"><img src="img/tmall.png" alt="天猫logo"></div>
                <div class="top_right"><h1>欢迎来到天猫，理想生活上天猫......</h1></div>
            </div>

            <hr>
<!--            <p class="text-success">* 程序使用四种会话技术实现简单购物车功能，将购物车列表存储在了会话中。程序需要使用数据库中的Product表，具体内容请参考各个Servlet或页面的代码和注释</p>-->

            <hr>
            <h3>最新促销</h3>

            <ul class="proList">
                <li>
                    <!--使用URL重写参数-->

                    <a href="ProductServlet?pid=1" class="img-rounded img-thumbnail">
                        <img src="img/P001.jpg" alt="卫龙辣条"/><hr>
                        卫龙辣条，你值得拥有
                    </a>
                </li>
                <li>
                    <a href="ProductServlet?pid=2" class="img-rounded img-thumbnail">
                        <img src="img/P002.jpg" alt="青岛大虾"/><hr>
                        青岛大虾，不一样的大虾
                    </a>
                </li>
                <li>
                    <a href="ProductServlet?pid=3" class="img-rounded img-thumbnail">
                        <img src="img/P003.jpg" alt="四级真题"/><hr>
                        四级真题，600包过
                    </a>
                </li>
                <li>
                    <a href="ProductServlet?pid=4" class="img-rounded img-thumbnail">
                        <img src="img/P004.jpg" alt="快乐肥宅水"/><hr>
                        快乐肥宅水，你今天快乐了吗？
                    </a>
                </li>
            </ul>


            <hr>
            <h3>潮鞋推荐</h3>
            <ul class="proList">
                <li>
                    <!--使用URL重写参数-->

                    <a href="ProductServlet?pid=5" class="img-rounded img-thumbnail">
                        <img src="img/P005.jpg" alt="Puma低帮休闲板鞋 男女同款"/><hr>
                       Puma低帮休闲板鞋 男女同款
                    </a>
                </li>
                <li>
                    <a href="ProductServlet?pid=6" class="img-rounded img-thumbnail">
                        <img src="img/P006.jpg" alt=" adidas orginals Forum白绿"/><hr>
                        adidas orginals Forum白绿
                    </a>
                </li>
                <li>
                    <a href="ProductServlet?pid=7" class="img-rounded img-thumbnail">
                        <img src="img/P007.jpg" alt="断勾黑黄鸳鸯板鞋"/><hr>
                       断勾黑黄鸳鸯板鞋
                    </a>
                </li>
                <li>
                    <a href="ProductServlet?pid=8" class="img-rounded img-thumbnail">
                        <img src="img/P008.jpg" alt=" 送礼推荐！！"/><hr>
                       送礼推荐！！
                    </a>
                </li>
            </ul>

            <br>
            <a href="cart.jsp" class="cartImg">
                查看购物车<img src="img/cart.png" alt="购物车"/>
            </a>
            <br>
            <br>
        </div>
    </body>
</html>
