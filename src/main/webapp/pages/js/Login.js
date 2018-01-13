
$("#button").click(function(){


	$.ajax({
		url: '/userController/login',
		type: 'post',
		data:{
            "accountNum":$('#value_1').val(),
			"password":$('#value_2').val()
           },
		dataType:'json',
		success: function(data){
               if(data.success==true){
               	if(data.IsAdmin==1){
               		location.href="/pages/adviceroom.html"
				}else{

				}
			   }else {
               	alert("用户账号或密码错误");
			   }
		}
	});

});