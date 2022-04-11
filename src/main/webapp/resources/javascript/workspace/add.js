const onSubmit = async() => {
    const form = document.getElementById('join-form');
    if(!form.workspaceName.value){
        alert("워크스페이스 제목을 입력해주세요.");
        return;
    }
	if(!form.nick.value){
        alert("닉네임을 입력해주세요.");
        return;
    }
    $.ajax({
    	url: location.pathname,
    	method: 'post',
    	data: $(form).serialize(),
    	dataType: 'json',
    	async: true,
    	success: (data, textStatus, response) => {
    		alert(data.message);
    		if(redirectURL = response.getResponseHeader('Location')){
    			location.href = redirectURL;
    			return;
    		}
    		location.href = "/bysl";
    	},
    	error: (jqXHR) => {
    		alert(jqXHR.responseJSON.message);
    		location.reload();
    	},
    });
}