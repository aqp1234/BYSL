<%@ page language="java" contentType="text/html;"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/bysl/resources/css/findSchool.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <title>FindSchool</title>
</head>
<body>
    <div class="text_find_school">학교 검색</div>
    <div id="search_form">
        <input type="text" name="searchSchoolName" id="searchSchoolName" class="inputSchool">
        <button class="findbtn" onclick="findschoolfunc()">검색</button>
    </div>
    <div class="text_result_school">검색 결과</div>
    <div class="search_table_div">
        <table class="search_table">
            <thead>
                <tr>
                    <th width="25%" scope="col" class="school_name">학교명</th>
                    <th width="60%" scope="col" class="school_address">주소</th>
                    <th width="15%" scope="col" class="school_address">선택</th>
                </tr>
            </thead>
            <tbody id="search_tbody">
            </tbody>
        </table>
    </div>
    <script async src="/bysl/resources/javascript/auth/findSchool.js"></script>
</body>
</html>