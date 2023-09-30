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
<title>Add Teacher</title>
<script>
	//    alert(new Date());
	function validateForm() {
		let tid = document.getElementById("tid").value;
		let isValid = true;

		if (tid.length > 6) {
			alert("Teacher ID length should be less than or equal to 6. Please enter valid id");
			isValid = false;
		}
		return isValid;
	}
</script>

</head>
<body>
	<h1>Enter teacher details to add</h1>
	<form action="addteacher" method="post" onsubmit="return validateForm()">
		<table>
			<tr>
				<td>New Teacher ID:</td>
				<td><input type="text" name="teacherid"
					placeholder="Teacher ID" id="tid" required /></td>
			</tr>
			<tr>
				<td>Teacher name:</td>
				<td><input type="text" name="teachername"
					placeholder="Teacher Name" id="tname" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Teacher" /></td>
			</tr>
		</table>
	</form>
</body>
</html>