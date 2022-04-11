<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.ConferenceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
	ConferenceDTO conference = (ConferenceDTO) request.getAttribute("conference");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conference</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/conference.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<a href='/bysl/conference/${userWorkspace.workspaceId}' id='exit-btn'>◀</a>
		<span class="text_deco11"><a href='/bysl/conference/${userWorkspace.workspaceId}/update/${conference.id}' class="text_deco12">글 수정</a></span>
		<span class="text_deco11"><a href='#' class="text_deco12" id="delete-btn">글 삭제</a></span>
		
		<div id="detail_top">
		    <hr class="line_deco01" width = "100%" color="#414141">
		        <div class="detail_title">${conference.subject}</div>
		    <hr class="line_deco01" width = "100%" color="#414141">
		</div>
		<div readonly id="detail_body">${conference.content}</div>
		<hr class="line_deco01" width = "100%" color="#414141">
    </section>
	<script src="/bysl/resources/javascript/conference/detail.js"></script>
</body>
</html>