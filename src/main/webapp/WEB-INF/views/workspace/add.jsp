<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" type="text/css" href="/bysl/resources/css/workspaceAdd.css">
</head>

<body>
    <form id="join-form" action="" method="post">
        <div class="input-group">
            <label for="workspace-name">Workspace Name</label><br>
            <input id="workspace-name" type="text" name="workspaceName">
        </div>
        <div class="input-group">
            <label for="nick">Nick Name</label><br>
            <input id="nick" type="text" name="nick">
        </div>
        <div class="input-group">
            <label for="color">Your Color</label><br>
            <input id="color" type="color" name="color">
        </div>
        <button id="workspace-btn" type="button" class="btn" onclick="onSubmit()">New Workspace</button>
    </form>
    <script src="/bysl/resources/javascript/workspace/add.js"></script>
</body>

</html>