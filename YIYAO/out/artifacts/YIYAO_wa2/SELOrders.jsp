<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>药品管理信息</title> 
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  <!-- Stylesheets -->
  <link href="style/bootstrap.css" rel="stylesheet">
  <!-- Font awesome icon -->
  <link rel="stylesheet" href="style/font-awesome.css"> 
  <!-- jQuery UI -->
  <link rel="stylesheet" href="style/jquery-ui.css"> 
  <!-- Calendar -->
  <link rel="stylesheet" href="style/fullcalendar.css">
  <!-- prettyPhoto -->
  <link rel="stylesheet" href="style/prettyPhoto.css">  
  <!-- Star rating -->
  <link rel="stylesheet" href="style/rateit.css">
  <!-- Date picker -->
  <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css">
  <!-- CLEditor -->
  <link rel="stylesheet" href="style/jquery.cleditor.css"> 
  <!-- Uniform -->
  <link rel="stylesheet" href="style/uniform.default.css"> 
  <!-- Bootstrap toggle -->
  <link rel="stylesheet" href="style/bootstrap-switch.css">
  <!-- Main stylesheet -->
  <link href="style/style.css" rel="stylesheet">
  <!-- Widgets stylesheet -->
  <link href="style/widgets.css" rel="stylesheet">   
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

<%@ include file="navbar.jsp" %>

<%@ include file="header.jsp" %>

<!-- Main content starts -->
<div class="content">

  	<!-- Sidebar -->
     <%@ include file="adminSidbar2.jsp" %>

    <!-- Sidebar ends -->

  	<!-- Main bar -->
  	<div class="mainbar">
      
	    <!-- Page heading -->
	    <div class="page-head">
	      <h2 class="pull-left"><i class="icon-home"></i> 药品信息查询</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="index.html"><i class="icon-home"></i> 首页</a> 
          <!-- Divider -->
          <span class="divider">/</span> 
          <a href="#" class="bread-current">控制台</a>
        </div>

        <div class="clearfix"></div>

	    </div>
	    <!-- Page heading ends -->

	    <!-- Matter -->

	    <div class="matter">
        <div class="container">
        
        
    <!-- Table -->
 <form class="form-horizontal"  id="frmMain" action="SELOrdersServlet" method="post" >
 <input type="hidden" id="hidOperationType" name="hidOperationType" />         
           
            <div class="row">

                            
           
                          <div class="col-md-6">                              


              <div class="widget wgreen">    
                    
                    <div class="widget-head" >            
                  <div class="pull-left">药名信息查询  </div>    
                  <div class="widget-icons pull-right">                               
                <c:if test="${searchNumber == '' and searchName == ''}"><a href="#" class="wminimize"><i class="icon-chevron-down"></i></a></c:if> 
              <a href="#" class="wclose"><i class="icon-remove"></i></a>           
                  </div>
                  <div class="clearfix"></div>
                                
                </div>
   <div class="widget-content" <c:if test="${ searchNumber == '' and searchName == '' }"> style="display: none;'"</c:if>>  
                				<div class="padd">         
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">药名</label>
                                  <div class="col-md-8">
        <input type="text" class="form-control" placeholder="药名"  name="txtSearchNumber" id="txtSearchNumber"
        		value="${searchNumber }">
  
                                  </div>
                                </div>
                                 
                                                                                           
                                <div class="form-group">
                                  <div class="col-lg-offset-1 col-lg-9">                             
                                  <button type="button" class="btn btn-danger"  onclick="search()" >查询</button>
								 <button type="reset" class="btn btn-default" onclick="cancelSearch()">取消查询</button>                                  </div>
                                </div>
                                </div>
                              </div>
                              </div>
                              </div>
                              </div>
                              </form>
 
  
              
                <div class="widget-head">
                  <div class="pull-left">药品信息表</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
					
                    <table class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th><a href="">药品号</a></th>
                          <th><a href="">药名</a></th>
                          <th><a href="">类型</a></th>
                          <th><a href="">数量</a></th>
                          <th><a href="">描述</a></th>
                          
                        </tr>
                      </thead>
                      <tbody>
                     
					<c:forEach items="${requestScope.orders }" var="order"  > 
                        <tr>
                          <td>${order.uId}</td>
                          <td>${order.name}</td>
                          <td>${order.type} </td>
                          <td>${order.number}</td>
                          <td>${order.description}</td>   
                         
                        </tr>
								</c:forEach>
                      </tbody>
                    </table>

                    <div class="widget-foot">
                    
                       
                     
                      <div class="clearfix"></div> 

                    </div>

                  </div>

                </div>


              </div>

            </div>


                    </div>
	    


		<!-- Matter ends -->

    

   <!-- Mainbar ends -->
   <div class="clearfix"></div>



<!-- Content ends -->

<%@ include file="footer.jsp" %>

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span> 

<!-- JS -->
<script src="js/jquery.js"></script> <!-- jQuery -->
<script src="js/bootstrap.js"></script> <!-- Bootstrap -->
<script src="js/jquery-ui-1.9.2.custom.min.js"></script> <!-- jQuery UI -->
<script src="js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="js/jquery.rateit.min.js"></script> <!-- RateIt - Star rating -->
<script src="js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->

<!-- jQuery Flot -->
<script src="js/excanvas.min.js"></script>
<script src="js/jquery.flot.js"></script>
<script src="js/jquery.flot.resize.js"></script>
<script src="js/jquery.flot.pie.js"></script>
<script src="js/jquery.flot.stack.js"></script>

<!-- jQuery Notification - Noty -->
<script src="js/jquery.noty.js"></script> <!-- jQuery Notify -->
<script src="js/themes/default.js"></script> <!-- jQuery Notify -->
<script src="js/layouts/bottom.js"></script> <!-- jQuery Notify -->
<script src="js/layouts/topRight.js"></script> <!-- jQuery Notify -->
<script src="js/layouts/top.js"></script> <!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="js/sparklines.js"></script> <!-- Sparklines -->
<script src="js/jquery.cleditor.min.js"></script> <!-- CLEditor -->
<script src="js/bootstrap-datetimepicker.min.js"></script> <!-- Date picker -->
<script src="js/jquery.uniform.min.js"></script> <!-- jQuery Uniform -->
<script src="js/bootstrap-switch.min.js"></script> <!-- Bootstrap Toggle -->
<script src="js/filter.js"></script> <!-- Filter for support page -->
<script src="js/custom.js"></script> <!-- Custom codes -->
<script src="js/charts.js"></script> <!-- Charts & Graphs -->

<!-- Script for this page -->
<script type="text/javascript">

$(function () {

    /* Bar Chart starts */

    var d1 = [];
    for (var i = 0; i <= 20; i += 1)
        d1.push([i, parseInt(Math.random() * 30)]);

    var d2 = [];
    for (var i = 0; i <= 20; i += 1)
        d2.push([i, parseInt(Math.random() * 30)]);


    var stack = 0, bars = true, lines = false, steps = false;
    
    function plotWithOptions() {
        $.plot($("#bar-chart"), [ d1, d2 ], {
            series: {
                stack: stack,
                lines: { show: lines, fill: true, steps: steps },
                bars: { show: bars, barWidth: 0.8 }
            },
            grid: {
                borderWidth: 0, hoverable: true, color: "#777"
            },
            colors: ["#ff6c24", "#ff2424"],
            bars: {
                  show: true,
                  lineWidth: 0,
                  fill: true,
                  fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
            }
        });
    }

    plotWithOptions();
    
    $(".stackControls input").click(function (e) {
        e.preventDefault();
        stack = $(this).val() == "With stacking" ? true : null;
        plotWithOptions();
    });
    $(".graphControls input").click(function (e) {
        e.preventDefault();
        bars = $(this).val().indexOf("Bars") != -1;
        lines = $(this).val().indexOf("Lines") != -1;
        steps = $(this).val().indexOf("steps") != -1;
        plotWithOptions();
    });

    /* Bar chart ends */

});


/* Curve chart starts */

$(function () {
    var sin = [], cos = [];
    for (var i = 0; i < 14; i += 0.5) {
        sin.push([i, Math.sin(i)]);
        cos.push([i, Math.cos(i)]);
    }

    var plot = $.plot($("#curve-chart"),
           [ { data: sin, label: "sin(x)"}, { data: cos, label: "cos(x)" } ], {
               series: {
                   lines: { show: true, fill: true},
                   points: { show: true }
               },
               grid: { hoverable: true, clickable: true, borderWidth:0 },
               yaxis: { min: -1.2, max: 1.2 },
               colors: ["#1eafed", "#1eafed"]
             });

    function showTooltip(x, y, contents) {
        $('<div id="tooltip">' + contents + '</div>').css( {
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5,
            border: '1px solid #000',
            padding: '2px 8px',
            color: '#ccc',
            'background-color': '#000',
            opacity: 0.9
        }).appendTo("body").fadeIn(200);
    }

    var previousPoint = null;
    $("#curve-chart").bind("plothover", function (event, pos, item) {
        $("#x").text(pos.x.toFixed(2));
        $("#y").text(pos.y.toFixed(2));

        if ($("#enableTooltip:checked").length > 0) {
            if (item) {
                if (previousPoint != item.dataIndex) {
                    previousPoint = item.dataIndex;
                    
                    $("#tooltip").remove();
                    var x = item.datapoint[0].toFixed(2),
                        y = item.datapoint[1].toFixed(2);
                    
                    showTooltip(item.pageX, item.pageY, 
                                item.series.label + " of " + x + " = " + y);
                }
            }
            else {
                $("#tooltip").remove();
                previousPoint = null;            
            }
        }
    }); 

    $("#curve-chart").bind("plotclick", function (event, pos, item) {
        if (item) {
            $("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
            plot.highlight(item.series, item.datapoint);
        }
    });

});

/* Curve chart ends */
 </script>
 
 
 <script type="text/javascript">

 
 

 
 function search(){
 	var frmMain=document.getElementById("frmMain");
 	var hidOperationType=document.getElementById("hidOperationType");
 
 	hidOperationType.value="search";
	 frmMain.submit();
 }
 
 
 function cancelSearch(){
 //清空条件
 var txtSearchNumber=document.getElementById("txtSearchNumber");
 
 txtSearchNumber.value="";
 search();
 
 }
 
 function save(){
	 if(checkInput()){
		 var frmMain=document.getElementById("frmMain");
		 var hidOperationType=document.getElementById("hidOperationType");
		 
		 hidOperationType.value="save";
		 frmMain.submit();
	 }	 
 }
 
 
 
 function cancelSave(){
	 var frmMain=document.getElementById("frmMain");
	 var hidOperationType=document.getElementById("hidOperationType");
	 
	 hidOperationType.value="cancelSave";
	 frmMain.submit();
 }
 
 function modfiy(uId){
	 var frmMain=document.getElementById("frmMain"); 
	 var hidOperationType=document.getElementById("hidOperationType");
	 var hidUId=document.getElementById("hidUId");
	 
	 hidUId.value=uId;
	 hidOperationType.value="modfiy";
     frmMain.submit();
	 
	 
 }
 

 
 function add() {
		if(checkInput()){
			var frmMain=document.getElementById("frmMain");
			var hidOperationType=document.getElementById("hidOperationType");
					
			hidOperationType.value="add";
			frmMain.submit();  //通过脚本编程提交表单
		}
	}
 
 function remove(uId){
		//alert(uId);  只有确定按钮 信息提示
		if(confirm("是否删除序号为"+uId+"的药品信息？")){
			var frmMain=document.getElementById("frmMain");
			var hidOperationType=document.getElementById("hidOperationType");
			var hidUId=document.getElementById("hidUId");
			
			hidUId.value=uId;
			hidOperationType.value="remove";
			frmMain.submit();		
		}	
	}
 
 
 function clearSpan(){
		var spanNumber=document.getElementById("spanNumber");
		var spanName=document.getElementById("spanName");
		var spanBeginDate=document.getElementById("spanBeginDate");
		var spanEndDate=document.getElementById("spanEndDate");
		var spanStudentCount=document.getElementById("spanStudentCount");
		
		spanNumber.innerText="";
		spanName.innerText="";
		spanBeginDate.innerText="";
		spanEndDate.innerText="";
		spanStudentCount.innerText="";
		
		var spanMessage=document.getElementById("spanMessage");
		spanMessage.innerText="";
}

 
 
 function checkInput(){
	var txtNumber=document.getElementById("txtNumber");
	var txtName=document.getElementById("txtName");
	
	var spanNumber=document.getElementById("spanNumber");
	var spanName=document.getElementById("spanName");
	

	
	var flag1=true;
	
	if(txtNumber.value.length==0){
		spanNumber.innerText="数量不能为空！";
		flag1=false;
	}else{
		spanNumber.innerText="";
	}
	
	if(txtName.value.length==0){
		spanName.innerText="药名不能为空！";
		flag1=false;
	}else{
		spanName.innerText="";
	}
	
	
	
	return flag1;
}
</script>
</body>
</html>