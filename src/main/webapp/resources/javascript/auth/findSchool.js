const setParentText = (e) => {
    opener.document.getElementById('school_name').value = e.getAttribute('school_name');
    opener.document.getElementById('location_name').value = e.getAttribute('location_name');
    window.close();
}
const deleteSchoolData = () => {
    const tbody = document.querySelector("#search_tbody");
    while(tbody.hasChildNodes()){
        tbody.removeChild(tbody.firstChild);
    }
}
const findschoolfunc = async() => {
    deleteSchoolData();
    let calHtml = "";
    const findword = document.getElementById('searchSchoolName').value;
    $.ajax({
		url: location.pathname,
		method: 'post',
		data: {
			word: findword,
		},
		dataType: 'json',
		async: true,
		success: (result) => {
			const data = result.data;
			if(data.dataSearch.content.length == 0){
				alert("검색 결과가 없습니다.");
				return;
			}
	        data.dataSearch.content.map(school => {
	            calHtml += "<tr><td class='school_name'>" + school.schoolName + "</td>" + 
	                "<td class='school_address'>" + school.adres + "</td><td school_name='" + school.schoolName + "' location_name='" + school.region + "' onclick='setParentText(this)'>" + "<input class='choose' type='button' value='✔'>" + "</td></tr>";
	        });
	        document.querySelector('#search_tbody').insertAdjacentHTML('afterBegin', calHtml);
		},
		error: (jqXHR) => {
			alert(jqXHR.responseJSON.message);
		}
	});
}