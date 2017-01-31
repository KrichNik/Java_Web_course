<%@ page import="java.util.*"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="SportServlet">
		Введите имя: <input name="userName" />
		<p>Проголосуйте:</p>
		<select name="listSport">
			<%
				List<String> listSport = (List<String>) request.getAttribute("listSport");
				for (String sport : listSport) {
			%>
			<option value="<%=sport%>"><%=sport%></option>
			<%
				}
			%>
		</select> <input type="submit" value="Проголосовать" />
	</form>
</body>
</html>