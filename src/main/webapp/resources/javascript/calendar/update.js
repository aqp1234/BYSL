start_date.max = end_date.value;
end_date.min = start_date.value;

start_date.addEventListener("change", (e) => {
    end_date.min = e.target.value;
});

end_date.addEventListener("change", (e) => {
    start_date.max = e.target.value;
});

const form = document.getElementById("join-form");
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
		success: (result) => {
			alert(result.message);
			opener.location.reload();
			window.close();
		},
		error: (error) => {
			alert(error.responseText);
			location.reload();
		}
	});
};

$("#calendar-btn").click(submit);