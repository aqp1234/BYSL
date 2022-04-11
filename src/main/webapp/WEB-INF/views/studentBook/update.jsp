<%@page import="com.kms.bysl.dto.StudentBookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	StudentBookDTO studentBook = (StudentBookDTO) request.getAttribute("studentBook");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StudentBook</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/studentBook.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/soloWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<div id="main_top">
		    <span class="text_deco01">학생부 활동 수정하기</span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		
		<div class="container">
		    <form action="" method="post" id="studentbook_form">
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_manager">양식 선택</label>
		            <select name="layout_id" id="layout_id" disabled>
		                <option value="none" optionid="0" id="option0">양식 로딩중</option>
		                <option value="thesis" optionid="1">소논문 기록</option>
		                <option value="awards" optionid="2">수상 기록</option>
		                <option value="bookreading" optionid="3">독서 기록</option>
		            </select>
		        </div>
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_title">제목</label>
		            <input id="subject" type="text" name="subject" value=<%= studentBook.getSubject() %>>
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_content">내용</label>
		            <div class="input_deco_input_content">
		                <textarea name="content" id="content"><%= studentBook.getContent() %></textarea>
		            </div>
		        </div>
		        <div>
		            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드 ✔</a>
		        </div>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="/bysl/resources/javascript/studentBook/layout.js"></script>
	<script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="/bysl/resources/javascript/studentBook/update.js"></script>
</body>
</html>