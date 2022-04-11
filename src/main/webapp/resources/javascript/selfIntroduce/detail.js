const deletethis = () => {
	$.ajax({
		url: location.pathname,
		method: 'delete',
		dataType: 'json',
		async: true,
		success: (result, textStatus, response) => {
			alert(result.message);
			if(redirectURL = response.getResponseHeader("Location")){
				location.href = redirectURL;
				return;
			}
			location.href = "/bysl";
		},
		error: (jqXHR) => {
			alert(jqXHR.responseJSON.message);
			location.reload();
		}
	});
}

const onSubmit = () => {
	const comment_form = document.getElementById("comment_form");
	if(!comment_form.comment.value){
        alert("댓글을 입력해주세요.");
    }else{
    	$.ajax({
			url: location.pathname + "/comment",
			type: 'post',
    		data: $(comment_form).serialize(),
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
}

const editCommentbtn = (index) => {
    document.getElementById('comment_edit_' + index).style.display = 'block';
    document.getElementById('comment_' + index).style.display = 'none';
}

const editCommentCancelbtn = (index) => {
    document.getElementById('comment_edit_' + index).style.display = 'none';
    document.getElementById('comment_' + index).style.display = 'block';
}

const onEditComment = async (e) => {
    const edit_form = document.getElementById('comment_edit_form_' + e.dataset.id);
    if(!edit_form.comment.value){
        alert("댓글을 입력해주세요.");
    }else{
    	$.ajax({
			url: location.pathname + "/comment/" + e.dataset.id,
			type: 'put',
			dataType: 'json',
    		data: $(edit_form).serialize(),
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
}

const deleteComment = async(comment_id) => {
    if(confirm("정말로 댓글을 삭제하시겠습니까?")){
    	$.ajax({
			url: location.pathname + "/comment/" + comment_id,
			type: 'delete',
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
}

const openInvitePage = () => {
    window.name = 'parentForm';
    openWin = window.open('/bysl/soloShare?url=' + location.pathname, 'childForm', 'width=570, height=400, resizable=no, scrollbars=yes');
}