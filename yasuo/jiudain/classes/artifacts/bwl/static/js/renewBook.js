
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});


function renewJiudian(id){
	var postdata = "borrowId="+id;
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/borrowManageAction_renewJiudian.action',
				params: postdata,
	    		callback:function(data) {
					if(data==1){
						showInfo("续订成功");
					}else if(data==-1){
						showInfo("该客房已退,无法续订");
					}else if(data==-2){
						showInfo("该客房续订过了,无法续订");
					}else if(data==-3){
						showInfo("已超续订期了,无法进行续订,请尽快去退房和缴纳罚金");
					}else if(data==-0){
						showInfo("续订失败");
					}
				}
			}
			   
    	);
			
		
	
}







function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


