const body = document.getElementById('detail_body');
body.innerHTML = body.innerText;
const deletesubmit = (e) => {
	if(confirm("정말로 삭제하시겠습니까?")){
		$.ajax({
			url: location.pathname,
			type: 'delete',
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
};
$("#delete-btn").click(deletesubmit);