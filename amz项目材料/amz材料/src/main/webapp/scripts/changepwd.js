function changepwd(){
	var pwd = $("#passWord").val();
	var rpwd = $("#rePassWord").val();
	if(pwd == ""){
		$("#userNamemsg").text("请输入新密码")
		return false;
	}
	if(rpwd == ""){
		$("#unamemsg").text("请输入重复密码")
		return false;
	}
	if(pwd != rpwd){
		
		return false;
	}
	return true;
}