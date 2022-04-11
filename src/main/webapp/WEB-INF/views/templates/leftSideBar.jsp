<%@page import="com.kms.bysl.dto.SoloShareDTO"%>
<%@page import="com.kms.bysl.dto.SoloWorkspaceDTO"%>
<%@page import="java.util.List"%>
<%@ page import = "com.kms.bysl.dto.WorkspaceDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<WorkspaceDTO> myWorkspaces = (List<WorkspaceDTO>) session.getAttribute("myWorkspaces");
	List<SoloWorkspaceDTO> mySoloWorkspaces = (List<SoloWorkspaceDTO>) session.getAttribute("mySoloWorkspaces");
	List<SoloShareDTO> soloShares = (List<SoloShareDTO>) session.getAttribute("soloShares");
%>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src='https://unpkg.com/axios/dist/axios.min.js'></script>

<nav id="left_side_bar">
	<button id="show" onclick="show_or_hide_left_side_bar()">◀</button>
	<span id="wrap">
	    <nav class="menu_wrap">
	        <ul class="menu">
	        	<% if(mySoloWorkspaces != null) {%>
	            <% for (SoloWorkspaceDTO soloWorkspace : mySoloWorkspaces){ %>
	            <li class="menu_list">
	                <a href="/bysl/solo/workspace/<%= soloWorkspace.getId() %>" class="menu_text" style="color: white;"><%= soloWorkspace.getSoloWorkspaceName() %></a>
	            </li>
	            <% }} %>
	            <% if(myWorkspaces != null){ %>
	            <% for (WorkspaceDTO workspace : myWorkspaces){ %>
	            <li class="menu_list">
	                <a href="/bysl/calendar/<%= workspace.getId() %>" class="menu_text"><%= workspace.getWorkspaceName() %></a>
	            </li>
	            <% }} %>
	            <li class="plus_menu">
	                <a class='plus' href='#workspace-select-layer'>+</a>
	                <div class="dim-layer">
	                    <div class="dimBg"></div>
	                    <div id="workspace-select-layer" class="pop-layer">
	                        <div class="pop-container">
	                            <div class="pop-conts">
	                                <div class="btn-c">
	                                    <a href="#" class="btn-close">Close</a>
	                                </div>
	                                <div class="btn-r">
	                                    생성하려는 프로젝트의 종류를 선택해주세요.
	                                </div>
	
	                                <div class="btn-r">
	                                    <a href="#" class="btn-Ok">Team</a>
	                                    <a href="#solo-workspace-select-layer" class="btn-No">Personal</a>
	                                </div>
	                                <div class="btn-r">
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="dim-layer2">
	                    <div class="dimBg2"></div>
	                    <div id="solo-workspace-select-layer" class="pop-layer2">
	                        <div class="pop-container2">
	                            <div class="pop-conts">
	                                <div class="btn-c">
	                                    <a href="#" class="btn-close">Close</a>
	                                </div>
	                                <div class="btn-r">
	                                    개인 워크스페이스 이름을 입력해주세요
	                                </div>
	                                <form id="join-form" action="/bysl/solo/workspace/add" method="post">
	                                    <span class="input-group">
	                                        <input id="solo-workspace-name" type="text" name="soloWorkspaceName">
	                                    </span>
	                                    <button id="add-solo-workspace-btn" type="submit" class="btn-add" style="margin-left: 10px;">개인 워크스페이스 생성</button>
	                                </form>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </li>
	        </ul>
	    </nav>
	    <nav class="menu_wrap" style="background-color: rgb(250, 230, 237) ;">
	        <ul class="menu">
	            <li class="menu_list" style="font-family: 'Noto Sans KR', sans-serif; font-weight: bolder;">
                    ☁ 공유 받은 파일 ☁
                </li>
                <% for (SoloShareDTO soloShare : soloShares) { %>
                <li class="menu_list">
                    <a href="<%= soloShare.getUrl() %>" class="menu_text_share"><%= soloShare.getName() %> : <%= soloShare.getType() %></a>
                </li>
                <% } %>
            </ul>
        </nav>
    </span>
</nav>

<script async src="/bysl/resources/javascript/templates/layerPopup.js"></script>
<script async src="/bysl/resources/javascript/templates/leftSideBar.js"></script>