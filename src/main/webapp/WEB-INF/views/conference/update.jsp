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
    	<div id="main_top">
		    <span class="text_deco01">회의록 수정하기</span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		
		<div class="container">
		    <form action="" method="post" id="conference_form">
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_title">제목</label>
		            <input id="subject" type="text" name="subject" value="${conference.subject}">
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_content">내용</label>
		            <div class="input_deco_input_content">
		                <textarea name="content" id="content">${conference.content}</textarea>
		            </div>
		        </div>
	            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드</a>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="/bysl/resources/javascript/conference/update.js"></script>
</body>
</html>