
<%@page import="com.scode.admin.model.SubjectAssignment"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if (request.getAttribute("sas") == null) {
	response.sendRedirect("dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<SubjectAssignment> sas = (ArrayList<SubjectAssignment>) request.getAttribute("sas");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Class Subject assignments</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!sas.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Subject ID</th>
			<th>Class ID</th>
		</tr>
		<%
		for (SubjectAssignment sa : sas) {
		%>
		<tr>
			<td><%=sa.getSid()%></td>
			<td><%=sa.getCid()%></td>
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