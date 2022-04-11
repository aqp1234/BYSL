<%@page import="com.kms.bysl.dto.SoloCalendarDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SoloCalendarDTO soloCalendar = (SoloCalendarDTO) request.getAttribute("soloCalendar");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SoloCalendar</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="/bysl/resources/css/addCalendar.css">
<link rel="stylesheet" href="/bysl/resources/css/addSoloCalendar.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<form id="join-form" action="" method="post">
        <div class="input-group">
            <div class="input-what">Subject</div> <!-- calendar 제목 입력 -->
            <input id="subject" type="text" name="subject" value=<%= soloCalendar.getSubject() %>>
        </div>
        <div class="input-group">
            <div class="input-what">Content</div> <!-- 세부내용 입력 -->
            <input id="content" type="text" name="content" value=<%= soloCalendar.getContent() %>>
        </div>
        <div class="input-group">
            <div class="input-what">Start Date</div> <!-- 시작 날짜 입력 -->
            <input id="start_date" type="date" name="start_date" value=<%= soloCalendar.getStartDate() %>>
        </div>
        <div class="input-group">
            <div class="input-what">End Date</div> <!-- 끝 날짜 입력 -->
            <input id="end_date" type="date" name="end_date" value=<%= soloCalendar.getEndDate() %>>
        </div>
        <button id="calendar-btn" type="button" class="btn">Change Solo calendar</button>
    </form>
    <script src="/bysl/resources/javascript/soloCalendar/update.js"></script>
</body>
</html>