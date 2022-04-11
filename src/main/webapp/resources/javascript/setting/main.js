const invite_reject = async (e) => {
    $.ajax({
    	url: location.pathname + '/invite/' + e,
    	method: 'delete',
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
    })
}
const set_team = async (button) => {
    const select = button.parentNode.previousElementSibling.childNodes[1];
    $.ajax({
    	url: location.pathname + '/setTeam',
    	method: 'post',
    	data: {
            user_id: button.dataset.user_id,
            team_id: select.options[select.selectedIndex].value
    	},
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
}
const delete_user = (e) => {
    if(confirm('정말 사용자를 삭제하시겠습니까?')) {
    	$.ajax({
        	url: location.pathname + '/user/' + e,
        	method: 'delete',
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
        })
    }
}