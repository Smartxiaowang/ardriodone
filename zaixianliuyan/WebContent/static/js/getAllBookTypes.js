window.onload = new function(){
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
						
						document.getElementById("jiudianTypeId").appendChild(op);
					}
				}
		   }
	);
};