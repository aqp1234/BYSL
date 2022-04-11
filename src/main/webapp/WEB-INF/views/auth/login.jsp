<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <link rel="stylesheet" type="text/css" href="/bysl/resources/css/login.css">
    </head>
    <body>
	    <div id="logo"><img src="/bysl/resources/img/logo3.png" alt="logo"></div>
	    <form id="login-form" action="" method="post" enctype="application/x-www-form-urlencoded">
	        <div class="input-group">
	            <label for="email">Email Address</label><br>
	            <input class="email" type="email" name="email" required autofocus>
	        </div>
	        <div class="input-group">
	            <label for="password">Password</label><br>
	            <input class="password" type="password" name="password" required>
	        </div>
	        <button class="login" type="submit" class="btn">Login</button><br>
	        <div>
	            <div class="question">아직 회원이 아니신가요? </div>
	            <a class="join" href="/bysl/member/memJoin" class="btn">회원가입</a>
	        </div>  
	    </form>
    </body>
</html>
