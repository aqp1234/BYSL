<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solo Calendar</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="/bysl/resources/css/calendar.css">
<link rel="stylesheet" href="/bysl/resources/css/soloCalendarPopup.css">
<link rel="stylesheet" href="/bysl/resources/css/calendarPopup.css">
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/soloWorkspaceBar.jsp"></jsp:include>
	<section id="main" class="workspace_section">
		<a href='#' class="add" onclick="openAddPage()">일정 추가 ➕</a>
		<div class="calendar-button">
		    <div class="nav">
		        <button id="calendar-prev-btn" class="nav-btn" onclick="goPrevCalendar()">&lt;</button>
		        <div class="this_day">
		            <div class="year-month"></div>
		            <button id="calendar-now-btn" class="nav-btn" onclick="goTodayCalendar()">Today ✔</button>
		        </div>
		        <button id="calendar-next-btn" class="nav-btn" onclick="goNextCalendar()">&gt;</button>
		    </div>
		</div>
		<div class="solo_calendar_dim-layer">
		    <div class="solo_calendar_dimBg"></div>
		    <div id="solo_calendar_layer" class="solo_calendar_pop-layer" style="width: fit-content;">
		        <div class="solo_calendar_pop-container" style="width: fit-content;">
		            <div class="solo_calendar_pop-conts">
		                <div class="btn-c">
		                    <a href="#" class="btn-close">Close ✖</a>
		                </div>
		                <div class="calendar_info_text"><span style="margin-right: 8px;">☁  </span>개인 일정  ☁</div>
		                <table style="margin: 0;">
		                    <colgroup>
		                        <col style="width: max-content">
		                        <col style="width: max-content">
		                    </colgroup>
		                    <tbody class="solo_calendar_tbody">
		                        <input type="hidden" value="" id="solo_calendar_id">
		                        <tr>
		                            <th scope="row">제목</th>
		                            <td><p id="solo_calendar_subject">제목</p></td>
		                        </tr>
		                        <tr>
		                            <th scope="row">내용</th>
		                            <td><p id="solo_calendar_content">내용</p></td>
		                        </tr>
		                        <tr>
		                            <th scope="row">시작 날짜</th>
		                            <td><p id="solo_calendar_start_date">시작 날짜</p></td>
		                        </tr>
		                        <tr>
		                            <th scope="row">끝 날짜</th>
		                            <td><p id="solo_calendar_end_date">끝 날짜</p></td>
		                        </tr>
		                    </tbody>
		                </table>
		                <div class="btn-c" id="onlyowner">
		                    <span style="margin-right: 5px;"></span><a href="#" class="btn-update" onclick="openUpdatePage()">일정 수정</a></span>
		                    <span></span><a href="#" class="btn-delete" onclick="deleteCalendar()">일정 삭제</a></span>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<div id="calendar">
		</div>
		<div id="detail" style="margin-top: 50px; display:none;">
		    <h3 id="detail_h3" style="text-align:center;">~~일의 일정</h3>
		    <div>
		        <div style="background-color: rgb(242, 242, 242); border-radius: 5px;">
		            <table order='1px solid black' id="detail_table">
		                <colgroup>
		                    <col style="width: 20%">
		                    <col style="width: 60%">
		                    <col style="width: 20%">
		                </colgroup>
		                <thead>
		                    <tr id="table_title">
		                        <td>종류</td>
		                        <td>내용</td>
		                        <td>자세히보기</td>
		                    </tr>
		                </thead>
		                <tbody id="detail_tbody">
		                </tbody>
		            </table>
		        </div>
		    </div>
		</div>
	</section>
	<script src="/bysl/resources/javascript/soloCalendar/main.js"></script>
</body>
</html>