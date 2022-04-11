<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<nav id="workspace_side_bar">
    <nav class="menu_wrap">
        <ul class="menu">
            <li class="menu_list">
                <a href='/bysl/solo/workspace/${soloWorkspace.id}' class="menu_text">Calendar</a>
            </li>
            <li class="menu_list">
                <a href='/bysl/solo/studentbook/${soloWorkspace.id}' class="menu_text" style="font-family: 'Noto Sans KR', sans-serif; font-weight: bold;">학생부 활동 기록</a>
            </li>
            <li class="menu_list">
                <a href='/bysl/solo/schoolRecord/${soloWorkspace.id}' class="menu_text" style="font-family: 'Noto Sans KR', sans-serif; font-weight: bold;">생활기록부 관리</a>
            </li>
            <li class="menu_list">
                <a href='/bysl/solo/selfIntroduce/${soloWorkspace.id}' class="menu_text" style="font-family: 'Noto Sans KR', sans-serif; font-weight: bold;">자기소개서 관리</a> 
            </li>
        </ul>
    </nav>
</nav>