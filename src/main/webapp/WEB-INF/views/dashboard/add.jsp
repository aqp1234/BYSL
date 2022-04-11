<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
	List<UserWorkspaceDTO> userWorkspaces = (List<UserWorkspaceDTO>) request.getAttribute("userWorkspaces");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/dashboard.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
	    <div id="main_top">
	    	<span class="text_deco09"> 새로운 대시보드 작성</span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		
		<div class="container">
		    <form action="" method="post" id="dashboard_form">
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_manager">처리자선택</label>
		            <select name="managerId" id="manager_id">
		                <% for (UserWorkspaceDTO exUserWorkspace : userWorkspaces) { %>
		                    <option value="<%= exUserWorkspace.getUserId() %>"><%= exUserWorkspace.getNick() %></option>
		                <% } %>
		            </select>
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">진행 상태</label>
		            <select name="flag" id="flag_id">
		                <option value=1>할 일</option>
		                <option value=2>진행 중</option>
		                <option value=3>완료</option>
		            </select>
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">제목</label>
		            <input id="subject" type="text" name="subject">
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">내용</label>
		            <div class="input_deco_input_content">
		                <textarea name="content" id="content"><span></span></textarea>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">시작 날짜</label>
		            <input id="start_date" type="date" name="start_date">
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">종료 날짜</label>
		            <input id="end_date" type="date" name="end_date">
		        </div>
		        <div>
		            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드 ✔</a>
		        </div>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="/bysl/resources/javascript/dashboard/add.js"></script>
</body>
</html>