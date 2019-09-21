<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import = "person.User , schedule.Plan , java.util.ArrayList , java.text.SimpleDateFormat ,java.util.Calendar , java.text.SimpleDateFormat" %>

    <%
    ArrayList<Plan> planList = (ArrayList<Plan>) application.getAttribute("planList");
    ArrayList<User> loginUserList = (ArrayList<User>) session.getAttribute("loginUserList");
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Schedule Sharing!!</title>
<link rel="stylesheet" type="text/css" href="./css/main.css">
</head>
<body>

<header><h1>Schedule Sharing!!</h1></header>

<div class = "main">

		<% SimpleDateFormat currentTime = new SimpleDateFormat("h/m"); %>
		<% currentTime.applyPattern("HH:mm"); %>
		<% Calendar cal = Calendar.getInstance(); %>
		<% SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd (E)"); %>

	<p>
		ログインユーザー:<span><%= loginUserList.get(0).getName() %></span>
		現在の日付:<span><% out.println(today.format(cal.getTime()) +" "+ currentTime.format(cal.getTime())); %></span>
	</p>

<div class = "posting">
	<form action = "/scheduleSharing/MainCtr" method = "post">

		<input type = "date"  name = "planTime">

		<select name="planCategory" >
			<option value = "出張">出張</option>
			<option value = "会議">会議</option>
			<option value = "休憩">休憩</option>
			<option value = "商談">商談</option>
			<option value = "電話">電話</option>
			<option value = "外回り">外回り</option>
		</select>

		<input type = "time" name ="firstTime" min ="06:00" max ="21:00" step ="300">
		<input type = "time" name ="lastTime" min ="06:00" max ="21:00" step ="300">
		<input type = "text" size = "40" maxlength = "10" name = "planText" >
		<input type = "submit" value = "投稿">

	</form>


<div class = "sort">
	<form action ="/scheduleSharing/DeliteSortCtr" method ="post">

		<select name="sort">
		<option value = "category">カテゴリー</option>
		<option value = "name">名前</option>
		<option value = "deadLine">期限</option>
		</select>

		<input type="submit" value="検索">

	</form>
  </div>
</div>
<br>

<div class = "error" >${errorMessage}</div>

<% for(Plan plan : planList){ %>

	<div class = "schedule">

		<ul>
			<li><%= plan.getName() %></li>
			<li><%= plan.getPlanTime() %></li>
			<li><%= plan.getPlanCategory() %></li>
			<li><%= plan.getFirstTime() %>-<%= plan.getLastTime() %></li>
			<li><%= plan.getPlanText() %></li>
			<li><a href ="/scheduleSharing/UpDataCtr?number=<%= String.valueOf(plan.getNumber())%>&id=<%= String.valueOf(plan.getId())%>">編集</a></li>
			<li><a href ="/scheduleSharing/DeliteSortCtr?number=<%= String.valueOf(plan.getNumber())%>&id=<%= String.valueOf(plan.getId())%>">削除</a></li>
		</ul>

	</div>
<% } %>

<footer><a href="/scheduleSharing/index.jsp">ログアウト</a></footer>

</div>
</body>
</html>