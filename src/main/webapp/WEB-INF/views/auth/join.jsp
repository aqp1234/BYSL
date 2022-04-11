<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Join</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" type="text/css" href="/bysl/resources/css/join.css">
</head>
<body>
    <div id="logo"><a href='login'><img src="/bysl/resources/img/logo3.png" alt="logo"></a></div>
    <div class="timeline">
        <form id="join-form" action="" method="post" enctype="application/json">
            <input type="hidden" value="" name="certNo" id="certNo">
            <input type="hidden" value="true" name="isAuth" id="isAuth"> <!-- 테스트 할때마다 인증 받기 귀찮아서 임의적으로 value=false > true로 변경 -->
            <div class="input-group">
                <label for="join-firstname">Name</label><br>
                <input class="join-firstname" type="name" name="name">
            </div>
            <div class="input-group">
                <label for="join-email">Email</label><br>
                <input class="join-email" type="email" name="email" id="input-email">
                <input type="button" value="SEND" class="join-email-number" onclick="sendEmail()">
            </div>
            <div class="input-group">
                <label for="join-email-number">Email Authentication Number</label><br>
                <input class="join-email-authentication-number" type="text" name="email-number" id="input-email-number">
                <input type="button" value="CHECK" class="join-email-number" onclick="checkCertNo()">
            </div>
            <div class="input-group">
                <label for="join-password">Password</label><br>
                <input class="join-password" type="password" name="password">
            </div>
            <div class="input-group">
                <label for="join-phone">Phone</label><br>
                <input class="join-phone" type="text" name="phone">
            </div>
            <div class="input-group">
                <label for="join-school">School</label><br>
                <input class="join-school" type="text" name="schoolName" id="school_name" readonly onclick="openchild()">
            </div>
            <div class="input-group">
                <input class="join-school" type="hidden" name="locationName" id="location_name" value="">
            </div>
            <div>
                <button id="join-btn" type="button" class="btn" onclick="onSubmit()">Join</button> <!-- 테스트 할때마다 인증 받기 귀찮아서 임의적으로 disabled 삭제 -->
            </div>
        </form>
    </div>
    <script src="/bysl/resources/javascript/auth/join.js"></script>
</body>

</html>