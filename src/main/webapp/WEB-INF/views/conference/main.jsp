<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.ConferenceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ConferenceDTO> conferences = (List<ConferenceDTO>) request.getAttribute("conferences");
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conference</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/conference.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
	    <div id="main_top">
	        <span class="text_deco01">회의록</span>
	        <a href="/bysl/conference/${userWorkspace.workspaceId}/add" class="text_deco02">+</a>
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
	        <% for (ConferenceDTO conference : conferences) { %>
	          <tr>
	            <td class="text_deco07"><%= index %></td>
	            <td class="text_deco08"><a href="/bysl/conference/<%= userWorkspace.getWorkspaceId() %>/<%= conference.getId() %>"><%= conference.getSubject() %></a></td>
	            <% if(conference.getOwnerId() == null){ %>
	            <td class="text_deco09">탈퇴한 사용자</td>
	            <% }else { %>
	            <td class="text_deco09"><%= conference.getNick() %></td>
	            <% } %>
	            <td class="text_deco10"><%= conference.getCreatedAt() %></td>
	          </tr>
          	<% index++; %>
	        <% } %>
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