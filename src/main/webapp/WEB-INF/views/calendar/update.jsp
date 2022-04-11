<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.CalendarDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CalendarDTO calendar = (CalendarDTO) request.getAttribute("calendar");
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Calendar</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="/bysl/resources/css/addCalendar.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
</head>
<body>
    <form id="join-form" action="" method="post">
   		<input type="hidden" name="id" value=<%= calendar.getId() %>>
        <div class="input-group">
            <div class="input-what">Subject</div>
            <input id="subject" type="text" name="subject" value=<%= calendar.getSubject() %>>
        </div>
        <div class="input-group">
            <div class="input-what">Content</div>
            <input id="content" type="text" name="content" value=<%= calendar.getContent() %>>
        </div>
        <div class="input-group">
            <div class="input-what">Start Date</div>
            <input id="start_date" type="date" name="start_date" value=<%= calendar.getStartDate() %>>
        </div>
        <div class="input-group">
            <div class="input-what">End Date</div>
            <input id="end_date" type="date" name="end_date" value=<%= calendar.getEndDate() %>>
        </div>
        <button id="calendar-btn" type="button" class="btn">Change calendar</button>
    </form>
	<script src="/bysl/resources/javascript/calendar/update.js"></script>
</body>
</html>