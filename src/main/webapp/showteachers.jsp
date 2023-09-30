
<%@page import="com.scode.admin.model.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if (request.getAttribute("teachers") == null) {
	response.sendRedirect("dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<Teacher> teachers = (ArrayList<Teacher>) request.getAttribute("teachers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teachers</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!teachers.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Teacher ID</th>
			<th>Teacher Name</th>
		</tr>
		<%
		for (Teacher teacher : teachers) {
		%>
		<tr>
			<td><%=teacher.getId()%></td>
			<td><%=teacher.getName()%></td>
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