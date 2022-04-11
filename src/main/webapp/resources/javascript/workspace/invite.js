var search = location.search;
var params = new URLSearchParams(search);
var getType= params.get('key');
document.getElementById("key").value = getType;
const submit = () => {
	const form = document.getElementById('join-form');
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
$("#workspace-btn").click(submit);