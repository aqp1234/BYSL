const byteCounter = (text) => {
    let byte = 0;
    for(let i=0; i<text.length;i++) {
        if (/[ㄱ-ㅎㅏ-ㅣ가-힣一-龥ぁ-ゔァ-ヴー々〆〤]/.test(text[i])) {
            byte = byte+2;
        } else {
            byte++;
        }
    }
    return byte;
}

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "content",
 sSkinURI: "/bysl/resources/smarteditor/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});

var contentarea = ``;
var contentareainterval = setInterval(() => {
    try{
        contentarea = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelector(".se2_inputarea");
        if(contentarea){

            textbody = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelector('body');
            if(textbody){
                textlength = document.querySelector("#textlength");
                textlength.innerText = `${textbody.innerText.length} 자 / ${byteCounter(textbody.innerText)} Byte`;
                $(textbody).on('DOMSubtreeModified', (e) => {
                    document.querySelector("#textlength").innerText = `${e.target.innerText.length} 자 / ${byteCounter(e.target.innerText)} Byte`;
                });
                clearInterval(contentareainterval);
            }

        }
    }catch(e){
        console.log(e);
    }
}, 1);

function submitContents() {
    window.onbeforeunload = null;
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("ir1").value를 이용해서 처리한다.
    const form = document.getElementById("self_introduce_form");
    if(!form.content.value){
        alert("내용을 입력해주세요.");
    }else if(!form.subject.value){
        alert("제목을 입력해주세요.");
    }else{
        $.ajax({
        	url: location.pathname,
        	method: 'post',
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
}