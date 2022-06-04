
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function getBorrowInfoById(id){
	ajax(
		  {
		  	method:'POST',
    		url:'admin/borrowManageAction_getBorrowInfoById.action',
			params: "borrowId=" + id,
			type:"json",
    		callback:function(data) {
				
				$("#borrowId").val(data.borrowId);
				$("#ISBN").val(data.jiudian.ISBN);
				$("#jiudianName").val(data.jiudian.jiudianName);
				$("#jiudianType").val(data.jiudian.jiudianType.typeName);
				$("#paperNO").val(data.reader.paperNO);
				$("#readerName").val(data.reader.name);
				$("#readerType").val(data.reader.readerType.readerTypeName);
				$("#overday").val(data.overday);
				if (data.state == 0) {
					$("#state").val("未退房");
				}else if(data.state == 1){
					$("#state").val("逾期未退房");
				}else if(data.state == 2){
					$("#state").val("退房");
				}else if(data.state == 3){
					$("#state").val("续订未退房");
				}else if(data.state == 4){
					$("#state").val("续订逾期未退房");
				}else if(data.state == 5){
					$("#state").val("续订退房");
				}
				$("#admin").val(data.admin.name);
			}
		}
										   
							    
						
	);	
	
	
	
	
	
			

}





function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


