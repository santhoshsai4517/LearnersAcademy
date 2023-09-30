
<%@page import="com.scode.admin.model.Classes"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if (request.getAttribute("classes") == null) {
	response.sendRedirect("dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<Classes> allclasses = (ArrayList<Classes>) request.getAttribute("classes");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Classes</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!allclasses.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Class ID</th>
			<th>Class Name</th>
		</tr>
		<%
		for (Classes classes : allclasses) {
		%>
		<tr>
			<td><%=classes.getId()%></td>
			<td><%=classes.getName()%></td>
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