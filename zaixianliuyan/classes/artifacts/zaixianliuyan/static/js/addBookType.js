
/**
 * ajax提交添加客房分类的信息
 * @param {Object} '#addJiudianType'
 */
$(function () {
	

    $('#addJiudianType').click(function () {

    	
    	if (!validAddJiudianType()) {
            return;
        }
    	
	var postdata = "typeName="+$.trim($("#addJiudianTypeName").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/jiudianTypeManageAction_addJiudianType.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("添加成功");	

	                }else if (data == -1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("该客房分类存在");
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
	
	

});



function validAddJiudianType() {
    var flag = true;

    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");	
	var jiudianType = $.trim($("#addJiudianTypeName").val());
	if(jiudianType == ""){
		 $('#addJiudianTypeName').parent().addClass("has-error");
        $('#addJiudianTypeName').next().text("请输入客房分类名称");
        $("#addJiudianTypeName").next().show();
        flag = false;
	}else if(!reg.test(jiudianType)){
		$('#addName').parent().addClass("has-error");
        $('#addName').next().text("客房分类名称必须为中文");
        $("#addName").next().show();
        flag = false;
	}else {
        $('#addJiudianTypeName').parent().removeClass("has-error");
        $('#addJiudianTypeName').next().text("");
        $("#addJiudianTypeName").next().hide();
    }
	
	
	
	
    return flag;
}






function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


