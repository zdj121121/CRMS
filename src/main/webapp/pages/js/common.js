$(function(){
	// 显示用户名
	$.ajax({
		type: 'post',
		url: 'agency/getagencyname',
		dataType:'json',
		success: function(data){
			$('.w_userName').text(data.agencyname);
		}
	});
	
	// 注销账号
	$('.logOut').click(function(){
		$.ajax({
			type: 'GET',
			url: 'agency/logout',
			success: function(data){
				if(data.status == 1) {
					alert(data.msg);
					window.location.href = data.url;
				} else {
					alert(data.msg);
					window.location.reload();
				}
			}
		});

	});
});
