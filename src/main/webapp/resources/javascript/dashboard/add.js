start_date.addEventListener("change", (e) => {
    end_date.min = e.target.value;
});
end_date.addEventListener("change", (e) => {
    start_date.max = e.target.value;
});

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "content",
 sSkinURI: "/bysl/resources/smarteditor/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});

function submitContents() {
    window.onbeforeunload = null;
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("ir1").value를 이용해서 처리한다.
    const form = document.getElementById('dashboard_form');
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
			if(redirectURL = response.getResponseHeader("Location")){
				location.href = redirectURL;
				return;
			}
			location.href="/bysl";
		},
		error: (jqXHR) => {
			alert(jqXHR.responseJSON.message);
			location.reload();
		}
	});
}