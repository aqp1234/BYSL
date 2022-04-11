<%@page import="com.kms.bysl.dto.UserWorkspaceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.DashboardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<DashboardDTO> dashboards = (List<DashboardDTO>) request.getAttribute("dashboards");
	UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/bysl/resources/css/dashboard.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/teamWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
	    <div id="main_top">
	        <span class="text_deco01">DashBoard</span>
	        <a href="/bysl/dashboard/${userWorkspace.workspaceId}/add" class="text_deco02">+</a>
	    </div>
	    <hr width = "100%" color="#414141" size = "3">
	    <table id="table_top">
	        <tr>
	            <td class="text_deco03">할 일</td>
	            <td class="text_deco04">진행 중</td>
	            <td class="text_deco05">완료</td>
	        </tr>
	    </table>
	    <hr width = "100%" color="#414141" size = "2">
	    <table id="table_body">
	        <tbody style="vertical-align: top;">
	          <tr style="padding: 5px;">
	            <td class="text_deco06">
	            </td>
	            <td class="text_deco07">
	            </td>
	            <td class="text_deco08">
	            </td>
	          </tr>
	        </tbody>
	    </table>
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js"></script>
    <script>
    	<% for (DashboardDTO dashboard : dashboards) {%>
    		<% if (dashboard.getFlag() == 1){ %>
   				var html = "";
   				html += '<div class="flag01" style="background-color: <%= dashboard.getManagerColor() %>;"><div>';
   				html += '<a href="/bysl/dashboard/<%= userWorkspace.getWorkspaceId() %>/<%= dashboard.getId() %>" class="flag_deco01"><%= dashboard.getSubject() %></a>';
   				html += '<span class="flag_deco07">시작일 :<span class="flag_deco06"><%= dashboard.getStartDate() %></span></span></div>';
   				html += '<hr width = "99%" color="#616161" style="float: left; height: 0.3px;">';
   				html += '<textarea readonly class="flag_deco04" data-content="<%= dashboard.getContent() %>"></textarea>';
   				html += '<div class="flag_deco05">담당자 : <%= dashboard.getManagerNick() %></div></div>'
                
   				$(".text_deco06").append(html);
    		<% } else if (dashboard.getFlag() == 2) {%>
    			var html = "";
    			html += '<div class="flag02" style="background-color: <%= dashboard.getManagerColor() %>;"><div>';
    			html += '<a href="/bysl/dashboard/<%= userWorkspace.getWorkspaceId() %>/<%= dashboard.getId() %>" class="flag_deco02"><%= dashboard.getSubject() %></a>';
	            html += '<span class="flag_deco07">시작일 :<span class="flag_deco06"><%= dashboard.getStartDate() %></span></span>';
	            html += '<span class="flag_deco07">종료일 :<span class="flag_deco06"><%= dashboard.getEndDate() %></span></span></div>';
	            html += '<hr width = "99%" color="#616161" style="float: left; height: 0.3px;">';
	            html += '<textarea readonly class="flag_deco04" data-content="<%= dashboard.getContent() %>"></textarea>';
	            html += '<div class="flag_deco05">담당자 : <%= dashboard.getManagerNick() %></div></div>';
	                
	            $(".text_deco07").append(html);
    		<% } else if (dashboard.getFlag() == 3) { %>
    			var html = "";
    			html += '<div class="flag03" style="background-color:rgb(241, 241, 241);"><div>';
    			html += '<a href="/bysl/dashboard/<%= userWorkspace.getWorkspaceId() %>/<%= dashboard.getId() %>" class="flag_deco03"><%= dashboard.getSubject() %></a>';
    			html += '<span class="flag_deco07">종료일 :<span class="flag_deco06"><%= dashboard.getEndDate() %></span></span></div>';
                html += '<hr width = "99%" color="#616161" style="float: left; height: 0.3px;">';
                html += '<textarea readonly class="flag_deco04" data-content="<%= dashboard.getContent() %>"></textarea>';
                html += '<div class="flag_deco05">담당자 : <%= dashboard.getManagerNick() %></div></div>';
                
                $(".text_deco08").append(html);
    		<% } %>
    	<% } %>
    </script>
    <script>
        const dates = document.getElementsByClassName("flag_deco06");
        for(i = 0; i < dates.length; i++){
            dates[i].textContent = moment(dates[i].textContent).format('LL');
        }
    </script>
    <script>
        const contents = document.getElementsByClassName("flag_deco04");
        for(i = 0; i < contents.length; i++){
            contents[i].innerHTML = contents[i].getAttribute("data-content").replace(/<br>/ig, "\n").replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
        }
    </script>
</body>
</html>