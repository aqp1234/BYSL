const form = document.getElementById('change-form');
const submit = (e) => {
	if(!form.nick.value){
		alert("닉네임을 입력해주세요.");
		e.preventDefault();
		return;
	}
	$.ajax({
		url: location.pathname,
		type: 'post',
		data: $(form).serialize(),
		dataType: 'json',
		async: true,
		success: (result) => {
			alert(result.message);
			opener.location.reload();
			window.close();
		},
		error: (jqXHR) => {
			alert(jqXHR.responseJSON.message);
			location.reload();
		}
	});
}

$("#profile-btn").click(submit);