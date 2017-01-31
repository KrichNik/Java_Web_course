<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <title>Part3</title>
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

	<h1>Part3</h1>

	<hr>

	<div class="main">
		<form method="post" action="/Practice10/controller">
			<input type="hidden" name="command" value="part3" />
				Введите имя:
			<input name="userName" /> <input type="submit" value="Ввод" />
		</form>

		<c:forEach items="${userNames}" var="userName">
			<c:out value="${userName}" />
		</c:forEach>
		<br/>
		<a href="/Practice10/controller?command=part3&isRemove=true">remove</a>
	</div>

	<hr>

	<br/>
	${wrongAction}
	<br/>
	${nullPage}

</body>
</html>
