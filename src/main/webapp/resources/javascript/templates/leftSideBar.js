history.replaceState({}, null, location.pathname);

if(location.pathname == '/bysl/'){
    document.getElementById('main').className = 'main';
}
if(!"${soloWorkspace}" && !"${workspace}"){
    document.getElementById('main').className = 'main';
}

const show_or_hide_left_side_bar = () => {
    if(wrap.style.display=='none') {
        wrap.style.display='';show.innerText='◀';
        main.style.width = main.offsetWidth - 210 + 'px';
    } else {
        wrap.style.display='none';show.innerText='▶';
        main.style.width = main.offsetWidth + 210 + 'px';
    }
}