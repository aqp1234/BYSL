<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.StudentBookDTO"%>
<%@page import="com.kms.bysl.dto.SoloWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SoloWorkspaceDTO soloWorkspace = (SoloWorkspaceDTO) session.getAttribute("soloWorkspace");
	List<StudentBookDTO> studentBooks = (List<StudentBookDTO>) request.getAttribute("studentBooks");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StudentBook</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/studentBook.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/soloWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<div id="main_top">
	        <span class="text_deco01">학생부 활동 기록</span>
	        <a href="/bysl/solo/studentbook/<%= soloWorkspace.getId() %>/add" class="text_deco02">+</a>
	    </div>
	    <hr width = "100%" color="#414141" size = "3">
	    <table id="table_top">
	        <tr>
	            <td class="text_deco03">NO</td>
	            <td class="text_deco04">제목</td>
	            <td class="text_deco05">작성자</td>
	            <td class="text_deco06">DATE</td>
	        </tr>
	    </table>
	    <hr width = "100%" color="#414141" size = "2">
	    <table id="table_body">
	        <tbody>
	        <% int index = 1; %>
	        <% for (StudentBookDTO studentBook : studentBooks) { %>
	          <tr>
	            <td class="text_deco07"><%= index %></td>
	            <td class="text_deco08"><a href="/bysl/solo/studentbook/<%= soloWorkspace.getId() %>/<%= studentBook.getId() %>"><%= studentBook.getSubject() %></a></td>
	            <td class="text_deco09"><%= studentBook.getName() %></td>
	            <td class="text_deco10"><%= studentBook.getCreatedAt() %></td>
	          </tr>
	        <% } %>
	        <% index++; %>
	        </tbody>
	    </table>
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js"></script>
	<script>
	    const dates = document.getElementsByClassName("text_deco10");
	    for(i = 0; i < dates.length; i++){
	        dates[i].textContent = moment(dates[i].textContent).format('LL');
	    }
	</script>
</body>
</html>