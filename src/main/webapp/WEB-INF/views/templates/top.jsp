<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.SoloWorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.WorkspaceDTO"%>
<%@page import = "com.kms.bysl.dto.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	MemberDTO member = (MemberDTO) session.getAttribute("member");
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
	WorkspaceDTO workspace = (WorkspaceDTO) session.getAttribute("workspace");
	SoloWorkspaceDTO soloWorkspace = (SoloWorkspaceDTO) session.getAttribute("soloWorkspace");
%>

<link rel="stylesheet" type="text/css" href="/bysl/resources/css/layout.css">
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/sideBar.css">
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/clientSideBar.css">
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/layoutPopup.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
        

<nav>
    <div id="top">
        <div style="margin-top: -7px"><a href='/bysl'><img src="/bysl/resources/img/logo3.png" alt="logo" id="logo"></a></div>
        <div id="top_right">
            <% if(workspace != null) {%>
                <div class="name">${userWorkspace.nick}</div>
                <div class="user_color" style="background-color: ${userWorkspace.color}"></div>
                <div><a class="nav-link" href="#" onclick="openChangeProfile()">Change Profile</a></div>
                <div><a class="nav-link" href="/bysl/setting/${userWorkspace.workspaceId}">Settings</a></div>
            <%} else if(soloWorkspace != null) {%>
                <div class="name">${member.name}</div>
                <div class="user_color" style="background-color: rgb(215, 226, 249)"></div>
                <div><a class="nav-link" href="#" onclick="openSoloSettings()">Settings</a></div>
            <%} else {%>
                <span class="name">${member.name}</span>
                <div class="user_color" style="background-color: rgb(215, 226, 249)"></div>
            <% } %>
            <!-- 팀 워크스페이스 내 상단바 -->
            <% if(member != null) {%>
                <a class='nav-link' href='/bysl/member/logout'>LOGOUT</a>
           	<% } else { %>
                <a class='nav-link' href='/bysl/member/login'>LOGIN</a>
            <% } %>
        </div>
    </div>
</nav>
<script>
	const openChangeProfile = () => {
	    window.name = "parentForm";
	    openWin = window.open("/bysl/profile/${userWorkspace.workspaceId}", 'childForm', 'width=500, height=300, resizable=no, scrollbars=yes');
	}
	const openSoloSettings = () => {
	    window.name = "parentForm";
	    openWin = window.open("/bysl/solo/workspace/${soloWorkspace.id}/settings", 'childForm', 'width=500, height=200, resizable=no, scrollbars=yes');
	}
</script>