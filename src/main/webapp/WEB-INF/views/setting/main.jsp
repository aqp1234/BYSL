<%@page import="com.kms.bysl.dto.PermissionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kms.bysl.dto.InviteDTO"%>
<%@page import="com.kms.bysl.dto.TeamPermissionDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.TeamDTO"%>
<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.MemberDTO"%>
<%@page import="com.kms.bysl.dto.WorkspaceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	WorkspaceDTO workspace = (WorkspaceDTO) session.getAttribute("workspace");
	MemberDTO member = (MemberDTO) session.getAttribute("member");
	List<PermissionDTO> myTeamPermissions = (List<PermissionDTO>) session.getAttribute("myTeamPermissions");
	List<UserWorkspaceDTO> userWorkspaces = (List<UserWorkspaceDTO>) request.getAttribute("userWorkspaces");
	List<TeamDTO> teams = (List<TeamDTO>) request.getAttribute("teams");
	List<InviteDTO> invites = (List<InviteDTO>) request.getAttribute("invites");
	List<Integer> myTeamPermissionIds = new ArrayList<Integer>();
	for(PermissionDTO myTeamPermission : myTeamPermissions){
		myTeamPermissionIds.add(myTeamPermission.getId());
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting</title>
</head>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/settings.css">
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/teamSettings.css">
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<div id="settings_body">
		    <div id="select_user_permission_team">
		        <span class="text_deco01"><span>[ <%= workspace.getWorkspaceName() %> ]</span> 내부 사용자 권한 설정</span>
		        <div readonly id="detail_body">
		            <table id="select_user_permission_team_table">
		                <tr style="text-align: center; font-size: 16px; background-color: #e7e7e7; font-weight: bold; color: rgb(66, 65, 65);">
		                    <% if (workspace.getOwnerId() == member.getId()) { %>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 25%;">이메일</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 22.5%;">사용자 이름</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 22.5%;">권한그룹</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 15%;">설정</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 15%;">사용자 추방</td>
		                    <% } else { %>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 30%;">이메일</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 25%;">사용자 이름</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 25%;">권한그룹</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 20%;">설정</td>
		                    <% } %>
		                </tr>
		                <% for (UserWorkspaceDTO userWorkspace : userWorkspaces) { %>
		                    <tr>
		                        <td><span class="user_email"><%= userWorkspace.getUserEmail() %></span></td>
		                        <td><span class="user_name"><%= userWorkspace.getNick() %></span></td>
		                        <td style="text-align: center;">
		                            <% if (workspace.getOwnerId() == userWorkspace.getUserId()) { %>
		                            <select class="user_select" disabled>
		                            <% } else { %>
		                            <select class="user_select">
		                            <% } %>
		                                <% for (TeamDTO team : teams) { %>
		                                    <% if (team.getId() == userWorkspace.getTeamId()) { %>
		                                    <option value="<%= team.getId() %>" selected><%= team.getName() %></option>
		                                    <% } else { %>
		                                    <option value="<%= team.getId() %>"><%= team.getName() %></option>
		                                    <% } %>
		                                <% } %>
		                            </select>
		                        </td>
		                        <td style="text-align: center;">
		                            <% if (myTeamPermissionIds.contains(25)) { %>
		                            	<% if(userWorkspace.getUserId() == workspace.getOwnerId()) { %>
		                            	<button type="button" class="set_team" disabled>그룹 설정</button>
		                            	<% } else { %>
		                            	<button type="button" class="set_team" onclick="set_team(this)" data-user_id="<%= userWorkspace.getUserId() %>">그룹 설정</button>
		                            	<% } %>
		                            <% } else { %>
		                            <button type="button" class="set_team" disabled>그룹 설정</button>
		                            <% } %>
		                        </td>
		                        <td style="text-align: center;">
		                            <% if (workspace.getOwnerId() == member.getId()) { %>
		                            	<% if(userWorkspace.getUserId() == workspace.getOwnerId()) { %>
		                            	<button type="button" class="delete_user"disabled>워크스페이스 방장은 추방할 수 없습니다.</button>
		                            	<% } else { %>
		                            	<button type="button" class="delete_user" onclick="delete_user(<%= userWorkspace.getUserId() %>)">사용자 추방</button>
		                            	<% } %>
		                            <% } %>
		                        </td>
		                    </tr>
		                <% } %>
		            </table>
		        </div>
		    </div>
		    <div id="make_user_permission_team">
		        <div class="text_deco01">
		            <span style="margin-right: 10px;">권한 그룹</span>
		            <% if (myTeamPermissionIds.contains(21)) { %>
		                <a href="/bysl/setting/<%= workspace.getId() %>/addTeam" class="text_deco02" style="text-decoration: none; color: rgb(66, 65, 65);">+</a>
		            <% } %>
		        </div>
		        <div readonly id="detail_body">
		            <table id="make_user_permission_team_list_table">
		                <tr style="text-align: center; font-size: 16px; background-color: #e7e7e7; font-weight: bold; color: rgb(66, 65, 65);">
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 30%;">그룹명</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 70%;">권한 정보</td>
		                </tr>
		                <% for (TeamDTO team : teams) { %>
		                <tr>
		                    <td><span class="team_name"><%= team.getName() %></span></td>
		                    <td>
		                        <% if (team.isAdmin()) { %>
		                        <span style="margin-left: 10px;">All Permission Access (Default) / 모든 기능 접근 허용(기본값)</span>
		                        <% } else if (team.isGuest()) { %>
		                            <span style="margin-left: 10px;">Default team for invited People</small>
		                            <% if (myTeamPermissionIds.contains(23)) { %>
		                            <a href="/bysl/setting/<%= workspace.getId() %>/team/<%= team.getId() %>">그룹 권한 설정</a>
		                            <% } %>
		                        <% } else { %>
		                            <% if (myTeamPermissionIds.contains(23)) { %>
		                            <a style="margin-left: 10px;" href="/bysl/setting/<%= workspace.getId() %>/team/<%= team.getId() %>">그룹 권한 설정</a>
		                            <% } %>
		                        <% } %>
		                    </td>
		                </tr>
		                <% } %>
		            </table>
		        </div>
		    </div>
		    
		    <div id="invite_info">
		        <span class="text_deco01">초대 정보</span>
		        <div readonly id="detail_body">
		            <table style="border-spacing: 5px 10px; width: 100%;">
		                <tr style="text-align: center; font-size: 16px; background-color: #e7e7e7; font-weight: bold; color: rgb(66, 65, 65);">
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 30%;">초대 받는 이메일</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 20%;">초대 보낸 사용자</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 40%;">초대 보낸 시각</td>
		                    <td style="padding: 5px; border-radius: 5px 5px 0 0; width: 10%;">초대 취소</td>
		                </tr>
		                <% for (InviteDTO invite : invites) { %>
		                <tr>
		                    <td><span class="invite_email"><%= invite.getEmail() %></span></td>
		                    <td><span class="invite_user_name"><%= invite.getSender() %></span></td>
		                    <td><span class="invite_createdAt"><%= invite.getCreatedAt() %></span></td>
		                    <td style="text-align: center;">
		                    	<% if (invite.isAccepted()) { %>
		                    	<span class="invite_accepted">수락된 초대</span>
		                        <% } else if (myTeamPermissionIds.contains(20)) { %>
		                        <button type="button" class="invite_reject" onclick="invite_reject(<%= invite.getId() %>)" style="text-align: center;">Reject</button>
		                        <% } else {%>
		                        <button type="button" class="invite_reject" style="text-align: center;" disabled>권한 없음</button>
		                        <% } %>
		                    </td>
		                </tr>
		                <% } %>
		            </table>
		        </div>
		    </div>
		    
		    <div id="my_permissions_list">
		        <span class="text_deco01">내 권한 정보</span>
		        <div readonly id="detail_body" style="margin-top: -5px;">
		            <ol>
		                <% for (PermissionDTO permission : myTeamPermissions) { %>
		                <li style="margin-bottom: 5px;">
		                    <div class="permission_list">
		                        <span class="permission_name"><%= permission.getName() %></span>
		                    </div>
		                </li>
		                <% } %>
		            </ol>
		        </div>
		    </div>
		</div>
    </section>
	<script src="/bysl/resources/javascript/setting/main.js"></script>
</body>
</html>