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
<title>Assign Subject to Class</title>
<script>
	//    alert(new Date());
	function validateForm() {
		let cid = document.getElementById("cid").value;
		let sid = document.getElementById("sid").value;
		let isValid = true;

		if (cid.length > 6) {
			alert("Class ID length should be less than or equal to 6. Please enter valid id");
			isValid = false;
		}
		if (sid.length > 6) {
			alert("Subject ID length should be less than or equal to 6. Please enter valid id");
			isValid = false;
		}
		return isValid;
	}
</script>

</head>
<body>
	<h1>Enter Subject ID and Class ID to assign</h1>
	<form action="assignsubject" method="post"
		onsubmit="return validateForm()">
		<table>
			<tr>
				<td>Class ID:</td>
				<td><input type="text" name="classid" placeholder="Class ID"
					id="cid" required /></td>
			</tr>
			<tr>
				<td>Subject ID:</td>
				<td><input type="text" name="subjectid" placeholder="Subject ID"
					id="sid" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Assign subject" /></td>
			</tr>
		</table>
	</form>
</body>
</html>