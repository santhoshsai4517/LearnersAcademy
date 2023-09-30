<%@page import="com.scode.admin.model.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if (request.getAttribute("subjects") == null) {
	response.sendRedirect("dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subjects</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!subjects.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Subject ID</th>
			<th>Subject Name</th>
		</tr>
		<%
		for (Subject subject : subjects) {
		%>
		<tr>
			<td><%=subject.getId()%></td>
			<td><%=subject.getName()%></td>
		</tr>
		<%
		}
		%>
	</table>

<%
}
}
%>
	<a href="dashboard.jsp">Go to dashboard</a>
</body>
</html>