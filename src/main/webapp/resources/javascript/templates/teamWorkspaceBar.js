$('.invite').click(function(){
    var $href = $(this).attr('href');
    invite_layer_popup($href);
});

function invite_layer_popup(el){
    var $el = $(el);    //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('invite_dimBg'); //dimmed 레이어를 감지하기 위한 boolean 변수

    isDim ? $('.invite_dim-layer').fadeIn() : $el.fadeIn();

    var $elWidth = ~~($el.outerWidth()),
        $elHeight = ~~($el.outerHeight()),
        docWidth = $(document).width(),
        docHeight = $(document).height();

    // 화면의 중앙에 레이어를 띄운다.
    if ($elHeight < docHeight || $elWidth < docWidth) {
        $el.css({
            marginTop: -$elHeight /2,
            marginLeft: -$elWidth/2
        })
    } else {
        $el.css({top: 0, left: 0});
    }

    $el.find('a.btn-close').click(function(){
        isDim ? $('.invite_dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        return false;
    });
}

const form = document.getElementById("send-email-form");
const submit = (e) => {
	if(!form.email.value){
		alert("이메일을 입력해주세요.");
		e.preventDefault();
		return;
	}
	$.ajax({
		url: form.action,
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
};
$("#send-workspace-invite-email-btn").click(submit);