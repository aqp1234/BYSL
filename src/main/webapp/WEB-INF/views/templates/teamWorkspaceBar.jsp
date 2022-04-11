<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav id="workspace_side_bar">
	<nav class="menu_wrap">
	    <ul class="menu">
	        <li class="menu_list">
	            <a href='/bysl/chat/${userWorkspace.workspaceId}' class="menu_text">Chat</a>
	        </li>
            <li class="menu_list">
                <a href='/bysl/calendar/${userWorkspace.workspaceId}' class="menu_text">Calendar</a>
            </li>
            <li class="menu_list">
                <a href='/bysl/dashboard/${userWorkspace.workspaceId}' class="menu_text">Dashboard</a>
            </li>
	        <li class="menu_list">
	            <a href='/bysl/conference/${userWorkspace.workspaceId}' class="menu_text">Conference</a>
	        </li>
	        <li class="menu_list">
	            <a href='/bysl/share/${userWorkspace.workspaceId}' class="menu_text">Share</a>
	        </li>
	        <li class="menu_list">
	            <a class='invite menu_text' href='#invite-workspace-layer'>Invite</a>
	            <div class="invite_dim-layer">
                <div class="invite_dimBg"></div>
                <div id="invite-workspace-layer" class="invite_pop-layer">
                    <div class="invite_pop-container">
                        <div class="invite_pop-conts">
                            <div class="btn-c">
                                <a href="#" class="btn-close">Close ✖</a>
                            </div>
                            <div class="btn-r">
                                초대할 인원의 이메일을 입력해주세요.
                            </div>
                            <form id="send-email-form" action="/bysl/workspace/${userWorkspace.workspaceId}/send_invite_mail" method="post">
                                <span class="input-group">
                                    <input id="email" type="email" name="email">
                                </span>
                                <button id="send-workspace-invite-email-btn" type="button" class="btn-send">초대 이메일 발송</button>
                            </form>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </nav>
</nav>
<script async src="/bysl/resources/javascript/templates/teamWorkspaceBar.js"></script>
	