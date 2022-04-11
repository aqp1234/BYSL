<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.DashboardDTO"%>
<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
	List<UserWorkspaceDTO> userWorkspaces = (List<UserWorkspaceDTO>) request.getAttribute("userWorkspaces");
	DashboardDTO dashboard = (DashboardDTO) request.getAttribute("dashboard");
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
		    <span class="text_deco01">대시보드 수정하기</span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		<div class="container">
		    <form action="" method="post" id="dashboard_form">
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_manager">처리자선택</label>
		            <select name="managerId" id="manager_id">
		                <% for ( UserWorkspaceDTO exUserWorkspace : userWorkspaces) { %>
		                	<% if (dashboard.getManagerId() == exUserWorkspace.getId()) { %>
		                    	<option value="<%= exUserWorkspace.getUserId() %>" selected><%= exUserWorkspace.getNick() %></option>
		                	<% } else { %>
		                    	<option value="<%= exUserWorkspace.getUserId() %>"><%= exUserWorkspace.getNick() %></option>
		                    <% } %>
		                <% } %>
		            </select>
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">진행 상태</label>
		            <select name="flag" id="flag">
		            <% int flag = dashboard.getFlag(); %>
		            <% if(flag == 1) { %>
		            	<option value=1 selected>할 일</option>
		                <option value=2>진행 중</option>
		                <option value=3>완료</option>
		            <% } else if (flag == 2) { %>
		                <option value=1>할 일</option>
		                <option value=2 selected>진행 중</option>
		                <option value=3>완료</option>
		            <% } else { %>
		                <option value=1>할 일</option>
		                <option value=2>진행 중</option>
		                <option value=3 selected>완료</option>
		            <% } %>
		            </select>
		        </div>
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_title">제목</label>
		            <input id="subject" type="text" name="subject" value="${dashboard.subject}">
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_content">내용</label>
		            <div class="input_deco_input_content">
		                <textarea id="content" type="text" name="content">${dashboard.content}</textarea>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">시작 날짜</label>
		            <input id="start_date" type="date" name="start_date" value=<%= dashboard.getStartDate() %>>
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_dashboard">종료 날짜</label>
		            <input id="end_date" type="date" name="end_date" value=<%= dashboard.getEndDate() %>>
		        </div>
		        <div>
		            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드 ✔</a>
		        </div>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js"></script>
    <script>
        
    </script>
    <script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
    <script src="/bysl/resources/javascript/dashboard/update.js"></script>
</body>
</html>