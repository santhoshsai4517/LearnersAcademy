<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null)
	response.sendRedirect("index.jsp");
	//System.out.println(session.getAttribute("isValid"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invalid login page</title>
</head>
<body>
	<h1>Invalid login deatils</h1>
	<%
		session.removeAttribute("isValid");
	%>
	<a href="index.jsp">Go to login</a>
</body>
</html>