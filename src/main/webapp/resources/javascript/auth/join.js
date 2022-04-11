var openWin;
const openchild = () => {
    window.name = 'parentForm';
    openWin = window.open('/bysl/member/findSchool', 'childForm', 'width=570, height=350, resizable=no, scrollbars=yes');
}
const sendEmail = async() => {
    $.ajax({
		url: `${location.pathname}/sendEmail`,
		method: 'post',
		data: {
        	email: document.getElementById('input-email').value,
		},
		dataType: 'json',
		async: true,
		success: (result) => {
        	document.getElementById('certNo').value = result.data.number;
			alert(result.message);
		},
		error: (jqXHR) => {
			alert(jqXHR.responseJSON.message);
		}
	});
}
const checkCertNo = async() => {
    $.ajax({
		url: `${location.pathname}/checkCertNo`,
		method: 'post',
		data: {
	        certNo: document.getElementById('certNo').value,
	        inputNum: document.getElementById('input-email-number').value,
		},
		dataType: 'json',
		async: true,
		success: (result) => {
	        document.getElementById('isAuth').value = true;
	        document.getElementById('join-btn').disabled = false;
	        alert(result.message);
		},
		error: (jqXHR) => {
        	document.getElementById('isAuth').value = false;
			alert(jqXHR.responseJSON.message);
		}
	});
}
const onSubmit = () => {
	const join_form = document.getElementById("join-form");
	if(!join_form.name.value){
		alert("이름을 입력해주세요.");
	}else if(!join_form.email.value){
		alert("이메일을 입력해주세요.");
	}else if(!join_form.password.value){
		alert("비밀번호를 입력해주세요.");
	}else if(!join_form.phone.value){
		alert("전화번호를 입력해주세요.");
	}else if(!join_form.school_name.value){
		alert("학료를 입력해주세요.");
	}else{
		$.ajax({
        	url: location.pathname,
        	method: 'post',
        	data: $(join_form).serialize(),
        	dataType: 'json',
        	async: true,
			success: (result, textStatus, response) => {
				alert(result.message);
				if(redirectURL = response.getResponseHeader("Location")){
					location.href = redirectURL;
					return;
				}
				location.href = "/bysl/member/login";
			},
			error: (jqXHR) => {
				alert(jqXHR.responseJSON.message);
			}
        });
	}
}