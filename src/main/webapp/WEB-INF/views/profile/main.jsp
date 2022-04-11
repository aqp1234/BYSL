<%@page import="com.kms.bysl.dto.WorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	WorkspaceDTO workspace = (WorkspaceDTO) session.getAttribute("workspace");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Profile</title>
    <script src='https://unpkg.com/axios/dist/axios.min.js'></script>
    <link rel="stylesheet" href="/bysl/resources/css/changeProfile.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
    <form id="change-form" action="" method="post">
    	<input hidden="hidden" >
        <div class="input-group">
            <label for="nick" class="text">Nick Name</label><br> <!-- 닉네임 입력 -->
            <input id="nick" type="text" name="nick" value="${userWorkspace.nick}"></div>
        </div>
        <div class="input-group">
            <label for="color" class="text">Your Color</label><br> <!-- 워크스페이스 내부 개인 고유 색상 입력 -->
            <input id="color" type="color" name="color" value="${userWorkspace.color}"></div>
        </div>
        <button id="profile-btn" type="button" class="btn">Change Profile</button>
    </form>
    <script src="/bysl/resources/javascript/profile/main.js"></script>
</body>
</html>