<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.SoloShareDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<SoloShareDTO> soloShares = (List<SoloShareDTO>) request.getAttribute("soloShares");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SoloShare</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="/bysl/resources/css/staffInvitePopup.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
</head>
<body>
    <form id="join-form" action="" method="post">
        <div class="input-group">
            <div for="staff_email" id="invite_email_text">초대 받을 관계자 이메일</div>
            <div><input id="staff_email" type="email" name="managerEmail"></div>
        </div>
        <input type="hidden" name="url" id="url">
        <input type="hidden" name="type" id="type">
        <div style="text-align: center; margin-top: 10px;"><button id="new_staff_btn" type="button" class="btn" onclick="staffinvitesubmit()">New Staff Invite ✔</button></div>
    </form>
    <hr/>
    <div class="container">
        <div class="text_invite_table">초대 목록</div>
        <table width="95%" style="border-spacing: 15px;">
            <thead>
                <tr style="font-size: 15px;">
                    <th width="25%" scope="col" class="staff_name">이름</th>
                    <th width="60%" scope="col" class="staff_email">이메일</th>
                    <th width="15%" scope="col" class="staff_delete">삭제</th>
                </tr>
            </thead>
            <tbody id="search_tbody">
                <% for (SoloShareDTO soloShare : soloShares) { %>
                <tr>
                    <td class='staff_name'><% if(soloShare.getManagerName() != null) { %> <%= soloShare.getManagerName() %> <% } else { %> 미가입자 <% } %></td>
                    <td class='staff_email'><%= soloShare.getManagerEmail() %></td>
                    <td data-id="<%= soloShare.getId() %>" onclick='deleteStaffInvite(<%= soloShare.getId() %>)'>
                        <input class='choose' type='button' value='삭제'>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="/bysl/resources/javascript/soloShare/main.js"></script>
</body>
</html>