<%@page import="com.kms.bysl.dto.WorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.PermissionDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<PermissionDTO> permissions = (List<PermissionDTO>) request.getAttribute("permissions");
	WorkspaceDTO workspace = (WorkspaceDTO) session.getAttribute("workspace");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/teamSettings.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<div class="container">
		    <div>
		        <div style="display: flex; align-items: center; justify-content: space-between;">
		            <span class="text_add_team">그룹 추가</span>
		            <div class="submit">
		                <input id="team_submit_button" type="button" value="Add team" onclick="submitForm()">
		            </div>
		        </div>
		        <div><hr size="3" noshade color="FDECB8" style="margin-top: 20px;"/></div>
		        <form action="" method="post" id="team_form">
		            <fieldset>
		                <div class="team_name">
		                    <span class="text_add_team_input">그룹 이름</span>
		                    <input type="text" name="team_name" value="" maxlength="40" required id="team_name">
		                </div>
		                <div class="selector">
		                    <div class="selector-available">
		                        <div class="available_permissions">Available permissions</div>
		                        <select name="permissions_available" id="permissions_from" multiple="" class="filtered" style="height: 268px;">
		                            <% for (PermissionDTO permission : permissions) { %>
		                            <option value="<%= permission.getId() %>"><%= permission.getName() %></option>
		                            <% } %>
		                        </select><a href="#" id="add_all_permissions" class="selector-chooseall active">Choose all ✔</a>
		                    </div>
		                    <ul class="selector-chooser">
		                        <li><a title="Choose" href="#" id="add_permissions" class="selector-add active">▶</a></li>
		                        <li><a title="Remove" href="#" id="remove_permissions" class="selector-remove active">◀</a></li>
		                    </ul>
		                    <div class="selector-chosen">
		                        <div class="chosen_permissions">Chosen permissions</div>
		                        <select name="permissions_chosen" id="permissions_to" multiple style="height: 268px;">
		                            
		                        </select>
		                        <a href="#" id="remove_all_permissions" class="selector-clearall active">Remove all ✔</a>
		                    </div>
		                </div>
		                <div class="help">* 여러개 선택하려면 드래그 하거나 Ctrl을 누르고 클릭하세요. (Mac의 경우 Command)</div>
		            </fieldset>
		        </form>
		    </div>
		</div>
    </section>
    <script src="/bysl/resources/javascript/setting/addTeam.js"></script>
</body>
</html>