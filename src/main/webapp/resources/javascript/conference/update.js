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
    const form = document.getElementById('conference_form');
    try{
    	if(!form.subject.value){
    		alert("제목을 입력해주세요.");
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
    		error: (error) => {
    			alert(error.responseText);
    			location.reload();
    		}
    	});
    }catch(e){
        console.error(e);
    }
}