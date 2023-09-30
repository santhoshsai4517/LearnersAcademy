
<%@page import="com.scode.admin.model.Student"%>
<%@page import="com.scode.admin.model.Subject"%>
<%@page import="com.scode.admin.model.Teacher"%>
<%@page import="com.scode.admin.model.Classes"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
else if (request.getAttribute("teachers") == null || request.getAttribute("isFound") == null || request.getAttribute("students") == null || request.getAttribute("subjects") == null) {
	response.sendRedirect("dashboard.jsp");
} else {
	boolean isFound = (Boolean) request.getAttribute("isFound");
	String tmessage = (String) request.getAttribute("tmessage");
	String smessage = (String) request.getAttribute("smessage");
	String stumessage = (String) request.getAttribute("stumessage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Class details</title>
</head>
<body>
	<%
	if (!isFound) {
	%>
	<h1>Class not found</h1>
	<%
	} else {
	%>
	<h1><%=tmessage%></h1>
	<%
	ArrayList<Teacher> teachers = (ArrayList<Teacher>) request.getAttribute("teachers");
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
	%>
	<h1><%=smessage%></h1>
	<%
	ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");
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
	%>
		<h1><%=stumessage%></h1>
	<%
	ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
	if (!students.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Student ID</th>
			<th>Student Name</th>
		</tr>
		<%
		for (Student stu : students) {
		%>
		<tr>
			<td><%=stu.getId()%></td>
			<td><%=stu.getName()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	}
	%>
<%
}
}
%>

	<a href="dashboard.jsp">Go to dashboard</a>
</body>
</html>