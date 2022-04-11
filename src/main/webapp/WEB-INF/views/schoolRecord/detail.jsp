<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.SchoolRecordFileDTO"%>
<%@page import="com.kms.bysl.dto.SchoolRecordDTO"%>
<%@page import="com.kms.bysl.dto.SoloWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SoloWorkspaceDTO soloWorkspace = (SoloWorkspaceDTO) session.getAttribute("soloWorkspace");
	SchoolRecordDTO schoolRecord = (SchoolRecordDTO) request.getAttribute("schoolRecord");
	List<SchoolRecordFileDTO> files = (List<SchoolRecordFileDTO>) request.getAttribute("files");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SchoolRecord</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/soloSchoolRecord.css">  
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/share.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/soloWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<div style="margin-left: 15px;">
		    <div style="display: flex; justify-content: space-between; align-items: center;">
		        <a href='/bysl/solo/schoolRecord/<%= soloWorkspace.getId() %>' id='exit-btn'>◀</a>
		        <div>
		            <span class="text_deco13"><a href='/bysl/solo/schoolRecord/<%= soloWorkspace.getId() %>/update/<%= schoolRecord.getId() %>' class="text_deco14">글 수정</a></span>
		            <span class="text_deco13"><a href='#' class="text_deco14" id="delete-btn">글 삭제</a></span>
		        </div>
		        
		    </div>
		    <div id="detailTop">
		            <div><%= schoolRecord.getSubject() %></div>
		    </div>
		    <!-- 글이 길어질 경우 스크롤 처리 해놓음 -->
		    <div readonly id="detail_body" style="height: fit-content;"><%= schoolRecord.getContent() %></div>
		    <hr size="3" noshade="" color="FDECB8" style="margin-top: 20px;">
		    <div class="detail_download" style="margin-bottom: 10px;">
		        <span class="text_deco11" id="text_deco11">파일 다운로드</span>
		        <% if (files.size() != 0) { %> 
		            <% for (SchoolRecordFileDTO file : files) { %>
	                    <a class="text_deco12" href="/bysl/solo/schoolRecord/download/<%= file.getId() %>"><%= file.getOriginalName() %></a>
		            <% } %>
		        <% } else { %>
		            <span class="text_deco12">파일 없음</span>
		        <% } %>
		    </div>
		</div>
    </section>
    <script src="/bysl/resources/javascript/schoolRecord/detail.js"></script>
</body>
</html>