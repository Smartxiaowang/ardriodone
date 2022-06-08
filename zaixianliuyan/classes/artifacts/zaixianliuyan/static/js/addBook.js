
$(function () {
	

  $('#addJiudian').click(function () {


 	if (!validAddJiudian()) {
        return;
    }

	var postdata = "jiudianName="+$.trim($("#addJiudianName").val())+"&autho="+ $.trim($("#addAutho").val())+"&press="+ $.trim($("#addPress").val())+"&num="+ $.trim($("#addNum").val())+"&price="+ $.trim($("#addPrice").val())+"&description="
	+ $.trim($("#addDescription").val())+"&jiudianTypeId="+ $.trim($("#addJiudianType").val())+"&ISBN="+ $.trim($("#addISBN").val());
	
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/jiudianManageAction_addJiudian.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("添加成功");	

	                }else {
						$("#addModal").modal("hide");//关闭模糊框
						showInfo("添加失败");
					}
								
				}
			}
			   
    	);
			
		
	});



		
   
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
		
		
		 $('#btn_add').click(function () {
		 	$("#addJiudianType option[value!=-1]").remove();//移除先前的选项
			ajax(
					  {
			    		url:"admin/jiudianManageAction_getAllJiudianTypes.action",
			    		type:"json",
			    		callback:function(data) {
							// 循环遍历每个客房分类，每个名称生成一个option对象，添加到<select>中
							for(var index in data) {
								var op = document.createElement("option");//创建一个指名名称元素
								op.value = data[index].typeId;//设置op的实际值为当前的客房分类编号
								var textNode = document.createTextNode(data[index].typeName);//创建文本节点
								op.appendChild(textNode);//把文本子节点添加到op元素中，指定其显示值
								
								document.getElementById("addJiudianType").appendChild(op);
							}
						}
			  		 }
				);	
		});
		
		
});



function validAddJiudian() {
    var flag = true;

    var ISBN = $.trim($("#addISBN").val());
    if (ISBN == "") {
        $('#addISBN').parent().addClass("has-error");
        $('#addISBN').next().text("请输入客房ISBN码");
        $("#addISBN").next().show();
        flag = false;
    } else {
        $('#addISBN').parent().removeClass("has-error");
        $('#addISBN').next().text("");
        $("#addISBN").next().hide();
    }

    var jiudianName = $.trim($("#addJiudianName").val());
    if (jiudianName == "") {
        $('#addJiudianName').parent().addClass("has-error");
        $('#addJiudianName').next().text("请输入客房名称");
        $("#addJiudianName").next().show();
        flag = false;
    }else {
        $('#addJiudianName').parent().removeClass("has-error");
        $('#addJiudianName').next().text("");
        $("#addJiudianName").next().hide();
    }
	
	
	var jiudianType = $.trim($("#addJiudianType").val());
	if(jiudianType == -1){
		 $('#addJiudianType').parent().addClass("has-error");
        $('#addJiudianType').next().text("请选择客房分类");
        $("#addJiudianType").next().show();
        flag = false;
	}else {
        $('#addJiudianType').parent().removeClass("has-error");
        $('#addJiudianType').next().text("");
        $("#addJiudianType").next().hide();
    }
	
	var autho = $.trim($("#addAutho").val());
	if(autho == ""){
		 /*$('#addAutho').parent().addClass("has-error");
        $('#addAutho').next().text("请输入作者名称");
        $("#addAutho").next().show();
        flag = false;*/
	}else {
        $('#addAutho').parent().removeClass("has-error");
        $('#addAutho').next().text("");
        $("#addAutho").next().hide();
    } 


	var press = $.trim($("#addPress").val());
	if(press == ""){
		 $('#addPress').parent().addClass("has-error");
        $('#addPress').next().text("请输入出版社名称");
        $("#addPress").next().show();
        flag = false;
	}else {
        $('#addPress').parent().removeClass("has-error");
        $('#addPress').next().text("");
        $("#addPress").next().hide();
    } 
	
	var num = $.trim($("#addNum").val());
	if(num == ""){
		 $('#addNum').parent().addClass("has-error");
        $('#addNum').next().text("请输入总数量");
        $("#addNum").next().show();
        flag = false;
	}else if(num<=0 || num!=parseInt(num)){
		 $('#addNum').parent().addClass("has-error");
        $('#addNum').next().text("数量必须为正整数");
        $("#addNum").next().show();
        flag = false;
	}else {
        $('#addNum').parent().removeClass("has-error");
        $('#addNum').next().text("");
        $("#addNum").next().hide();
    } 
	
	
	var price = $.trim($("#addPrice").val());
	if(price == ""){
		 $('#addPrice').parent().addClass("has-error");
        $('#addPrice').next().text("请输入总数量");
        $("#addPrice").next().show();
        flag = false;
	}else if(price<=0 || price!=parseInt(price)){
		 $('#addPrice').parent().addClass("has-error");
        $('#addPrice').next().text("数量必须为正整数");
        $("#addPrice").next().show();
        flag = false;
	}else {
        $('#addPrice').parent().removeClass("has-error");
        $('#addPrice').next().text("");
        $("#addPrice").next().hide();
    } 
	
	
	
    return flag;
}




function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


