<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/multiplication.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Multiplication Table JSTL</title>
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

	<h1>Part2</h1>

	<hr>

	<table border="1">

		<tr>
			<c:forEach var="i" begin="0" end="9">
				<c:if test="${ i eq 0 }">
					<td><c:out value=" " /></td>
				</c:if>
				<c:if test="${ i != 0 }">
					<td class="numbers"><c:out value="${i} " /></td>
				</c:if>
			</c:forEach>

		</tr>

		<c:forEach var="i" begin="1" end="9">
			<tr>
				<td class="numbers"><c:out value="${i}" /></td>

				<c:forEach var="j" begin="1" end="9">
					<td><c:out value="${i*j}" /></td>
				</c:forEach>

			</tr>
		</c:forEach>

	</table>
</body>
</html>
