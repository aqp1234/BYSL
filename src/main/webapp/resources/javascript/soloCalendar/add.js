start_date.addEventListener("change", (e) => {
    end_date.min = e.target.value;
});

end_date.addEventListener("change", (e) => {
    start_date.max = e.target.value;
});

const calendarsubmit = () => {
    const form = document.getElementById('join-form');
    if(!form.subject.value){
        alert('제목을 입력해주세요.');
        return;
    }
	if(!form.content.value){
        alert('내용을 입력해주세요.');
        return;
    }
	if(!form.start_date.value){
        alert('시작날짜를 입력해주세요.');
        return;
    }
	if(!form.end_date.value){
        alert('종료날짜를 입력해주세요.');
        return;
    }
    $.ajax({
		url: location.pathname,
		method: 'post',
		data: $(form).serialize(),
		dataType: 'json',
		async: true,
		success: (result, textStatus, response) => {
			alert(result.message);
			opener.location.reload();
			window.close();
		},
		error: (jqXHR) => {
			alert(jqXHR.responseJSON.message);
		}
	});
}