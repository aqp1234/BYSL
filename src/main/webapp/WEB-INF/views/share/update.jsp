<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.ShareDTO"%>
<%@page import="com.kms.bysl.dto.ShareFileDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ShareDTO share = (ShareDTO) request.getAttribute("share");
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
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
    	<div id="main_top">
		    <span class="text_deco01">게시글 수정하기 </span>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		
		<div class="container">
		    <form action="" method="post" enctype="multipart/form-data" id="share_form">
		        <div class="form-group">
		            <label for="inputTitle" class="text_deco_input_title">제목</label>
		            <input id="subject" type="text" name="subject" value="${share.subject}">
		        </div>
		        <div class="form-group">
		            <label for="inputContent" class="text_deco_input_content">내용</label>
		            <div class="input_deco_input_content">
		                <textarea id="content" type="text" name="content">${share.content}</textarea>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputFile" class="text_deco_input_file">첨부 파일 추가</label>
		            <span class="input_deco_input_file"><input multiple="multiple" id="file" type="file" name="files"></span>
		        </div>
		        <div id="fileDelete">
		        	<% if(files.size() > 0){ %>
		            <label for="inputFile" class="text_deco_input_file">첨부 파일</label>
		            <% } %>
		            <%
		            for (ShareFileDTO file : files) {
		            %>
		                <div style="margin-left: 15px; display: flex; font-family: 'Noto Sans KR', sans-serif; color: rgb(65,65,65); margin-bottom: 5px;">
		                    <div class="outputfile">파일명 : <%= file.getOriginalName() %></div>
		                    <input type="checkbox" id="deletefile" value=<%= file.getId() %> name="deletefile" style="margin-left: 10px;">파일 삭제
		                </div>   
		            <% } %>
		        </div>
		            <a class="btn-form-throw" role="button" onclick="submitContents()" href="#">업로드</a>
		        </div>
		    </form>
		</div>
    </section>
    <script type="text/javascript" src="/bysl/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="/bysl/resources/javascript/share/update.js"></script>
</body>
</html>