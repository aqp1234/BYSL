<%@page import="com.kms.bysl.dto.SelfIntroduceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.SoloWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SoloWorkspaceDTO soloWorkspace = (SoloWorkspaceDTO) session.getAttribute("soloWorkspace");
	List<SelfIntroduceDTO> selfIntroduces = (List<SelfIntroduceDTO>) request.getAttribute("selfIntroduces");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SelfIntroduce</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/selfIntroduce.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/soloWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<div id="main_top">
		    <span class="text_deco01">자기소개서 기록</span>
		    <a href="/bysl/solo/selfIntroduce/<%= soloWorkspace.getId() %>/add" class="text_deco02">+</a>
		</div>
		<hr width = "100%" color="#414141" size = "3">
		<table id="table_top">
		    <tr>
		        <td class="text_deco03">NO</td>
		        <td class="text_deco04">DATE</td>
		        <td class="text_deco05">제목</td>
		        <td class="text_deco06">작성자</td>
		    </tr>
		</table>
		<hr width = "100%" color="#414141" size = "2">
		<table id="table_body">
		    <tbody>
		    <% int index = 1; %>
		    <% for (SelfIntroduceDTO selfIntroduce : selfIntroduces) { %>
		        <tr>
		            <td class="text_deco07"><%= index %></td>
		            <td class="text_deco08"><%= selfIntroduce.getCreatedAt() %></td>
		            <td class="text_deco09"><a href="/bysl/solo/selfIntroduce/<%= soloWorkspace.getId() %>/<%= selfIntroduce.getId() %>"><%= selfIntroduce.getSubject() %></a></td>
		            <td class="text_deco10"><%= selfIntroduce.getName() %></td>
		        </tr>
		    <% } %>
		    </tbody>
		</table>
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js"></script>
	<script>
	    const dates = document.getElementsByClassName("text_deco08");
	    for(i = 0; i < dates.length; i++){
	        dates[i].textContent = moment(dates[i].textContent).format('LL');
	    }
	</script>
</body>
</html>