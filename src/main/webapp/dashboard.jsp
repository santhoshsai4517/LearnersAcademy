<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//System.out.println(session.getAttribute("isValid"));
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome to dashboard</h1>
	<table>
		<tr>
			<td><a href="changepassword.jsp">Change password</a></td>
		</tr>
		<tr>
			<td><a href="addsubject.jsp">Add subject</a></td>
		</tr>
		<tr>
			<td><a href="addclass.jsp">Add class</a></td>
		</tr>
		<tr>
			<td><a href="addteacher.jsp">Add teacher</a></td>
		</tr>
		<tr>
			<td><a href="addstudent.jsp">Add student</a></td>
		</tr>
		<tr>
			<td><a href="assignsubject.jsp">Subject assignment</a></td>
		</tr>
		<tr>
			<td><a href="assignteacher.jsp">Teacher assignment</a></td>
		</tr>
		<tr>
			<td><a href="showsubjects">Show subjects</a></td>
		</tr>
		<tr>
			<td><a href="showteachers">Show teachers</a></td>
		</tr>
		<tr>
			<td><a href="showclasses">Show classes</a></td>
		</tr>
		<tr>
			<td><a href="showstudents">Show students</a></td>
		</tr>
		<tr>
			<td><a href="showteacherassignments">Show teacher
					assignments</a></td>
		</tr>
		<tr>
			<td><a href="showsubjectassignments">Show subject
					assignments</a></td>
		</tr>
		<tr>
			<td><a href="showclassinfo.jsp">Show class information </a></td>
		</tr>
		<tr>
			<td><a href="logout">Logout</a></td>
		</tr>
	</table>
</body>
</html>