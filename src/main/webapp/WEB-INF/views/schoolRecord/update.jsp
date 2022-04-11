<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.SchoolRecordFileDTO"%>
<%@page import="com.kms.bysl.dto.SchoolRecordDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
    	<div id="main_top">
		    <span class="text_deco01">게시글 수정하기 </span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		
		<div class="container">
		    <form action="" method="post" enctype="multipart/form-data" id="school_record_form">
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_title">제목</label>
		            <input id="subject" type="text" name="subject" value="<%= schoolRecord.getSubject() %>">
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_content">내용</label>
		            <div class="input_deco_input_content">
		                <textarea id="content" type="text" name="content"><%= schoolRecord.getContent() %></textarea>
		            </div>
		        </div>
		        <div> 
		        </div>
		        <div class="form-group">
		            <label for="inputFile" class="text_deco_input_file">첨부 파일 추가</label>
		            <span class="input_deco_input_file"><input multiple="multiple" id="file" type="file" name="files"></span>
		        </div>
		        <div id="fileDelete">
		        	<% if(files.size() > 0){ %>
		            <label for="inputFile" class="text_deco_input_file">첨부 파일</label>
		            <% } %>
		            <% for (SchoolRecordFileDTO file : files) { %>
	                    <div class="outputfile">파일명 : <%= file.getOriginalName() %></div>
	                    <input type="checkbox" id="deletefile" value=<%= file.getId() %> name="deletefile">파일 삭제
		            <% } %>
		        </div>
		            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드</a>
		        </div>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="/bysl/resources/javascript/schoolRecord/update.js"></script>
</body>
</html>