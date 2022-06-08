<%@ page import="java.util.List" %>
<%@ page import="com.edu.entity.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cmn-Hans" class="ua-windows ua-ff98">
<head>
    <meta charset="UTF-8">
    <meta name="google-site-verification" content="ok0wCgT20tBBgo9_zat2iAcimtN4Ftf5ccsh092Xeyw"/>
    <meta name="description" content="提供图书、电影、音乐唱片的推荐、评论和价格比较，以及城市独特的文化生活。">
    <meta name="keywords" content="豆瓣,小组,电影,同城,豆品,广播,登录豆瓣">
    <meta property="qc:admins" content="2554215131764752166375"/>
    <meta property="wb:webmaster" content="375d4a17a4fa24c2"/>
    <meta name="mobile-agent" content="format=html5; url=">
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
    <title>影评</title>
    <script>
        function set_cookie(t, e, o, n) {
            var i, a, r = new Date;
            r.setTime(r.getTime() + 24 * (e || 30) * 60 * 60 * 1e3), i = "; expires=" + r.toGMTString();
            for (a in t) document.cookie = a + "=" + t[a] + i + "; domain=" + (o || "douban.com") + "; path=" + (n || "/")
        }

        function get_cookie(t) {
            var e, o, n = t + "=", i = document.cookie.split(";");
            for (e = 0; e < i.length; e++) {
                for (o = i[e]; " " == o.charAt(0);) o = o.substring(1, o.length);
                if (0 === o.indexOf(n)) return o.substring(n.length, o.length).replace(/\"/g, "")
            }
            return null
        }

        window.Douban = window.Douban || {};
        var Do = function () {
            Do.actions.push([].slice.call(arguments))
        };
        Do.ready = function () {
            Do.actions.push([].slice.call(arguments))
        }, Do.add = Do.define = function (t, e) {
            Do.mods[t] = e
        }, Do.global = function () {
            Do.global.mods = Array.prototype.concat(Do.global.mods, [].slice.call(arguments))
        }, Do.global.mods = [], Do.mods = {}, Do.actions = [], Douban.init_show_login = function (t) {
            Do("dialog", function () {
                var t = "/j/misc/login_form";
                dui.Dialog({
                    title: "登录",
                    url: t,
                    width: /device-mobile/i.test(document.documentElement.className) ? .9 * document.documentElement.offsetWidth : 350,
                    cache: !0,
                    callback: function (t, e) {
                        e.node.addClass("dialog-login"), e.node.find("h2").css("display", "none"), e.node.find(".hd h3").replaceWith(e.node.find(".bd h3")), e.node.find("form").css({
                            border: "none",
                            width: "auto",
                            padding: "0"
                        }), e.update()
                    }
                }).open()
            })
        }, Do(function () {
            function t(t, e) {
                var o = ["ref=" + encodeURIComponent(location.pathname)];
                for (var n in e) e.hasOwnProperty(n) && o.push(n + "=" + e[n]);
                window._SPLITTEST && o.push("splittest=" + window._SPLITTEST), localStorage.setItem("report", (localStorage.getItem("report") || "") + "_moreurl_separator_" + o.join("&"))
            }

            !function () {
                "localStorage" in window || (window.localStorage = function () {
                    var t = document;
                    if (!t.documentElement.addBehavior) throw"don't support localstorage or userdata.";
                    var e = "_localstorage_ie", o = t.createElement("input");
                    o.type = "hidden";
                    var n = function (n) {
                        return function () {
                            t.body.appendChild(o), o.addBehavior("#default#userData");
                            var i = new Date;
                            i.setDate(i.getDate() + 365), o.expires = i.toUTCString(), o.load(e);
                            var a = n.apply(o, arguments);
                            return t.body.removeChild(o), a
                        }
                    };
                    return {
                        getItem: n(function (t) {
                            return this.getAttribute(t)
                        }), setItem: n(function (t, o) {
                            this.setAttribute(t, o), this.save(e)
                        }), removeItem: n(function (t) {
                            this.removeAttribute(t), this.save(e)
                        }), clear: n(function () {
                            for (var t, o = this.XMLDocument.documentElement.attributes, n = 0; t = o[n]; n++) this.removeAttribute(t.name);
                            this.save(e)
                        })
                    }
                }())
            }(), $(window).one("load", function () {
                var t = localStorage.getItem("report");
                if (t) {
                    t = t.split("_moreurl_separator_");
                    var e = function (o) {
                        return "" == o ? void e(t.shift()) : void $.get("undefined" == typeof _MOREURL_REQ ? "/stat.html?" + o : _MOREURL_REQ + "?" + o, function () {
                            return t.length ? (e(t.shift()), void localStorage.setItem("report", t.join("_moreurl_separator_"))) : void localStorage.removeItem("report")
                        })
                    };
                    e(t.shift())
                }
            }), window.moreurl = t, $(document).click(function (e) {
                var o = e.target, n = $(o).data("moreurl-dict");
                n && t(o, n)
            }), $.ajax_withck = function (t) {
                return "POST" == t.type && (t.data = $.extend(t.data || {}, {ck: get_cookie("ck")})), $.ajax(t)
            }, $.postJSON_withck = function (t, e, o) {
                return $.post_withck(t, e, o, "json")
            }, $.post_withck = function (t, e, o, n) {
                return $.isFunction(e) && (n = o, o = e, e = {}), $.ajax({
                    type: "POST",
                    url: t,
                    data: $.extend(e, {ck: get_cookie("ck")}),
                    success: o,
                    dataType: n || "text"
                })
            }, $("html").click(function (t) {
                var e = $(t.target), o = e.attr("class");
                o && $(o.match(/a_(\w+)/gi)).each($.proxy(function (e, o) {
                    var n = Douban[o.replace(/^a_/, "init_")];
                    "function" == typeof n && (t.preventDefault(), n.call(this, t))
                }, e[0]))
            })
        });
        Do.add('dialog', {
            path: 'https://img3.doubanio.com/f/shire/bc881a837a728ab82aa01d653b1c180190bb5a5d/js/ui/dialog.js',
            type: 'js',
            requires: ['https://img3.doubanio.com/f/shire/8377b9498330a2e6f056d863987cc7a37eb4d486/css/ui/dialog.css']
        });
        Do.global('https://img3.doubanio.com/f/sns/b5793c2d7c298173d57ecf7d96708b5615336def/js/sns/fp/base.js', 'dialog');
    </script>
    <link rel="stylesheet"
          href="https://img3.doubanio.com/f/shire/32336a6003aedfb3aed372bf3ae0b51c92d12316/css/core/_init_.css">
    <link rel="stylesheet"
          href="https://img3.doubanio.com/f/sns/ff18c28bb4d2a80510aa6599f1726ddf758faa55/css/sns/dist/anonymous_home/index.css">
    <style type="text/css">
        .rec_topics_name {
            display: inline-block;
            margin-bottom: 6px;
            font-size: 14px;
            line-height: 1.3;
            color: #3377aa;
        }

        .rec_topics_subtitle {
            display: block;
            margin-bottom: 15px;
            font-size: 13px;
            line-height: 1;
            color: #aaaaaa;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .rec_topics_label {
            transform: translateY(-0.5px);
            display: inline-block;
            font-size: 13px;
            margin-left: 2px;
        }

        .rec_topics {
            line-height: 1;
            margin-bottom: 15px;
        }

        .rec_topics:last-child {
            margin-bottom: 0;
        }

        .rec_topics_label_ad {
            color: #c9c9c9;
            -moz-transform: scale(0.91);
            -webkit-transform: scale(0.91);
            transform: scale(0.91);
        }

        html[class*=ua-ff] .rec_topics_subtitle {
            line-height: 14px;
        }

    </style>
</head>

<body class='' style="padding-top: 65px;">


<div>
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
                        <a class="navbar-brand"><strong>影评网</strong></a>
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

    <div>

        <div id="anony-nav">
            <div class="anony-nav-links">
                <ul>
                    <li>
                        <a target="_blank" class="lnk-book">豆瓣读书？？？</a>
                    </li>
                    <li>
                        <a target="_blank" class="lnk-movie">豆瓣电影</a>
                    </li>
                    <li>
                        <a target="_blank" class="lnk-music">豆瓣音乐</a>
                    </li>
                    <li>
                        <a target="_blank" class="lnk-events">豆瓣同城</a>
                    </li>
                    <li>
                        <a target="_blank" class="lnk-group">豆瓣小组</a>
                    </li>
                    <li>
                        <a target="_blank" class="lnk-read">豆瓣阅读</a>
                    </li>
                    <li>
                        <a target="_blank" class="lnk-shijian" href="">豆瓣时间</a>
                    </li>
                    <li>
                        <a target="_blank" class="lnk-market" href="">豆瓣豆品</a>
                    </li>
                </ul>
            </div>


            <div class="anony-srh">
                <form action="https://www.douban.com/search" method="get">
                    <span class="inp"><input type="text" maxlength="60" size="12" placeholder="书籍、电影、音乐、小组、小站、成员"
                                             name="q" autocomplete="off"></span>
                    <span class="bn"><input type="submit" value="搜索"></span>
                </form>
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
<div id="anony-time" class="section">
    <div class="wrapper">


        <div class="sidenav">
            <h2 class="section-title"><a href=""></a></h2>
        </div>

        <div class="side"></div>
        <div class="main">
            <ul class="time-list">
                <%
                    List<Movie> openUpList = new MovieDao().getMovieList();
                    for (Movie d : openUpList) {
                %>

                <li>
                    <a class="cover time-activity new " href="" alt="">
                        <img src= <%= d.getmImg()%> alt="">
                    </a>
                    <a class="title" href="Command.jsp?id=<%= d.getmId()%>" target="_blank"><%= d.getmIntro()%></a>
                    <span class="type"><%= d.getmName()%></span>
                </li>
                <% } %>
            </ul>

        </div>
    </div>

</div>


<%--
<script src="https://img3.doubanio.com/f/shire/df6a1d712d36db03e9bddb32cbd5fa080bfcddfc/js/core/do/_init_.js"
        data-cfg-corelib="https://img3.doubanio.com/f/shire/72ced6df41d4d158420cebdd254f9562942464e3/js/jquery.min.js"></script>
<script type="text/javascript" src="https://img3.doubanio.com/misc/mixed_static/394461a1af018fe4.js"></script>
--%>

<!-- douban ad begin -->


<script type="text/javascript">
    (function (global) {
        var newNode = global.document.createElement('script'),
            existingNode = global.document.getElementsByTagName('script')[0],
            adSource = '//erebor.douban.com/',
            userId = '',
            browserId = 'PtYzahLoCd0',
            criteria = '3:/',
            preview = '',
            debug = false,
            adSlots = ['dale_anonymous_homepage_top_for_crazy_ad', 'dale_anonymous_homepage_right_top', 'dale_anonymous_homepage_movie_bottom', 'dale_anonymous_home_page_top', 'dale_homepage_online_activity_promo_1', 'dale_anonymous_homepage_doublemint', 'dale_anonymous_home_page_middle', 'dale_anonymous_home_page_middle_2', 'dale_anonymous_home_page_bottom'];

        global.DoubanAdRequest = {
            src: adSource,
            uid: userId,
            bid: browserId,
            crtr: criteria,
            prv: preview,
            debug: debug
        };
        global.DoubanAdSlots = (global.DoubanAdSlots || []).concat(adSlots);

        newNode.setAttribute('type', 'text/javascript');
        newNode.setAttribute('src', '//img1.doubanio.com/MnJwNGlleS9mL2FkanMvY2M1OGQyNTQ2N2I2YmQzOTlmNTliMGJiMjI4MWRhZTlkNTNjYTFkZC9hZC5yZWxlYXNlLmpz');
        newNode.setAttribute('async', true);
        existingNode.parentNode.insertBefore(newNode, existingNode);
    })(this);
</script>


<!-- douban ad end -->


<!-- Google Tag Manager -->
<noscript>
    <iframe src="" height="0" width="0"
            style="display:none;visibility:hidden"></iframe>
</noscript>
<script>(function (w, d, s, l, i) {
    w[l] = w[l] || [];
    w[l].push({'gtm.start': new Date().getTime(), event: 'gtm.js'});
    var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : '';
    j.async = true;
    j.src = '//www.googletagmanager.com/gtm.js?id=' + i + dl;
    f.parentNode.insertBefore(j, f);
})(window, document, 'script', 'dataLayer', 'GTM-5WP579');</script>
<!-- End Google Tag Manager -->


<script type="text/javascript">
    var _paq = _paq || [];
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function () {
        var p = (('https:' == document.location.protocol) ? 'https' : 'http'), u = p + '://fundin.douban.com/';
        _paq.push(['setTrackerUrl', u + 'piwik']);
        _paq.push(['setSiteId', '100001']);
        var d = document, g = d.createElement('script'), s = d.getElementsByTagName('script')[0];
        g.type = 'text/javascript';
        g.defer = true;
        g.async = true;
        g.src = p + '://img3.doubanio.com/dae/fundin/piwik.js';
        s.parentNode.insertBefore(g, s);
    })();
</script>

<script type="text/javascript">
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-7019765-1']);
    _gaq.push(['_setCampNameKey', 'dcn']);
    _gaq.push(['_setCampSourceKey', 'dcs']);
    _gaq.push(['_setCampMediumKey', 'dcm']);
    _gaq.push(['_setCampTermKey', 'dct']);
    _gaq.push(['_setCampContentKey', 'dcc']);
    _gaq.push(['_addOrganic', 'baidu', 'word']);
    _gaq.push(['_addOrganic', 'soso', 'w']);
    _gaq.push(['_addOrganic', '3721', 'name']);
    _gaq.push(['_addOrganic', 'youdao', 'q']);
    _gaq.push(['_addOrganic', 'so.360.cn', 'q']);
    _gaq.push(['_addOrganic', 'vnet', 'kw']);
    _gaq.push(['_addOrganic', 'sogou', 'query']);
    _gaq.push(['_addIgnoredOrganic', '豆瓣']);
    _gaq.push(['_addIgnoredOrganic', 'douban']);
    _gaq.push(['_addIgnoredOrganic', '豆瓣网']);
    _gaq.push(['_addIgnoredOrganic', 'www.douban.com']);
    _gaq.push(['_setDomainName', '.douban.com']);


    _gaq.push(['_setCustomVar', 1, 'responsive_view_mode', 'desktop', 3]);

    _gaq.push(['_trackPageview']);
    _gaq.push(['_trackPageLoadTime']);

    window._ga_init = function () {
        var ga = document.createElement('script');
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        ga.setAttribute('async', 'true');
        document.documentElement.firstChild.appendChild(ga);
    };
    if (window.addEventListener) {
        window.addEventListener('load', _ga_init, false);
    } else {
        window.attachEvent('onload', _ga_init);
    }
</script>


</body>
</html>



