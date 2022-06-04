//找回密码表单提交
function findbackpwd(){
	var userName = $("#userName").val();
	if(userName == ""){
		$("#userNamemsg").text("请输入用户名")
		return false;
	}
	var email = $("#email").val();
	if(email == ""){
		$("#emailmsg").text("请输入邮箱")
		return false;
	}
	return true;
}