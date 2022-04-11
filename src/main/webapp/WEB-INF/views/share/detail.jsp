<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.ShareFileDTO"%>
<%@page import="com.kms.bysl.dto.ShareDTO"%>
<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
	ShareDTO share = (ShareDTO) request.getAttribute("share");
	List<ShareFileDTO> files = (List<ShareFileDTO>) request.getAttribute("files");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Share</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/share.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
	    <a href='/bysl/share/${userWorkspace.workspaceId}' id='exit-btn'>◀</a>
		<span class="text_deco13"><a href='/bysl/share/${userWorkspace.workspaceId}/update/${share.id}' class="text_deco14">글 수정</a></span>
		<span class="text_deco13"><a href='#' class="text_deco14" id="delete-btn">글 삭제</a></span>
		
		<div id="detail_top">
		    <hr class="line_deco01" width = "100%" color="#414141">
		        <div class="detail_title">${share.subject}</div>
		    <hr class="line_deco01" width = "100%" color="#414141">
		</div>
		<div readonly id="detail_body">${share.content}</div>
		<hr class="line_deco01" width = "100%" color="#414141">
		<div class="detail_download">
		    <span class="text_deco11" id="text_deco11">파일 다운로드</span>
		    <%
		    int index = 0;
		    %>
		    <%
		    if (files.size() != 0) {
		    %>
		        <%
		        for (ShareFileDTO file : files) {
		        %>
	                <a class="text_deco12" href="/bysl/share/download/<%= file.getId() %>"><%= file.getOriginalName() %></a>
		        <% } %>
		    <% } else {%>
		        <span class="text_deco12">파일 없음</span>
		    <% } %>
		</div>
    </section>
    <script src="/bysl/resources/javascript/share/detail.js"></script>
</body>
</html>