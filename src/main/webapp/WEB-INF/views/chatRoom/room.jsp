<%@page import="com.kms.bysl.dto.MemberDTO"%>
<%@page import="com.kms.bysl.dto.ChatDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.RoomDTO"%>
<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
	RoomDTO room = (RoomDTO) request.getAttribute("room");
	List<ChatDTO> chats = (List<ChatDTO>) request.getAttribute("chats");
	MemberDTO member = (MemberDTO) session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/chat.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<a href='/bysl/chat/${userWorkspace.workspaceId}' id='exit-btn'>◀</a>
	    <span class="chatroom_name"><%= room.getName() %></span>
	    <a href='#' id='delete-btn'>채팅방 삭제</a>
	    <fieldset>
	        <div id='chat-list'>
	            <% for (ChatDTO chat : chats) {%>
	                <% if (chat.getOwnerId() == member.getId()) {%>
	                    <div class='mine'>
	                        <div class='mine_nick'>{{chat.user_name}}</div>
	                        <div class='mine_chat'>{{chat.chat}}</div>
	                        <div class='mine_color' style="background-color: {{chat.user_color}};"></div>
	                    </div>
	                <% } else { %>
	                    <div class='other'>
	                        <div class='other_color' style="background-color: {{chat.user_color}};"></div>
	                        <div class='other_nick'>{{chat.user_name}}</div>
	                        <div class='other_chat'>{{chat.chat}}</div>
	                    </div>
	                <% } %>
	            <% } %>
	        </div>
	    </fieldset>
	    <div id='chat-form'>
	        <textarea type='text' id='chat' name='chat'></textarea>
	        <button type='button' id='send-btn'>전송</button>
	    </div>
    </section>
    <script src="/bysl/resources/javascript/room.js"></script>
	<script>
	/*
		function appendMsg(owner, nick, chat){
			if(owner == "${member.email}"){
				var html = "<div class='mine'>";
				html += "<div class='mine_nick'>" + nick + "</div>";
			    html += "<div class='mine_chat'>" + chat + "</div>";
			    html += "<div class='mine_color' style='background-color: #bdd7ff;'></div></div>";
			    
			    $("#chat-list").append(html);
			}else{
				var html = "<div class='other'>";
				html += "<div class='other_color' style='background-color: #bdd7ff;'></div>";
				html += "<div class='other_nick'>" + nick + "</div>";
				html += "<div class='other_chat'>" + chat + "</div></div>";
				
				$("#chat-list").append(html);
			}
		}
	*/
	var socket = null;
	connect();

	function connect() {
		var ws = new WebSocket("ws:/bysl/chatting");
		socket = ws;
		//이벤트 헨들러
		ws.onopen = function() {
			console.log('Info: connection opened.');
		};
		
		//소켓에 메시지를 보냈을 때(sess.sendMessage) 여기서 받아짐 
		ws.onmessage = function (event) {
			var sm = event.data;
			var sl = sm.split(',');
			$("#writer").text("보내는 이 : " + sl[0]);
			$("#receiver").text("받는 이 : " + sl[1]);
			$("#content").text("내용: " + sl[2]);
			
			console.log("ReceiveMessage:" + event.data+'\n');
		};
		
		ws.onclose = function (event) { 
			console.log('Info: connection closed'); 
			setTimeout( function() {connect(); }, 1000); // retry connection!!
		};
		
		ws.onerror = function (err) { console.log('Error:', err); };
	}

	$(document).ready(function() {
		$('#send-btn').on('click', function(evt) {
			let receiveId = $('#receiveId').val();
			let msg = $('#chat').val();
			evt.preventDefault();
			if (socket.readyState !== 1 ) return;
			socket.send(gUserId + "," + receiveId + "," + msg);
		});
		
		$('#wsClose').on('click', function(e) {
			socket.onclose();
			
		});
	});
	</script>
</body>
</html>