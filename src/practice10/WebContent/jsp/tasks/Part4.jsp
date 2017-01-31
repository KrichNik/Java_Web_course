<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Part4</title>
</head>
<body>
	<div class="header">
		<div class="links">
			<a href="/Practice10/jsp/tasks/Part1.jsp">Part1</a>
			<a href="/Practice10/jsp/tasks/Part2.jsp">Part2</a>
			<a href="/Practice10/jsp/tasks/Part3.jsp">Part3</a>
			<a href="/Practice10/jsp/tasks/Part4.jsp">Part4</a>
		</div>

		<div class="logMessage">
			<p>You are logged as ${role} ${login}. ${name}, hello!</p>
			<a href="/Practice10/controller?command=logout">Logout</a>
		</div>
	</div>

	<h1>Part4</h1>

	<hr>

	<form method="post" action="/Practice10/controller">
		<input type="hidden" name="command" value="part4" />
			Введите новое имя:
		<input name="newName" /> <input type="submit" value="Ввод" />
	</form>

	<br/>
	${wrongAction}
	<br/>
	${nullPage}

</body>
</html>
