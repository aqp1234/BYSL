document.getElementById('delete-btn').addEventListener('click', () => {
    if(confirm('정말로 삭제하시겠습니까?')){
        fetch(location.pathname, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json'},
        })
        .then((res) => res.json())
        .then((data) => {
            location.href = '/room/' + data.workspace_id;
        })
        .catch((err) => {
            console.error(err);
        });
    }
});


