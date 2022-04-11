<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.RoomDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<RoomDTO> rooms = (List<RoomDTO>) session.getAttribute("rooms");
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Room - Main</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/roomMain.css">
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/chatRoomPopup.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
		<div id="room_main_box">
		    <div class="br"><a href="#add_chatroom" class="chat_maker"><span class="text_deco_01">+</span><span class="text_deco_02">채팅방 생성</span></a></div>
		    <div class="dim-layer3">
		        <div class="dimBg"></div>
		        <div id="add_chatroom" class="pop-layer">
		            <div class="pop-container">
		                <div class="pop-conts">
		                    <div class="btn-c">
		                        <a href="#" class="btn-close">Close ✖</a>
		                    </div>
		                    <div class="btn-r">
		                        채팅방 이름을 입력해주세요.
		                    </div>
		                    <form id="room-join-form" action="/bysl/chat/${userWorkspace.workspaceId}/add" method="post">
		                        <span class="input-group">
		                            <input id="room-name" type="text" name="name">
		                        </span>
		                        <button id="room-btn" type="button" class="btn-add">New Room</button>
		                    </form>
		                    <div class="btn-r"></div>
		                </div>
		            </div>
		        </div>
		    </div>
		    <div id="check_chat_room">
		        <% for (RoomDTO room : rooms) {%>
		        <div id="chat_room_show" style="background-color: #bdd7ff;">
		            <span class="chatroomname"><%= room.getName() %></span>
		            <!-- <span class="lastchat">{{lastchats[chatroom._id]}}</span>  -->
		            <span><button class='join-btn' onclick="go_chat_room(<%= room.getId() %>)">입장</button><span>
		        </div>
		        <% } %>
		    </div>
		</div>
	</section>
</body>
<script src="/bysl/resources/javascript/roomList.js"></script>
</html>