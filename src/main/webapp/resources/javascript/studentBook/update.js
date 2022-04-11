$('input').keydown(e => {
    if(e.keyCode == 13){
        e.preventDefault();
    }
});

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
            layout_id.disabled = false;
            option0.text = "저장된 양식"
            content.innerHTML = content.innerText;
            $("select").focus((e) => {
                layoutarray[e.target.value] = contentarea.innerHTML;
            }).change((e) => {
                contentarea.innerHTML = layoutarray[e.target.value];
                textInput = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelectorAll('.input_text_body');
                textlength = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.getElementsByClassName('textlength');
                textInput.forEach((_textInput, i) => {
                    eval('var _textlength' + i + ' = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelector("#textlength' + i + '");')
                    $(_textInput).on('DOMSubtreeModified', () => {
                        eval('_textlength' + i + '.innerText = "' + _textInput.innerText.length + ' 자 / ' + byteCounter(_textInput.innerText) + ' Byte";')
                    })
                })
            });
            clearInterval(contentareainterval);
        }
    }catch(e){
        console.log(e);
    }
}, 1);

var textInputinterval = setInterval(() => {
    try{
        textInput = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelectorAll('.input_text_body');
        if(textInput.length){
            textInput = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelectorAll('.input_text_body');
            textlength = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.getElementsByClassName('textlength');
            textInput.forEach((_textInput, i) => {
                eval('var _textlength' + i + ' = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelector("#textlength' + i + '");')
                $(_textInput).on('DOMSubtreeModified', () => {
                    eval('_textlength' + i + '.innerText = "' + _textInput.innerText.length + ' 자 / ' + byteCounter(_textInput.innerText) + ' Byte";')
                })
            })
            clearInterval(textInputinterval)
        }
    }catch(e){
        console.log(e);
    }
}, 1)

const submitContents = () => {
    window.onbeforeunload = null;
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("ir1").value를 이용해서 처리한다.
    const form = document.getElementById('studentbook_form');
    if(!form.content.value || form.content.value == '<span></span>'){
        alert("내용을 입력해주세요.");
        return;
    }
	if(!form.subject.value){
        alert("제목을 입력해주세요.");
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