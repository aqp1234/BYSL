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
<title>SelfIntroduce</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/selfIntroduce.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/soloWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
	    <div id="main_top">
		    <span class="text_deco01"> 자기소개서 작성</span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		
		<div class="container">
		    <form action="" method="post" id="self_introduce_form">
		        <div class="form-group" style="display: flex;">
		            <div for="inputTitle" class="text_deco_input_title">질문</div>
		            <input id="subject" type="text" name="subject" placeholder="00대학교 00번 문항 : 질문 내용">
		        </div>
		        <div class="form-group">
		            <div for="inputContent" class="text_deco_input_content" style="display: flex; justify-content: space-between; width: 96%;">
		                <div>본문</div> 
		                <div>(<span id="textlength">0 자 / 0 Byte</span>)</div>
		            </div>
		            <div class="input_deco_input_content">
		                <textarea name="content" id="content"><span></span></textarea>
		            </div>
		        </div>
		        <div>
		            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드 ✔</a>
		        </div>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
    <script src="/bysl/resources/javascript/selfIntroduce/add.js"></script>
</body>
</html>