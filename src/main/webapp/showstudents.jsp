<%@page import="com.scode.admin.model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if (request.getAttribute("students") == null) {
	response.sendRedirect("dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
	//System.out.println(message);
	//System.out.println(students);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!students.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Student ID</th>
			<th>Student Name</th>
		</tr>
		<%
		for (Student student : students) {
		%>
		<tr>
			<td><%=student.getId()%></td>
			<td><%=student.getName()%></td>
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