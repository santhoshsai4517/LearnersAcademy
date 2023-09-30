
<%@page import="com.scode.admin.model.TeacherAssignment"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if (request.getAttribute("tas") == null) {
	response.sendRedirect("dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<TeacherAssignment> ta = (ArrayList<TeacherAssignment>) request.getAttribute("tas");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher Class Subject assignments</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!ta.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Teacher ID</th>
			<th>Subject ID</th>
			<th>Class ID</th>
		</tr>
		<%
		for (TeacherAssignment t : ta) {
		%>
		<tr>
			<td><%=t.getTid()%></td>
			<td><%=t.getSid()%></td>
			<td><%=t.getCid()%></td>
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