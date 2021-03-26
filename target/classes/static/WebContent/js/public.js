function ajaxPost(url,param,returnObj){
	$.ajax({
		url:url,
		type:'post',
		async:false,
		dataType: 'json',
		contentType: 'application/json', 
		data:param,
		success:function(data,status){
			debugger;
			console.log(data);
			returnObj = data;
		},
		error: function(){  
            alert("服务器没有返回数据，可能服务器忙，请重试");  
        }
	});
}