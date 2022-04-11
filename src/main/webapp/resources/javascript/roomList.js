$('.chat_maker').click(function(){
    var $href = $(this).attr('href');
    layer_popup3($href);
});

const go_chat_room = (e) => {
	location.href = location.pathname + "/room/" + e;
}

function layer_popup3(el){
    var $el = $(el);    //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('dimBg'); //dimmed 레이어를 감지하기 위한 boolean 변수

    isDim ? $('.dim-layer3').fadeIn() : $el.fadeIn();

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
        $el.css({top: 500, left: -500});
    }

    $el.find('a.btn-close').click(function(){
        isDim ? $('.dim-layer3').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        return false;
    });
}
const roomform = document.getElementById("room-join-form");
const roomsubmit = (e) => {
	if(!roomform.name.value){
		alert("제목을 입력해주세요.");
		e.preventDefault();
		return;
	}
	$.ajax({
		url: roomform.action,
		type: 'post',
		data: $(roomform).serialize(),
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
$("#room-btn").click(roomsubmit);