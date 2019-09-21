<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/index.css">
<title>Schedule Sharing!!ログイン画面</title>
</head>
<body>

<header><h1>Schedule Sharing!</h1></header>

<div class = "main">
	<div class = "contents">
		<form action ="/scheduleSharing/LoginCtr" method = "post">
		<div class ="contents-top">

				<p>登録している方はこちら</p>
				<p1>ID:<input type = "text" name = "id" maxlength = "8" size = "8"></p1>
				<p>PASSWORD:<input type = "text" name = "password" maxlength = "10" size = "10"></p>
				<p><input type = "submit" value ="ログイン"></p>

		</form>
				<div class = "error">${errorMessage}${resultMessage}</div>
		</div>

		<div class ="contents-bottom">
			<form action ="/yoteiMemo/LoginCtr" method = "get">

				<p>登録していない方はこちら</p>
				<p><input type = "submit" value = "新規登録画面"></p>

			</form>
		</div>
	</div>
</div>

<footer></footer>
</body>
</html>