const deleteStaffInvite = async(e) => {
    if(confirm("초대를 취소하시겠습니까?")){
        $.ajax({
    		url: `${location.pathname}/${e}`,
    		type: 'delete',
    		dataType: 'json',
    		async: true,
    		success: (result) => {
    			alert(result.message);
    			location.reload();
    		},
    		error: (jqXHR) => {
    			alert(jqXHR.responseJSON.message);
    			location.reload();
    		}
    	});
    }
}

const email_check = (email) => {    
    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return (regex.test(email)); 
}

document.addEventListener("keydown", (e) => {
    if(e.keyCode == 13){
        e.preventDefault();
        staffinvitesubmit();
    }
});

const staffinvitesubmit = () => {
    const form = document.getElementById('join-form');
    const type = opener.document.getElementById('staffinvitebtn').dataset['type'];
    form.url.value = opener.location.pathname;
    form.type.value = type;
    if(!form.staff_email.value){
        alert('이메일을 입력해주세요.');
        return;
    }
	if(!email_check(form.staff_email.value)){
        alert('이메일 형식으로 입력해주세요.');
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
			location.reload();
		},
		error: (jqXHR) => {
			alert(jqXHR.responseJSON.message);
			location.reload();
		}
	});
}