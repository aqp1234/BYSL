<%@page import="com.kms.bysl.dto.SoloWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SoloWorkspaceDTO soloWorkspace = (SoloWorkspaceDTO) session.getAttribute("soloWorkspace");
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
		    <span class="text_deco01"> 생활기록부 관리</span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		
		<div class="container">
		    <form action="" method="post" enctype="multipart/form-data" id="school_record_form">
		        <div class="form-group" style="display: flex; flex-direction: column; align-items: flex-start;">
		            <div for="inputTitle" class="text_deco_input_title" style="margin-bottom: 10px;">제목</div>
		            <input id="subject" type="text" name="subject" style="margin-left: 15px; width: 95%;" placeholder="0학년 0학기 생활기록부 ver.01">
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_content">메모</label>
		            <div class="input_deco_input_content">
		                <textarea name="content" id="content"><span></span></textarea>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputFile" class="text_deco_input_file">생활기록부 파일</label>
		            <span class="input_deco_input_file"><input multiple="multiple" id="file" type="file" name="files"></span>
		        </div>
		        <div>
		        </div>
		            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드</a>
		        </div>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="/bysl/resources/javascript/schoolRecord/add.js"></script>
</body>
</html>