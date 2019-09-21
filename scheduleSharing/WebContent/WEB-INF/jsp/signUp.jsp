<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="./css/signUp.css">
</head>
<body>

<header><h1>Schedule Sharing!</h1></header>

<div class = "main">
	<div class = "contents-signup">
		<form action = "/scheduleSharing/SignUpCtr" method = "post">
			<p>新規登録を行います</p>
			<p>※ID:半角英数 6-8桁</p>
			<p>※PASSWORD:半角英数 8-10桁</p>
			<p>※USERNAME:名前 10文字以内</p>
			<p1>ID:<input type = "text" name = "newId" maxlength = "8" size = "8"></p1>
			<p>PASSWORD:<input type = "password" name = "newPassword" maxlength= "10" size = "10"></p>
			<p2>NAME:<input type = "text" name = "name" maxlength = "8" size = "10"></p2>

<div class = "error">${errorMessage}</div>

			<p><input type="submit" value = "新規登録"></p>

		</form>

			<p><a href = "/yoteiMemo/index.jsp">ログイン画面へ</a></p>
	</div>
</div>

<footer></footer>
</body>
</html>