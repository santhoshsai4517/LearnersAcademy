<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String message = (String) request.getAttribute("message");
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if(message == null)
	response.sendRedirect("dashboard.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=message%></title>
</head>
<body>

	<h1><%=message%></h1>
	<a href="dashboard.jsp">Go to dashboard</a>
</body>
</html>