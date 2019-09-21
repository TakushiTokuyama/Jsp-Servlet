<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import = "schedule.Plan , java.util.ArrayList" %>

    <%
    ArrayList<Plan> SSList = (ArrayList<Plan>) application.getAttribute("SSList");
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/edit.css">
<title>Schedule Sharing 編集</title>
</head>
<body>

<header><h1>Schedule Sharing!!</h1></header>

<div class = "main">

	<div class = "error" >${errorMessage}</div>

	<form action = "/scheduleSharing/UpDataCtr" method = "post">

		<% for(Plan plan : SSList){ %>

			<div class = "schedule">

				<ul>
					<li><input type = "hidden" name = "id" value = "<%= plan.getId() %>"></li>
					<li><%= plan.getName() %></li>
					<li><input type = "date"  name = "planTime" value = "<%= plan.getPlanTime() %>"></li>

					<li><select name="planCategory" >
					    <option ><%= plan.getPlanCategory() %></option>
						<option value = "出張">出張</option>
						<option value = "会議">会議</option>
						<option value = "休憩">休憩</option>
						<option value = "商談">商談</option>
						<option value = "電話">電話</option>
						<option value = "内勤">内勤</option>
						<option value = "外勤">外勤</option>
					</select></li>

					<li><input type = "time" name ="firstTime" value="<%= plan.getFirstTime() %>" min ="06:00" max ="21:00" step ="300"></li>
					<li><input type = "time" name ="lastTime" value ="<%= plan.getLastTime() %>" min ="06:00" max ="21:00" step ="300"></li>
					<li><input type = "text" size = "40" maxlength = "10" name = "planText" value = "<%= plan.getPlanText() %>"></li>
					<li><input type = "submit" value = "編集"></li>
				</ul>
			</div>
		<% } %>
	</form>

	<footer></footer>
</div>
</body>
</html>