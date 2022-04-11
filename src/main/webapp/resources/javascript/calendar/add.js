const form = document.getElementById("join-form");
start_date.addEventListener("change", (e) => {
    end_date.min = e.target.value;
});
end_date.addEventListener("change", (e) => {
    start_date.max = e.target.value;
});
const submit = (e) => {
	if(!form.subject.value){
		alert("제목을 입력해주세요.");
		e.preventDefault();
		return;
	}
	if(!form.start_date.value){
		alert("시작 날짜를 입력해주세요.");
		e.preventDefault();
		return;
	}
	if(!form.end_date.value){
		alert("끝 날짜를 입력해주세요.");
		e.preventDefault();
		return;
	}
	$.ajax({
		url: location.pathname,
		type: 'post',
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
			opener.location.reload();
			window.close();
		}
	});
};
$("#calendar-btn").click(submit);