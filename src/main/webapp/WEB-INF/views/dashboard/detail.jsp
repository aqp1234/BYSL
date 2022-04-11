<%@page import="com.kms.bysl.dto.DashboardDTO"%>
<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
	DashboardDTO dashboard = (DashboardDTO) request.getAttribute("dashboard");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/dashboard.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet"> <!-- 한국어 글꼴 -->
<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet"> <!-- 영어 글꼴 -->
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<a href='/bysl/dashboard/${userWorkspace.workspaceId}' id='exit-btn'>◀</a>
		<span class="detail_text_button"><a href='/bysl/dashboard/${userWorkspace.workspaceId}/update/${dashboard.id}' class="text_deco10">글 수정</a></span>
		<span class="detail_text_button"><a href='#' id="delete-btn" class="text_deco10">글 삭제</a></span>
	    <div id="detail_top">
		    <hr class="line_deco01" width = "100%" color="#414141">
		        <div class="detail_title">${dashboard.subject}</div>
		    <hr class="line_deco01" width = "100%" color="#414141">
		    <div id="detail_date">
		        <table style="width: 100%;">
		            <tr>
		                <td class="detail_date_deco01">시작일 : <span class="detail_date_deco02">${dashboard.startDate}</span></td>
		                <td class="detail_date_deco01">종료일 : <span class="detail_date_deco02">${dashboard.endDate}</span></td>
		                <td class="detail_date_deco03">대시보드 작성자 : <span class="detail_date_deco04">${dashboard.nick}</span></td>
		                <td class="detail_date_deco05">담당자 : <span class="detail_date_deco06">${dashboard.managerNick}</span></td>
		            </tr>
		        </table>
		    </div>
		    <hr class="line_deco01" width = "100%" color="#414141">
		</div>
		<div readonly id="detail_body">${dashboard.content}</div>
		<hr class="line_deco01" width = "100%" color="#414141">
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js"></script>
	<script>
	    const dates = document.getElementsByClassName("detail_date_deco02");
	    for(i = 0; i < dates.length; i++){
	        dates[i].textContent = moment(dates[i].textContent).format('LL');
	    }
	</script>
	<script src="/bysl/resources/javascript/dashboard/detail.js"></script>
</body>
</html>