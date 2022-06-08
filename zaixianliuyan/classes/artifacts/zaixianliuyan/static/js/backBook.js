
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});


function backJiudian(id){
	var postdata = "borrowId="+id;
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/backManageAction_backJiudian.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						showInfo("退房成功");

	                }else if(data == -1){
						showInfo("该客房已经退了");
					}else if(data == 2){
						showInfo("退房成功,请缴纳逾期罚金");
					}else{
						showInfo("退房失败");
					}
								
				}
			}
			   
    	);
			
		
	
}







function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


