const filecount = document.getElementsByClassName("text_deco12");
if(!filecount.length){
    const span = document.createElement("span");
    span.className = "text_deco12";
    span.textContent = "파일 없음";
    document.getElementById("text_deco11").appendChild(span);
}

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