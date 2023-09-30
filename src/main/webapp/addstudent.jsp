<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.scode.util.ConnectionUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
<script>
	//    alert(new Date());
	function validateForm() {
		let stuid = document.getElementById("stuid").value;
		let stuclass = document.getElementById("classid").value;
		//alert(stuclass);
		let isValid = true;

		if (stuid.length > 10) {
			alert("Student ID length should be less than or equal to 10. Please enter valid id");
			isValid = false;
		}
		if(stuclass == "-1"){
			alert("Please select a class");
			isValid = false;
		}
		return isValid;
	}
</script>

</head>
<body>
	<h1>Enter student details to add</h1>
	<form action="addstudent" method="post"
		onsubmit="return validateForm()">
		<table>
			<tr>
				<td>New Student ID:</td>
				<td><input type="text" name="studentid"
					placeholder="Student ID" id="stuid" required /></td>
			</tr>
			<tr>
				<td>Student name:</td>
				<td><input type="text" name="studentname"
					placeholder="Student Name" id="stuname" required /></td>
			</tr>
			<tr>
				<td>Class ID:</td>
				<td><select name="classid" id="classid" required>
						<option value="-1">Select</option>
						<%
						Connection con = ConnectionUtil.getConnection();
						PreparedStatement stmt = con.prepareStatement("select * from classes");
						ArrayList<String> classes = new ArrayList<String>();
						ResultSet rs = stmt.executeQuery();
						while(rs.next()){
							%>
							<option value="<%= rs.getString(1)%>"><%= rs.getString(1) %></option>
							<%
						}
						con.close();
						stmt.close();
						rs.close();
						%>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Student" /></td>
			</tr>
		</table>
	</form>
</body>
</html>