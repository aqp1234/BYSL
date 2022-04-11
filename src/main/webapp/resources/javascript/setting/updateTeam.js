const permissions_from = document.getElementById('permissions_from');
const permissions_to = document.getElementById('permissions_to');
const choose_all = document.getElementById('add_all_permissions');
const add_link = document.getElementById('add_permissions');
const remove_link = document.getElementById('remove_permissions');
const clear_all = document.getElementById('remove_all_permissions');

choose_all.addEventListener('click', (e) => {
    const options = permissions_from.options;
    [...options].forEach((option) => {
        option.selected = true;
        permissions_to.appendChild(option);
    });
});

add_link.addEventListener('click', (e) => {
    const options = permissions_from.selectedOptions;
    [...options].forEach((option) => {
        option.selected = true;
        permissions_to.appendChild(option);
    });
});

remove_link.addEventListener('click', (e) => {
    const options = permissions_to.selectedOptions;
    [...options].forEach((option) => {
        option.selected = false;
        permissions_from.appendChild(option);
    });
});

clear_all.addEventListener('click', (e) => {
    const options = permissions_to.options;
    [...options].forEach((option) => {
        option.selected = false;
        permissions_from.appendChild(option);
    });
});

const submitForm = async () => {
    const form = document.getElementById('team_form');
    const options = [...form.permissions_to.options]
    const values = options.map((option) =>{
        return option.value;
    });
    $.ajax({
    	url: location.pathname,
    	method: 'post',
    	data: {
            team_name: form.team_name.value,
            permissions: values
        },
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

const delete_team = async() => {
    if(confirm("정말로 삭제하시겠습니까?")){
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
}