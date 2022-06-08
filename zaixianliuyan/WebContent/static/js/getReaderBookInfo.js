
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function getJiudianInfo(id){
		
	ajax(
		  {
		  	method:'POST',
    		url:'jiudianAction_getJiudian.action',
			params: "jiudianId=" + id,
			type:"json",
    		callback:function(data) {
				$("#findISBN").val(data.ISBN);
				$("#findJiudianName").val(data.jiudianName);
				$("#findJiudianType").val(data.jiudianType.typeName);
				$("#findAutho").val(data.autho);
				$("#findPress").val(data.press);
				$("#findPrice").val(data.price);
				$("#findDescription").val(data.description);
				$("#findNum").val(data.num);
				$("#findAdmin").val(data.admin.name);
				$("#findCurrentNum").val(data.currentNum);
			}
		}
										   
							    
						
	);	
	
	
	
	
	
			

}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


