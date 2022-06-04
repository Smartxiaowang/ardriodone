function addJiudianNum(id,isbn){
	
	$("#addJiudianNumId").val(id);
	$("#addJiudianNumISBN").val(isbn);
}


$(function () {
	

    $('#add_JiudianNum').click(function () {

    	
    	if (!validAddJiudianNum()) {
            return;
        }
	var postdata = "jiudianId="+$.trim($("#addJiudianNumId").val())+"&num="+$.trim($("#addJiudianNum").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/jiudianManageAction_addJiudianNum.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addNumModal").modal("hide");//关闭模糊框		
						showInfo("新增成功");	

	                }else {
						$("#addNumModal").modal("hide");//关闭模糊框
						showInfo("新增失败");
					}
								
				}
			}
			   
    	);
			
		
	});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function validAddJiudianNum() {
    var flag = true;

    var num = $.trim($("#addJiudianNum").val());
    if (num == "") {
        $('#addJiudianNum').parent().addClass("has-error");
        $('#addJiudianNum').next().text("请输入新增客房数量");
        $("#addJiudianNum").next().show();
        flag = false;
    }else if(num<=0 || num!=parseInt(num)){
    	$('#addJiudianNum').parent().addClass("has-error");
        $('#addJiudianNum').next().text("客房数量必须为正整数");
        $("#addJiudianNum").next().show();
        flag = false;
	} else {
        $('#addJiudianNum').parent().removeClass("has-error");
        $('#addJiudianNum').next().text("");
        $("#addJiudianNum").next().hide();
    }


    return flag;
}


function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}
