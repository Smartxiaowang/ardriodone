
/**
 * ajax提交修改客房分类的信息
 * @param {Object} '#updateJiudianType'
 */
$(function () {
	

    $('#updateJiudianType').click(function () {

    	
    	if (!validUpdateJiudianType()) {
            return;
        }
    	
	var postdata = "id="+$.trim($("#updateJiudianTypeId").val())+"&typeName="+$.trim($("#updateJiudianTypeName").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/jiudianTypeManageAction_updateJiudianType.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#updateModal").modal("hide");//关闭模糊框		
						showInfo("修改成功");	

	                }else {
						$("#updateinfo").modal("hide");//关闭模糊框
	                    showInfo("修改失败");
	                }
								
				}
			}
			   
    	);
			
		
	});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});






/**
 * 获取需要修改客房分类信息
 * @param {Object} id 需要修改的客房分类id
 */
function updateJiudianType(id){
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/jiudianTypeManageAction_getJiudianType.action',
				params: "id=" + id,
				type:"json",
	    		callback:function(data) {
					$("#updateJiudianTypeId").val(data.typeId);
					$("#updateJiudianTypeName").val(data.typeName);
								
				}
			}
			   
    	);
			

}



function validUpdateJiudianType() {
    var flag = true;

    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");	
	var jiudianType = $.trim($("#updateJiudianTypeName").val());
	if(jiudianType == ""){
		 $('#updateJiudianTypeName').parent().addClass("has-error");
        $('#updateJiudianTypeName').next().text("请输入客房分类名称");
        $("#updateJiudianTypeName").next().show();
        flag = false;
	}else if(!reg.test(jiudianType)){
		$('#updateJiudianTypeName').parent().addClass("has-error");
        $('#updateJiudianTypeName').next().text("客房分类名称必须为中文");
        $("#updateJiudianTypeName").next().show();
        flag = false;
	}else {
        $('#updateJiudianTypeName').parent().removeClass("has-error");
        $('#updateJiudianTypeName').next().text("");
        $("#updateJiudianTypeName").next().hide();
    }
	
	
	
	
    return flag;
}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


